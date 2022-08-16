package com.example.supperapp.service;

import java.util.HashMap;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supperapp.dao.DaoLayer;

import com.example.supperapp.entities.MasterDdlRequest;



@Service
public class DocumentVerifyServiceImpl implements DocumentVerifyService {
	
	@Autowired
	private DaoLayer daoLayer;

	public HashMap<String, Object> insertDocumentVerify(HashMap<String, Object> map) 
	{
		HashMap<String, Object> responseData = new HashMap<>();
		HashMap<String, Object> getData = daoLayer.insertDocumentVerify(map);

		if (getData.get("flag").equals("Yes")) {
			responseData.put("Status", "1");
			responseData.put("ErrorCode", "0");
			responseData.put("Message", "Success");
			responseData.put("flag", getData.get("flag"));
		} else {
			responseData.put("Status", "0");
			responseData.put("ErrorCode", "1");
			responseData.put("Message", "Failure");
			responseData.put("flag", getData.get("flag"));
		}

		return responseData;
//		return this.daoLayer.insertDocumentVerify(map);
	}

	
	public HashMap<String, Object> sendSmsEmail(HashMap<String, Object> map) {
		HashMap<String, Object> responseData = new HashMap<>();
		HashMap<String, Object> getData = daoLayer.sendSmsEmail(map);

		if (getData.get("flag").equals("Yes")) {
			responseData.put("Status", "1");
			responseData.put("ErrorCode", "0");
			responseData.put("Message", "Success");
			responseData.put("flag", getData.get("flag"));
		} else {
			responseData.put("Status", "0");
			responseData.put("ErrorCode", "1");
			responseData.put("Message", "Failure");
			responseData.put("flag", getData.get("flag"));
		}

		return responseData;
//		return  this.daoLayer.sendSmsEmail(map);
	}
	
	
	public HashMap<String, Object> insertExchangeData(HashMap<String, Object> map) {
		 
		HashMap<String, Object> responseData = new HashMap<>();
		HashMap<String, Object> getData = daoLayer.insertExchangeData(map);

		if (getData.get("flag").equals("Yes")) {
			responseData.put("Status", "1");
			responseData.put("ErrorCode", "0");
			responseData.put("Message", "Success");
			responseData.put("flag", getData.get("flag"));
		} else {
			responseData.put("Status", "0");
			responseData.put("ErrorCode", "1");
			responseData.put("Message", "Failure");
			responseData.put("flag", getData.get("flag"));
		}

		return responseData;
//		return  this.daoLayer.insertExchangeData(map);
	}
	
	public HashMap<String, Object> masterDetail(HashMap<String, Object> masterDdlRequest ){
		HashMap<String, Object> map =daoLayer.masterDetail(masterDdlRequest); 
            return map;
	}
	
	public HashMap<String, Object> selfie(HashMap<String, Object> map) {
		HashMap<String, Object> responseData = new HashMap<>();
		HashMap<String, Object> getData = daoLayer.selfie(map);

		if (getData.get("flag").equals("Yes")) {
			responseData.put("Status", "1");
			responseData.put("ErrorCode", "0");
			responseData.put("Message", "Success");
			responseData.put("flag", getData.get("flag"));
		} else {
			responseData.put("Status", "0");
			responseData.put("ErrorCode", "1");
			responseData.put("Message", "Failure");
			responseData.put("flag", getData.get("flag"));
		}

		return responseData;
//		return  this.daoLayer.sendSmsEmail(map);
	}


	@Override
	public HashMap<String, Object> insertBMIData(HashMap<String, Object> map) {
		HashMap<String, Object> responseData = new HashMap<>();
		HashMap<String, Object> getData = daoLayer.insertBMIData(map);

		if (getData.get("flag").equals("Yes")) {
			responseData.put("Status", "1");
			responseData.put("ErrorCode", "0");
			responseData.put("Message", "Success");
			responseData.put("flag", getData.get("flag"));
		} else {
			responseData.put("Status", "0");
			responseData.put("ErrorCode", "1");
			responseData.put("Message", "Failure");
			responseData.put("flag", getData.get("flag"));
		}

		return responseData;
	}
}
