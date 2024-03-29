package com.maker.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageDTO {

    private Long imgNum;

    private String uuid;

    private String imgName;

    private String path;

    public String getImageURL(){
        try{
            return URLEncoder.encode(path+"/"+uuid+"_"+imgName, "UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return  "";
    }

    public String getThumbnailURL(){
        try{
            return URLEncoder.encode(path+"/"+uuid+"_"+0+"_"+imgName, "UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return  "";
    }

    public String getProductImgURL(int count){
        try{
            return URLEncoder.encode(path+"/"+uuid+"_"+count+"_"+imgName, "UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return  "";
    }
}
