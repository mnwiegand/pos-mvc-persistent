package com.example.posmvcpersistent.controllers;

import com.example.posmvcpersistent.models.Registry;
import com.example.posmvcpersistent.models.ShopItem;
import com.example.posmvcpersistent.models.data.RegistryDao;
import com.example.posmvcpersistent.models.data.ShopItemDao;
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
@RequestMapping(value = "Registry")
public class RegistryController {

    @Autowired
    private RegistryDao registryDao;

    @Autowired
    private ShopItemDao shopItemDao;

    @RequestMapping(value = "")
    public String index (Model model){
        model.addAttribute("title", "Registries");
        model.addAttribute("registries", registryDao.findAll());
        model.addAttribute("shop items", shopItemDao.findAll());
        return "Registry/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add (Model model){
        model.addAttribute("title", "Add Registry");
        model.addAttribute(new Registry());
        return "Registry/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add (Model model,
                       @Valid @ModelAttribute Registry registry, Errors errors){
    if (errors.hasErrors()){
        model.addAttribute("title", "Add Registry");
        return "Registry/add";
    }

    registryDao.save(registry);
    // Hibernate generates the Registry id

    return "redirect:view/" + registry.getId();
    }

    @RequestMapping(value = "view/{registryId}", method = RequestMethod.GET)
    public String viewRegistry(Model model, @PathVariable int registryId){
        Registry registry = registryDao.findOne(registryId);
        model.addAttribute("title", registry.getName());
        model.addAttribute("ShopItems", registry.getShopItems());
        model.addAttribute("RegistryId", registry.getId());

        return "Registry/view";
    }

    @RequestMapping(value = "add-item/{registryId}", method = RequestMethod.GET)
    //why did I have public Object addItem()?
    public String addItem(Model model, @PathVariable int registryId){
        Registry registry = registryDao.findOne(registryId);
        Iterable<ShopItem> shopItems = shopItemDao.findAll();
        AddRegistryItemForm aForm = new AddRegistryItemForm(registry, shopItems);

        model.addAttribute("title", "Add item to Registry: " + registry.getName());
        model.addAttribute("form", aForm);
        return "Registry/add-item";
    }

    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @Valid @ModelAttribute AddRegistryItemForm aForm, Errors errors, Integer registryId) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Try Adding an Item Again");
            model.addAttribute("form", aForm);
            return "Registry/add-item";
        }

        ShopItem aShopItem = shopItemDao.findOne(aForm.getShopItemId());
        Registry aRegistry = registryDao.findOne(aForm.getRegistryId());
        aRegistry.addItem(aShopItem);
        registryDao.save(aRegistry);

        return "redirect:/Registry/view/" + aRegistry.getId();

    }
}
