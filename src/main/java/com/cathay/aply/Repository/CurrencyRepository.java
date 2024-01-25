package com.cathay.aply.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cathay.aply.Entity.Currency;


public interface CurrencyRepository extends JpaRepository<Currency,String> {
	Currency findByCode(String code);
	void deleteByCode(String code);
}
