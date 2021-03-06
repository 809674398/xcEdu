package com.xuecheng.manage_course.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.framework.domain.course.ext.CourseInfo;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRibbon {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    TeachplanMapper teachplanMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    CourseMarketMapper courseMarketMapper;

    @Test
    public void testRibbon(){
       //确定要获取的服务名
        String serviceId = "XC-SERVICE-MANAGE-CMS";
        for (int i = 0; i < 10; i++) {
            ResponseEntity<Map> forEntity = restTemplate.getForEntity("http://" + serviceId + "/cms/page/get/5c90d78c5cf59e1c24006b44", Map.class);
            Map map = forEntity.getBody();
            System.out.println(map);
        }



    }



}
