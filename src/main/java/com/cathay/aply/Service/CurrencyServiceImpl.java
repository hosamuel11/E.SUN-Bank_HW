package com.cathay.aply.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cathay.aply.Entity.Currency;
import com.cathay.aply.Repository.CurrencyRepository;

@Service
public class CurrencyServiceImpl implements CurrencyService{
	
	@Autowired
	CurrencyRepository currencyRepository;
	
    @Override
    @Transactional
    public List<Currency> getCurrencyList() {
        return currencyRepository.findAll();
    }
    @Override
    @Transactional
    public Currency findCurrencyByCore(String code) {
    	return currencyRepository.findByCode(code);
    }
    
    @Override
    @Transactional
    public Currency createCurrency(Currency currency) {
    	return currencyRepository.save(currency);
    }
    
    @Override
    @Transactional
    public Currency updateCurrency(Currency currency) {
    	return currencyRepository.save(currency);
    }
    
    @Override
    @Transactional
    public void deleteCurrency(String code) {
    	currencyRepository.deleteByCode(code);
    }
}
