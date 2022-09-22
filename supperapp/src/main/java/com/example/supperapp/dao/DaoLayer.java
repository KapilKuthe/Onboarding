package com.example.supperapp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.supperapp.entities.CardList;
import com.example.supperapp.entities.MasterDDL;

import com.example.supperapp.entities.SubTypeName;
import com.example.supperapp.service.Jsondata;

@Repository
public class DaoLayer {

	@Autowired
	private EntityManager emanager;

	public HashMap<String, Object> insertDocumentVerify(HashMap<String, Object> map) {
		HashMap<String, Object> responseData = new HashMap<String, Object>();
		try {
			System.out.println("This is Doa before exe insertDocumentValidate" + map);
			StoredProcedureQuery query = emanager.createStoredProcedureQuery("InsertDocumentValidateStatus")
					.registerStoredProcedureParameter("CustGuId", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("OfferingGuId", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("DocumentGuId", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("DocNumber", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("AccountNumber", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("IFSCCode", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("BankName", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Branch", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Address", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("City", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("State", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("DateOfBirth", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Citizenship", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("AadharStatus", Boolean.class, ParameterMode.IN)
					.registerStoredProcedureParameter("CustomerPanName", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("FirstName", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("MiddleName", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("LastName", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("MicrCode", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("AccountType", String.class, ParameterMode.IN)

					.setParameter("CustGuId", (String) map.get("CustGuId"))
					.setParameter("OfferingGuId", (String) map.get("OfferingGuId"))
					.setParameter("DocumentGuId", (String) map.get("DocumentGuId"))
					.setParameter("DocNumber", (String) map.get("DocNumber"))
					.setParameter("AccountNumber", (String) map.get("CusAccountNumbertGuId"))
					.setParameter("IFSCCode", (String) map.get("IFSCCode"))
					.setParameter("BankName", (String) map.get("BankName"))
					.setParameter("Branch", (String) map.get("Branch"))
					.setParameter("Address", (String) map.get("Address")).setParameter("City", (String) map.get("City"))
					.setParameter("State", (String) map.get("State"))
					.setParameter("DateOfBirth", (String) map.get("DateOfBirth"))
					.setParameter("Citizenship", (String) map.get("Citizenship"))
					.setParameter("AadharStatus", (Boolean) map.get("AadharStatus"))
					.setParameter("CustomerPanName", (String) map.get("CustomerPanName"))
					.setParameter("FirstName", (String) map.get("FirstName"))
					.setParameter("MiddleName", (String) map.get("MiddleName"))
					.setParameter("LastName", (String) map.get("LastName"))
					.setParameter("MicrCode", (String) map.get("MicrCode"))
					.setParameter("AccountType", (String) map.get("AccountType"));

			boolean b = query.execute();
			if (!b) {
				responseData.put("flag", "Yes");
			} else {
				responseData.put("flag", "No");
			}

		} catch (Exception e) {
			e.printStackTrace();
			responseData.put("flag", null);
			responseData.put("Error", e.toString());
			responseData.put("Status", 0);
		}
		System.out.println(responseData);
		return responseData;
	}

	public HashMap<String, Object> sendSmsEmail(HashMap<String, Object> map) {
		HashMap<String, Object> responseData = new HashMap<String, Object>();
		try {
			StoredProcedureQuery query = emanager.createStoredProcedureQuery("InsertSmsEmailOtp")
					.registerStoredProcedureParameter("Email", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Mobile", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("OTP", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Type", String.class, ParameterMode.IN)

					.setParameter("Email", (String) map.get("Email")).setParameter("Mobile", (String) map.get("Mobile"))
					.setParameter("OTP", (String) map.get("OTP")).setParameter("Type", (String) map.get("Type"));

			boolean b = query.execute();
			if (!b) {
				responseData.put("flag", "Yes");
			} else {
				responseData.put("flag", "No");
			}

		} catch (Exception e) {
			e.printStackTrace();
			responseData.put("flag", null);
			responseData.put("Error", e.toString());
			responseData.put("Status", 0);
		}
		System.out.println(responseData);
		return responseData;

	}

	public HashMap<String, Object> insertExchangeData(HashMap<String, Object> map) {
		HashMap<String, Object> responseData = new HashMap<String, Object>();
		try {
			StoredProcedureQuery query = emanager.createStoredProcedureQuery("InsertExchangeSelectionDetail")
					.registerStoredProcedureParameter("CustGuId", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("MFGuid", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("EquityGuid", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("DerivativeGuid", String.class, ParameterMode.IN)

					.setParameter("CustGuId", (String) map.get("CustGuId"))
					.setParameter("MFGuid", (String) map.get("MFGuid"))
					.setParameter("EquityGuid", (String) map.get("EquityGuid"))
					.setParameter("DerivativeGuid", (String) map.get("DerivativeGuid"));

			boolean b = query.execute();
			if (!b) {
				responseData.put("flag", "Yes");
			} else {
				responseData.put("flag", "No");
			}

		} catch (Exception e) {
			e.printStackTrace();
			responseData.put("flag", null);
			responseData.put("Error", e.toString());
			responseData.put("Status", 0);
		}
		System.out.println(responseData);
		return responseData;

	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Object> masterDetail(HashMap<String, Object> masterDdlRequest) {
		List<MasterDDL> list = new ArrayList<>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			StoredProcedureQuery query = emanager.createStoredProcedureQuery("GetMasterForDDL", "MasterDDL")
					.registerStoredProcedureParameter("Type", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("SelectedGuId", String.class, ParameterMode.IN)
					.setParameter("Type", masterDdlRequest.get("Type"))
					.setParameter("SelectedGuId", masterDdlRequest.get("SelectedGuId"));
			list = query.getResultList();
			
			map.put("Data", list);
			System.out.println(map);
			return map;

		} catch (Exception e) {
			e.printStackTrace();
			map.put("Data", null);
			map.put("Error", e.toString());
			map.put("Status", 0);
			return map;
		}

	}

	public HashMap<String, Object> selfie(HashMap<String, Object> map) {
		HashMap<String, Object> responseData = new HashMap<String, Object>();
		try {
			StoredProcedureQuery query = emanager.createStoredProcedureQuery("InsertExchangeSelectionDetail")
					.registerStoredProcedureParameter("CustGuId", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("SelfiImage", Byte[].class, ParameterMode.IN)
					.registerStoredProcedureParameter("IsMatch", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("MatchScore", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("ImageFaceDetected", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("DocumentFaceDetected", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("FaceLiveness", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("selfipath", String.class, ParameterMode.IN)

					.setParameter("CustGuId", map.get("CustGuId")).setParameter("SelfiImage", map.get("SelfiImage"))
					.setParameter("IsMatch", map.get("IsMatch")).setParameter("MatchScore", map.get("MatchScore"))
					.setParameter("ImageFaceDetected", map.get("ImageFaceDetected"))
					.setParameter("DocumentFaceDetected", map.get("DocumentFaceDetected"))
					.setParameter("FaceLiveness", map.get("FaceLiveness"))
					.setParameter("selfipath", map.get("selfipath"));

			boolean b = query.execute();
			if (!b) {
				responseData.put("flag", "Yes");
			} else {
				responseData.put("flag", "No");
			}

		} catch (Exception e) {
			e.printStackTrace();
			responseData.put("flag", null);
			responseData.put("Error", e.toString());
			responseData.put("Status", 0);
		}
		System.out.println(responseData);
		return responseData;

	}

	// BMI insert
	public HashMap<String, Object> addBMIData(HashMap<String, Object> map) {
		HashMap<String, Object> responseData = new HashMap<String, Object>();
		try {
			StoredProcedureQuery query = emanager.createStoredProcedureQuery("InsertBmiData")
					.registerStoredProcedureParameter("CustGuId", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Gender", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Height", Double.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Weight", Double.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Age", Integer.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Bmi_Report", Double.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Bmi_Category", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("Health_Score", Double.class, ParameterMode.IN)
					.registerStoredProcedureParameter("flag", String.class, ParameterMode.IN)

					.setParameter("CustGuId", map.get("CustGuId")).setParameter("Gender", map.get("Gender"))
					.setParameter("Height", Double.parseDouble(map.get("Height").toString()))
					.setParameter("Weight", Double.parseDouble(map.get("Weight").toString()))
					.setParameter("Age", Integer.parseInt(map.get("Age").toString()))

					.setParameter("Bmi_Report", Double.parseDouble(map.get("Bmi_Report").toString()))
					.setParameter("Bmi_Category", map.get("Bmi_Category"))
					.setParameter("Health_Score", Double.parseDouble(map.get("Health_Score").toString()))
					.setParameter("flag", "Insert");

			boolean b = query.execute();
			if (!b) {
				responseData.put("flag", "Yes");
			} else {
				responseData.put("flag", "No");
			}

		} catch (Exception e) {
			e.printStackTrace();
			responseData.put("flag", null);
			responseData.put("Error", e.toString());
			responseData.put("Status", 0);
		}
//		System.out.println(responseData);
		return responseData;

	}

	public HashMap<String, Object> hamburgerMenu(HashMap<String, Object> productId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			StoredProcedureQuery query = emanager.createStoredProcedureQuery("USP_ENHamburgerMainMenu");

			map.put("Data", query.getSingleResult());
			if (map.get("Data") != null) {
				map.put("flag", "Yes");
			} else {
				map.put("flag", "No");
			}

			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("Data", null);
			map.put("Error", e.toString());
			map.put("Status", 0);
			return map;
		}
	}
	
	public HashMap<String, Object> globalFilter(HashMap<String, Object> gblFilter) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			StoredProcedureQuery query = emanager.createStoredProcedureQuery("GetMasterForDD")
					.registerStoredProcedureParameter("SelectedGuId", String.class, ParameterMode.IN)
					.setParameter("SelectedGuId", gblFilter.get("SelectedGuId"));
			
			map.put("data", query.getFirstResult());
			System.out.println(map);
			return map;

		} catch (Exception e) {
			e.printStackTrace();
			map.put("Data", null);
			map.put("Error", e.toString());
			map.put("Status", 0);
			return map;
		}

	
	}

	
	public HashMap<String, Object> getDashboard(HashMap<String, Object> gettc) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		try {
			StoredProcedureQuery query = emanager.createStoredProcedureQuery("USP_ENGetCoinAmount")
					.registerStoredProcedureParameter("TokenID", String.class, ParameterMode.IN)
					.setParameter("TokenID", gettc.get("TokenID"));
			
			map.put("data",query.getSingleResult());
			System.out.println(map);
			return map;

		} catch (Exception e) {
			e.printStackTrace();
			map.put("Data", null);
			map.put("Error", e.toString());
			map.put("Status", 0);
			return map;
		}

	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getcardList(HashMap<String, Object> gettc) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<CardList> list = new ArrayList<>();
		try {
			StoredProcedureQuery query = emanager.createStoredProcedureQuery("USP_ENGetClubProductListing","ProductListing");
					list=query.getResultList();
			
			map.put("data",list);
			System.out.println(map);
			return map;

		} catch (Exception e) {
			e.printStackTrace();
			map.put("Data", null);
			map.put("Error", e.toString());
			map.put("Status", 0);
			return map;
		}

	}

}
