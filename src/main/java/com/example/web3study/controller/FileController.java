package com.example.web3study.controller;

import com.example.web3study.utils.IpfsUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



/**
 * @author sun
 */

@RestController
public class FileController {
    @PostMapping("/uploadNftImg")
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam("imgName") String name) throws Exception {
        System.out.println(file.getName());
        return  IpfsUtils.uploadIpfsPic(file.getBytes(), name);
    }
}
