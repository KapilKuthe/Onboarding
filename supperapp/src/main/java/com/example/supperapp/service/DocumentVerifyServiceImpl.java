package com.example.supperapp.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
			jdataJsondata.setSubCategory(originalList.get(i));
			jdataJsondata.setCategory(subLStrings.get(i));
			list.add(i, jdataJsondata);
			System.out.println(list.get(i));
		}
		System.out.println(originalList);
		System.out.println(subLStrings);

		// logic
		for (int i = 0; i < list.size(); i++) {
			String grandparent = list.get(i).getSubCategory().toString();
			if (list.get(i).getCategory().equalsIgnoreCase("NA")) {
				submenu = new LinkedHashMap<>();
				submenu.put("name", grandparent);

				AMC1 = new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					submenu1 = new LinkedHashMap<>();

					int c = 0;
					if (list.get(j).getCategory().equalsIgnoreCase(grandparent)) {
						String parent = list.get(j).getSubCategory().toString();
						submenu1.put("name", list.get(j).getSubCategory().toString());

						for (int k = 0; k < list.size(); k++) {
							submenu2 = new LinkedHashMap<>();

							int c1 = 0;
							if (list.get(k).getCategory().equalsIgnoreCase(parent)) {
								String child = list.get(k).getSubCategory().toString();
								submenu2.put("name", list.get(k).getSubCategory().toString());

								for (int l = 0; l < list.size(); l++) {
									submenu3 = new LinkedHashMap<>();

									int c2 = 0;
									if (list.get(l).getCategory().equalsIgnoreCase(child)) {

										submenu3.put("name", list.get(l).getSubCategory().toString());
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

	public HashMap<String, Object> globalFilter1(HashMap<String, Object> gblFilter) {
		LinkedHashMap<String, Object> lvl0 = new LinkedHashMap<>();
		LinkedHashMap<String, Object> lvl1 = new LinkedHashMap<>();
		LinkedHashMap<String, Object> lvl2 = new LinkedHashMap<>();
		LinkedHashMap<String, Object> lvl3 = new LinkedHashMap<>();
		List<String> arrlist = new ArrayList<>();
		List<HashMap<String, Object>> Arrlist0 = new ArrayList<>();
		List<HashMap<String, Object>> Arrlist1 = new ArrayList<>();
		List<HashMap<String, Object>> Arrlist2 = new ArrayList<>();
		List<HashMap<String, Object>> arrEmpty = new ArrayList<>();

		LinkedHashMap<String, Object> responseData = new LinkedHashMap<>();
		HashMap<String, Object> getData = daoLayer.globalFilter1(gblFilter);

		@SuppressWarnings("unchecked")
		List<Jsondata> list = (List<Jsondata>) getData.get("data");

		for (int i = 0; i < list.size(); i++) {
			String grandparent = list.get(i).getCategory().toString();
			if (list.get(i).getSubCategory().equalsIgnoreCase("NA")) {
				arrlist.add(i, grandparent);
			} else {
				continue;
			}
		}
		responseData.put("Orderlist", arrlist);

		// logic
		for (int i = 0; i < list.size(); i++) {
			String grandparent = list.get(i).getCategory().toString();
			if (list.get(i).getSubCategory().equalsIgnoreCase("NA")) {
				lvl0 = new LinkedHashMap<>();
//				lvl0.put("name", grandparent);

				Arrlist1 = new ArrayList<>();
				for (int j = 0; j < list.size(); j++) {
					lvl1 = new LinkedHashMap<>();

					int c = 0;
					if (list.get(j).getSubCategory().equalsIgnoreCase(grandparent)) {
						String parent = list.get(j).getCategory().toString();
						lvl1.put("name", list.get(j).getCategory().toString());

						for (int k = 0; k < list.size(); k++) {
							lvl2 = new LinkedHashMap<>();

							int c1 = 0;
							if (list.get(k).getSubCategory().equalsIgnoreCase(parent)) {
								String child = list.get(k).getCategory().toString();
								lvl2.put("name", list.get(k).getCategory().toString());

//								Arrlist3 = new ArrayList<>();
								lvl2.put("hasLayer", arrEmpty);
								Arrlist2.add(c, lvl2);
								c1++;
							}
							if (Arrlist2.isEmpty()) {
								lvl1.put("hasLayer", arrEmpty);
								continue;
							} else {
								lvl1.put("hasLayer", Arrlist2);
							}

						}
//								submenu1.put("hasLayer", "");
						Arrlist2 = new ArrayList<>();
						Arrlist1.add(c, lvl1);
						c++;
					}
					if (Arrlist1.isEmpty()) {
						lvl0.put("hasLayer", arrEmpty);
						continue;
					} else {
						lvl0.put("hasLayer", Arrlist1);
					}

				}
//					submenu.put("hasLayer",AMC1);
//				Arrlist0.add(i, lvl0);
				responseData.put(grandparent, Arrlist1);

			} else {
				continue;
			}
		}

		return responseData;

	}

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

	@SuppressWarnings({ "unchecked", "unused", "null" })
	public HashMap<String, Object> getcardList(HashMap<String, Object> gettc) {
		LinkedHashMap<String, Object> responseData = new LinkedHashMap<>();
		LinkedHashMap<String, Object> data = new LinkedHashMap<>();
		LinkedHashMap<String, Object> data1 = new LinkedHashMap<>();
		LinkedHashMap<String, Object> data2 = new LinkedHashMap<>();
		LinkedHashMap<String, Object> data3 = new LinkedHashMap<>();
		LinkedHashMap<String, Object> data4 = new LinkedHashMap<>();
		LinkedHashMap<String, Object> data5 = new LinkedHashMap<>();
		LinkedHashMap<String, Object> data6 = new LinkedHashMap<>();
		LinkedHashMap<String, Object> data7 = new LinkedHashMap<>();

		List<HashMap<String, Object>> jackPot = new ArrayList<>();
		List<HashMap<String, Object>> upcomingJackPot = new ArrayList<>();
		List<HashMap<String, Object>> vouchers = new ArrayList<>();
		List<HashMap<String, Object>> giftCards = new ArrayList<>();
		List<HashMap<String, Object>> cashback = new ArrayList<>();
		List<HashMap<String, Object>> ourbrand = new ArrayList<>();
		List<HashMap<String, Object>> referearn = new ArrayList<>();
		List<HashMap<String, Object>> allData = new ArrayList<>();

		HashMap<String, Object> getData = daoLayer.getcardList(gettc);
		List<CardList> list = (List<CardList>) getData.get("data");

		// JackPot Rewards
		for (int i = 0; i < list.size(); i++) {
			data = new LinkedHashMap<>();
			int c = 0;
			if (list.get(i).getCardType().equalsIgnoreCase("JackPot")) {
				Double minPriceDouble = list.get(i).getMinPrice();
				Double maxPriceDouble = list.get(i).getMaxPrice();

				data.put("desImg", list.get(i).getSmallImages());
				data.put("rewardBackgroundColor", "cromaBAckGroundImg");
				data.put("cardType", list.get(i).getCardType());
				data.put("title", list.get(i).getProductName());
				data.put("des", list.get(i).getDescription());
				if (minPriceDouble == null || minPriceDouble == 0.0) {
					data.put("prize", "Upto " + list.get(i).getMaxPrice());
				} else if (minPriceDouble == null && maxPriceDouble == null) {
					data.put("prize", "");
				} else {
					data.put("prize", list.get(i).getMinPrice() + "-" + list.get(i).getMaxPrice());
				}
				data.put("btnName", "Claim Now");
				data.put("btnColor", "btnColor");
				jackPot.add(c, data);
				c++;
			} else {
				continue;
			}
		}
		data1.put("Title", "JackPot Rewards");
		data1.put("count", jackPot.size());
		data1.put("rewardDetail", "Spin and win vouchers,coins and big prizes");
		data1.put("col", "9");
		data1.put("type", "grid");
		data1.put("size", "custSizeJackpot");
		data1.put("jackpotReward", jackPot);

		// Upcoming JackPot Rewards
		for (int i = 0; i < list.size(); i++) {
			data = new LinkedHashMap<>();
//			Date currentDate=java.time.LocalDate.now()
			int c = 0;
			if (list.get(i).getCardType().equalsIgnoreCase("JackPot")) {
				if (list.get(i).getStartDate().after(new Date())) {
					Double minPriceDouble = list.get(i).getMinPrice();
					Double maxPriceDouble = list.get(i).getMaxPrice();

					data.put("desImg", list.get(i).getSmallImages());
					data.put("rewardBackgroundColor", "cromaBAckGroundImg");
					data.put("cardType", list.get(i).getCardType());
					data.put("title", list.get(i).getProductName());
					data.put("des", list.get(i).getDescription());
					if (minPriceDouble == null || minPriceDouble == 0.0) {
						data.put("prize", "Upto " + list.get(i).getMaxPrice());
					} else if (minPriceDouble == null && maxPriceDouble == null) {
						data.put("prize", "");
					} else {
						data.put("prize", list.get(i).getMinPrice() + "-" + list.get(i).getMaxPrice());
					}
					data.put("btnName", "🔒Locked");
					data.put("availability", "Available from 22Mar");
					data.put("btnColor", "btnColor");
					upcomingJackPot.add(c, data);
					c++;
				} else {
					continue;
				}

			} else {
				continue;
			}
		}
		data2.put("Title", "Upcoming JackPot Rewards");
		data2.put("count", upcomingJackPot.size());
		data2.put("rewardDetail", "Spin and win vouchers,coins and big prizes");
		data2.put("col", "9");
		data2.put("type", "grid");
		data2.put("size", "custSizeUpcomingJackpot");
		data2.put("jackpotReward", upcomingJackPot);

		// Vouchers Rewards
		for (int i = 0; i < list.size(); i++) {
			data = new LinkedHashMap<>();
			int c = 0;
			if (list.get(i).getCardType().equalsIgnoreCase("Vouchers")) {
				Double minPriceDouble = list.get(i).getMinPrice();
				Double maxPriceDouble = list.get(i).getMaxPrice();

				data.put("desImg", list.get(i).getSmallImages());
				data.put("rewardBackgroundColor", "cromaBAckGroundImg");
				data.put("cardType", list.get(i).getCardType());
				data.put("title", list.get(i).getProductName());
				data.put("des", list.get(i).getDescription());
				if (minPriceDouble == null || minPriceDouble == 0.0) {
					data.put("prize", "Upto " + list.get(i).getMaxPrice());
				} else if (minPriceDouble == null && maxPriceDouble == null) {
					data.put("prize", "");
				} else {
					data.put("prize", list.get(i).getMinPrice() + "-" + list.get(i).getMaxPrice());
				}
				if (list.get(i).getStartDate().after(new Date())) {
					data.put("btnName", "🔒Locked");
				} else {
					data.put("btnName", "Claim Now");
				}
				data.put("btnColor", "btnColor");
				vouchers.add(c, data);
				c++;
			} else {
				continue;
			}
		}
		data3.put("Title", "Vouchers");
		data3.put("count", vouchers.size());
		data3.put("rewardDetail", "Play and win assured voucher on these brands");
		data3.put("col", "7");
		data3.put("type", "grid");
		data3.put("size", "custSizeVoucher");
		data3.put("jackpotReward", vouchers);

		// giftCards Rewards
		for (int i = 0; i < list.size(); i++) {
			data = new LinkedHashMap<>();
			int c = 0;
			if (list.get(i).getCardType().equalsIgnoreCase("Gift Cards")) {
				Double minPriceDouble = list.get(i).getMinPrice();
				Double maxPriceDouble = list.get(i).getMaxPrice();

				data.put("desImg", list.get(i).getSmallImages());
				data.put("rewardBackgroundColor", "custBackgroundBlue");
				data.put("cardType", list.get(i).getCardType());
				data.put("title", list.get(i).getProductName());
				data.put("des", list.get(i).getDescription());
				if (minPriceDouble == null || minPriceDouble == 0.0) {
					data.put("prize", "Upto " + list.get(i).getMaxPrice());
				} else if (minPriceDouble == null && maxPriceDouble == null) {
					data.put("prize", "");
				} else {
					data.put("prize", list.get(i).getMinPrice() + "-" + list.get(i).getMaxPrice());
				}
				if (list.get(i).getStartDate().after(new Date())) {
					data.put("btnName", "🔒Locked");
				} else {
					data.put("btnName", "Claim Now");
				}
				data.put("btnColor", "btnColor");
				giftCards.add(c, data);
				c++;
			} else {
				continue;
			}
		}
		data4.put("Title", "Gift Cards");
		data4.put("count", giftCards.size());
		data4.put("rewardDetail", "Play and win assured voucher on these brands");
		data4.put("col", "7");
		data4.put("type", "grid");
		data4.put("size", "custSizeGiftCard");
		data4.put("jackpotReward", giftCards);

		// Cash Back Rewards
		for (int i = 0; i < list.size(); i++) {
			data = new LinkedHashMap<>();
			int c = 0;
			if (list.get(i).getCardType().equalsIgnoreCase("CashBack")) {
				Double minPriceDouble = list.get(i).getMinPrice();
				Double maxPriceDouble = list.get(i).getMaxPrice();

				data.put("desImg", list.get(i).getSmallImages());
				data.put("rewardBackgroundColor", "custBackgroundBlue");
				data.put("cardType", list.get(i).getCardType());
				data.put("title", list.get(i).getProductName());
				data.put("des", list.get(i).getDescription());
				if (minPriceDouble == null || minPriceDouble == 0.0) {
					data.put("prize", "Upto " + list.get(i).getMaxPrice());
				} else if (minPriceDouble == null && maxPriceDouble == null) {
					data.put("prize", "");
				} else {
					data.put("prize", list.get(i).getMinPrice() + "-" + list.get(i).getMaxPrice());
				}
				if (list.get(i).getStartDate().after(new Date())) {
					data.put("btnName", "🔒Locked");
				} else {
					data.put("btnName", "Claim Now");
				}
				data.put("btnColor", "btnColor");
				cashback.add(c, data);
				c++;
			} else {
				continue;
			}
		}
		data5.put("Title", "Cash Back");
		data5.put("count", cashback.size());
		data5.put("rewardDetail", "Unlock cashback rewards by investing more");
		data5.put("col", "8");
		data5.put("type", "grid");
		data5.put("size", "custSizeGiftCard");
		data5.put("jackpotReward", cashback);

		// Our Brand Partners
		String[] words;
		String strTitle;
		List<String> wordList = new ArrayList<String>();
		List<String> uniqueList = new ArrayList<String>();
		List<CardList> brandlist = new ArrayList<CardList>();
		for (int l = 0; l < list.size(); l++) {
			strTitle = list.get(l).getProductName();

			int c = 0;
			if (strTitle == null) {
				continue;
			} else {
				words = strTitle.split("[\\W_]+");
				strTitle = words[0].toString();
				wordList.add(c, strTitle);
				uniqueList = wordList.stream().distinct().collect(Collectors.toList());
				CardList blist = list.get(l);
				brandlist.add(blist);
				c++;
			}
		}

//		System.out.println("the internal brand list: " + brandlist.size());
		for (int i = 0; i < brandlist.size(); i++) {
			data = new LinkedHashMap<>();
			int c = 0;
			if (list.get(i).getCardType().equalsIgnoreCase("Brand")) {
				Double minPriceDouble = brandlist.get(i).getMinPrice();
				Double maxPriceDouble = brandlist.get(i).getMaxPrice();

				data.put("desImg", brandlist.get(i).getSmallImages());
				data.put("rewardBackgroundColor", "cromaBAckGroundImg");
				data.put("cardType", brandlist.get(i).getCardType());
				data.put("title", brandlist.get(i).getProductName());
				data.put("des", brandlist.get(i).getDescription());
				if (minPriceDouble == null || minPriceDouble == 0.0) {
					data.put("prize", "Upto " + brandlist.get(i).getMaxPrice());
				} else if (minPriceDouble == null && maxPriceDouble == null) {
					data.put("prize", "");
				} else {
					data.put("prize", brandlist.get(i).getMinPrice() + "-" + brandlist.get(i).getMaxPrice());
				}
				data.put("btnName", "Claim Now");
				data.put("btnColor", "btnColor");
				ourbrand.add(c, data);
				c++;
			} else {
				continue;
			}
		}
		data6.put("Title", "Our Brand Partners");
		data6.put("count", "0");
		data6.put("rewardDetail", "Choose a brand to view all of their available rewards ");
		data6.put("col", "6");
		data6.put("type", "grid");
		data6.put("size", "custSizeVoucher");
		data6.put("jackpotReward", ourbrand);

		// Refer & Earn
		for (int i = 0; i < 1; i++) {
			data = new LinkedHashMap<>();
			Double minPriceDouble = list.get(i).getMinPrice();

			Double maxPriceDouble = list.get(i).getMaxPrice();

			data.put("desImg", "");
			data.put("rewardBackgroundColor", "cromaBAckGroundImg");
			data.put("cardType", "");
			data.put("title", "");
			data.put("des", "");
			if (minPriceDouble == null || minPriceDouble == 0.0) {
				data.put("prize", "Upto 1000");
			} else if (minPriceDouble == null && maxPriceDouble == null) {
				data.put("prize", "");
			} else {
				data.put("prize", "");
			}
			data.put("btnName", "Claim Now");
			data.put("btnColor", "btnColor");
			referearn.add(i, data);

		}
		data7.put("Title", "Refer & Earn");
		data7.put("count", "0");
		data7.put("rewardDetail", "Refer a friend and earn assured cashback when they invest here");
		data7.put("col", "12");
		data7.put("type", "grid");
		data7.put("size", "custSizeVoucher");
		data7.put("jackpotReward", referearn);

		// final data
		allData.add(0, data1);
		allData.add(1, data2);
		allData.add(2, data3);
		allData.add(3, data4);
		allData.add(4, data5);
		allData.add(5, data6);
		allData.add(6, data7);

		responseData.put("allData", allData);
		responseData.put("coin", torusClubCoinAllocation(gettc));
		return responseData;
	}

	public HashMap<String, Object> torusClubCoinAllocation(HashMap<String, Object> map) {
		String urlmapping = res.getRequestURI();
		map.put("url", urlmapping);
//		System.out.println(map);
		
		LinkedHashMap<String, Object> data = new LinkedHashMap<>();
		LinkedHashMap<String, Object> subData = new LinkedHashMap<>();
		LinkedHashMap<String, Object> scrollingCardList = new LinkedHashMap<>();
		LinkedHashMap<String, Object> cardList = new LinkedHashMap<>();

		List<HashMap<String, Object>> scrollingCard = new ArrayList<>();
		List<HashMap<String, Object>> card = new ArrayList<>();

		if(map.get("url").equals("") || map.get("url").equals(null) )
		{
//			 data;
		}
		
		
		data.put("giftpop", "/assets/icon/fataka.svg");
		data.put("title", "Congrats! You won");
		data.put("icon", "/assets/icon/torusT.svg");
		data.put("prize", "2,300 coins");
		data.put("subtitle", "Win exciting rewards");
		data.put("subtitles", "Stand a chance to win coin, cashback, vouchers or gift.");

		// scrollingCard List
		for (int i = 0; i < 3; i++) {
			scrollingCardList = new LinkedHashMap<>();
			
			scrollingCardList.put("title","");
			scrollingCardList.put("subtitle","");
			scrollingCardList.put("icon","");
			scrollingCardList.put("arrow","");
			
			scrollingCard.add(i,scrollingCardList);
		}

		data.put("scrollingCard", scrollingCard);

		// scrollingCard List
		for (int i = 0; i < 1; i++) {
			cardList = new LinkedHashMap<>();
			
			cardList.put("title","");
			cardList.put("subtitle","");
			cardList.put("prize","");
			cardList.put("icon","");
			cardList.put("btn","");
			
			card.add(i,cardList);
		}
		data.put("Card", card);

		data.put("closeBtn", "Close");
		data.put("torusBtn", "Go To Torus Club");
		
		return data;
		
	}

}
