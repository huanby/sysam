package com.sysam.sysam_generator.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.sysam.sysam_generator.utils.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 代码生成配置表
 * </p>
 *
 * @author jibl
 * @since 2021-02-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_generator_config")
@ApiModel(value="GeneratorConfig对象", description="代码生成配置表")
public class GeneratorConfig extends Model<GeneratorConfig> {

    private static final long serialVersionUID=1L;

    public static final String TRIM_YES = "1";
    public static final String TRIM_NO = "0";

    @ApiModelProperty(value = "主键")
    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "作者")
    @TableField("author")
    private String author;

    @ApiModelProperty(value = "基础包名")
    @TableField("base_package")
    private String basePackage;

    @ApiModelProperty(value = "entity文件存放路径")
    @TableField("entity_package")
    private String entityPackage;

    @ApiModelProperty(value = "mapper文件存放路径")
    @TableField("mapper_package")
    private String mapperPackage;

    @ApiModelProperty(value = "mapper xml文件存放路径")
    @TableField("mapper_xml_package")
    private String mapperXmlPackage;

    @ApiModelProperty(value = "servcie文件存放路径")
    @TableField("service_package")
    private String servicePackage;

    @ApiModelProperty(value = "serviceImpl文件存放路径")
    @TableField("service_impl_package")
    private String serviceImplPackage;

    @ApiModelProperty(value = "controller文件存放路径")
    @TableField("controller_package")
    private String controllerPackage;

    @ApiModelProperty(value = "是否去除前缀 1是 0否")
    @TableField("is_trim")
    private String isTrim;

    @ApiModelProperty(value = "前缀内容")
    @TableField("trim_value")
    private String trimValue;

    /**
     * java文件路径，固定值
     */
    private transient String javaPath = "/src/main/java/";
    /**
     * 配置文件存放路径，固定值
     */
    private transient String resourcesPath = "src/main/resources";
    /**
     * 文件生成日期
     */
    private transient String date = DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULL_TIME_SPLIT_PATTERN);

    /**
     * 表名
     */
    private transient String tableName;
    /**
     * 表注释
     */
    private transient String tableComment;
    /**
     * 数据表对应的类名
     */
    private transient String className;

    private transient boolean hasDate;
    private transient boolean hasBigDecimal;
    private transient List<Column> columns;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
