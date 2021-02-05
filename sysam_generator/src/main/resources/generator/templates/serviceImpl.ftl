package ${basePackage}.${serviceImplPackage};

import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${mapperPackage}.${className}Mapper;
import ${basePackage}.${servicePackage}.I${className}Service;
ort com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ${tableComment} Service实现
 *
 * @author ${author}
 * @date ${date}
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ${className}ServiceImpl extends ServiceImpl<${className}Mapper, ${className}> implements I${className}Service {

    @Autowired
    ${className}Mapper ${className?uncap_first}Mapper;

    @Override
    public IPage<${className}> ${className?uncap_first}PageList(${className} ${className?uncap_first}, HttpServletRequest request) {
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        IPage<${className}> jobLogPage = new Page<>(currentPage, pageSize);
        QueryWrapper<${className}> queryWrapper = new QueryWrapper<${className}>();
        // TODO 设置查询条件
        jobLogPage = ${className?uncap_first}Mapper.selectPage(jobLogPage, queryWrapper);
        return jobLogPage;
    }

    @Override
    public int insert(${className} ${className?uncap_first}) {
        return ${className?uncap_first}Mapper.insert(${className?uncap_first});
    }

    @Override
    public int delete(${className} ${className?uncap_first}) {
        LambdaQueryWrapper<${className}> wrapper = new LambdaQueryWrapper<>();
        // TODO 设置删除条件
        return ${className?uncap_first}Mapper.deleteById(wrapper);
    }

    @Override
    public int updateInfo(${className} ${className?uncap_first}) {
        QueryWrapper<${className}> queryWrapper = new QueryWrapper<${className}>();
        // TODO 设置修改条件
        return ${className?uncap_first}Mapper.update(${className?uncap_first},queryWrapper);
    }
}
