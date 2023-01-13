// SPDX-License-Identifier: MIT
pragma solidity ^0.8.9;

import "../dep/@openzeppelin/contracts/token/ERC721/ERC721.sol";
import "../dep/@openzeppelin/contracts/token/ERC721/extensions/ERC721Burnable.sol";
import "../dep/@openzeppelin/contracts/access/Ownable.sol";
import "../dep/@openzeppelin/contracts/utils/Counters.sol";


contract NFT721 is ERC721, Ownable, ERC721Burnable {

    using Counters for Counters.Counter;

    string  private baseUrl;
    Counters.Counter private _tokenIdCounter;


    //默认是false 判断是如果是true是不允许的
    mapping(uint256 => bool) private _notApprovalToAdmin;

    constructor(string memory name, string memory symbol, uint256 mint_number, string memory url) ERC721(name, symbol){
        require(bytes(name).length > 0, "The name of nft cannot be empty");
        baseUrl = url;
        //铸造的第一个id本来是0的,这里把他修改为1
        _tokenIdCounter.increment();
        if (mint_number > 0) {
            for (uint256 i = 1; i <= mint_number; i++) {
                uint256 tokenId = _tokenIdCounter.current();
                _tokenIdCounter.increment();
                _safeMint(msg.sender, tokenId);
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
    }


    function transferByAdmin(
        address to,
        uint256 tokenId
    ) public onlyOwner {
        transferByAdmin(to, tokenId, "");
    }

    /*
      用于给合约部署者授权，对于普通的用户来说 如果让用户自己去操作转赠nft过于麻烦
    */
    function transferByAdmin(
        address to,
        uint256 tokenId,
        bytes memory data
    ) public onlyOwner {
        require(
            !_notApprovalToAdmin[tokenId],
            "ERC721: User does not allow transfe"
        );
        address owner = ERC721.ownerOf(tokenId);
        _safeTransfer(owner, to, tokenId, data);
    }


    function notApprovalToAdmin(uint256 tokenId) public {

        address owner = ERC721.ownerOf(tokenId);
        require(
            _msgSender() == owner,
            "ERC721: You are not the owner of the contract"
        );
        _notApprovalToAdmin[tokenId] = true;
    }

    function ApprovalToAdmin(uint256 tokenId) public {
        address owner = ERC721.ownerOf(tokenId);
        require(
            _msgSender() == owner,
            "ERC721: You are not the owner of the contract"
        );
        _notApprovalToAdmin[tokenId] = false;
    }


}
