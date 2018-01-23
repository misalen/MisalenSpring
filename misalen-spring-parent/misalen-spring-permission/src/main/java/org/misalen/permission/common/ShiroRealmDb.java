package org.misalen.permission.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.misalen.permission.domain.SysAdmin;
import org.misalen.permission.service.SysAdminService;
import org.misalen.permission.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * shiro 自定义用户认证
 * 
 * @author guochao
 *
 */
@Service
public class ShiroRealmDb extends AuthorizingRealm {

	private static final Logger LOGGER = LogManager.getLogger(ShiroRealmDb.class);
	@Autowired
	private SysAdminService sysAdminService;
	@Autowired
	private SysRoleService sysRoleService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		LOGGER.info("Shiro开始登录认证");
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		if (token == null || token.getUsername() == null) {
			throw new UnsupportedTokenException("身份令牌异常");
		}
		SysAdmin sysAdmin = sysAdminService.getByUsername(token.getUsername());
		if (sysAdmin == null) {
			throw new UnknownAccountException("用户名或密码错误");
		}
		ShiroUser shiroUser = new ShiroUser(sysAdmin.getPrimaryKey(), sysAdmin.getUsername());
		shiroUser.setRoleSet(sysRoleService.findByAdminId(sysAdmin.getPrimaryKey()));
		shiroUser.setNickname(sysAdmin.getNickname());
		// LockedAccountException
		// DisabledAccountException
		return new SimpleAuthenticationInfo(shiroUser, sysAdmin.getLoginPwd(), getName());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		LOGGER.info("Shiro获取用户角色和权限");
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(shiroUser.getRoleSet());
		return info;
	}

	public void updateRole() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		super.clearCachedAuthorizationInfo(principals);
		if(shiroUser!=null){
			shiroUser.setRoleSet(sysRoleService.findByAdminId(shiroUser.getUserId()));
		}
	}
}
