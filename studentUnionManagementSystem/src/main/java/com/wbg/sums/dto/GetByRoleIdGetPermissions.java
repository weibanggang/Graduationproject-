package com.wbg.sums.dto;
import com.wbg.sums.entity.GeUserPermissionsAssociate;
import com.wbg.sums.entity.Permissions;

/**
 * @author: WBG
 * @description: 根据角色ID查询权限
 * @date: 2019/02/19 07:20
 */
public class GetByRoleIdGetPermissions {
    private Permissions permissions;
    private GeUserPermissionsAssociate geUserPermissionsAssociate;

    public Permissions getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return "GetByRoleIdGetPermissions{" +
                "Permissions=" + permissions +
                ", geUserPermissionsAssociate=" + geUserPermissionsAssociate +
                '}';
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public GeUserPermissionsAssociate getGeUserPermissionsAssociate() {
        return geUserPermissionsAssociate;
    }

    public void setGeUserPermissionsAssociate(GeUserPermissionsAssociate geUserPermissionsAssociate) {
        this.geUserPermissionsAssociate = geUserPermissionsAssociate;
    }
}
