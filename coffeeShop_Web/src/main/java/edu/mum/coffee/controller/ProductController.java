package edu.mum.coffee.controller;

import com.google.common.base.Preconditions;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.model.PersonDTO;
import edu.mum.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;

@Controller
@RequestMapping(value="/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String getProducts(Model model){
        model.addAttribute("products", productService.getAllProduct());
        System.out.println("products");
        return "products";
    }

    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public String getProductsForAdmin(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "admin_products";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddNewProductForm(Model model) {
        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);
        return "addProduct";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct, BindingResult result, HttpServletRequest request) {

        if(result.hasErrors()) {
            return "addProduct";
        }

        String[] suppressedFields = result.getSuppressedFields();
        if (suppressedFields.length > 0) {
            throw new RuntimeException("Attempting to bind disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        MultipartFile productImage = newProduct.getProductImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        if (productImage!=null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(rootDirectory+"resources/images/"+ newProduct.getProductName() + ".png"));
                System.out.println("save product image ok " + rootDirectory+"resources/images/"+ newProduct.getProductName() + ".png");
            } catch (Exception e) {
                throw new RuntimeException("Product Image saving failed", e);
            }
        }

        productService.save(newProduct);
        return "redirect:/products";
    }

    @RequestMapping("/productDetail")
    public String getProductById(@RequestParam("id") int id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "product";
    }

    @GetMapping(value = "/update/{id}")
    public String getUpdateProductForm(@PathVariable int id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "updateProduct";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateProduct(@PathVariable int id, @ModelAttribute("product") @Valid Product product, BindingResult result) {
        Preconditions.checkNotNull(product);
        Product updatedProduct = productService.getProduct(id);

        updatedProduct.setProductName(product.getProductName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setProductType(product.getProductType());

        productService.save(updatedProduct);
        return "redirect:/products/admin";
    }


    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public String deleteProduct(@PathVariable int id){

        System.out.println("IN delete product");
        Product product = productService.getProduct(id);
        Preconditions.checkNotNull(product);
        productService.delete(product);
        return "redirect:/";
    }
}
