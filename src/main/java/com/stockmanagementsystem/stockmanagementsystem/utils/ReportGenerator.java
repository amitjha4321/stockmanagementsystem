package com.stockmanagementsystem.stockmanagementsystem.utils;


import com.stockmanagementsystem.stockmanagementsystem.models.Vendor;
import com.stockmanagementsystem.stockmanagementsystem.repository.VendorRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportGenerator {

    @Autowired
    private VendorRepository repository;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        List<Vendor> vendors = repository.findAll();
        //load file
        File file= ResourceUtils.getFile("classpath:vendorlist.jrxml");
        //compile file
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(vendors);
        Map<String,Object> parameters= new HashMap<>();
        parameters.put("createdBy","Amit");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters,dataSource);
        //JasperPrint jasperPrint1=JasperFillManager.
        if(reportFormat.equalsIgnoreCase("xlsx")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,"C:\\Users\\97798\\Downloads"+"\\vendors.html");

            //JasperExportManager.
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\97798\\Downloads"+"\\vendors.pdf");
        }
        return "Report Successfully Generated in : Downloads";


    }
}
