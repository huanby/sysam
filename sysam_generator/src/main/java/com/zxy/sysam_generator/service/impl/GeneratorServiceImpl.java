package com.zxy.sysam_generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxy.sysam_generator.dao.GeneratorMapper;
import com.zxy.sysam_generator.entity.Column;
import com.zxy.sysam_generator.entity.Table;
import com.zxy.sysam_generator.service.GeneratorService;
import com.zxy.sysam_generator.utils.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: sysam
 * @Package: com.zxy.sysam_generator.service.impl
 * @ClassName: GeneratorServiceImpl
 * @Author: jibl
 * @Description:
 * @Date: 2021/2/4 15:16
 * @Version: 1.0
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Autowired
    GeneratorMapper generatorMapper;

    @Override
    public BaseResult selectTableInfos(String tableName, HttpServletRequest request) {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        Page<Table> page = new Page<>(currentPage, pageSize);
        IPage<Table> data = generatorMapper.selectTableInfos(page, tableName);
        return new BaseResult(data);
    }

    @Override
    public List<Column> getColumns(String databaseType, String tableName) {
        return generatorMapper.getColumns(databaseType, tableName);
    }
}
