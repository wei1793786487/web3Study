package com.example.web3study.utils;

import cn.hutool.http.HttpRequest;
import com.example.web3study.exception.XxException;
import com.example.web3study.pojo.ReturnCode;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class IpfsUtils {
    private static String PinataApiKey;

    private static String PinataSecretApiKey;

    private static String IpfsNode;



    @Value("${ipfs.pinata.pinata_api_key}")
    public void setPinataApiKey(String pinataApiKey) {
        PinataApiKey = pinataApiKey;
    }

    @Value("${ipfs.pinata.pinata_secret_api_key}")
    public  void setPinataSecretApiKey(String pinataSecretApiKey) {
        PinataSecretApiKey = pinataSecretApiKey;
    }

    @Value("${ipfs.node_url}")
    public static void setIpfsNode(String ipfsNode) {
        IpfsNode = ipfsNode;
    }

    public static String uploadIpfsPic(byte[] byteFile, String fileName) {
        try {
            String result2 = HttpRequest.post("https://api.pinata.cloud/pinning/pinFileToIPFS")
                    .header("pinata_api_key", PinataApiKey)
                    .header("pinata_secret_api_key",PinataSecretApiKey)
                    .form("file",byteFile,fileName)
                    .timeout(20000)//超时，毫秒
                    .execute().body();
            Gson gson = new Gson();
            Map map = gson.fromJson(result2, Map.class);
            Object ipfsHash = map.get("IpfsHash");
            if (ipfsHash==null){
                log.error("ipfs上传失败"+result2);
                throw new XxException(ReturnCode.IPFS_UPLOAD_ERROR);
            }else{
                return (String) ipfsHash;
            }
        } catch (RuntimeException e) {
            log.error("ipfs上传失败"+e);
            throw new XxException(ReturnCode.IPFS_UPLOAD_ERROR);
        }
    }
}
