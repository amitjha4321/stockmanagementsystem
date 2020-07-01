package com.stockmanagementsystem.stockmanagementsystem.Controller;


import com.stockmanagementsystem.stockmanagementsystem.models.*;
import com.stockmanagementsystem.stockmanagementsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/dashboard/admin")
public class PurchaseController {

    private  OrderedItemRepository orderedItemRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    DocumentNumberingRepository documentNumberingRepository;

    @GetMapping(value = "/addpurchase")
    private String getPurchaseForm1(Model model){
        List<Vendor> venorList= vendorRepository.findAll();
        model.addAttribute("vendorList", venorList);
        List<Item> allItems= itemRepository.findAll();
        model.addAttribute("allItems", allItems);
        List<Purchase> allPurchased= purchaseRepository.findAll();
        model.addAttribute("allPurchased", allPurchased);
        System.out.println(allPurchased);

        DocumentNumbering no1= documentNumberingRepository.findById(1).get();
        model.addAttribute("num" , no1.getDisplayNo());

        model.addAttribute("purchase", new Purchase());
        return "admin/addpurchaseupdated";
    }

    @GetMapping(value = "/purchaseList")
    private String listPurchaseForm(Model model){
        List<Purchase> allPurchased= purchaseRepository.findAll();

        // for a purchase, concatenamed item name for that purachase
        List<String> concatenatedItemNameForAllPurchase = new ArrayList<>();

        model.addAttribute("allPurchased", allPurchased);



        for (Purchase purchase: allPurchased){
            String itemNamesConcatenated = "";
            for ( OrderedItem orderedItem: purchase.getOrderedItems()) {

                itemNamesConcatenated +=  orderedItem.getItemName().getName() +", ";
            }
            itemNamesConcatenated=itemNamesConcatenated.substring(0,itemNamesConcatenated.length()-2);
            concatenatedItemNameForAllPurchase.add(itemNamesConcatenated);
        }

        System.out.println("all purchases: " + allPurchased);
        System.out.println("all item names: " + concatenatedItemNameForAllPurchase);
        model.addAttribute("allItemName", concatenatedItemNameForAllPurchase);



        model.addAttribute("purchase", new Purchase());
        return "admin/purchaselist";
    }

    @PostMapping(value = "/addPurchase")
    private String addPurchase(Model model, Purchase purchase, @RequestParam("invoice")MultipartFile multipartFile){
        System.out.println(purchase);
        List<Vendor> vendorList= vendorRepository.findAll();
        model.addAttribute("vendorList", vendorList);
        float quantity;
        float rate;
        float totalPrice = 0;
        System.out.println("size size"+purchase.getOrderedItems().size());
        System.out.println("ordered items"+ purchase.getOrderedItems());
        for(int i=0; i<purchase.getOrderedItems().size(); i++){
            quantity=purchase.getOrderedItems().get(i).getQuantity();
            rate= purchase.getOrderedItems().get(i).getItemRate();
            float pp= quantity*rate;
            totalPrice += totalPrice+pp;
        }
        System.out.println("total price" + totalPrice);
        purchase.setTotalPrice(totalPrice);
        float remAmt= totalPrice - purchase.getAmountPaid();
        purchase.setRemainingAmount(remAmt);
        System.out.println(remAmt);
        System.out.println("purhase" +purchase);
        //for file upload related features
        purchase.setFileName(multipartFile.getOriginalFilename());
        purchase.setFileType(multipartFile.getContentType());
        try {
            purchase.setFileUpload(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        purchaseRepository.save(purchase);


        //      ++++++  For updating Document numbering +++++++

        DocumentNumbering no1 = documentNumberingRepository.findById(1).get();
//      increment value for numbering
        no1.setStartingPrefixNo(no1.getStartingPrefixNo()+1);
        no1.setStartingSuffixNo(no1.getStartingSuffixNo()+1);

        int prefixAmt = no1.getPrefixNo();
        int prefixStartingNum = no1.getStartingPrefixNo();
        String prefixStr = String.format("%0" + prefixAmt + "d", prefixStartingNum);

        int suffixAmt = no1.getSuffixNo();
        int suffixStartingNum = no1.getStartingSuffixNo();
        String suffixStr = String.format("%0" + suffixAmt + "d", suffixStartingNum);

        String display = no1.getPrefixWord()+"-"+prefixStr+"-"+no1.getSuffixWord()+"-"+suffixStr;
        no1.setDisplayNo(display);

        documentNumberingRepository.save(no1);
        //end of increment value for numbering

        model.addAttribute("num", documentNumberingRepository.findById(1).get().getDisplayNo());


        model.addAttribute("success", "purchase is successfully added");
        List<Purchase> allPurchased= purchaseRepository.findAll();
        model.addAttribute("allPurchased", allPurchased);
        model.addAttribute("purchase", new Purchase());

        return "admin/addpurchaseupdated";

    }

    @GetMapping("/updatePurchase/{id}")
    public String updatePurchase(@PathVariable int id, Purchase purchase, Model model){
        System.out.println("update executed");
        System.out.println(purchaseRepository.findById(id).get());
        List<Vendor> venorList= vendorRepository.findAll();
        model.addAttribute("vendorList", venorList);
        List<Item> allItems= itemRepository.findAll();
        model.addAttribute("allItems", allItems);
        List<Purchase> allPurchased= purchaseRepository.findAll();
        model.addAttribute("allPurchased", allPurchased);
        model.addAttribute("purchase", purchaseRepository.findById(id).get());
        return "admin/addPurchaseupdated";
    }

    @GetMapping("/downloadPurchaseInvoice/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable int id){
        Purchase purchase=purchaseRepository.findById(id).get();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(purchase.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+purchase.getFileName()+"\"")
                .body(new ByteArrayResource(purchase.getFileUpload()));
    }
}
