package com.example.web3study.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
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

    public static String uploadIPFSPic() {
        try {
            String result2 = HttpRequest.post("https://api.pinata.cloud/pinning/pinFileToIPFS")
                    .header("pinata_api_key", PinataApiKey)
                    .header("pinata_secret_api_key",PinataSecretApiKey)
                    .form("file", FileUtil.file("D:\\fly.png"))
                    .timeout(20000)//超时，毫秒
                    .execute().body();
            Gson gson = new Gson();
            Map map = gson.fromJson(result2, Map.class);
            Object ipfsHash = map.get("IpfsHash");
            if (ipfsHash==null){
                throw new RuntimeException("ipfs上传失败"+result2);
            }else{
                return (String) ipfsHash;
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e+"ipfs上传异常");
        }
    }
}
