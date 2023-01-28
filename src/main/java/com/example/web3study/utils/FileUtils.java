package com.example.web3study.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class FileUtils {
    public static String getAbsolutePath() throws FileNotFoundException {

        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) {
            path = new File("");
        }
        return path.getAbsolutePath();



    }
}
