package com.hakimov.owner.controllers;

import com.hakimov.owner.dao.OwnerDAO;
import com.hakimov.owner.models.Owner;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/owners")
public class OwnerController {

    private OwnerDAO ownerDAO;

    @GetMapping()
    public String getAllOwners(Model model) {
        model.addAttribute("owners", ownerDAO.getAllOwners());
        return "owners/owners";
    }

    @GetMapping("/{id}")
    public String getOwnerById(@PathVariable int id, Model model) {
        model.addAttribute("owner", ownerDAO.getOwnerById(id));
        return "owners/show";
    }

    @GetMapping("/new")
    public String newOwner(@ModelAttribute("owner") Owner owner) {
        return "owners/new";
    }

    @PostMapping()
    public String saveOwner(@ModelAttribute("owner") @Valid Owner owner,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "owners/new";
        }
        ownerDAO.saveOwner(owner);
        return "redirect:/owners";
    }

    @GetMapping("{id}/edit")
    public String editOwner(@PathVariable int id, Model model) {
        model.addAttribute("owner", ownerDAO.getOwnerById(id));
        return "owners/edit";
    }

    @PatchMapping("/{id}")
    public String updateOwner(@PathVariable("id") int id,
                              @ModelAttribute("owner") @Valid Owner owner,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "owners/edit";
        }
        ownerDAO.updateOwner(owner, id);

        return "redirect:/owners";
    }

    @DeleteMapping("/{id}")
    public String deleteOwner(@PathVariable("id") int id) {
        ownerDAO.deleteOwnerById(id);
        return "redirect:/owners";
    }


}
