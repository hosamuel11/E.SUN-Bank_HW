package com.cathay.aply;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.web.client.RestTemplate;

import com.cathay.aply.Entity.Currency;
import com.cathay.aply.Service.CurrencyServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CathayApplicationTests	 {

	
    @Autowired
	CurrencyServiceImpl currencyServiceImpl;
	 
    public RestTemplate restTemplate = new RestTemplate();
	String coindeskApiUrl = "https://api.coindesk.com/v1/bpi/currentprice.json";
	ResponseEntity<String> response = restTemplate.getForEntity(coindeskApiUrl, String.class);
	String responseBody = response.getBody();
	
 	
	//新增幣別的 API。 
	@Test
	public void NewCurrency(){
		Currency data = new Currency();
	    data.setCode("JPY");
	    data.setName("日幣");
	    currencyServiceImpl.createCurrency(data);
	    System.out.println(data);
	}
 	//利用幣別查詢的 API。 
	@Test
    public void FindCurrencyByCode(){
    	Currency currency = currencyServiceImpl.findCurrencyByCore("USD");
        System.out.println(currency);
 	}
 	//刪除幣別的 API。
	@Test 
	public void deleteCurrency(){
    	currencyServiceImpl.deleteCurrency("USD");
 	}
	//更新幣別 API。
	@Test
    public void updateCurrencyByCode(){
		Currency currency = currencyServiceImpl.findCurrencyByCore("JPY");
		currency.setName("日幣");  
		currencyServiceImpl.updateCurrency(currency);
		System.out.println("updateSuccess");
 	}
 	//呼叫 coindesk 的 API。
 	@Test
    public void ShowcoindeskApi(){
 		System.out.println(responseBody);
 	}
 	//呼叫 coindesk 的 API,並進行資料轉換,組成新 API。
// 	@Test
//    public void getNewApiData() {
// 		JSONObject jsonObject = new JSONObject(responseBody);
//        //提取更新時間
//        String updatedTime = jsonObject.getJSONObject("time").getString("updated");
//
//        //獲取貨幣資訊
//        JSONObject bpiObject = jsonObject.getJSONObject("bpi");
//        Map<String, Double> exchangeRates = new HashMap<>();
//        for (String currency : bpiObject.keySet()) {
//            double rate = bpiObject.getJSONObject(currency).getDouble("rate_float");
//            exchangeRates.put(currency, rate);
//        }
//        // 新api
//        Map<String, Object> newApiResponse = new HashMap<>();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        newApiResponse.put("更新時間", dateFormat.format(new Date(updatedTime)));
//        newApiResponse.put("匯率", exchangeRates);
//
//        System.out.println(newApiResponse);
//    }
}
