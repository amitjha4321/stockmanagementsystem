package com.stockmanagementsystem.stockmanagementsystem.utils;

import com.stockmanagementsystem.stockmanagementsystem.models.Vendor;
import com.stockmanagementsystem.stockmanagementsystem.repository.VendorRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelGenerator {
    @Autowired
    VendorRepository vendorRepository;

    public ByteArrayInputStream exportVendorListtoExcel(){
        List<Vendor> vendors= vendorRepository.findAll();
       try(Workbook workbook =new XSSFWorkbook()) {

           Sheet sheet = workbook.createSheet("Vendors");
           Row row =sheet.createRow(0);
           CellStyle headerCellStyle=workbook.createCellStyle();
           headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
           headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

           //creating header cells
           Cell cell=row.createCell(0);
           cell.setCellValue("Name");
           cell.setCellStyle(headerCellStyle);


           cell=row.createCell(1);
           cell.setCellValue("Vendor Code");
           cell.setCellStyle(headerCellStyle);

           cell=row.createCell(2);
           cell.setCellValue("Address");
           cell.setCellStyle(headerCellStyle);

           cell=row.createCell(3);
           cell.setCellValue("PAN");
           cell.setCellStyle(headerCellStyle);

           cell=row.createCell(4);
           cell.setCellValue("VAT");
           cell.setCellStyle(headerCellStyle);

           cell=row.createCell(5);
           cell.setCellValue("Mobile No");
           cell.setCellStyle(headerCellStyle);

//            creating data row for each of vendor object
           for (int i=0; i<vendors.size(); i++){
            Row dataRow=sheet.createRow(i+1); // + 1 to exclude the header row
               dataRow.createCell(0).setCellValue(vendors.get(i).getName());
               dataRow.createCell(1).setCellValue(vendors.get(i).getCode());
               dataRow.createCell(2).setCellValue(vendors.get(i).getAddress());
               dataRow.createCell(3).setCellValue(vendors.get(i).getPanNumber());
               dataRow.createCell(4).setCellValue(vendors.get(i).getVatNumber());
               dataRow.createCell(5).setCellValue(vendors.get(i).getPhone());

           }

           //Making sure the size of excel cell auto resize to fit the data
           sheet.autoSizeColumn(0);
           sheet.autoSizeColumn(1);
           sheet.autoSizeColumn(2);
           sheet.autoSizeColumn(3);
           sheet.autoSizeColumn(4);
           sheet.autoSizeColumn(5);

           ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
           workbook.write(outputStream);

           return new ByteArrayInputStream(outputStream.toByteArray());

       }catch (IOException e){
           e.printStackTrace();
           return null;
       }

    }

}
