package com.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 1403218278293175897L;
	
	public static final String SUCCESS_CODE = "000";
	
	private String status=SUCCESS_CODE ;
	
	private String message;
	
	private List<Object> data;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Object> getData() {
		if(data == null) {
			data = new ArrayList<Object>();
		}
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}

