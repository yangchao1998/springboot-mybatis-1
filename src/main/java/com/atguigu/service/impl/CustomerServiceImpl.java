package com.atguigu.service.impl;

import com.atguigu.dao.CustomerDao;
import com.atguigu.domain.Customer;
import com.atguigu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    CustomerDao customerDao;

    @Autowired
    private RedisTemplate redisTemplate; //操作任何类型数据

    @Autowired
    private StringRedisTemplate stringRedisTemplate; //操作String类型

    /**
     * 先查缓存，如果redis中有数据从redis中获取。
     * 如果redis中没有，那么，我们从mysql中获取。并放到redis中，为下次访问数据提供性能优化。
     * @return
     */
    @Override
    public List<Customer> findCustomers() {
        String key = "allcustomer";
        List<Customer> list = (List<Customer>)redisTemplate.boundValueOps(key).get();
        if(list!=null){
            System.out.println("from redis list = " + list);
            return list ;
        }
        list = customerDao.findAll();
        redisTemplate.boundValueOps(key).set(list);
        System.out.println("from mysql list = " + list);
        return list;
    }

    @Override
    public Customer findCustomerById(Integer id) {
        return customerDao.getOne(id);
    }

    @Override
    public void saveCustomer(Customer c) {
        customerDao.save(c); //bean对象OID值为null就进行save操作
    }

    @Override
    public void updateCustomer(Customer c) {
        customerDao.save(c); //bean对象有OID就进行update操作
    }

    @Override
    public void deleteCustomerById(Integer id) {
        customerDao.deleteById(id);
    }
}
