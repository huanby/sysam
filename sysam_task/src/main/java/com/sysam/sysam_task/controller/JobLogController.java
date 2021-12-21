package com.sysam.sysam_task.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sysam.sysam_task.entity.JobLog;
import com.sysam.sysam_task.service.JobLogService;
import com.sysam.sysam_task.utils.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 调度日志表 前端控制器
 * </p>
 *
 * @author jibl
 * @date 2021 -12-21 20:48:58
 * @since 2021 -02-01
 */
@Api(tags = "定时任务调度日志")
@RestController
@RequestMapping("/joblog")
public class JobLogController {

    /**
     * The Job log service.
     */
    @Autowired
    JobLogService jobLogService;

    /**
     * Select job infos base result.
     *
     * @param request the request
     * @param jobLog  the job log
     * @return the base result
     * @author jibl
     * @date 2021 -12-21 20:48:58
     */
    @ApiOperation("调度日志列表")
    @GetMapping("/list")
    public BaseResult selectJobInfos(HttpServletRequest request, JobLog jobLog) {
        return jobLogService.selectJobLogInfos(request, jobLog);
    }


    /**
     * Batch delete base result.
     *
     * @param params the params
     * @return the base result
     * @author jibl
     * @date 2021 -12-21 20:48:58
     */
    @ApiOperation("调度日志批量删除")
    @PostMapping("/batchDelete")
    public BaseResult batchDelete(@RequestBody String params) {
        JSONObject json = JSONObject.parseObject(params);
        List<String> joblogs = JSON.parseArray(json.getJSONArray("ids").toJSONString(), String.class);
        jobLogService.removeByIds(joblogs);
        return new BaseResult(1);
    }


    /**
     * Delete base result.
     *
     * @param id the id
     * @return the base result
     * @author jibl
     * @date 2021 -12-21 20:48:58
     */
    @ApiOperation("调度日志删除")
    @GetMapping("/delete")
    public BaseResult delete(long id) {
        jobLogService.removeById(id);
        return new BaseResult(1);
    }

}

