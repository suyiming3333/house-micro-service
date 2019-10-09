package com.mooc.house.api.inteceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConf extends WebMvcConfigurerAdapter {

  @Autowired
  private AuthInterceptor authInterceptor;
  
  @Autowired
  private AuthActionInterceptor authActionInterceptor;
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authInterceptor).excludePathPatterns("/static").addPathPatterns("/**");
    registry
        .addInterceptor(authActionInterceptor)
         .addPathPatterns("/house/toAdd")
        .addPathPatterns("/accounts/profile").addPathPatterns("/accounts/profileSubmit")
        .addPathPatterns("/house/bookmarked").addPathPatterns("/house/del")
        .addPathPatterns("/house/ownlist").addPathPatterns("/house/add")
        .addPathPatterns("/house/toAdd").addPathPatterns("/agency/agentMsg")
        .addPathPatterns("/comment/leaveComment").addPathPatterns("/comment/leaveBlogComment");
    
    super.addInterceptors(registry);
  }

  /**
   * 全局跨域配置
   * @param registry
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("*")//放行那些域名这里是所有
            .allowCredentials(true)//是否允许发送cookies
            .allowedMethods("GET","PUT","POST","DELETE")//放行的方法
            .allowedHeaders("*")//用于预检请求，放行那些原始域
            .exposedHeaders("Header1","header2");//暴露那些头部信息
  }
}
