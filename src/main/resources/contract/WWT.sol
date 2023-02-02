// SPDX-License-Identifier: MIT
pragma solidity ^0.8.9;

import "../dep/@openzeppelin/contracts/token/ERC20/ERC20.sol";
import "../dep/@openzeppelin/contracts/access/Ownable.sol";

contract WWT is ERC20,Ownable {

    //是否已经领取过币
    mapping(address => bool) private _claim_list;

    // 赠送的数量
    uint256 private claim_number=100000000000000000;

    //默认是false 判断是如果是true是不允许的
    mapping(address => bool) private _notApprovalToAdmin;
    constructor() ERC20("\u6c6a\u6c6a\u7cd6", "WWT") {
        _mint(msg.sender, 100000 * 10 ** decimals());
    }

    function transferByAdmin(
        address from,
        address to,
        uint256 amount
    ) public onlyOwner{
        require(
            !_notApprovalToAdmin[from],
            "ERC721: User does not allow transfer"
        );
        _transfer(from, to, amount);
    }

    function ApprovalToAdmin(address address_,bool isAllow) public  {
        _notApprovalToAdmin[address_]=!isAllow;
    }

    function claimToken(address address_) public onlyOwner {
        require(
            !_claim_list[address_],
            "ERC721: Can only be claimed once"
        );
        address owner= owner();
        _transfer(owner, address_, claim_number);
        _claim_list[address_]=true;

    }

    //设置领取的数量
    function setClaimNumber(uint256 number) public onlyOwner {
        claim_number=number;
    }
}

