package com.example.posmvcpersistent.controllers;

import com.example.posmvcpersistent.models.InvItem;
import com.example.posmvcpersistent.models.Registry;
import com.example.posmvcpersistent.models.data.CustomerDao;
import com.example.posmvcpersistent.models.data.InvItemDao;
import com.example.posmvcpersistent.models.forms.AddRegistryItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "checkOut")
public class CheckOutController {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private InvItemDao invItemDao;

    @RequestMapping(value = "")
    public String index (Model model){
        model.addAttribute("title", "Prepare to Checkout Customer");
        model.addAttribute("customers", customerDao.findAll());
        model.addAttribute("invItems", invItemDao.findAll());
        return "checkOut/index";
    }

}
