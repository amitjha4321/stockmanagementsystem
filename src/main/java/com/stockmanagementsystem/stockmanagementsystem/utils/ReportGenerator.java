package com.stockmanagementsystem.stockmanagementsystem.utils;


import com.stockmanagementsystem.stockmanagementsystem.models.Vendor;
import com.stockmanagementsystem.stockmanagementsystem.repository.VendorRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportGenerator {

    @Autowired
    private VendorRepository repository;

    public void exportReport(String reportFormat, HttpServletResponse response) throws IOException,FileNotFoundException, JRException {
        List<Vendor> vendors = repository.findAll();

        //load file
        File file= ResourceUtils.getFile("classpath:vendorlist.jrxml");
        //compile file
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(vendors);
        Map<String,Object> parameters= new HashMap<>();
        parameters.put("createdBy","Amit");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameters,dataSource);

        SimpleDateFormat sdfDate = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        

        String filenamewithtime= "attachment; filename=vendors " + strDate +  ".pdf";

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",filenamewithtime);

        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            //JasperExportManager.exportReportToPdfFile(jasperPrint,"C:\\Users\\97798\\Downloads"+"\\vendors.pdf");
        }
        //return "Report Successfully Generated in : Downloads";


    }
}






