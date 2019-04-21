package com.lynu.yzshopping.manager;

import com.lynu.yzshopping.mybatis.entity.Result;
import com.lynu.yzshopping.util.FastDFSClient;
import com.lynu.yzshopping.util.ResultHandle;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@Api(value = "图片上传", tags = "图片上传")
@RestController
@RequestMapping("upload")
public class UploadController {
	

	private String FILE_SERVER_URL = "http://47.103.7.97/";//文件服务器地址


    @ApiOperation(value = "上传图片", notes = "上传图片")

	@PostMapping(value = "upload",consumes = "multipart/*",headers = "content-type=multipart/form-data")
	public Result upload(@ApiParam(value="上传的图片",required = true) MultipartFile file) {
		//1、取文件的扩展名
		String originalFilename = file.getOriginalFilename();
		String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

		try {
			FastDFSClient fastDFSClient
			= new FastDFSClient("classpath:logbackConfig/fdfs_client.conf");
			
			//执行上传
			String path = fastDFSClient.uploadFile(file.getBytes(), extName);
			//4、拼接返回的 url 和 ip 地址，拼装成完整的 url
			String url = FILE_SERVER_URL + path;			
//			return new Result(true,url);
            return ResultHandle.getSuccessResult(url);

        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			return new Result(false, "上传失败");
            return ResultHandle.getFailResult("上传失败");
		}

	}
}
