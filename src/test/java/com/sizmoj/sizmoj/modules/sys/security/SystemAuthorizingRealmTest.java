package com.sizmoj.sizmoj.modules.sys.security;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import com.sizmoj.sizmoj.modules.sys.service.UserService;
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class SystemAuthorizingRealmTest extends SpringTransactionalTestCase{
	@Autowired
	private UserService userService;
	private SystemAuthorizingRealm systemAuthorizingRealm;
	//@Before
	public void setUp() {
		systemAuthorizingRealm = new SystemAuthorizingRealm();
	}
	//@Test
	public void testDoGetAuthenticationInfoAuthenticationToken() {
		UsernamePasswordToken authcToken = new UsernamePasswordToken();
		authcToken.setUsername("admin");
		authcToken.setPassword("admin".toCharArray());
		SimpleAuthenticationInfo authenticationInfo = 
				(SimpleAuthenticationInfo) systemAuthorizingRealm.doGetAuthenticationInfo(authcToken);
		System.out.println(authenticationInfo.getPrincipals());
	}

	//@Test
	public void testDoGetAuthorizationInfoPrincipalCollection() {
/*		Object userIdentity = 1L;
		PrincipalCollection principals = new SimplePrincipalCollection(1L, "系统管理员");
		systemAuthorizingRealm.doGetAuthorizationInfo(principals);*/
	}

}
