package com.esun_bank.aply.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esun_bank.aply.Entity.AdjustSeatRequest;
import com.esun_bank.aply.Entity.Employee;
import com.esun_bank.aply.Entity.SeatingChart;
import com.esun_bank.aply.Repository.EmployeeRepository;
import com.esun_bank.aply.Repository.SeatingChartRepository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
public class SeatingChartService {

    @Autowired
    private SeatingChartRepository seatingChartRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void adjustSeat(AdjustSeatRequest request) {
        try {
            SeatingChart seatingChart = seatingChartRepository.findById(request.getFloorSeatSeq())
                    .orElseThrow(() -> new EntityNotFoundException("Seating chart not found"));

            if (seatingChart.getEmployee() != null) {
                throw new IllegalStateException("Seat is already occupied");
            }

            Employee employee = employeeRepository.findByEmpId(request.getEmployeeId())
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

            seatingChart.setEmployee(employee);
            seatingChartRepository.save(seatingChart);
        } catch (Exception e) {
            // 輸出異常信息，以便進一步調試
            e.printStackTrace();
            throw e;  // 將異常再次拋出，以便上層捕獲
        }
    }
    public List<SeatingChart> getAllSeatingCharts() {
        return seatingChartRepository.findAll();
    }
}