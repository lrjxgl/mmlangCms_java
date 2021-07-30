package com.model;

import java.io.File;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Oos {
 
	public static String url="http://oos.mmlang.com/upload.php";
	public static Boolean upload(String filename)  {
		MediaType MEDIA_TYPE_TEXT = MediaType.parse("image/png");
	 
	    RequestBody fileBody = RequestBody.create( new File(filename),MEDIA_TYPE_TEXT);        
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file",filename, fileBody)
                .addFormDataPart("filename",filename)
                .build();


		Request request = new Request.Builder().url(url).post(requestBody).build();
		OkHttpClient client = new OkHttpClient();
		try {
			Response response = client.newCall(request).execute();
			System.out.print(response);
			return true;
		}catch(Exception e) {
			System.out.print(e);
			return false;
		}
		
	}
	
}
