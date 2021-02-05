package ${basePackage}.${servicePackage};

import ${basePackage}.${entityPackage}.${className};

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import java.util.List;

/**
 * ${tableComment} Service接口
 *
 * @author ${author}
 * @date ${date}
 */
public interface I${className}Service extends IService<${className}> {
    /**
    * 查询（分页）
    *
    * @param request QueryRequest
    * @param ${className?uncap_first} request
    * @return IPage<${className}>
    */
    IPage<${className}> ${className?uncap_first}PageList(${className} ${className?uncap_first}, HttpServletRequest request);

    /**
    * 新增
    *
    * @param ${className?uncap_first}
    */

    int insert(${className} ${className?uncap_first});
    /**
    * 修改
    *
    * @param ${className?uncap_first}
    */
    int updateInfo(${className} ${className?uncap_first});
    /**
    * 删除
    *
    * @param ${className?uncap_first}
    */
    int delete(${className} ${className?uncap_first});
  
}
