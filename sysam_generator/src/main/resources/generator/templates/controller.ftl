package ${basePackage}.${controllerPackage};

import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${servicePackage}.I${className}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * ${tableComment} Controller
 *
 * @author ${author}
 * @date ${date}
 */
@Api(tags = "${tableComment}")
@Controller
@ResponseBody
public class ${className}Controller {

    @Autowired
    private I${className}Service ${className?uncap_first}Service;


    @ApiOperation("${tableComment}查询")
    @GetMapping("${className?uncap_first}/list")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:list")
    public List<${className}> ${className?uncap_first}List(${className} ${className?uncap_first}) {
        return ${className?uncap_first}Service.list();
    }

    @ApiOperation("${tableComment}分页查询")
    @GetMapping("${className?uncap_first}/pageList")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:pageList")
    public IPage<${className}> ${className?uncap_first}PageList(${className} ${className?uncap_first}, HttpServletRequest request) {
        return ${className?uncap_first}Service.${className?uncap_first}PageList(${className?uncap_first},request);
    }

    @ApiOperation("${tableComment}添加")
    @PostMapping("${className?uncap_first}")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:add")
    public int add${className}(@Valid ${className} ${className?uncap_first}) {
        return this.${className?uncap_first}Service.insert(${className?uncap_first});
    }

    @ApiOperation("${tableComment}删除")
    @GetMapping("${className?uncap_first}/delete")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:delete")
    public int delete${className}(${className} ${className?uncap_first}) {
        return this.${className?uncap_first}Service.delete(${className?uncap_first});
    }

    @ApiOperation("${tableComment}修改")
    @PostMapping("${className?uncap_first}/update")
    @ResponseBody
    @RequiresPermissions("${className?uncap_first}:update")
    public int update${className}(${className} ${className?uncap_first}) {
        return this.${className?uncap_first}Service.updateInfo(${className?uncap_first});
    }

}
