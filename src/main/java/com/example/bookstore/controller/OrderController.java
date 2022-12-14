package com.example.bookstore.controller;

import com.example.bookstore.dto.OrderDto;
import com.example.bookstore.dto.ResponseDto;
import com.example.bookstore.model.Order;
import com.example.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    /**
     * Implemented orderPost method add the order details
     * @param orderDto-as request body
     * @return
     */
    @PostMapping("/postorder")
    public ResponseEntity<ResponseDto> orderPost(@RequestBody OrderDto orderDto){
        Order order=orderService.placeOrder(orderDto);
        ResponseDto responseDto=new ResponseDto("saved data successful",order);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    /**
     *Implemented getOrderList method by getting all order details
     * @return
     */
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> getOrderList(){
        List<Order> orderList=orderService.getAllOrders();
        ResponseDto responseDto=new ResponseDto("get all orders",orderList);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    /**
     *Implemented getOrderById method to list all orders which is placed through order id
     * @param orderId-as path variable
     * @return
     */
    @GetMapping("/ById/{orderId}")
    public ResponseEntity<ResponseDto> getOrderById(@PathVariable Long orderId){
        Order order=orderService.getOrderById(orderId);
        ResponseDto responseDto=new ResponseDto("get order by id: "+orderId+" successfully",order);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    /**
     *Implemented orderCancel method to canceling the order through boolean value
     * @param orderId-as path variable
     * @return
     */
    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<ResponseDto> orderCancel(@PathVariable Long orderId){
        Order order=orderService.cancelOrder(orderId);
        ResponseDto responseDto=new ResponseDto("order cancelled id: "+orderId+" done",order);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
}