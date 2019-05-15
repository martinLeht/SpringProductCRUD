package com.mithrandir.springcrud.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mithrandir.springcrud.entity.Product;
import com.mithrandir.springcrud.entity.ProductDetail;
import com.mithrandir.springcrud.service.ProductService;

@Controller
public class ProductsController {

	@Autowired
	private ProductService productService;

	@Value("#{${category.names}}")
	private Map<String, String> categoryOptions;

	@Value("#{${country.names}}")
	private Map<String, String> countryOptions;

	// initbinder to convert trim input strings
	// remove leading and trailing whitespace for validation

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

	}

	@GetMapping("/")
	public String showProducts(Model model) {

		// get customers from db
		List<Product> products = productService.getProducts();

		// add customers to mvc model
		model.addAttribute("products", products);

		return "index";
	}

	@GetMapping("/add")
	public String getAddForm(Model model) {

		// create model attribute to bind all form data
		Product product = new Product();
		ProductDetail productDetail = new ProductDetail();

		// associating product and product details
		product.setProductDetail(productDetail);
		product.setCreateDateTime(new Date());
		
		// Set creation datetime
		//product.setCreateDateTime(new Date());
		
		System.out.println("Product ===> " + product);
		System.out.println("Details ===> " + product.getProductDetail());

		model.addAttribute("categoryMap", categoryOptions);
		model.addAttribute("countryMap", countryOptions);
		model.addAttribute("product", product);

		return "product-form";
	}

	@GetMapping("/updateProduct")
	public String getUpdateForm(@RequestParam("productId") int productId, Model model) {

		// get product from db
		Product product = productService.getProduct(productId);
		
		System.out.println("Product ===> " + product);
		System.out.println("Details ===> " + product.getProductDetail());
		
		

		// set product as a model to pre-populate the form
		model.addAttribute("categoryMap", categoryOptions);
		model.addAttribute("countryMap", countryOptions);
		model.addAttribute("product", product);

		return "product-form";
	}

	@GetMapping("/delete")
	public String deleteProduct(@RequestParam("productId") int productId, Model model) {

		productService.deleteProduct(productId);

		return "redirect:/";
	}

	@PostMapping("/save")
	public String saveOrUpdate(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult,
			Model model) {

		// if result set has errors, return to product form with errors
		if (bindingResult.hasErrors()) {
			model.addAttribute("categoryMap", categoryOptions);
			model.addAttribute("countryMap", countryOptions);
			return "product-form";
		} else {

			System.out.println("Product ===> " + product);
			System.out.println("Details ===> " + product.getProductDetail());
			
			// calculate value in stock to product before saving
			product.setValueInStock();

			product.setUpdateDateTime(new Date());
			productService.saveProduct(product);

			return "redirect:/";
		}

	}

	@PostMapping("/search")
	public String searchProducts(@RequestParam("searchName") String productName, Model model) {

		List<Product> searchResult = productService.searchProducts(productName);

		model.addAttribute("products", searchResult);

		return "index";
	}

	@GetMapping("/view/{id}")
	public String viewProduct(@PathVariable("id") int id, Model model) {

		Product product = productService.getProduct(id);

		model.addAttribute("product", product);

		return "product-card";
	}

	@GetMapping("/edit/{id}")
	public String getUpdateProductForm(@PathVariable("id") int id, Model model) {

		Product product = productService.getProduct(id);
		System.out.println(product);
		ProductDetail productDetail = product.getProductDetail();
		System.out.println(productDetail);

		model.addAttribute("categoryMap", categoryOptions);
		model.addAttribute("countryMap", countryOptions);
		model.addAttribute("product", product);
		model.addAttribute("productDetail", productDetail);

		return "product-edit";
	}

	@PostMapping("/update")
	public String updateProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("categoryMap", categoryOptions);
			model.addAttribute("countryMap", countryOptions);
			model.addAttribute("productDetail", product.getProductDetail());
			return "product-edit";
		}

		return "redirect:/view/" + product.getId();
	}
	
	@GetMapping("/edit/{id}/general")
	public String getUpdateGeneralForm(@PathVariable("id") int id, Model model) {

		Product product = productService.getProduct(id);

		model.addAttribute("categoryMap", categoryOptions);
		model.addAttribute("countryMap", countryOptions);
		model.addAttribute("product", product);

		return "product-general-edit";
	}

	@PostMapping("/update/general")
	public String updateGeneral(@Valid @ModelAttribute("product") Product product,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "product-general-edit";
		}
		
		return "redirect:/view/" + product.getId();
	}

	@GetMapping("/edit/{id}/details")
	public String getUpdateDetailsForm(@PathVariable("id") int id, Model model) {

		ProductDetail productDetail = productService.getProduct(id).getProductDetail();
		
		System.out.println(productDetail.getProduct());

		model.addAttribute("categoryMap", categoryOptions);
		model.addAttribute("countryMap", countryOptions);
		model.addAttribute("productDetail", productDetail);

		return "product-details-edit";
	}

	@PostMapping("/update/details")
	public String updateDetails(@Valid @ModelAttribute("productDetail") ProductDetail productDetail,
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("categoryMap", categoryOptions);
			model.addAttribute("countryMap", countryOptions);
			return "product-details-edit";
		}
		System.out.println(productDetail.getProduct());
		System.out.println(productDetail.getProduct().getId());
	
		return "redirect:/view/" + productDetail.getProduct().getId();
	}

}
