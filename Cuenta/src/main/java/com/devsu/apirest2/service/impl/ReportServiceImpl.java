package com.devsu.apirest2.service.impl;

import com.devsu.apirest2.service.CustomerService;
import com.devsu.apirest2.dto.report.ReportDTO;
import com.devsu.apirest2.repository.TransactionRepository;
import com.devsu.apirest2.service.ReportService;
import com.devsu.apirest2.util.mapper.ReportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);

    private CustomerService customerService;
    private TransactionRepository transactionRepository;

    public ReportServiceImpl(CustomerService customerService, TransactionRepository transactionRepository){
        this.customerService = customerService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<ReportDTO> getReportByDateAndCustomer(Long customerId, LocalDate startDate, LocalDate endDate) {
        LOGGER.info("Starting get report by dateRange and customerId");
        try{
            customerService.findCustomerIfExists(customerId);
            ZoneId zoneId = ZoneId.of("America/Lima");
            ZonedDateTime startDateTime = startDate.atStartOfDay(zoneId);
            ZonedDateTime endDateTime = endDate.plusDays(1).atStartOfDay(zoneId).minusNanos(1);
            return transactionRepository.findByAccount_Customer_CustomerIdAndDateBetweenOrderByDateAsc(
                    customerId, startDateTime, endDateTime).stream().map(ReportMapper::transactionToReportDTO).toList();
        } finally {
            LOGGER.info("Finish get report by dateRange and customerId");
        }

    }
}
