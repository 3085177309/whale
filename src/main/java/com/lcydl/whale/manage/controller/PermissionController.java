package com.lcydl.whale.manage.controller;

import com.lcydl.whale.common.pojo.Permission;
import com.lcydl.whale.common.util.Page;
import com.lcydl.whale.common.util.R;
import com.lcydl.whale.manage.pojo.PermissionMessage;
import com.lcydl.whale.manage.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("")
    public String article(){
        //查询所有文章类别

        return "system/permission";
    }

    @GetMapping("/list")
    @ResponseBody
    public PermissionMessage getList(Page page){
        return permissionService.list(page);
    }

    @PostMapping("/list")
    @ResponseBody
    public List<Permission> postList(){
        return permissionService.list();
    }

    /**
     * 保存
     * @param permission
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public R save(Permission permission){
        return permissionService.save(permission);
    }

    /**
     * 获取
     * @return
     */
    @GetMapping("/edit/{id}")
    @ResponseBody
    public Permission edit(@PathVariable("id") Long id){
        return permissionService.get(id);
    }

    /**
     * 编辑
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public R update(Permission permission){
        return permissionService.update(permission);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Long id){
        return permissionService.delete(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/batch")
    @ResponseBody
    public R deleteBatch(@RequestParam("ids[]") Long[] ids){
        return permissionService.deleteBatch(ids);
    }

}
