package com.stockmanagementsystem.stockmanagementsystem.Controller;

import com.stockmanagementsystem.stockmanagementsystem.models.Category;
import com.stockmanagementsystem.stockmanagementsystem.models.Item;
import com.stockmanagementsystem.stockmanagementsystem.repository.CategoryRepository;
import com.stockmanagementsystem.stockmanagementsystem.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(value = "/addItem")
    public String addItemForm(Model model){

        List<Item> retrive=  itemRepository.findAll();
        model.addAttribute("items",retrive);
        List<Category> allCategories= categoryRepository.findAll();
        System.out.println(allCategories);
        model.addAttribute("allCategories", allCategories);
        model.addAttribute("item", new Item());
        return "admin/item";
    }

    @PostMapping(value = "/addItem")
    public String addItem(Model model, Item item){
        List<Category> allCategories= categoryRepository.findAll();
        model.addAttribute("allCategories", allCategories);
        System.out.println(item);
        itemRepository.save(item);
        List<Item> retrive=  itemRepository.findAll();
        model.addAttribute("success", "Item is added successfully");
        model.addAttribute("item", new Item());
        model.addAttribute("items",retrive);
        return "admin/item";
    }
}
