package com.stockmanagementsystem.stockmanagementsystem.Controller;

import com.stockmanagementsystem.stockmanagementsystem.models.Vendor;
import com.stockmanagementsystem.stockmanagementsystem.repository.VendorRepository;
import com.stockmanagementsystem.stockmanagementsystem.utils.ReportGenerator;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;
import java.util.List;

@Controller

public class VendorController {

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    private ReportGenerator reportGenerator;

    @GetMapping("/vendorlist")
    public String getVendorList(Model model){

        List<Vendor> vendorList = vendorRepository.findAll();
        model.addAttribute("vendorlist",vendorList);

        return "/admin/vendorlist";
    }
    @GetMapping(value = "/addvendor")
    public String getAddVendorForm(Model model){
        List<Vendor> retrive = vendorRepository.findAll();
        System.out.println(retrive);
        model.addAttribute("vendors",retrive);
        model.addAttribute("vendor",new Vendor());
        return "/admin/addvendor";
    }

    @PostMapping(value = "/add")
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
    public ResponseEntity generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
         reportGenerator.exportReport(format);

        //return "vendorlist";
       return ResponseEntity.ok().body("generated");
    }
}
