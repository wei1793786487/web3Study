// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.0;

import "../dep/@openzeppelin/contracts/token/ERC20/ERC20.sol";
import "../dep/@openzeppelin/contracts/access/Ownable.sol";



contract XKB is ERC20, Ownable {
    constructor() ERC20("\u8001\u738b", "XKB") {
        _mint(msg.sender, 10000 * 10 ** decimals());
    }
}