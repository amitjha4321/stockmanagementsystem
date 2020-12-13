package com.stockmanagementsystem.stockmanagementsystem.Controller;

import com.stockmanagementsystem.stockmanagementsystem.models.DocumentNumberingforSales;
import com.stockmanagementsystem.stockmanagementsystem.models.Sales;
import com.stockmanagementsystem.stockmanagementsystem.models.UserDetails;
import com.stockmanagementsystem.stockmanagementsystem.models.Vendor;
import com.stockmanagementsystem.stockmanagementsystem.repository.DocumentNumberingforSalesRepository;
import com.stockmanagementsystem.stockmanagementsystem.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class SalesController {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private DocumentNumberingforSalesRepository documentNumberingforSalesRepository;

    @GetMapping("/dashboard/admin/addsales")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addSalesForm(Model model){
        List<Sales> salesList =salesRepository.findAll();

        DocumentNumberingforSales dnfs= documentNumberingforSalesRepository.findById(1).get();
        model.addAttribute("billnumber" , dnfs.getDisplayNo());

        model.addAttribute("sales",salesList);
        model.addAttribute("sale",new Sales());
        return "/admin/sales";
    }

    @PostMapping(value = "/dashboard/admin/addsales")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addSales(Sales sales,Model model){
        salesRepository.save(sales);

        System.out.println("SSSSales" + sales);
        DocumentNumberingforSales dnfs = documentNumberingforSalesRepository.findById(1).get();
        dnfs.setStartingPrefixNo(dnfs.getStartingPrefixNo()+1);
        dnfs.setStartingSuffixNo(dnfs.getStartingSuffixNo()+1);

        int prefixAmt = dnfs.getPrefixNo();
        int prefixStartingNum = dnfs.getStartingPrefixNo();
        String prefixStr = String.format("%0" + prefixAmt + "d", prefixStartingNum);

        int suffixAmt = dnfs.getSuffixNo();
        int suffixStartingNum = dnfs.getStartingSuffixNo();
        String suffixStr = String.format("%0" + suffixAmt + "d", suffixStartingNum);

        String display = dnfs.getPrefixWord()+"-"+prefixStr+"-"+dnfs.getSuffixWord()+"-"+suffixStr;
        dnfs.setDisplayNo(display);

        documentNumberingforSalesRepository.save(dnfs);

        System.out.println("DDDDDDDDDisplay " + documentNumberingforSalesRepository.findById(1).get().getDisplayNo() );

        model.addAttribute("billnumber", documentNumberingforSalesRepository.findById(1).get().getDisplayNo());
        model.addAttribute("success","Sales Record is saved successfully!!!");
        model.addAttribute("sale",new Sales());
        return "admin/sales";
    }
    @GetMapping("/saleslist")
    public String getSalesList(Model model){
        List<Sales> salesList=salesRepository.findAll();
        model.addAttribute("sale",new Sales());
        model.addAttribute("sales",salesList);
        return "/admin/saleslist";

    }
    @GetMapping("/sales/findbyid")
    @ResponseBody
    public Sales findById(int id){
        return salesRepository.findById(id);
    }
//    @RequestMapping(value = "/users/update",method = {RequestMethod.PUT , RequestMethod.GET})
//    public String update(UserDetails user) throws Exception {
//        userService.updateUser(user);
//        return "redirect:/userlist";
//    }
    @RequestMapping(value = "/sales/update",method = {RequestMethod.PUT,RequestMethod.GET})
    public String update(Sales sales){
        salesRepository.save(sales);
        return "redirect:/saleslist";
    }

    @RequestMapping(value = "/sales/delete",method = {RequestMethod.DELETE,RequestMethod.GET})
    public String delete(int id){
        salesRepository.deleteById(id);
        return "redirect:/saleslist";
    }
}
