package com.test.TestProject.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.*;

@Service
public class ReportService {

    public byte[] generateExcelReport() throws Exception {
        // Load Jasper template (JRXML)
        InputStream templateStream = new ClassPathResource("reports/report.jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(templateStream);

        // Sample data
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> data1 = new HashMap<>();
        data1.put("name", "John Doe");
        data1.put("email", "john.doe@example.com");
        dataList.add(data1);

        // Fill the report with data
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

        // Export to Excel
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
