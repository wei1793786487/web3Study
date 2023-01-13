package com.example.web3study.pojo;


import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Web3TransactionError {

    public Web3TransactionError(String transactionHash, String error, Double programCounter, String returnData, String reason) {
        this.transactionHash = transactionHash;
        this.error = error;
        this.programCounter = programCounter;
        this.returnData = returnData;
        this.reason = reason;
    }

    private  String  transactionHash;
    private  String error;

    @SerializedName("program_counter")
    private Double programCounter;

    @SerializedName("return")
    private  String returnData;

    private String reason;
}
