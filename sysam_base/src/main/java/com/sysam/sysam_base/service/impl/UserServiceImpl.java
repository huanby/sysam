package com.sysam.sysam_base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sysam.sysam_base.entity.User;
import com.sysam.sysam_base.dao.UserMapper;
import com.sysam.sysam_base.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sysam.sysam_base.wrapper.UserQueryWrapper;
import com.sysam.sysam_common.utils.PageUtil;
import com.sysam.sysam_common.utils.PageUtilResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jibl
 * @since 2021-01-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    /**
     * 分页获取用户列表
     *
     * @param userQueryWrapper
     * @param pageUtil
     * @return
     */
    public PageUtilResult<User> userList(UserQueryWrapper userQueryWrapper, PageUtil pageUtil) {
        //初始化查询条件
        IPage<User> page = this.page(new Page<>(pageUtil.getCurrentPage(), pageUtil.getPageSize()), userQueryWrapper);
        //result 返回数据
        PageUtilResult<User> result = new PageUtilResult<>();
        //返回结果
        result.setRows(page.getRecords());
        result.setTotal(page.getTotal());
        return result;
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @Override
    public boolean userUpdate(User user) {
        //修改用户信息
        return this.updateById(user);
    }


    /**
     * 通过用户ID删除用户信息
     *
     * @param id
     * @return
     */
    @Override
    public boolean userDelete(Integer id) {
        return this.removeById(id);
    }

}
