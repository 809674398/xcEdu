package com.xuecheng.manage_cms;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GridFsTest {

   @Autowired
    GridFsTemplate gridFsTemplate;

   @Autowired
    GridFSBucket gridFSBucket;

   @Test
   public void test1() throws FileNotFoundException {
       File file = new File("D:\\course.ftl");
        //定义文件字符输入流
       FileInputStream fileInputStream = new FileInputStream(file);
       ObjectId objectId = gridFsTemplate.store(fileInputStream, "course.ftl");
       System.out.println(objectId);
   }

   //取文件
    @Test
    public void queryFile() throws IOException {
        GridFSFile gridFiles = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is("5c937cc95cf59e2504b5cb22")));
        //打开下载流
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFiles.getObjectId());
        //创建resource对象,获取流
        GridFsResource gridFsResource = new GridFsResource(gridFiles,gridFSDownloadStream);
        //从流中获取数据
        String content = IOUtils.toString(gridFsResource.getInputStream(), "utf-8");
        System.out.println(content);
    }
}
