package com.xuecheng.manage_cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCmsPage {

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Test
    public void test1(){
        List<CmsPage> all = cmsPageRepository.findAll();
        System.out.println(all);
    }

    @Test
    public void test2(){
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page,size);
        Page<CmsPage> pageCms = cmsPageRepository.findAll(pageable);
        System.out.println(pageCms);

    }
    @Test
    public void test03(){
        //先查询
        Optional<CmsPage> optional = cmsPageRepository.findById("5ae1973b0e6618644cd7a6fa");
        if (optional.isPresent()){
            CmsPage cmsPage = optional.get();
            cmsPage.setPageAliase("课程预览页面11");
            cmsPageRepository.save(cmsPage);
            System.out.println(cmsPage);
        }
    }
    @Test
    public void test04(){
        CmsPage cmsPage = cmsPageRepository.findByPageName("10101.html");
        System.out.println(cmsPage);
    }

    @Test
    public void test05() {
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page,size);
        CmsPage cmsPage = new CmsPage();
        cmsPage.setPageAliase("课程预览页面");
        //条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        exampleMatcher = exampleMatcher.withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains());
        Example example = Example.of(cmsPage,exampleMatcher);
        Page all = cmsPageRepository.findAll(example, pageable);
        System.out.println(all);


    }
}
