package com.cathay.aply.Service;

import java.util.List;

import com.cathay.aply.Entity.Currency;

public interface CurrencyService {
	public List<Currency> getCurrencyList();
    public Currency findCurrencyByCore(String core);
    public Currency createCurrency(Currency currency);
    public Currency updateCurrency(Currency currency);
    public void deleteCurrency(String name);
}
