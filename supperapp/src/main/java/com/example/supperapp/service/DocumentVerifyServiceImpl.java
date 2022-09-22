package com.example.supperapp.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.aspectj.apache.bcel.classfile.InnerClass;
import org.aspectj.weaver.ast.And;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.select.Evaluator.IsEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.supperapp.dao.DaoLayer;
import com.example.supperapp.entities.CardList;
import com.example.supperapp.entities.SubTypeName;

@Service
public class DocumentVerifyServiceImpl implements DocumentVerifyService {

	@Autowired
	private DaoLayer daoLayer;
	@Autowired
	HttpServletRequest res;

	public String camelCase(String[] words) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			if (i == 0) {
				word = word.isEmpty() ? word : word.toLowerCase();
			} else {
				word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
			}
			builder.append(word);
		}
		return builder.toString();
	}

	// creating a generic function that converts the Array into List
	public static <T> List<T> ArrayToListConversion(T array[]) {
		// creating the constructor of the List class
		List<T> list = new ArrayList<>();
		for (T t : array) {
			// adding each element to the List
			list.add(t);
		}
		return list;
	}

	/* Json data reader function */
	public static void parseObject(JSONObject json, String key) {
		// System.out.println(json.has(key));
		System.out.println(json.get(key));
	}

	/* reading dynamic json object */
	public static void getKey(JSONObject json, String key) {

		boolean exists = json.has(key);
		Iterator<?> keys;
		String nextKeys;

		if (!exists) {
			keys = json.keys();
			while (keys.hasNext()) {
				nextKeys = (String) keys.next();
				System.out.println(nextKeys);
				try {

					if (json.get(nextKeys) instanceof JSONObject) {

						if (exists == false) {
							getKey(json.getJSONObject(nextKeys), key);
						}

					} else if (json.get(nextKeys) instanceof JSONArray) {
						JSONArray jsonarray = json.getJSONArray(nextKeys);
						for (int i = 0; i < jsonarray.length(); i++) {
							String jsonarrayString = jsonarray.get(i).toString();
							System.out.println(jsonarrayString);
							JSONObject innerJSOn = new JSONObject(jsonarrayString);

							if (exists == false) {
								getKey(innerJSOn, key);
							}

						}

					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		} else {
			parseObject(json, key);
		}

	}

	public HashMap<String, Object> insertDocumentVerify(HashMap<String, Object> map) {
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

	public HashMap<String, Object> masterDetail(HashMap<String, Object> masterDdlRequest) {
		HashMap<String, Object> map = daoLayer.masterDetail(masterDdlRequest);
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
	public HashMap<String, Object> addBMIData(HashMap<String, Object> map) {
		// logic
		String weight = (String) map.get("Weight");
		String height = (String) map.get("Height");
		Double weightAc, heightAc;
		String Category;
		Double BMI, HC;

		String[] strArray = weight.split("_");
		weightAc = Double.parseDouble(strArray[0]);

		String[] strArray1 = height.split("_");
		heightAc = Double.parseDouble(strArray1[0]);

		if (strArray[1].equals("LBS")) {
			weightAc = weightAc / (2.2046);
		}

		if (strArray1[1].equals("CM")) {
			heightAc = heightAc * (0.01);
		}
		if (strArray1[1].equals("FT")) {
			heightAc = heightAc * (0.3048);
		}

		BMI = weightAc / (heightAc * heightAc);

		if (BMI > 12 && BMI <= 18)
			Category = "Under Weight";
		else if (BMI > 18.001 && BMI <= 24.999)
			Category = "Healthly";
		else if (BMI > 18.001 && BMI <= 24.999)
			Category = "Over Weight";
		else if (BMI > 25.000 && BMI <= 29.999)
			Category = "obese";
		else if (BMI > 30.000 && BMI <= 39.999)
			Category = "obese II";
		else if (BMI > 40.000)
			Category = " Extremly obese";
		else
			Category = "";

		HC = 100 - (Math.abs((BMI - 21.75) / 21.75) * 100);

		map.put("Height", heightAc);
		map.put("Weight", weightAc);
		map.put("Bmi_Report", BMI);
		map.put("Bmi_Category", Category);
		map.put("Health_Score", HC);

		HashMap<String, Object> responseData = new HashMap<>();
		HashMap<String, Object> getData = daoLayer.addBMIData(map);

		if (getData.get("flag").equals("Yes")) {
			responseData.put("Status", "1");
			responseData.put("ErrorCode", "0");
			responseData.put("Message", "Success");
			responseData.put("BMI", new DecimalFormat("##.###").format(BMI));
			responseData.put("BMI", Category);
			responseData.put("HealthScore", new DecimalFormat("##.###").format(HC));
		} else {
			responseData.put("Status", "0");
			responseData.put("ErrorCode", "1");
			responseData.put("Message", "Failure");
			responseData.put("BMI", new DecimalFormat("##.###").format(BMI));
			responseData.put("BMI", Category);
			responseData.put("HealthScore", new DecimalFormat("##.###").format(HC));
		}

		return responseData;

	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Object> hamburgerMenu(HashMap<String, Object> productId) throws ParseException {

		HashMap<String, Object> responseData = new HashMap<>();
		HashMap<String, Object> getData = daoLayer.hamburgerMenu(productId);

		// logic for adding icon url in jsonArray
		JSONObject json = new JSONObject((String) getData.get("Data"));
		SubTypeName sub = new SubTypeName();
		String strTitle;
		String[] words;
		String url = res.getRequestURL().toString();
		String path = res.getRequestURI();
		url = url.replace(path, "");
		String BaseUrl = "/SuperApp/Engagement/EngagementHamburger/getImage/";

		Iterator<?> keys;
		String nextKeys;
		keys = json.keys();
		while (keys.hasNext()) {
			nextKeys = (String) keys.next();
			List<SubTypeName> listy = new ArrayList<SubTypeName>();
			try {
				if (json.get(nextKeys) instanceof JSONArray) {
					JSONArray jsonarray = json.getJSONArray(nextKeys);

					for (int i = 0; i < jsonarray.length(); i++) {
						sub.setTitle(jsonarray.get(i).toString());
						strTitle = sub.getTitle();
						words = strTitle.split("[\\W_]+");
						strTitle = camelCase(words);
						sub.setTitleUrl(url + BaseUrl + "/" + strTitle + ".svg");

						listy.add(i, new SubTypeName(sub.getTitle(), sub.getTitleUrl()));
//						System.out.println(listy.get(i).getTitle());
						json.put(nextKeys, listy);
//						System.out.println(json.get(nextKeys));
					}
				}

			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}

		JSONParser parser = new JSONParser(json.toString());
		HashMap<String, Object> data = (HashMap<String, Object>) parser.parse();

		if (getData.get("flag").equals("Yes")) {
			responseData.put("Status", "1");
			responseData.put("ErrorCode", "0");
			responseData.put("BaseUrl", url + BaseUrl);
			responseData.put("FileExtention", ".svg");
			responseData.put("Message", "Success");
			responseData.put("Data", data);
		} else {
			responseData.put("Status", "0");
			responseData.put("ErrorCode", "1");
			responseData.put("BaseUrl", url + BaseUrl);
			responseData.put("FileExtention", ".svg");
			responseData.put("Message", "Failure");
			responseData.put("Data", data);
		}
		return responseData;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Object> globalFilter(HashMap<String, Object> gblFilter) throws ParseException {

		LinkedHashMap<String, Object> submenu = new LinkedHashMap<>();
		LinkedHashMap<String, Object> submenu1 = new LinkedHashMap<>();
		LinkedHashMap<String, Object> submenu2 = new LinkedHashMap<>();
		LinkedHashMap<String, Object> submenu3 = new LinkedHashMap<>();
		List<HashMap<String, Object>> AMC = new ArrayList<>();
		List<HashMap<String, Object>> AMC1 = new ArrayList<>();
		List<HashMap<String, Object>> AMC2 = new ArrayList<>();
		List<HashMap<String, Object>> AMC3 = new ArrayList<>();
		Jsondata jdataJsondata = new Jsondata();
		LinkedHashMap<String, Object> responseData = new LinkedHashMap<>();
		List<LinkedHashMap<String, Object>> mainList = new ArrayList<>();
		List<String> originalList = new ArrayList<>();
		List<String> subLStrings = new ArrayList<>();
		List<Jsondata> list = new ArrayList<>();

		originalList.add(0, "Wealth");
		originalList.add(1, "Health");
		originalList.add(2, "Career");
		originalList.add(3, "Settings");
		originalList.add(4, "Money maker");
		originalList.add(5, "Money saver");
		originalList.add(6, "Equity");
		originalList.add(7, "Gold");
		originalList.add(8, "Mutual Fund");
		originalList.add(9, "Stocks");
		originalList.add(10, "ETFs");

		subLStrings.add(0, "NA");
		subLStrings.add(1, "NA");
		subLStrings.add(2, "NA");
		subLStrings.add(3, "NA");
		subLStrings.add(4, "Wealth");
		subLStrings.add(5, "Wealth");
		subLStrings.add(6, "Money maker");
		subLStrings.add(7, "Money saver");
		subLStrings.add(8, "Money maker");
		subLStrings.add(9, "Equity");
		subLStrings.add(10, "Equity");

		for (int i = 0; i < originalList.size(); i++) {
			jdataJsondata = new Jsondata();
			jdataJsondata.setName(originalList.get(i));
			jdataJsondata.setSubname(subLStrings.get(i));
			list.add(i, jdataJsondata);
			System.out.println(list.get(i));
		}
		System.out.println(originalList);
		System.out.println(subLStrings);

		// logic
		for (int i = 0; i < list.size(); i++) {
			String grandparent = list.get(i).getName().toString();
			if (list.get(i).getSubname().equalsIgnoreCase("NA")) {
				submenu = new LinkedHashMap<>();
				submenu.put("name", grandparent);

				AMC1 = new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					submenu1 = new LinkedHashMap<>();

					int c = 0;
					if (list.get(j).getSubname().equalsIgnoreCase(grandparent)) {
						String parent = list.get(j).getName().toString();
						submenu1.put("name", list.get(j).getName().toString());

						for (int k = 0; k < list.size(); k++) {
							submenu2 = new LinkedHashMap<>();

							int c1 = 0;
							if (list.get(k).getSubname().equalsIgnoreCase(parent)) {
								String child = list.get(k).getName().toString();
								submenu2.put("name", list.get(k).getName().toString());

								for (int l = 0; l < list.size(); l++) {
									submenu3 = new LinkedHashMap<>();

									int c2 = 0;
									if (list.get(l).getSubname().equalsIgnoreCase(child)) {

										submenu3.put("name", list.get(l).getName().toString());
										submenu3.put("hasLayer", "");
										AMC3.add(c, submenu3);
										c2++;
									}
									if (AMC3.isEmpty()) {
										submenu2.put("hasLayer", "");
										continue;
									} else {
										submenu2.put("hasLayer", AMC3);
									}

								}
								AMC3 = new ArrayList<>();
//								submenu2.put("hasLayer", "");
								AMC2.add(c, submenu2);
								c1++;
							}
							if (AMC2.isEmpty()) {
								submenu1.put("hasLayer", "");
								continue;
							} else {
								submenu1.put("hasLayer", AMC2);
							}

						}
//						submenu1.put("hasLayer", "");
						AMC2 = new ArrayList<>();
						AMC1.add(c, submenu1);
						c++;
					}
					if (AMC1.isEmpty()) {
						submenu.put("hasLayer", "");
						continue;
					} else {
						submenu.put("hasLayer", AMC1);
					}

				}
//			submenu.put("hasLayer",AMC1);

				AMC.add(i, submenu);
			} else {
				continue;
			}
		}

		responseData.put("List", AMC);

		return responseData;

	}

//	public HashMap<String, Object> filter(HashMap<String, Object> gettc) {
//		HashMap<String, Object> filter = new HashMap<>();
//		List<HashMap<String, Object>> AMC1 = new ArrayList<>();
//		
//		for (int i = 0; i < 2; i++) {
//			filter = new HashMap<>();
//			filter.put("id", i);
//			filter.put("name", "sub level");
//			AMC1.add(i, filter);
//		}
//		
//		
//		return gettc;
//
//	}

	public HashMap<String, Object> getDashboard(HashMap<String, Object> gettc) {
		HashMap<String, Object> responseData = new HashMap<>();
		HashMap<String, Object> Header = new HashMap<>();
		HashMap<String, Object> data = new HashMap<>();

		HashMap<String, Object> getData = daoLayer.getDashboard(gettc);

		responseData.put("heading", "Torus Earnings");
		responseData.put("title", "Torus Coins");
		responseData.put("coins", getData.get("data"));
		responseData.put("coinImg", "/assets/icon/torusCoin.svg");
		responseData.put("subtitle", "CashBack");
		responseData.put("prize", "35,755");
		responseData.put("prizeImg", "/assets/icon/cashBack.svg");
		responseData.put("rewardsImg", "/assets/icon/allRewards.svg");

		Header.put("Header", responseData);

		return Header;

	}

	@SuppressWarnings({ "unchecked", "unused" })
	public HashMap<String, Object> getcardList(HashMap<String, Object> gettc) {

		LinkedHashMap<String, Object> data = new LinkedHashMap<>();
		LinkedHashMap<String, Object> data1 = new LinkedHashMap<>();
		List<HashMap<String, Object>> subLStrings = new ArrayList<>();

		HashMap<String, Object> getData = daoLayer.getcardList(gettc);
		List<CardList> list = (List<CardList>) getData.get("data");

		// JackPot Rewards
		data1.put("Title", "JackPot Rewards");
		data1.put("count", list.size());
		data1.put("rewardDetail", "Spin and win vouchers,coins and big prizes");
		for (int i = 0; i < list.size(); i++) {
			data = new LinkedHashMap<>();
			Double minPriceDouble = list.get(i).getMinPrice();
			Double maxPriceDouble = list.get(i).getMaxPrice();

			data.put("desImg", list.get(i).getSmallImages());
			data.put("cardType", "jackPot");
			data.put("title", list.get(i).getProductName());
			data.put("des", list.get(i).getDescription());
			if (minPriceDouble == null || minPriceDouble == 0.0) {
				data.put("prize", "Upto " + list.get(i).getMaxPrice());
			} else if (minPriceDouble == null && maxPriceDouble == null) {
				data.put("prize", "");
			} else {
				data.put("prize", list.get(i).getMinPrice() + "-" + list.get(i).getMaxPrice());
			}
			subLStrings.add(i, data);
		}
		data1.put("jackpotReward", subLStrings);

		return data1;
	}

	private boolean IsEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

}
