package com.deitui.morelang.index.index;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.model.AppConfig;
import com.model.Help;
import com.model.Oos;

import net.coobird.thumbnailator.Thumbnails;

@RestController
@CrossOrigin("*")
public class UploadController {
	@RequestMapping("/upload/img")
	public String img(
			@RequestParam("upimg") MultipartFile file,
			@RequestParam(value="thumb",defaultValue="") String thumb
	) {
		if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        File rootPath;
        String filePath="attach/images/";
        try {
        	rootPath = new File(ResourceUtils.getURL("classpath:").getPath()+"/static");
            if(!rootPath.exists()) rootPath = new File("");
            
    		File upload = new File(rootPath.getAbsolutePath(),filePath);
    		if(!upload.exists()) upload.mkdirs();
        }catch(Exception e) {
        	 return Help.error(1, "上传失败1");
        }
        long time= new Date().getTime();
        //获取后缀
        String ftype=getFileExtension(fileName);
        String[] allowTypes= new String[] {"jpg","png","webp","bmp","jpeg"};
        if(!Help.in_array(ftype, allowTypes)) {
        	return Help.error(1, "文件类型不对");
        }
        String imgurl=filePath + time+DigestUtils.md5DigestAsHex(fileName.getBytes())+"."+ftype;
        File rootImgurl = new File(rootPath+"/"+imgurl);
        try {
            file.transferTo(rootImgurl);
            Oos.upload(rootPath+"/"+imgurl); 
            //处理缩略图
            if(thumb.equals("") || thumb.equals("100")) {
            	Thumbnails.of(rootImgurl)
            		.size(160, 160)
            		.toFile(rootImgurl+".100x100.jpg");
            	Oos.upload(rootPath+"/"+imgurl+".100x100.jpg"); 
            }
            if(thumb.equals("")) {
            	Thumbnails.of(rootImgurl)
	        		.size(480, 480)
	        		.toFile(rootImgurl+".small.jpg");
            	Oos.upload(rootPath+"/"+imgurl+".small.jpg"); 
            	Thumbnails.of(rootImgurl)
	        		.size(750, 750)
	        		.toFile(rootImgurl+".middle.jpg");
            	Oos.upload(rootPath+"/"+imgurl+".middle.jpg");
            }
            
            
	        
            JSONObject redata= JSONObject.parseObject( Help.success(0, "上传成功"));
            redata.put("imgurl", imgurl);
            redata.put("trueimgurl",AppConfig.IMAGES_SITE+imgurl);
            return redata.toJSONString();
             
        } catch (IOException e) {
        	 return Help.error(1, "上传失败2");
        }

		
	}
	
	private static String getFileExtension(String fileName) {
		if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(fileName.lastIndexOf(".")+1);
		}else { 
			return "";
		}
	}
	
}
