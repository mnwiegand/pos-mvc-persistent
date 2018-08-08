package com.example.posmvcpersistent.controllers;

import com.example.posmvcpersistent.models.Customer;
import com.example.posmvcpersistent.models.SalesSlip;
import com.example.posmvcpersistent.models.ShopItem;
import com.example.posmvcpersistent.models.data.CustomerDao;
import com.example.posmvcpersistent.models.data.SalesSlipDao;
import com.example.posmvcpersistent.models.data.ShopItemDao;
import com.example.posmvcpersistent.models.forms.AddSalesSlipItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "cashRegister")
public class SalesSlipController {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private ShopItemDao shopItemDao;

    @Autowired
    private SalesSlipDao salesSlipDao;

    @RequestMapping(value = "/index")
    public String index (Model model){
        model.addAttribute("title", "Sales Slip");
        model.addAttribute("Select Customer", customerDao.findAll());
        model.addAttribute("select item", shopItemDao.findAll());
        return "cashRegister/index";
    }

    @RequestMapping(value = "addCustomer", method = RequestMethod.GET)
    public String addCustomer (Model model){
        model.addAttribute("title", "Add Customer");
        model.addAttribute(new Customer());
        return "cashRegister/addCustomer";
    }

    @RequestMapping(value = "addCustomer", method = RequestMethod.POST)
    public String addCustomer (Model model,
                       @Valid @ModelAttribute Customer customer, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Customer");
            return "cashregister/addCustomer";
        }

        customerDao.save(customer);
        // Hibernate generates the customer id

        return "redirect:view/" + customer.getId();
    }

    @RequestMapping(value = "view/{salesSlipId}", method = RequestMethod.GET)
    public String viewRegistry(Model model, @PathVariable int salesSlipId){
        SalesSlip salesSlip = salesSlipDao.findOne(salesSlipId);
        model.addAttribute("title", "Sales Slip:");
        model.addAttribute("ShopItems", salesSlip.getShopItems());
        model.addAttribute("salesSlipId", salesSlipId);

        return "cashRegister/view";
    }

    @RequestMapping(value = "add-item/{registryId}", method = RequestMethod.GET)
    //why did I have public Object addItem()?
    public String addItem(Model model, @PathVariable int salesSlipId){
        SalesSlip salesSlip = salesSlipDao.findOne(salesSlipId);
        Iterable<ShopItem> shopItems = shopItemDao.findAll();
        AddSalesSlipItemForm aForm = new AddSalesSlipItemForm(salesSlip, shopItems);

        model.addAttribute("title", "Add item to Sales Slip: " + salesSlipId);
        model.addAttribute("form", aForm);
        return "Registry/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @Valid @ModelAttribute AddSalesSlipItemForm aForm, Errors errors, Integer salesSlipId) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Try Adding an Item Again");
            model.addAttribute("form", aForm);
            return "Registry/add-item";
        }

        ShopItem aShopItem = shopItemDao.findOne(aForm.getShopItemId());
        SalesSlip aSalesSlip = salesSlipDao.findOne(aForm.getSalesSlipId());
        aSalesSlip.addItem(aShopItem);
        salesSlipDao.save(aSalesSlip);

        return "redirect:/Registry/view/" + aSalesSlip.getId();

    }
}

