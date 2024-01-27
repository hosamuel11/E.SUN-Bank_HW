package com.esun_bank.aply.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esun_bank.aply.Entity.SeatingChart;

public interface SeatingChartRepository extends JpaRepository<SeatingChart, Long> {
	List<SeatingChart> findAll();
}
