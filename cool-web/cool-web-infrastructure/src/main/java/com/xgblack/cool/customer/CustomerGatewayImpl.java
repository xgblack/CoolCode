package com.xgblack.cool.customer;


import com.xgblack.cool.domain.customer.Customer;
import com.xgblack.cool.domain.customer.gateway.CustomerGateway;

@Component
public class CustomerGatewayImpl implements CustomerGateway {
    @Autowired
    private CustomerMapper customerMapper;

    public Customer getByById(String customerId){
      CustomerDO customerDO = customerMapper.getById(customerId);
      //Convert to Customer
      return null;
    }
}
