package org.smart4j.smartFrameworkDemo.service;

import java.util.List;
import java.util.Map;

import org.smart4j.framework.annotation.Service;
import org.smart4j.smartFrameworkDemo.helper.DatebaseHelper;
import org.smart4j.smartFrameworkDemo.model.Customer;

@Service
public class CustomerService {

    /**
     * 获取客户列表
     * @return
     */
    public List<Customer> getCustomerList() {
        return DatebaseHelper.queryEntityList(Customer.class);
    }

    /**
     * 获取客户
     * @param id
     * @return
     */
    public Customer getCustomer(long id){

        return  DatebaseHelper.queryEntity(Customer.class,id);
    }

    /**
     *
     * @param filedMap
     * @return
     */
    public boolean createCustomer( Map<String ,Object>filedMap){
        return DatebaseHelper.insertEntity(Customer.class,filedMap);
    }

    /**
     *
     * @param filedMap
     * @return
     */
    public boolean updateCustomer( Map<String ,Object>filedMap,long id){
        return DatebaseHelper.updateEntity(Customer.class,filedMap,id);
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean deleteCustomer( long id){
        return DatebaseHelper.deleteEntity(Customer.class,id);
    }
}
