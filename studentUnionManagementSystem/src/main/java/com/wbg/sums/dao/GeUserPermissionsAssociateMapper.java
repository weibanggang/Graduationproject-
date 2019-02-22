package com.wbg.sums.dao;

import com.wbg.sums.dto.GetByRoleIdGetPermissions;
import com.wbg.sums.entity.GeUserPermissionsAssociate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeUserPermissionsAssociateMapper {
    /**
     * 根据id删除操作
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加操作
     *
     * @param geUserPermissionsAssociate
     * @return
     */
    int insert(GeUserPermissionsAssociate geUserPermissionsAssociate);

    /**
     * 根据id查询操作
     *
     * @param id
     * @return
     */
    GeUserPermissionsAssociate selectByPrimaryKey(Integer id);

    /**
     * 全部查询操作
     *
     * @return
     */
    List<GeUserPermissionsAssociate> selectAll();

    /**
     * 根据id全部修改操作
     *
     * @param geUserPermissionsAssociate
     * @return
     */
    int updateByPrimaryKey(GeUserPermissionsAssociate geUserPermissionsAssociate);

    /**
     * 根据userId查询出用户的所有角色
     * @param roleId
     * @return
     */
    List<GetByRoleIdGetPermissions> getBuRoleIdGetPermissions(int roleId);
}