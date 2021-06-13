package com.alwaysup.tracker.api.config;

import com.alwaysup.tracker.api.service.DataPointService;
import com.alwaysup.tracker.api.service.DeviceService;
import com.alwaysup.tracker.api.service.UserService;
import com.alwaysup.tracker.api.service.impl.DataPointServiceImpl;
import com.alwaysup.tracker.api.service.impl.DeviceServiceImpl;
import com.alwaysup.tracker.api.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;


@Configuration
public class BeanConfig {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public UserService getUserService() {
        return new UserServiceImpl();
    }

    public DeviceService getDeviceService() {
        return new DeviceServiceImpl();
    }

    public DataPointService getDataPointService() {
        return new DataPointServiceImpl();
    }
}
