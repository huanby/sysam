package com.sysam.sysam_generator.service;

import com.sysam.sysam_generator.entity.Column;
import com.sysam.sysam_generator.utils.BaseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ProjectName: sysam
 * @Package: com.sysam.sysam_generator.service
 * @ClassName: GeneratorService
 * @Author: jibl
 * @Description:
 * @Date: 2021/2/4 15:16
 * @Version: 1.0
 */
public interface GeneratorService {
    BaseResult selectTableInfos(String tableName, HttpServletRequest request);

    List<Column> getColumns(String databaseType,  String name);
}
