package com.springboot.project.onlineShop.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import com.springboot.project.onlineShop.model.Product;
import com.springboot.project.onlineShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping(value="/admin")
    @ResponseBody
    public String adminHome()
    {
    	return "Hello World I am in admin";
    }
    
    @RequestMapping(value = "/getAllProducts",method = RequestMethod.GET)
    public ModelAndView getAllProducts() {
   	 List<Product> products = productService.getAllProducts();
   	 return new ModelAndView("productList", "products", products);
    }

    @RequestMapping(value = "/getProductById/{productId}", method = RequestMethod.GET)
    public ModelAndView getProductById(@PathVariable(value = "productId") Long productId) {
   	 Product product = productService.getProductById(productId);
   	 return new ModelAndView("productPage", "product", product);
    }

    @RequestMapping(value = "/admin/delete/{productId}",  method = RequestMethod.GET)
    public String deleteProduct(@PathVariable(value = "productId") Long productId) {
   	Path path = Paths.get("/Users/xxx/products/" + productId + ".jpg");
//Windows Path: Path path = Paths.get("C:\\products\\" + productId + ".jpg");


   	 if (Files.exists(path)) {
   		 try {
   			 Files.delete(path);
   		 } catch (IOException e) {
   			 e.printStackTrace();
   		 }
   	 }

   	 productService.deleteProduct(productId);
   	 return "redirect:/getAllProducts";
    }

	@RequestMapping(value = "/admin/product/addProduct", method = RequestMethod.GET)
	public ModelAndView getProductForm() {

    	return new ModelAndView("addProduct", "productForm", new Product());
	}


    @RequestMapping(value = "/admin/product/addProduct", method = RequestMethod.POST)
	@Transactional
    public String addProduct(@Valid @ModelAttribute(value = "productForm") Product product, BindingResult result) throws IOException {
   	 if (result.hasErrors()) {
   		 return "addProduct";
   	 }
   	 productService.addProduct(product);
   	 MultipartFile image = product.getProductImage();

   	 if (image != null && !image.isEmpty()) {
   		 //TODO: Probably Needs to Store the image on server side
   		 Path path = Paths.get("/Users/shangtingli/Desktop/PROJECT/onlineShop/target/onlineShop/WEB-INF/resource/images/" + image.getOriginalFilename() + ".jpg");


   		 try {
   			 image.transferTo(new File(path.toString()));
   		 } catch (IllegalStateException e) {
   			 e.printStackTrace();
   		 } catch (IOException e) {
   			 e.printStackTrace();
   		 }
   	 }
   	 return "redirect:/getAllProducts";
    }

    @RequestMapping(value = "/admin/product/editProduct/{productId}")
    public ModelAndView getEditForm(@PathVariable(value = "productId") Long productId) {
   	 Product product = productService.getProductById(productId);
   	 ModelAndView modelAndView = new ModelAndView();
   	 modelAndView.setViewName("editProduct");
   	 modelAndView.addObject("editProductObj", product);
   	 modelAndView.addObject("productId", productId);

   	 return modelAndView;
    }

    @RequestMapping(value = "/admin/product/editProduct/{productId}", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute(value = "editProductObj") Product product, @PathVariable(value = "productId") Long productId) {
   	 product.setId(productId);
   	 productService.updateProduct(product);
   	 return "redirect:/getAllProducts";
    }

    @RequestMapping("/getProductsList")
    public @ResponseBody List<Product> getProductsListJson() {
   	 return productService.getAllProducts();
    }
}
