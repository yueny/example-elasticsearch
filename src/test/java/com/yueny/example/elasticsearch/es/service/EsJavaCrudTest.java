package com.yueny.example.elasticsearch.es.service;

import com.yueny.example.elasticsearch.Bootstrap;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Bootstrap.class})
//@TestPropertySource("classpath:/application.test.properties")
@ImportResource(locations = { "classpath:/config/spring-properties.xml" })
public class EsJavaCrudTest {
    @Autowired
    private ICrud crud;

    @Test
    public void query() {
        String jsonData = crud.query("bank", "account", "25");
        System.out.println(jsonData);

        //SearchQuery
    }

}
