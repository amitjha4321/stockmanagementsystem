package com.stockmanagementsystem.stockmanagementsystem.Controller;

import com.stockmanagementsystem.stockmanagementsystem.models.Sales;
import com.stockmanagementsystem.stockmanagementsystem.models.Vendor;
import com.stockmanagementsystem.stockmanagementsystem.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

public class SalesController {

    @Autowired
    private SalesRepository salesRepository;

    @GetMapping("/dashboard/admin/addsales")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addSalesForm(Model model){
        List<Sales> salesList =salesRepository.findAll();
        model.addAttribute("sales",salesList);
        model.addAttribute("sale",new Sales());
        return "/admin/sales";
    }

    @PostMapping(value = "/dashboard/admin/addsales")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addSales(Sales sales,Model model){
        salesRepository.save(sales);
        model.addAttribute("success","Sales Record is saved successfully!!!");
        model.addAttribute("sale",new Sales());
        return "admin/sales";
    }

//    @GetMapping("/vendorlist")
//    public String getVendorList(Model model){
//
//        //List<Vendor> vendorList = vendorRepository.findAll();
//
//        Page<Vendor> page=vendorPagingService.listAll();
//        List<Vendor> listVendors=page.getContent();
//        System.out.println("pagination"  +listVendors);
//        model.addAttribute("vendorlist",listVendors);
//        // model.addAttribute("vendor",new Vendor());
//
//        //model.addAttribute("vendorlist",vendorList);
//
//        return "/admin/vendorlist";
//    }
    @GetMapping("/saleslist")
    public String getSalesList(Model model){
        List<Sales> salesList=salesRepository.findAll();
        model.addAttribute("sale",new Sales());
        model.addAttribute("sales",salesList);
        return "/admin/saleslist";

    }
}
