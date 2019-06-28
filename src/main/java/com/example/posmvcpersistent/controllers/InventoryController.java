package com.example.posmvcpersistent.controllers;

import com.example.posmvcpersistent.models.*;
import com.example.posmvcpersistent.models.data.*;
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

    @Autowired
    private BatchDao batchDao;

    @Autowired
    private AddressDao addressDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("inventory", invItemDao.findAll());
        model.addAttribute("title", "My Shop: All Inventory");

        return "inventory/index";
    }

    @RequestMapping(value = "prepEntry", method = RequestMethod.GET)
    public String prepEntry (Model model){
        Iterable<Vendor> vendors = vendorDao.findAll();
        Iterable<Address> addresses = addressDao.findAll();
        model.addAttribute("title", "Prepare to Enter Inventory from Invoice");
        model.addAttribute(new Batch());
        model.addAttribute("vendors", vendors);
        model.addAttribute("addresses", addresses);

        return "inventory/enterInvPart1";
    }

    @RequestMapping(value = "prepEntry", method = RequestMethod.POST)
    public String prepEntry(Model model,
                                @Valid @ModelAttribute Batch batch, Errors errors){

        if (errors.hasErrors()) {
            Iterable<Vendor> vendors = vendorDao.findAll();
            model.addAttribute("title", "Try Again... Prepare to Enter Inventory from Invoice");
            model.addAttribute("vendors", vendors);
            return "inventory/enterInvPart1";
        }

        batchDao.save(batch);

        return "redirect:createEntry/" + batch.getId();
    }

    @RequestMapping(value="createEntry/{batchId}", method = RequestMethod.GET)
    public String showCreateForm(Model model, @PathVariable int batchId) {
        Batch aBatch = batchDao.findOne(batchId);
        int aLineCount = aBatch.getLineCount();
        Vendor vendor =  aBatch.getVendor();
        String studioName = vendor.getStudioName();
        String batchName = aBatch.getName();
        List<Category> categories = vendor.getCategories();
        List<InvItem> invItems = new ArrayList<>();
        ListCreationDTO invoicedItemsForm = new ListCreationDTO(invItems);
        //Iterable<ItemType> itemTypes = category.getItemTypes();

        model.addAttribute("categories", categories);

        for (int i = 0; i < aLineCount; i++) {
            InvItem anInvItem = new InvItem();
            anInvItem.setVendor(vendor);
            invoicedItemsForm.addInvItem(anInvItem);
        }

        String messageB;
        if (aLineCount == 1) {
            messageB = "You are adding " + aLineCount + " Line Item to Inventory from "
                    + studioName + " from Invoice " + batchName;
        } else {
            messageB = "You are adding " + aLineCount + " Line Items to Inventory from "
                    + studioName + " from Invoice " + batchName;
        }
        model.addAttribute("messageB", messageB);
        model.addAttribute("title", "Adding Inventory from Invoice");

        model.addAttribute("form", invoicedItemsForm);
        //model.addAttribute("itemTypes", itemTypes);

        return "inventory/createForm";

    }

    @RequestMapping(value="createEntry/{batchId}", method = RequestMethod.POST)
    public String saveCreatedBatch(Model model, @PathVariable int batchId, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Try Again... Prepare to Enter Inventory from Invoice");
            return "inventory/create/" + batchId;
        }
        return "redirect:inventory/createEntry/" + batchId;
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
