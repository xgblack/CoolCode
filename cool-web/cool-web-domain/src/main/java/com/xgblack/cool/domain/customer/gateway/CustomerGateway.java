package com.xgblack.cool.domain.customer.gateway;


import com.xgblack.cool.domain.customer.Customer;

public interface CustomerGateway {
    Customer getByById(String customerId);
}
