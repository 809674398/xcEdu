package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.CourseMarket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMarketMapper {

    public CourseMarket getCourseMarketById(String courseId);
}
