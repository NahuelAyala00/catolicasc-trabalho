package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.bean.Product;
import com.example.demo.service.ProductService;
@Controller
public class HomeController {
	
    @Autowired
    private ProductService productService;
	@GetMapping("/")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		List<Product> produtos = productService.getAllProducts();
		model.addAttribute("produtos", produtos);
		return "index";
	}

}