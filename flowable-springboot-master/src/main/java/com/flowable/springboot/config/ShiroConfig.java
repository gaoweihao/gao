package com.flowable.springboot.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(1024);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    @Bean("userRealm")
    public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher")
                                       HashedCredentialsMatcher matcher) {

        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }

    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager")
                                                     DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置 SecurityManager
//        bean.setSecurityManager(securityManager);
//        // 设置登录成功跳转Url
//        bean.setSuccessUrl("/main");
//        // 设置登录跳转Url
//        bean.setLoginUrl("/toLogin");
//        // 设置未授权提示Url
//        bean.setUnauthorizedUrl("/error/unAuth");
//
//        /**
//         * anon：匿名用户可访问
//         * authc：认证用户可访问
//         * user：使用rememberMe可访问
//         * perms：对应权限可访问
//         * role：对应角色权限可访问
//         **/
//        Map<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/login","anon");
//        filterMap.put("/login2","anon");
//        filterMap.put("/user/index","authc");
//        filterMap.put("/vip/index","roles[vip]");
//        filterMap.put("/druid/**", "anon");
//        filterMap.put("/static/**","anon");
//
//        filterMap.put("/**","authc");
//        filterMap.put("/logout", "authc");
//
//        bean.setFilterChainDefinitionMap(filterMap);

        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("demo", new UserFilter());
        bean.setFilters(filterMap);
        bean.setSecurityManager(securityManager);
        //bean.setUnauthorizedUrl("/401");

        Map<String, String> filterRuleMap = new HashMap<>();
        //filterRuleMap.put("/login", "anon");
        filterRuleMap.put("/401", "anon");
        //filterRuleMap.put("/logout", "demo");
        filterRuleMap.put("/api/callback/**", "anon");
         filterRuleMap.put("/api/user/**", "demo");
        filterRuleMap.put("/api/expense/**", "demo");

        filterRuleMap.put("/signin", "anon");
        filterRuleMap.put("/signout", "anon");

        bean.setFilterChainDefinitionMap(filterRuleMap);

        return bean;
    }

    /**
     * 注入 securityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(
            HashedCredentialsMatcher hashedCredentialsMatcher) {

        DefaultWebSecurityManager securityManager =
                new DefaultWebSecurityManager();
        // 关联realm.
        securityManager.setRealm(userRealm(hashedCredentialsMatcher));
        return securityManager;
    }
}
