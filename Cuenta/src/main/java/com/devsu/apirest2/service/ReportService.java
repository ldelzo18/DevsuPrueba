package com.devsu.apirest2.service;

import com.devsu.apirest2.dto.report.ReportDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<ReportDTO> getReportByDateAndCustomer(Long customerId, LocalDate startDate, LocalDate endDate);
}
