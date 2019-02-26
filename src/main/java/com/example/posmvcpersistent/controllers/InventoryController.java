package com.example.posmvcpersistent.controllers;

import com.example.posmvcpersistent.models.*;
import com.example.posmvcpersistent.models.data.*;
import com.example.posmvcpersistent.models.forms.PrepAddInvForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("inventory")
public class InventoryController {

    @Autowired
    private InvItemDao invItemDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ItemTypeDao itemTypeDao;

    @Autowired
    private VendorDao vendorDao;


    // Request path: inventory
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("inventory", invItemDao.findAll());
        model.addAttribute("title", "My Shop: All Inventory");

        return "inventory/index";
    }

    @RequestMapping(value = "prepEntry", method = RequestMethod.GET)
    public String prepEntry (Model model){

        Iterable<Vendor> vendors = vendorDao.findAll();
        Iterable<Category> categories = categoryDao.findAll();
        int lineCount = 0;
        PrepAddInvForm aForm = new PrepAddInvForm(vendors, categories, lineCount);
        model.addAttribute("title", "Prepare to Enter Inventory from Invoice");
        model.addAttribute("form", aForm);
        return "inventory/prepEntry";
    }

    @RequestMapping(value = "prepEntry", method = RequestMethod.POST)
    public String processEntry(Model model, @Valid @ModelAttribute PrepAddInvForm aForm, Errors errors) {

        if (errors.hasErrors()) {
            Iterable<Vendor> vendors = vendorDao.findAll();
            Iterable<Category> categories = categoryDao.findAll();
            int lineCount = 0;
            PrepAddInvForm anotherForm = new PrepAddInvForm(vendors, categories, lineCount);
            model.addAttribute("title", "Try Again... Prepare to Enter Inventory from Invoice");
            model.addAttribute("form", anotherForm);
            return "inventory/prepEntry";
        }

        Category aCategory = categoryDao.findOne(aForm.getCategoryId());
        Vendor aVendor = vendorDao.findOne(aForm.getVendorId());
        int aLineCount = aForm.getLineCount();
        int catId = aCategory.getId();
        int vendorId = aVendor.getId();

        String info = catId + "/" + vendorId + "/" + aLineCount;

        return "redirect:/inventory/create/" + info;
    }

    @RequestMapping(value="create/{catId}/{vendorId}/{lineCount}", method = RequestMethod.GET)
    public String showCreateForm(Model model, @PathVariable int lineCount, @PathVariable int vendorId,
                                 @PathVariable int catId) {

        List<InvItem> invItems = new ArrayList<>();
        ListCreationDTO invoicedItemsForm = new ListCreationDTO(invItems);
        Vendor vendor = vendorDao.findOne(vendorId);
        String catName = categoryDao.findOne(catId).getName();
        String studioName = vendor.getStudioName();
        Iterable<ItemType> itemTypes = itemTypeDao.findAll();

        for (int i = 0; i < lineCount; i++) {
            InvItem anInvItem = new InvItem();
            anInvItem.setVendor(vendor);
            invoicedItemsForm.addInvItem(anInvItem);
        }

        String messageB;
        if (lineCount == 1) {
            messageB = "You are adding " + lineCount + " Line Item to Inventory from "
                    + studioName + " in the " + catName + " category:";
        } else {
            messageB = "You are adding " + lineCount + " Line Items to Inventory from "
                    + studioName + " in the " + catName + " category:";
        }
        model.addAttribute("messageB", messageB);
        model.addAttribute("title", "Adding Inventory from Invoice");

        model.addAttribute("form", invoicedItemsForm);
        model.addAttribute("itemTypes", itemTypes);

        return "inventory/createForm";

    }

    @RequestMapping(value="create/{catId}/{vendorId}/{lineCount}", method = RequestMethod.POST)
    public String saveCreatedBatch(Model model, @PathVariable int lineCount, @PathVariable int vendorId,
                                 @PathVariable int catId, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Try Again... Prepare to Enter Inventory from Invoice");
            return "inventory/create/" + catId + "/" + vendorId + "/" + lineCount;
        }
        return "redirect:inventory/view/" + vendorId;
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
