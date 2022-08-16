package com.example.supperapp.service;

import java.util.HashMap;



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
	
	public HashMap<String, Object> insertBMIData(HashMap<String, Object> map);
}
