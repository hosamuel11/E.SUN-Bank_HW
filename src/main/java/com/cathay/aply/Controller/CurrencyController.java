package com.cathay.aply.Controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import com.cathay.aply.Entity.Currency;
import com.cathay.aply.Service.CurrencyServiceImpl;

@RestController
@RequestMapping("/api")
public class CurrencyController {
    @Autowired
	CurrencyServiceImpl currencyServiceImpl;
	 
	private RestTemplate restTemplate = new RestTemplate();
	String coindeskApiUrl = "https://api.coindesk.com/v1/bpi/currentprice.json";
	ResponseEntity<String> response = restTemplate.getForEntity(coindeskApiUrl, String.class);
	String responseBody = response.getBody();
	JSONObject jsonObject = new JSONObject(responseBody);
	//**務必先新增資料至h2 : INSERT INTO Currency (code, name) VALUES ('EUR', '歐元'); INSERT INTO Currency (code, name) VALUES ('USD', '美元'); INSERT INTO Currency (code, name) VALUES ('GBP', '新臺幣'); 	
	//新增幣別的 API。 
	@GetMapping("/create-api")
	public ResponseEntity<List<Currency>> NewCurrency(){
		Currency data = new Currency();
	    data.setCode("JPY");
	    data.setName("日幣");
	    currencyServiceImpl.createCurrency(data);
	return ResponseEntity.ok(currencyServiceImpl.getCurrencyList());//返回全部的幣別跟中文名稱
	}
 	//利用幣別查詢的 API。 
	@GetMapping("/search-api")
    public ResponseEntity<Currency> FindCurrencyByCode(){
    	Currency currency = currencyServiceImpl.findCurrencyByCore("USD");
        return ResponseEntity.ok(currency);
 	}
 	//刪除幣別的 API。
	//@DeleteMapping("/delete-api")
	@GetMapping("/delete-api")
    public ResponseEntity<Void> deleteCurrency(){
    	currencyServiceImpl.deleteCurrency("USD");
        return ResponseEntity.noContent().build();
 	}
	//更新幣別 API。
	@GetMapping("/update-api")
    public ResponseEntity<Currency> updateCurrencyByCode(){
		Currency currency = currencyServiceImpl.findCurrencyByCore("GBP");
		currency.setName("新臺幣");  //從新台幣改成新臺幣
		currencyServiceImpl.updateCurrency(currency);
        return ResponseEntity.ok(currency);
 	}
 	//呼叫 coindesk 的 API。
 	@GetMapping("/coindesk-api")
    public ResponseEntity<String> ShowcoindeskApi(){
 		return ResponseEntity.ok(responseBody);
 	}
 	//呼叫 coindesk 的 API,並進行資料轉換,組成新 API。
    @GetMapping("/newcoindesk-api")
    public ResponseEntity<Map<String, Object>> getNewApiData() {
        //提取更新時間
        String updatedTime = jsonObject.getJSONObject("time").getString("updated");

        //獲取貨幣資訊
        JSONObject bpiObject = jsonObject.getJSONObject("bpi");
        Map<String, Double> exchangeRates = new HashMap<>();
        for (String currency : bpiObject.keySet()) {
            double rate = bpiObject.getJSONObject(currency).getDouble("rate_float");
            exchangeRates.put(currency, rate);
        }

        // 新api
        Map<String, Object> newApiResponse = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        newApiResponse.put("更新時間", dateFormat.format(new Date(updatedTime)));
        newApiResponse.put("匯率", exchangeRates);

        return ResponseEntity.ok(newApiResponse);
    }
}
