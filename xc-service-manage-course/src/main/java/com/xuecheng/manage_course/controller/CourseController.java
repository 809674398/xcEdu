package com.xuecheng.manage_course.controller;

import com.xuecheng.api.course.CourseControllerApi;
import com.xuecheng.framework.domain.cms.response.CoursePublishResult;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.framework.domain.course.ext.CourseView;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_course.dao.CourseBaseRepository;
import com.xuecheng.manage_course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController implements CourseControllerApi {

    @Autowired
    CourseService courseService;



    /**
     * 根据Id查询
     * @param couseId
     * @return
     */
    @Override
    @GetMapping("/teachplan/list/{courseId}")
    public TeachplanNode findTeachplanList(@PathVariable("courseId") String couseId) {
        return courseService.findTeachplanList(couseId);
    }

    /**
     * 分页查询
     * @param
     * @return
     */
    @Override
    @GetMapping("/coursebase/list/{page}/{size}")
    public QueryResponseResult findCourseListPage(@PathVariable("page") Integer page,@PathVariable("size") Integer size) {
        return courseService.findCourseListPage(page,size);
    }

    /**
     * 查询课程基本信息
     * @param courseId
     * @return
     */
    @Override
    @GetMapping("/coursebase/find/{courseId}")
    public CourseBase findCourseByCourseId(@PathVariable("courseId") String courseId) {
        return courseService.findCourseByCourseId(courseId);
    }

    /**
     * 修改课程基本信息
     * @param courseId
     * @param courseBase
     * @return
     */
    @Override
    @PostMapping("/coursebase/update/{courseId}")
    public ResponseResult updateCourse(@PathVariable("courseId") String courseId, @RequestBody CourseBase courseBase) {
        return courseService.updateCourse(courseId,courseBase);
    }

    /**
     * 课程营销查询
     * @param courseId
     * @return
     */
    @Override
    @GetMapping("/courseMarket/find/{courseId}")
    public CourseMarket getCourseMarketById(@PathVariable("courseId") String courseId) {
        return courseService.getCourseMarketById(courseId);
    }

    /**
     * 查询课程详情页信息
     * @param id
     * @return
     */
    @Override
    @GetMapping("/courseview/{id}")
    public CourseView courseview(@PathVariable("id") String id) {
        return courseService.courseview(id);
    }

    /**
     * 预览页面的方法
     * @param id
     * @return
     */
    @Override
    @PostMapping("/preview/{id}")
    public CoursePublishResult preview(@PathVariable("id") String id) {
        return courseService.preview(id);
    }

    /**
     * 页面发布
     * @param id
     * @return
     */
    @Override
    @PostMapping("/publish/{id}")
    public CoursePublishResult publish(@PathVariable("id") String id) {
        return courseService.publish(id);
    }


    /**
     * 添加课程计划
     * @param teachplan
     * @return
     */
    @Override
    @PostMapping("/teachplan/add")
    public ResponseResult addTeachplan(@RequestBody Teachplan teachplan) {
        return courseService.addTeachplan(teachplan);
    }

    /**
     * 课程基本信息添加
     * @param courseBase
     * @return
     */
    @Override
    @PostMapping("/coursebase/add")
    public ResponseResult addCoursebase(@RequestBody CourseBase courseBase) {
        return courseService.addCoursebase(courseBase);
    }


}
