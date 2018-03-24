//package com.hui.user_service.common.config.security;
//
//import com.hui.user_service.service.impl.MyUserDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.provider.approval.ApprovalStore;
//import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
//import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
//import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.web.cors.CorsUtils;
//
//@Configuration
//// 启用Spring Security的Web安全支持
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(myUserDetailsService())
//                .passwordEncoder(new Md5PasswordEncoder());
//    }
//
//    //注册UserDetailsService 的bean
//    @Bean
//    UserDetailsService customUserService(){
//        return new CustomUserService();
//    }
//
//    @Bean
//    public MyUserDetailService myUserDetailsService(){
//        return new MyUserDetailService();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //关闭CSRF
//        http
//                .csrf().disable()
//                .anonymous().disable()
//                .authorizeRequests()
//                .antMatchers("/oauth/token").permitAll();
//
//        http
//                // 添加不需要身份验证的url
//                .authorizeRequests()
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()//就是这一行啦
//                .antMatchers(HttpMethod.OPTIONS,"/api/*", "/home", "/save", "/api/web/user/login", "/api/web/user/getUsers")
//                .permitAll()
//                .anyRequest().authenticated()
//                .and()
//                // 指定登陆页面
//                .formLogin().loginPage("/api/web/user/login_page")
//                .usernameParameter("name").passwordParameter("password")
//                .defaultSuccessUrl("/api/web/user/login_page")
//                .permitAll()
//                .and()
//                // 注销url 和 注销后跳转的地址
//                .logout().logoutUrl("/logout").logoutSuccessUrl("/home");
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public TokenStore tokenStore() {
//        return new RedisTokenStore(redisConnection);
//    }
//
//    @Bean
//    @Autowired
//    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
//        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
//        handler.setTokenStore(tokenStore);
//        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
//        handler.setClientDetailsService(clientDetailsService);
//        return handler;
//    }
//
//    @Bean
//    @Autowired
//    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
//        TokenApprovalStore store = new TokenApprovalStore();
//        store.setTokenStore(tokenStore);
//        return store;
//    }
//
//    /**
//     * 添加 UserDetailsService， 实现自定义登录校验
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserService()); //user Details Service验证
//    }
//}