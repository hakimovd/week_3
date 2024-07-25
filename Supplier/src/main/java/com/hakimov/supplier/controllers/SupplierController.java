package com.hakimov.supplier.controllers;

import com.hakimov.supplier.dao.SupplierDAO;
import com.hakimov.supplier.models.Supplier;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    private SupplierDAO supplierDAO;

    @GetMapping()
    public String showAllSuppliers (Model model) {
        model.addAttribute("suppliers", supplierDAO.showAllSuppliers());
        return "suppliers/suppliers";
    }

    @GetMapping("/{id}")
    public String show (@PathVariable("id") int id, Model model) {
        model.addAttribute("supplier", supplierDAO.show(id));
        return "suppliers/show";
    }

    @GetMapping("/new")
    public String newSupplier (@ModelAttribute("supplier") Supplier supplier) {
        return "suppliers/new";
    }

    @PostMapping()
    public String create (@ModelAttribute("person") @Valid Supplier supplier,
                          BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "suppliers/new";

        supplierDAO.save(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("supplier", supplierDAO.show(id));
        return "suppliers/edit";
    }

    @PatchMapping("/{id}")
    public String update (@ModelAttribute("supplier") @Valid Supplier supplier,
                          BindingResult bindingResult,
                          @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "suppliers/edit";

        supplierDAO.update(id,supplier);
        return "redirect:/suppliers";
    }

    @DeleteMapping("/{id}")
    public String delete (@PathVariable("id") int id) {
        supplierDAO.delete(id);
        return "redirect:/suppliers";
    }


}
