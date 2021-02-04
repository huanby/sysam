package com.zxy.sysam_generator.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxy.sysam_generator.entity.Column;
import com.zxy.sysam_generator.entity.Table;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: sysam
 * @Package: com.zxy.sysam_generator.dao
 * @ClassName: GeneratorMapper
 * @Author: jibl
 * @Description:
 * @Date: 2021/2/4 15:17
 * @Version: 1.0
 */
public interface GeneratorMapper {

    IPage<Table> selectTableInfos(Page<Table> page, String tableName);

    List<Column> getColumns(String databaseType, String tableName);
}
