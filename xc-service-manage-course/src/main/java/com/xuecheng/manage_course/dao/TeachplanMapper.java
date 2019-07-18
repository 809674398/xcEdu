package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface TeachplanMapper  {

    /**
     * 查询teachplanList的方法
     * @param courseId
     * @return
     */
    public TeachplanNode findTeachplanList(String courseId);
}
