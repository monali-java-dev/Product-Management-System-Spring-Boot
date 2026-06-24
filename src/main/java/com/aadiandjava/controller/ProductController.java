package com.aadiandjava.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aadiandjava.entity.Product;
import com.aadiandjava.repo.ProductRepository;
import com.aadiandjava.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController {
	@Autowired
	ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @GetMapping({"/", "/product-form"})
    public String productForm(Model model) {

        model.addAttribute("product", new Product());

        return "product-form";
    }
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable int id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "update-form"; 
    }
   
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id,
                         RedirectAttributes attribute) {

        productService.deleteById(id);

        attribute.addFlashAttribute("msg", "Product Deleted Successfully");

        return "redirect:/products";
    }
//    @GetMapping("/products")
//    public String allProducts(Model model) {
//
//        List<Product> allProducts = productService.getAllProducts();
//
//        System.out.println("Size = " + allProducts.size());
//
//        model.addAttribute("products", allProducts);
//
//        return "products";
//    }

    @PostMapping("/save")
    public String save(@Valid Product product,
                       BindingResult result,
                       RedirectAttributes attribute) {

        if (result.hasErrors()) {
            return "product-form";
        }

        productService.saveProduct(product);

        attribute.addFlashAttribute(
                "msg",
                "Product Saved Successfully");

        return "redirect:/products";
    }
 @GetMapping("/products")
 public String allProducts(@RequestParam(defaultValue="0") int page,Model model) {

PageRequest of = PageRequest.of(page,5);
 Page<Product> productPage=productRepository.findAll(of);
 model.addAttribute("products", productPage.getContent());
 model.addAttribute("currentPage", page);
 model.addAttribute("totalPages", productPage.getTotalPages());
//   List<Product> allProducts = productService.getAllProducts();
//      System.out.println("Size = " + allProducts.size());
//     model.addAttribute("products", allProducts);
      return "products";

 }
 }