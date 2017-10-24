package com.controller.impl;


import java.io.File;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.header.RequestVtmInformation;
import com.header.VtmInfromation;
import com.model.BaseResponse;


@RestController
@RequestMapping("/testHttp")
public class TestController {
	
	@RequestMapping("/getStr")
	public String findAll(@RequestBody String request) {
		String sss="hello "+request;
		System.out.println("http qingqiu: "+sss);
		return sss;
	}
	@RequestMapping("/getFile")
	public File getFile() {
		File file=new File("D:/1.jpg");
		return file;
	}
	@RequestMapping("/getObject")
	public BaseResponse getObject(){
		System.out.println("调用了。。。。。。");
		 BaseResponse baseResponse=new BaseResponse();
		 baseResponse.getData().add("abc");
		 return baseResponse;
	}
	
	@RequestMapping("/testHeader")
	public BaseResponse testHeader(@VtmInfromation RequestVtmInformation requestVtmInformation,@RequestBody String request){
		System.out.println("调用了。。。。。。");
		System.out.println(request);
		System.out.println(requestVtmInformation.getMachineId());
		
		
		 return new BaseResponse();
	}
}
