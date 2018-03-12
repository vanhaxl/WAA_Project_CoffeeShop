package edu.mum.coffee.controller;

import com.google.common.base.Preconditions;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value="", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product){
        Preconditions.checkNotNull(product);
        return productService.save(product);
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public List<Product> getProducts(){
        return productService.getAllProduct();
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable int id){
        return productService.getProduct(id);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@PathVariable int id, @RequestBody Product product){
        Preconditions.checkNotNull(product);
        Preconditions.checkNotNull(productService.getProduct(id));
        if(id == product.getId()) {
            productService.save(product);
        }
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable int id){
        Product product = productService.getProduct(id);
        Preconditions.checkNotNull(product);
        productService.delete(product);
    }
}
