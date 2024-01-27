package com.esun_bank.aply.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esun_bank.aply.Entity.AdjustSeatRequest;
import com.esun_bank.aply.Entity.Employee;
import com.esun_bank.aply.Entity.SeatingChart;
import com.esun_bank.aply.Service.EmployeeService;
import com.esun_bank.aply.Service.SeatingChartService;

import java.util.List;

@Controller
@RequestMapping("api")
public class SeatingController {

    @Autowired
    private SeatingChartService seatingChartService;
    
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/")
    public String showSeatingChartPage() {
        return "seatingsystem";
    }
   
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/seating-charts")
    public ResponseEntity<List<SeatingChart>> getAllSeatingCharts() {
        List<SeatingChart> seatingCharts = seatingChartService.getAllSeatingCharts();
        return new ResponseEntity<>(seatingCharts, HttpStatus.OK);
    }

    @PostMapping("/seating-chart/adjust-seat")
    public ResponseEntity<String> adjustSeat(@RequestBody AdjustSeatRequest request) {
        try {
            seatingChartService.adjustSeat(request);
            return ResponseEntity.ok("Seat adjustment successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Seat adjustment failed");
        }
    }
}