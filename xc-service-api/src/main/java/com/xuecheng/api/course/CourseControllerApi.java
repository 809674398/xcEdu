package com.xuecheng.api.course;

import com.xuecheng.framework.domain.cms.response.CoursePublishResult;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.framework.domain.course.ext.CourseView;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="课程查询接口",description = "课程管理接口，提供课程的管理、查询接口")
public interface CourseControllerApi {

    @ApiOperation("课程计划查询")
    public TeachplanNode  findTeachplanList(String couseId);

    @ApiOperation("课程计划添加")
    public ResponseResult addTeachplan(Teachplan teachplan);

    @ApiOperation("课程基本信息添加")
    public ResponseResult addCoursebase(CourseBase courseBase);

    @ApiOperation("课程分页查询")
    public QueryResponseResult findCourseListPage(Integer page,Integer size);

    @ApiOperation("课程信息查询")
    public CourseBase findCourseByCourseId(String courseId);

    @ApiOperation("课程信息修改")
    public ResponseResult updateCourse(String courseId,CourseBase courseBase);

    @ApiOperation("课程营销查询")
    public CourseMarket getCourseMarketById(String courseId);

    @ApiOperation("课程视图查询")
    public CourseView courseview(String id);

    @ApiOperation("预览课程")
    public CoursePublishResult preview(String id);

    @ApiOperation("发布页面")
    public CoursePublishResult  publish(String id);

}
