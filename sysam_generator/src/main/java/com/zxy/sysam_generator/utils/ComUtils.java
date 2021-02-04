package com.zxy.sysam_generator.utils;

/**
 * @ProjectName: sysam
 * @Package: com.zxy.sysam_generator.utils
 * @ClassName: ComUtils
 * @Author: jibl
 * @Description:
 * @Date: 2021/2/4 16:56
 * @Version: 1.0
 */
public class ComUtils {
    /**
     * 下划线转驼峰
     *
     * @param value 待转换值
     * @return 结果
     */
    public static String underscoreToCamel(String value) {
        StringBuilder result = new StringBuilder();
        String[] arr = value.split("_");
        for (String s : arr) {
            result.append((String.valueOf(s.charAt(0))).toUpperCase()).append(s.substring(1));
        }
        return result.toString();
    }
}
