package com.example.posmvcpersistent.controllers;


import com.example.posmvcpersistent.models.Category;
import com.example.posmvcpersistent.models.Vendor;
import com.example.posmvcpersistent.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//TODO: make this restricted to Manager Level

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Categories");

        return "category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("title", "Add Category");
        model.addAttribute(new Category());
        return "category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model,
                      @ModelAttribute @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Category");
            return "category/add";
        }

        categoryDao.save(category);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCategoryForm(Model model) {
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Remove Category");
        return "category/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCategoryForm(@RequestParam int[] categoryIds) {
        for (int categoryId : categoryIds) {
            categoryDao.delete(categoryId);
        }
        return "redirect:";
    }

    @RequestMapping(value="view/{categoryId}", method = RequestMethod.GET)
    public String viewCategorysVendors(Model model, @PathVariable int categoryId){
        Category category = categoryDao.findOne(categoryId);
        List<Vendor> vendors = category.getVendors();
        model.addAttribute("vendors", vendors);
        return "category/view";
    }
}


