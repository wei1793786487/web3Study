第一种是  **[blockscout](https://github.com/wei1793786487/blockscout)**+**[go-ethereum](https://github.com/ethereum/go-ethereum)**

geth的优点在于是一个以太坊的节点环境 能够直接与以太坊进行连接 缺点是日志太多了 太消耗内存了

geth会自动的出快 不知道怎么回事 运行这个直接吧我电脑内存跑满了



脚本

*   需要进入控制台模式给账号分配金额`/web3/go-ethereum/build/bin/geth --datadir /web3/devdata --networkid 18 --port 30303 --http --http.addr 0.0.0.0 --http.vhosts=* --http.port 8545 --http.api 'db,net,eth,web3,personal' --http.corsdomain=* --dev --dev.period 1 console 2> 1.log`   

* ```
  后台默认不挖矿：nohup   /web3/go-ethereum/build/bin/geth --datadir /web3/devdata --networkid 18 --port 30303 --http --http.addr 0.0.0.0 --http.vhosts=* --http.port 8545 --http.api 'db,net,eth,web3,personal'   --http.corsdomain=* --dev  > 1.log   &       
  加上 --dev.period 1 为挖矿 
  ```
  



第二种是  **[blockscout](https://github.com/wei1793786487/blockscout)**+**[ganache-cli](https://www.npmjs.com/package/ganache-cli)**

ganache这个安装很方便 只要使用npm就行 缺点是只是一个测试环境  需要额外指定-db 来保证数据不刷新

* ``` nohup node /www/server/nodejs/v14.17.6/lib/node_modules/ganache-cli/cli.js  -h=0.0.0.0  -i=996 -g=6700000 -l=99999999 -db=./ -a=1 & ```

*``` nohup ganache -h=0.0.0.0   --database.dbPath=/web3/genacheCha/  --wallet.accounts="0x3cf44c4d4252d9d9277f5503dac4e365f56c69cff5d1396da4f156dc115ddcac, 1000000000000000000000" -g=6700000 -l=6721975   --miner.defaultTransactionGasLimit=268435455   &  小知识乌邦图似乎要后台运行后使用 exit 直接退出会失活``


docker-compose -f docker-compose-no-build-ganache.yml up