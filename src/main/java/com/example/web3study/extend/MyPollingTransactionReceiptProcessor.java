
package com.example.web3study.extend;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.response.TransactionReceiptProcessor;

import java.io.IOException;

public class MyPollingTransactionReceiptProcessor extends TransactionReceiptProcessor {
    final static int  RETRYCOUNT=5;
    private static final Logger logger = LoggerFactory.getLogger(MyPollingTransactionReceiptProcessor.class);

    private Web3j web3j;

    public MyPollingTransactionReceiptProcessor(Web3j web3j) {
        super(web3j);
        this.web3j=web3j;
    }

    @Override
    public TransactionReceipt waitForTransactionReceipt(String transactionHash) throws IOException, TransactionException {
        for (int i = 0; i < RETRYCOUNT; i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            EthGetTransactionReceipt transactionReceipt =
                    web3j.ethGetTransactionReceipt(transactionHash).send();

            if (transactionReceipt.hasError()) {
                if ("Proxy Error".equals(transactionReceipt.getError().getMessage())||transactionReceipt.getError().getCode()==403){
                    logger.info("等待武汉链交易完成");
                }else {
                    throw new TransactionException(
                            "Error processing request: " + transactionReceipt.getError().getMessage());
                }
            }else {
                return transactionReceipt.getTransactionReceipt().get();
            }
        }
        throw new TransactionException(
                transactionHash+":交易超时");
    }
}
