// SPDX-License-Identifier: MIT
pragma solidity ^0.8.9;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract WWT is ERC20,Ownable {
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

}
