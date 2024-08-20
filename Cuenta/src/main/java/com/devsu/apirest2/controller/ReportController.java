package com.devsu.apirest2.controller;

import com.devsu.apirest2.dto.report.ReportDTO;
import com.devsu.apirest2.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

    private ReportService reportService;

    public ReportController(ReportService reportService){
        this.reportService = reportService;
    }

    @GetMapping("")
    public ResponseEntity<List<ReportDTO>> getReportByCustomerAndDateRange(@RequestParam(name = "customerId", required = true) Long customerId,
                                                            @RequestParam(name = "startDate", required = true) LocalDate startDate,
                                                            @RequestParam(name = "endDate", required = true) LocalDate endDate){
        LOGGER.info("Receive a get reports petition for customer id {} and date between {} and {}", customerId, startDate, endDate);
        return new ResponseEntity<>(reportService.getReportByDateAndCustomer(customerId, startDate, endDate), HttpStatus.OK);
    }
}
