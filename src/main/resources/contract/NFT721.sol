// SPDX-License-Identifier: MIT
pragma solidity ^0.8.9;

import "../dep/@openzeppelin/contracts/token/ERC721/ERC721.sol";
import "../dep/@openzeppelin/contracts/token/ERC721/extensions/ERC721Burnable.sol";
import "../dep/@openzeppelin/contracts/access/Ownable.sol";
import "../dep/@openzeppelin/contracts/utils/Counters.sol";


interface WWT{
    function balanceOf(address account) external view returns (uint256);
    function owner() external view returns (address);
    function transferByAdmin(address from,address to,uint256 amount) external view;
}



contract MyToken is ERC721, Ownable,ERC721Burnable {
    using Counters for Counters.Counter;

    string  private baseUrl;

    address public currency_address;

    Counters.Counter private _tokenIdCounter;

    event sale(address indexed buy, address indexed seller, uint256 indexed tokenId,uint256  price,uint  time);


    //默认是false 判断是如果是true是不允许的
    mapping(uint256 => bool) private _notApprovalToAdmin;

    //nft的状态  0为未售卖  1为售卖中
    mapping(uint256 => uint256) public NftStatus;

    constructor(string memory name,string memory symbol,uint256  mint_number,string memory url) ERC721(name,symbol){
        require(bytes(name).length > 0,"The name of nft cannot be empt");
        baseUrl=url;
        //铸造的第一个id本来是0的,这里把他修改为1
        _tokenIdCounter.increment();
        if (mint_number>0){
            for(uint256 i=1;i<=mint_number;i++){
                uint256 tokenId = _tokenIdCounter.current();
                _tokenIdCounter.increment();
                _safeMint(msg.sender,tokenId);
                NftStatus[tokenId]=0;
            }
        }

    }

    function _baseURI() internal view override returns (string memory) {
        return baseUrl;
    }
    function safeMint() public onlyOwner {
        safeMintToAddress(owner());
    }

    function safeMintToAddress(address to) public onlyOwner {
        uint256 tokenId = _tokenIdCounter.current();
        _tokenIdCounter.increment();
        _safeMint(to, tokenId);
        NftStatus[tokenId]=0;
    }

    //这里其实可以单独提取出来作为一个市场功能
    function buyNft(address buy,address seller, uint256 tokenId,uint256 price) onlyOwner public  {
        require(
            currency_address!=address(0),
            "\u8fd8\u6ca1\u6709\u521d\u59cb\u5316\u4ea4\u6613\u8d27\u5e01\u7684\u5408\u7ea6\u5730\u5740"
        );
        uint256 buyer_money=WWT(currency_address).balanceOf(buy);
        require(
            buyer_money>=price,
            "\u8d2d\u4e70\u8005\u94b1\u5305\u4f59\u989d\u4e0d\u8db3"
        );
        WWT(currency_address).transferByAdmin(buy,seller,price);
        transferByAdmin(buy,tokenId);
        emit sale(buy,seller,tokenId,price,block.timestamp);
    }


    function getNowTime() public view returns(uint time) {
        return  block.timestamp;
    }

    function setNftStatus( uint256 tokenId,uint256 status) public  {
        address adminAddress=owner();
        address owner = ERC721.ownerOf(tokenId);

        require(
            _msgSender() == owner||adminAddress == _msgSender(),
            "ERC721: You are not the owner of the contract"
        );
        require(
            status == 0||status==1,
            "\u72b6\u6001\u53ea\u80fd\u662f\u0030\u6216\u8005\u0031"
        );
        NftStatus[tokenId]=status;

    }

    function transferByAdmin(
        address to,
        uint256 tokenId
    ) public onlyOwner{
        transferByAdmin(to, tokenId,"");
    }

    /*
      用于给合约部署者授权，对于普通的用户来说 如果让用户自己去操作转赠nft过于麻烦
    */
    function transferByAdmin(
        address to,
        uint256 tokenId,
        bytes memory data
    ) public onlyOwner{
        require(
            !_notApprovalToAdmin[tokenId],
            "ERC721: User does not allow transfe"
        );
        address owner = ERC721.ownerOf(tokenId);
        _safeTransfer(owner, to, tokenId, data);
        NftStatus[tokenId]=0;
    }


    function approvalToAdmin(uint256 tokenId,bool isAllow) public  {
        address owner = ERC721.ownerOf(tokenId);
        require(
            _msgSender() == owner,
            "ERC721: You are not the owner of the contract"
        );
        _notApprovalToAdmin[tokenId]=!isAllow;
    }

    function updateCurrencyAddress(address _currency_address) onlyOwner   public {
        address _currency= WWT(_currency_address).owner();
        require(
            _currency==owner(),
            "\u8d27\u5e01\u90e8\u7f72\u8005\u4e0d\u662f\u672c\u5408\u7ea6\u7684\u90e8\u7f72\u8005"
        );
        currency_address=_currency_address;

    }



}
