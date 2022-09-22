package com.example.supperapp.service;

import java.util.HashMap;

import org.apache.tomcat.util.json.ParseException;

import com.example.supperapp.entities.MasterDdlRequest;

public interface DocumentVerifyService {

	public HashMap<String, Object> insertDocumentVerify(HashMap<String, Object> map) ;
//	
	public HashMap<String, Object> sendSmsEmail(HashMap<String, Object> map);
//	
	public HashMap<String, Object> insertExchangeData(HashMap<String, Object> map);
//	
	public HashMap<String, Object> masterDetail(HashMap<String, Object> masterDdlRequest );
	
	public HashMap<String, Object> selfie(HashMap<String, Object> map);
	
	public HashMap<String, Object> addBMIData(HashMap<String, Object> map);
	
	public HashMap<String, Object> hamburgerMenu(HashMap<String, Object> productId) throws ParseException;
}
