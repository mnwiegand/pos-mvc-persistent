package com.example.posmvcpersistent.controllers;

import com.example.posmvcpersistent.models.ItemType;
import com.example.posmvcpersistent.models.Category;
import com.example.posmvcpersistent.models.data.CategoryDao;
import com.example.posmvcpersistent.models.data.ItemTypeDao;
import com.example.posmvcpersistent.models.forms.AddRegistryItemForm;
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
@RequestMapping(value = "itemType")
public class ItemTypeController {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ItemTypeDao itemTypeDao;

    @RequestMapping(value = "")
    public String index (Model model){
        model.addAttribute("title", "Item Types in Categories");
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("itemTypes", itemTypeDao.findAll());
        return "itemType/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add (Model model){
        model.addAttribute("title", "Add Item Type");
        model.addAttribute(new ItemType());

        return "itemType/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add (Model model,
                       @ModelAttribute @Valid ItemType itemType, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Registry");
            return "registry/add";
        }
        itemTypeDao.save(itemType);
        return "view/" + itemType.getId();
    }

    @RequestMapping(value = "view/{itemTypeId}", method = RequestMethod.GET)
    public String viewItemTypesByCategory(Model model, @PathVariable int itemTypeId){
        ItemType itemType = itemTypeDao.findOne(itemTypeId);
        model.addAttribute("title", itemType.getName());
        //model.addAttribute("shopItems", itemType.getCategories());
        model.addAttribute("categories", categoryDao.findAll());

        return "redirect:view/linkToCategory/" + itemType.getId();
    }
}
