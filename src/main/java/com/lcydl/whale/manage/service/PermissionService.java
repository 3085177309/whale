package com.lcydl.whale.manage.service;

import com.lcydl.whale.common.pojo.Permission;
import com.lcydl.whale.common.util.Page;
import com.lcydl.whale.common.util.R;
import com.lcydl.whale.manage.pojo.PermissionMessage;

import java.util.List;

public interface PermissionService {

    PermissionMessage list(Page page);

    /*PermissionMessage listParent();*/

    List<Permission> listParent();

    List<Permission> list();

    R save(Permission permission);

    Permission get(Long id);

    R update(Permission permission);

    R delete(Long id);

    R deleteBatch(Long[] ids);


}
