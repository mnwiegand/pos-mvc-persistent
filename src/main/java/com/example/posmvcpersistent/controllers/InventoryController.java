package com.example.posmvcpersistent.controllers;

import com.example.posmvcpersistent.models.Category;
import com.example.posmvcpersistent.models.InvItem;
import com.example.posmvcpersistent.models.Vendor;
import com.example.posmvcpersistent.models.data.CategoryDao;
import com.example.posmvcpersistent.models.data.InvItemDao;
import com.example.posmvcpersistent.models.data.VendorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("inventory")
public class InventoryController {

    @Autowired
    private InvItemDao invItemDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private VendorDao vendorDao;


    // Request path: inventory
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("inventory", invItemDao.findAll());
        model.addAttribute("title", "My Shop's Inventory");

        return "inventory/index";
    }

    @RequestMapping(value = "/prepEntry", method = RequestMethod.GET)
    public String prepEntry (Model model){

        Iterable<Vendor> vendors = vendorDao.findAll();
        Iterable<Category> categories = categoryDao.findAll();
        model.addAttribute("title", "Checking in Inventory: Step 1, Select the Vendor");
        model.addAttribute(new InvItem());
        model.addAttribute("vendors", vendors);
        model.addAttribute("categories", categories);
        return "inventory/prepEntry";
    }

    @RequestMapping(value = "/prepEntry", method = RequestMethod.POST)
    public String processEntry(@ModelAttribute @Valid InvItem newInvItem,
                                       Errors errors, @RequestParam int categoryId,
                                       Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add InvItem");
            model.addAttribute("categories", categoryDao.findOne(categoryId));
            return "inventory/selectVendor";
        }

        Category cat = categoryDao.findOne(categoryId);
        newInvItem.setShopItemCat(cat);
        invItemDao.save(newInvItem);
        return "redirect:";
    }

    //TODO: Make REMOVE restricted to manager level
    //TODO: log inventory ADJUSTMENTS with a date, reason & who is making the change
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveShopItemForm(Model model) {
        model.addAttribute("shopItems", invItemDao.findAll());
        model.addAttribute("title", "Remove InvItem");
        return "inventory/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveInvItemForm(@RequestParam int[] invItemIds) {

        for (int invItemId : invItemIds) {
            invItemDao.delete(invItemId);
        }

        return "redirect:";
    }

}
