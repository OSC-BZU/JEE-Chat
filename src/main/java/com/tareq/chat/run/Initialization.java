/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tareq.chat.run;

import com.tareq.chat.utils.EntityManagerFactoryWrapper;
import java.util.Date;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 *
 * @author tareq
 */
@WebListener
public class Initialization implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        EntityManagerFactoryWrapper.create();
        System.out.println(new Date().toString() + " : Application Launched");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EntityManagerFactoryWrapper.destroy();
        System.err.println(new Date().toString() + " : Application Shutdown");
    }
    
}
