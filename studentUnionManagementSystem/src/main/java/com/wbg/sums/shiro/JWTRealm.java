package com.wbg.sums.shiro;

import com.wbg.sums.dao.GeUserPermissionsAssociateMapper;
import com.wbg.sums.dao.RolesMapper;
import com.wbg.sums.dto.GetByRoleIdGetPermissions;
import com.wbg.sums.entity.MemberInformation;
import com.wbg.sums.entity.Roles;
import com.wbg.sums.service.LoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author: WBG
 * @description: 自定义Realm
 * @date: 2019/02/18 10:10
 */
@Service
public class JWTRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LoginService loginService;
	@Autowired
	private RolesMapper rolesMapper;
	@Autowired
	private GeUserPermissionsAssociateMapper geUserPermissionsAssociateMapper;

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String mUser = JWTUtil.getUsername(principals.toString());
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		//获取用户信息
        MemberInformation user = loginService.getByMUser(mUser);
        //用户的角色
		Roles roles = rolesMapper.selectByPrimaryKey(user.getrId());
		authorizationInfo.addRole(roles.getrName());
        //角色下的权限
		List<GetByRoleIdGetPermissions> list = geUserPermissionsAssociateMapper.getBuRoleIdGetPermissions(roles.getrId());
		for (GetByRoleIdGetPermissions getByRoleIdGetPermissions : list) {
			authorizationInfo.addStringPermission(getByRoleIdGetPermissions.getPermissions().getName());
		}
		return authorizationInfo;
	}

    /**
     * 使用JWT替代原生Token
     * 须在此方法中显式的进行替换，否则在进行判断时会一直失败
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

	/**
	 *
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)  {
		//获取密钥
		String token = (String) authenticationToken.getPrincipal();
		// 解密获得username，用于和数据库进行对比
		String username = JWTUtil.getUsername(token);
        if(username == null){
            //没找到token
			return null;
            //throw new UnknownAccountException("密钥无效");
        }
		//从数据库查询是否存在
		MemberInformation geUser = loginService.getByMUser(username);
		if(geUser == null){
			//没找到帐号
			return null;
			//throw new UnknownAccountException("账号不存在");
		}

        if (! JWTUtil.verify(token, username, geUser.getmPassword())) {
			return null;
            //throw new AuthenticationException("账号密码有误");
        }

		return new SimpleAuthenticationInfo(token,token,getName());
	}

}
