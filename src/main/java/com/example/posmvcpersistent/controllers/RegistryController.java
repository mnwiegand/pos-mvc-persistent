package com.example.posmvcpersistent.controllers;

import com.example.posmvcpersistent.models.InvItem;
import com.example.posmvcpersistent.models.Registry;
import com.example.posmvcpersistent.models.data.RegistryDao;
import com.example.posmvcpersistent.models.data.InvItemDao;
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
@RequestMapping(value = "registry")
public class RegistryController {

    @Autowired
    private RegistryDao registryDao;

    @Autowired
    private InvItemDao invItemDao;

    @RequestMapping(value = "")
    public String index (Model model){
        model.addAttribute("title", "Registries");
        model.addAttribute("registries", registryDao.findAll());
        return "registry/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add (Model model){
        model.addAttribute("title", "Add Registry");
        model.addAttribute(new Registry());

        return "registry/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add (Model model,
                       @ModelAttribute @Valid Registry registry, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Registry");
            return "registry/add";
        }

        registryDao.save(registry);
        return "redirect:view/" + registry.getId();
    }

    @RequestMapping(value = "view/{registryId}", method = RequestMethod.GET)
    public String viewRegistry(Model model, @PathVariable int registryId){
        Registry registry = registryDao.findOne(registryId);
        model.addAttribute("title", registry.getName());
        model.addAttribute("shopItems", registry.getShopItems());
        model.addAttribute("registryId", registry.getId());

        return "registry/view";
    }

    @RequestMapping(value = "add-inventory/{registryId}", method = RequestMethod.GET)
    //why did I have public Object addItem()?
    public String addItem(Model model, @PathVariable int registryId){
        Registry registry = registryDao.findOne(registryId);
        Iterable<InvItem> shopItems = invItemDao.findAll();
        AddRegistryItemForm aForm = new AddRegistryItemForm(registry, shopItems);

        model.addAttribute("title", "Add item to Registry: " + registry.getName());
        model.addAttribute("form", aForm);
        return "registry/add-Item";
    }

    @RequestMapping(value = "add-inventory", method = RequestMethod.POST)
    public String addItem(Model model, @Valid @ModelAttribute AddRegistryItemForm aForm, Errors errors, Integer registryId) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Try Adding an Item Again");
            model.addAttribute("form", aForm);
            return "redirect:";
        }

        InvItem aShopItem = invItemDao.findOne(aForm.getShopItemId());
        Registry aRegistry = registryDao.findOne(registryId);
        aRegistry.addItem(aShopItem);
        registryDao.save(aRegistry);

        return "redirect:/registry/view/" + registryId;

    }
}
