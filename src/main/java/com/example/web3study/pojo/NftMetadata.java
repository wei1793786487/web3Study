/**
 * Copyright 2023 bejson.com
 */
package com.example.web3study.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class NftMetadata {

    /*
     项目的id
   */
    private String tokenId;
    /*
    这是项目图像的 URL。可以是几乎任何类型的图片 可以是IPFS URLs或路径
    */
    private String image;

    /*项目的名称。*/
    private String name;

    /*
    项目的创建时间
    */
    private String createTime;

    /*
      铸造nft的链
   */
    private String chainName;

    /*
    铸造者的地址
  */
    private String mintAddress;


    /*项目的可读描述*/
    private String description;

    /*
     铸造时间
     */
    private Date mintTime;

    /*
    一个指向项目的多媒体附件的URL。支持GLTF、GLB、WEBM、MP4、M4V、OGV和OGG等文件扩展名，以及纯音频扩展名MP3、WAV和OGA。
    Animation_url也支持HTML页面，允许你使用JavaScript画布、WebGL等建立丰富的体验和互动的NFTs。
    现在支持HTML页面内的脚本和相对路径。然而，不支持对浏览器扩展的访问。
    */
    private String animationUrl;

    /*这些是项目的属性，它们将显示在项目的OpenSea页面上。(见下文)*/
    private List<String> attributes;

}