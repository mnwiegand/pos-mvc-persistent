package com.example.posmvcpersistent.controllers;

import com.example.posmvcpersistent.models.Category;
import com.example.posmvcpersistent.models.ShopItem;
import com.example.posmvcpersistent.models.data.CategoryDao;
import com.example.posmvcpersistent.models.data.ShopItemDao;
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
@RequestMapping("ShopItem")
public class ShopItemController {

    @Autowired
    private ShopItemDao shopItemDao;

    @Autowired
    private CategoryDao categoryDao;

    // Request path: ShopItem
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("ShopItems", shopItemDao.findAll());
        model.addAttribute("title", "My ShopItems");

        return "ShopItem/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddShopItemForm(Model model) {
        model.addAttribute("title", "Add ShopItem");
        model.addAttribute(new ShopItem());
        model.addAttribute("categories", categoryDao.findAll());
        return "ShopItem/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddShopItemForm(@ModelAttribute @Valid ShopItem newShopItem,
                                       Errors errors, @RequestParam int categoryId,
                                       Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add ShopItem");
            model.addAttribute("categories", categoryDao.findOne(categoryId));
            return "ShopItem/add";
        }

        Category cat = categoryDao.findOne(categoryId);
        newShopItem.setCategory(cat);
        shopItemDao.save(newShopItem);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveShopItemForm(Model model) {
        model.addAttribute("ShopItems", shopItemDao.findAll());
        model.addAttribute("title", "Remove ShopItem");
        return "ShopItem/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveShopItemForm(@RequestParam int[] ShopItemIds) {

        for (int ShopItemId : ShopItemIds) {
            shopItemDao.delete(ShopItemId);
        }

        return "redirect:";
    }

}
