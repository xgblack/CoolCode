package com.xgblack.cool.domain.customer.gateway;


import com.xgblack.cool.domain.customer.Credit;

//Assume that the credit info is in another distributed Service
public interface CreditGateway {
    Credit getCredit(String customerId);
}
