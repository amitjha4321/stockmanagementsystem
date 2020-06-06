package com.stockmanagementsystem.stockmanagementsystem.Controller;

import com.stockmanagementsystem.stockmanagementsystem.models.Vendor;
import com.stockmanagementsystem.stockmanagementsystem.repository.VendorRepository;
import com.stockmanagementsystem.stockmanagementsystem.service.VendorPagingService;
import com.stockmanagementsystem.stockmanagementsystem.utils.ExcelGenerator;
import com.stockmanagementsystem.stockmanagementsystem.utils.ReportGenerator;
import com.sun.jmx.snmp.Timestamp;
import net.sf.jasperreports.engine.JRException;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller

public class VendorController {

    @Autowired
    ExcelGenerator excelGenerator;

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    private ReportGenerator reportGenerator;

    @Autowired
    private VendorPagingService vendorPagingService;

    @GetMapping("/vendorlist")
    public String getVendorList(Model model){

        //List<Vendor> vendorList = vendorRepository.findAll();

        Page<Vendor> page=vendorPagingService.listAll();
        List<Vendor> listVendors=page.getContent();
        System.out.println("pagination"  +listVendors);
        model.addAttribute("vendorlist",listVendors);
       // model.addAttribute("vendor",new Vendor());

        //model.addAttribute("vendorlist",vendorList);

        return "/admin/vendorlist";
    }
    @GetMapping(value = "/addvendor")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAddVendorForm(Model model){
        List<Vendor> retrive = vendorRepository.findAll();
        System.out.println(retrive);

        model.addAttribute("vendors",retrive);
        model.addAttribute("vendor",new Vendor());

        return "/admin/addvendor";
    }

    @PostMapping(value = "/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addVendor(Vendor v,Model model){
        System.out.println("vendor post check");
        System.out.println(v);
        vendorRepository.save(v);
        List<Vendor> retrive = vendorRepository.findAll();
        System.out.println(retrive);

        model.addAttribute("vendorlist",retrive);
        //model.addAttribute("success","Vendor is added");
        model.addAttribute("vendor",new Vendor());
        return "admin/vendorlist";
    }
    @GetMapping("/generatereport/{format}")
    public void generateReport(@PathVariable String format, HttpServletResponse response) throws Exception, JRException {
         reportGenerator.exportReport(format, response);

    }
    @GetMapping("/generate/excelvendors")
    public void generateExcel(HttpServletResponse response) throws IOException{

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);

        String filenamewithtime= "attatchment; filename=" + "vendors " + strDate + ".xlsx";

//        response.setHeader("content-disposition", "inline; filename="+fileName);
//        setting this below will make the file available only to be viewed from browser...

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",filenamewithtime);
        //"attatchment; filename=vendors"
        ByteArrayInputStream inputStream= excelGenerator.exportVendorListtoExcel();

        IOUtils.copy(inputStream,response.getOutputStream());
    }
}
