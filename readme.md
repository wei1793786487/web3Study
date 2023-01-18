https://github.com/wei1793786487/blockscout


/web3/go-ethereum/build/bin/geth --datadir /web3/devdata --networkid 18 --port 30303 --http --http.addr 0.0.0.0 --http.vhosts=* --http.port 8545 --http.api 'db,net,eth,web3,personal' --http.corsdomain=* --dev --dev.period 1 console 2> 1.log



nohup   /web3/go-ethereum/build/bin/geth --datadir /web3/devdata --networkid 18 --port 30303 --http --http.addr 0.0.0.0 --http.vhosts=* --http.port 8545 --http.api 'db,net,eth,web3,personal'   --http.corsdomain=* --dev > 1.log   & 



后台默认不挖矿：nohup   /web3/go-ethereum/build/bin/geth --datadir /web3/devdata --networkid 18 --port 30303 --http --http.addr 0.0.0.0 --http.vhosts=* --http.port 8545 --http.api 'db,net,eth,web3,personal'   --http.corsdomain=* --dev  > 1.log   & 