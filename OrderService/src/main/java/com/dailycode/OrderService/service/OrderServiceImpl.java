package com.dailycode.OrderService.service;

import com.dailycode.OrderService.entity.Order;
import com.dailycode.OrderService.exception.CustomException;
import com.dailycode.OrderService.external.client.PaymentService;
import com.dailycode.OrderService.external.client.ProductService;
import com.dailycode.OrderService.external.client.request.PaymentRequest;
import com.dailycode.OrderService.external.client.response.PaymentResponse;
import com.dailycode.OrderService.model.OrderRequest;
import com.dailycode.OrderService.model.OrderResponse;
import com.dailycode.OrderService.repository.OrderRepository;
import com.dailycode.ProductService.model.ProductResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository oRepo;
    @Autowired
    private ProductService productService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public long placeOrder(OrderRequest orderRequest) {

        log.info("Calling the product service and placing the order:{} ", orderRequest );
//        create the order entity and save the data with status
        productService.reduceOrder(orderRequest.getProductId(),orderRequest.getQuantity());
        log.info("order created succesfully");

       Order order = Order.builder().product_id(orderRequest.getProductId())
               .orderDate(Instant.now())
               .amount(orderRequest.getAmount())
               .quantity(orderRequest.getQuantity())
               .orderStatus("Created")
               .build();
        oRepo.save(order);
        log.info("Calling payment service to complete the payment");
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId()).paymentMode(orderRequest.getPaymentMode())
                                .amount(orderRequest.getAmount()).build();
        log.info("following are the payment request details:{}", paymentRequest);
        String orderStatus = null;


        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment done succesfully. changing the order status to placed");
            orderStatus = "PLACED";

        }
        catch (Exception e){
            log.info("Error occured in payment: changing order status to payment failed ");
            orderStatus = "PAYMENT_FAILED";
        }
        order.setOrderStatus(orderStatus);
        oRepo.save(order);

       log.info("order placed succesfully with order Id", order.getId());


        return order.getId();
    }

    @Override
    public OrderResponse getOrder(long orderId) {
        log.info("accessing the order of orderid : {} ", orderId);
        Order order = oRepo.findById(orderId).orElseThrow(()-> new CustomException("order not found for id"+ orderId, "NOT_FOUND", 404));
        log.info("establishing connection with product services to get productname for order id:{} using productId",orderId);
        ProductResponse productResponse = restTemplate.getForObject("http://PRODUCT-SERVICE/product/" + order.getProduct_id(),ProductResponse.class);
        OrderResponse.ProductDetails productDetails = OrderResponse.ProductDetails.builder()
                .productName(productResponse.getProductName())
                .productId(productResponse.getProductId())
                .price(productResponse.getPrice())
                .quantity(productResponse.getQuantity())
                .build();
        log.info("getting payment information from PaymentService");
        PaymentResponse paymentResponse = restTemplate.getForObject("http://PAYMENT-SERVICE/payment/"+ order.getId(), PaymentResponse.class);
        OrderResponse.PaymentDetails paymentDetails = OrderResponse.
                PaymentDetails.builder().
                paymentDate(paymentResponse.getPaymentDate())
                .paymentStatus(paymentResponse.getStatus())
                .paymentId(paymentResponse.getPaymentId())
                .paymentMode(paymentResponse.getPaymentMode())
                .build();

        OrderResponse orderResponse = OrderResponse.builder().orderDate(Instant.now())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .orderStatus(order.getOrderStatus()).amount(order.getAmount()).orderId(order.getId()).build();



        return orderResponse;

    }
}
