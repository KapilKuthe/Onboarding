package com.example.supperapp.controller;


import java.util.HashMap;


import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.supperapp.entities.MasterDdlRequest;
import com.example.supperapp.service.DocumentVerifyServiceImpl;



@RestController
public class DocumentVerifyController {
	
	@Autowired
	private DocumentVerifyServiceImpl serviceImpl;
	
	@PostMapping("/insertDocumentVerify")
	 public HashMap<String, Object> insertDocumentVerify(@RequestBody HashMap<String, Object> insertDocumentVal) throws ParseException {
		return serviceImpl.insertDocumentVerify(insertDocumentVal);
	 }
	
	@PostMapping("/sendSmsEmail")
	 public HashMap<String, Object> sendSmsEmail(@RequestBody HashMap<String, Object> otpdetails) throws ParseException {
		return serviceImpl.sendSmsEmail(otpdetails);
	 }
	
	@PostMapping("/insertExchangeData")
	 public HashMap<String, Object> insertExchangeData(@RequestBody HashMap<String, Object> exchangedetails) throws ParseException {	    	
	    	return serviceImpl.insertExchangeData(exchangedetails);
	 }
	
	@PostMapping("/masterDetail")
	public HashMap<String, Object> masterDetail(@RequestBody HashMap<String, Object> masterDdlRequest ){
		return serviceImpl.masterDetail(masterDdlRequest);
		
	}
	
	@PostMapping("/selfie")
	 public HashMap<String, Object> selfie(@RequestBody HashMap<String, Object> selfie) throws ParseException {	    	
	    	return serviceImpl.selfie(selfie);
	 }
	
	@PostMapping("/insertBMIData")
	 public HashMap<String, Object> insertBMIData(@RequestBody HashMap<String, Object> insertBMIData) throws ParseException {	    	
	    	return serviceImpl.insertBMIData(insertBMIData);
	 }
	
}