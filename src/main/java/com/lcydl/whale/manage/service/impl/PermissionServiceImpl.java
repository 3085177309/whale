package com.lcydl.whale.manage.service.impl;

import com.lcydl.whale.common.mapper.PermissionMapper;
import com.lcydl.whale.common.pojo.Permission;
import com.lcydl.whale.common.pojo.PermissionExample;
import com.lcydl.whale.common.util.Page;
import com.lcydl.whale.common.util.R;
import com.lcydl.whale.common.util.TimeUtil;
import com.lcydl.whale.manage.pojo.PermissionMessage;
import com.lcydl.whale.manage.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PermissionMessage list(Page page) {
        List<Permission> articles = permissionMapper.list(page);
        PermissionExample example = new PermissionExample();
        Integer count = permissionMapper.countByExample(example);
        PermissionMessage articleMessage = new PermissionMessage();
        articleMessage.setCode(0);
        articleMessage.setMsg("");
        articleMessage.setCount(count);
        articleMessage.setData(articles);
        return articleMessage;
    }

    /*@Override
    public PermissionMessage listParent() {
        PermissionExample example = new PermissionExample();
        PermissionExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(0L);
        List<Permission> list = permissionMapper.selectByExample(example);
        Integer count = permissionMapper.countByExample(example);
        PermissionMessage message = new PermissionMessage();
        message.setCode(0);
        message.setMsg("");
        message.setCount(count);
        message.setData(list);
        return message;
    }*/

    @Override
    public List<Permission> listParent() {
        PermissionExample example = new PermissionExample();
        PermissionExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(0L);
        return permissionMapper.selectByExample(example);
    }

    @Override
    public List<Permission> list() {
        PermissionExample example = new PermissionExample();
        return permissionMapper.selectByExample(example);
    }

    @Override
    public R save(Permission permission) {
        try {
            //补全信息
            permission.setCreated(TimeUtil.getFormatNowDate());
            permission.setModified(TimeUtil.getFormatNowDate());
            //存入数据库
            permissionMapper.insertSelective(permission);
            //查询肛插进去的记录
            Permission one = permissionMapper.getPermissionDescLimitOne();
            return R.ok(one);
        } catch (ParseException e) {
            e.printStackTrace();
            return R.error("添加权限失败!");
        }
    }

    @Override
    public Permission get(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public R update(Permission permission) {
        try {
            //补全属性
            permission.setModified(TimeUtil.getFormatNowDate());
            //修改数据库
            permissionMapper.updateByPrimaryKeySelective(permission);
            return R.ok("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("修改权限失败!");
        }
    }

    @Override
    public R delete(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
        return R.ok("删除成功");
    }

    @Override
    public R deleteBatch(Long[] ids) {
        //批量删除
        for (Long id : ids) {
            permissionMapper.deleteByPrimaryKey(id);
        }
        return R.ok("批量删除成功");
    }
}
