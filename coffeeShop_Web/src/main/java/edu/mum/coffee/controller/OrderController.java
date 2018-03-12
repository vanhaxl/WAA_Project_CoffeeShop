package edu.mum.coffee.controller;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/orders")
@SessionAttributes({"myOrder"})
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    PersonService personService;

    @Autowired
    ProductService productService;

    @RequestMapping(method=RequestMethod.GET)
    public String getOrders(ModelMap modelMap){
        return "cart";
    }

    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public String getOrdersForAdmin(Model model){
        model.addAttribute("orders", orderService.findAll());
        return "admin_orders";
    }

    @RequestMapping(value="/checkout")
    public String checkout(ModelMap modelMap){
        Order order = this.getCurrentOrder(modelMap);
        Date date = new Date();
        order.setOrderDate(date);
        String email = getPrincipal();
        Person person = personService.findByEmail(email);
        order.setPerson(person);
        orderService.save(order);
        modelMap.addAttribute("order", order);
        modelMap.addAttribute("myOrder", new Order());//clear cart in session
        return "thankCustomer";
    }

    @RequestMapping(value = "/update/{orderId}", method = RequestMethod.PUT)
    public void update(@PathVariable(value = "orderId") int orderId, Order order) {
        if (orderId == order.getId()) {
            orderService.save(order);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(ModelMap modelMap) {
        System.out.println("hihi, vo ham delete" + this.getCurrentOrder(modelMap).getId());
        orderService.delete(this.getCurrentOrder(modelMap));
        modelMap.addAttribute("myOrder", new Order());
    }

    @RequestMapping(value="/add/{productId}", method=RequestMethod.GET)
    public String addProduct(@PathVariable("productId") int productId, ModelMap modelMap) {
        Order myOrder = this.getCurrentOrder(modelMap);
        boolean alreadyExist = false;

        for(Orderline orderline: myOrder.getOrderLines()){
            if(orderline.getProduct().getId() == productId){
                orderline.setQuantity(orderline.getQuantity()+1);
                alreadyExist = true;
            }
        }
        if(alreadyExist == false){
            Product product = productService.getProduct(productId);
            Orderline orderLine = new Orderline();
            orderLine.setProduct(product);
            orderLine.setOrder(myOrder);
            orderLine.setQuantity(1); // default
            myOrder.addOrderLine(orderLine);
        }
        addOrderToSession(modelMap, myOrder);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeProduct(@PathVariable int productId, ModelMap modelMap) {
        System.out.println("vo ham remove 1 order");
        Order myOrder = this.getCurrentOrder(modelMap);
        System.out.println("vo ham remove 1 order " + myOrder.getId());
        List<Orderline> orderlineList = myOrder.getOrderLines();
        for(int i = 0; i< orderlineList.size(); i++){
            if(orderlineList.get(i).getProduct().getId() == productId){
                myOrder.removeOrderLine(orderlineList.get(i));
            }
        }
        addOrderToSession(modelMap, myOrder);
    }

    public Order getCurrentOrder(ModelMap map) {
        Order myOrder = (Order)map.get("myOrder");
        if (myOrder == null) {
            myOrder = new Order();
            map.addAttribute("myOrder", myOrder);
        }
        return myOrder;
    }

    public void addOrderToSession(ModelMap map, Order order) {
        map.addAttribute("myOrder", order);
    }
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
