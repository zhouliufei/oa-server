package com.fanfan;

import com.fanfan.service.InitService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.fanfan.mapper")
public class Springboot implements CommandLineRunner {
    @Autowired
    private InitService initService;

    public static void main( String[] args )
    {
        SpringApplication.run(Springboot.class,args);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void run(String... args) throws Exception {
        initService.init();
    }
}
