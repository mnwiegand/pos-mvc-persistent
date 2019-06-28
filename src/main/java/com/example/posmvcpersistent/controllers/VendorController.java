package com.example.posmvcpersistent.controllers;

import com.example.posmvcpersistent.models.Category;
import com.example.posmvcpersistent.models.USStatesAndTerritories;
import com.example.posmvcpersistent.models.Vendor;
import com.example.posmvcpersistent.models.VendorSearchAreas;
import com.example.posmvcpersistent.models.data.CategoryDao;
import com.example.posmvcpersistent.models.data.VendorDao;
import com.example.posmvcpersistent.models.forms.AddVendorCategoryForm;
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
@RequestMapping("vendor")
public class VendorController {
    @Autowired
    private VendorDao vendorDao;

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("vendors", vendorDao.findAll());
        model.addAttribute("title", "Vendors");

        return "vendor/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model){

        model.addAttribute("title", "Add Vendor");
        model.addAttribute(new Vendor());
        model.addAttribute("states", USStatesAndTerritories.values());
        return "vendor/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model,
                      @ModelAttribute @Valid Vendor vendor, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Vendor");
            model.addAttribute("states", USStatesAndTerritories.values());
            return "vendor/add";
        }

        vendorDao.save(vendor);
        return "redirect:view/" + vendor.getId();
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveVendorForm(Model model) {
        model.addAttribute("vendors", vendorDao.findAll());
        model.addAttribute("title", "Remove Vendor");
        return "vendor/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveVendorForm(@RequestParam int[] vendorIds) {
        for (int vendorId : vendorIds) {
            vendorDao.delete(vendorId);
        }
        return "redirect:";
    }

    @RequestMapping(value="view/{vendorId}", method = RequestMethod.GET)
    public String viewVendor(Model model, @PathVariable int vendorId) {
        Vendor vendor = vendorDao.findOne(vendorId);
        model.addAttribute("title", vendor.getStudioName());
        model.addAttribute("categories", vendor.getCategories());
        model.addAttribute("vendorId", vendorId);

        return "vendor/view";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(Model model){

        String searchTerm = "";
        String searchType = "";

        model.addAttribute("title", "Search for Vendor");
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("searchTypes", VendorSearchAreas.values());
        model.addAttribute("searchType", searchType);

        return "vendor/search";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String processSearch(Model model, @ModelAttribute String searchTerm, @ModelAttribute String searchType)
    {


        if (searchType.equals("searchByAll")){

        }
        else if (searchType.equals("searchByStudio")){
            Iterable<Vendor> vendors = new ArrayList<>();
            vendors = vendorDao.findAll();

            String results = "";


            for (Vendor vendor : vendors){
                if (vendor.getStudioName().equals(searchTerm)) {
                    if (results.equals("")){
                        results = results + vendor.getId();
                    }
                    if (!results.equals("")) {
                        results = results + "+" + vendor.getId();
                    }
                }

                return "redirect: vendor/view/"+results;
            }
        }

        else if (searchType == "searchByPerson"){}

        else if (searchType == "searchByAddress"){}

        else {
            return "redirect:/vendor/search";
        }
    }

    @RequestMapping(value="add-category/{vendorId}", method = RequestMethod.GET)
    public String addVendorCategory(Model model, @PathVariable int vendorId){
        Vendor vendor = vendorDao.findOne(vendorId);
        Iterable<Category> categories = categoryDao.findAll();
        AddVendorCategoryForm aForm = new AddVendorCategoryForm( vendor, categories);
        model.addAttribute("title", "Assign categories to " + vendor.getStudioName());
        model.addAttribute("form", aForm);

        return "vendor/add-cat";
    }

    @RequestMapping(value="add-category", method = RequestMethod.POST)
    public String addCategory(Model model, @Valid @ModelAttribute AddVendorCategoryForm aForm,
                                    Integer vendorID, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("title", "Try adding a category again");
            model.addAttribute("form", aForm);
            return "vendor/add-cat";
        }

        Category aCategory = categoryDao.findOne(aForm.getCategoryId());
        Vendor aVendor = vendorDao.findOne(aForm.getVendorId());
        aVendor.addCategory(aCategory);
        vendorDao.save(aVendor);

        return "redirect:/vendor/view/" + aVendor.getId();
    }


}


