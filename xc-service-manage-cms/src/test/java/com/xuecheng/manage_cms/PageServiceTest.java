package com.xuecheng.manage_cms;

import com.xuecheng.manage_cms.service.PageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PageServiceTest {

   @Autowired
    PageService pageService;

   @Test
   public void test1(){
       String pageHtml = pageService.getPageHtml("5c90d78c5cf59e1c24006b44");
       System.out.println(pageHtml);
   }
}
