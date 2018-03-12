package edu.mum.coffee.controller;

import com.google.common.base.Preconditions;
import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value="", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Order addOrder(@RequestBody Order order){
        Preconditions.checkNotNull(order);
        return orderService.save(order);
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public List<Order> getOrders(){
        return orderService.findAll();
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable int id){
        return orderService.findById(id);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateOrder(@PathVariable int id, @RequestBody Order order){
        Preconditions.checkNotNull(order);
        Preconditions.checkNotNull(orderService.findById(id));
        if(id == order.getId()) {
            orderService.save(order);
        }
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable int id){
        Order order = orderService.findById(id);
        Preconditions.checkNotNull(order);
        orderService.delete(order);
    }
}
