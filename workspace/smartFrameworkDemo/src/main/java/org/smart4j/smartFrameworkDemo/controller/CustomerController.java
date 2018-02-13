package org.smart4j.smartFrameworkDemo.controller;

import java.util.List;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.View;
import org.smart4j.smartFrameworkDemo.model.Customer;
import org.smart4j.smartFrameworkDemo.service.CustomerService;

@Controller
public class CustomerController {
    @Inject
    private CustomerService service;
    
    /**
     * 进入客户列表界面
     */
    @Action(value = "get:/toCustomerList")
    protected View toCustomerList(Object args) {
        List<Customer> customerList = service.getCustomerList();
        View view = new View("customer.jsp");
        view.addModel("customerList", customerList);
        return view;
    }

    /**
     * 获取客户信息
     */
    @Action(value = "get:/getCustomerData")
    protected Data getCustomerData(Object args) {
        List<Customer> customerList = service.getCustomerList();
        Data data = new Data(customerList);
        return data;
    }

}

