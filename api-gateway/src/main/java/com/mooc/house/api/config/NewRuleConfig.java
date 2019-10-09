package com.mooc.house.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;

public class NewRuleConfig {
	
	@Autowired
	private IClientConfig ribbonClientConfig;

	/**
	 * 默认是不发送ping请求
	 * ping url探活检测，默认每十秒检查一次
	 * @param config
	 * @return
	 */
	@Bean
	public IPing ribbonPing(IClientConfig config){
		//第一个参数是否安全敏感的请求，第二个是acruator健康检查端点
		return new PingUrl(false,"/health");
	}
	
	@Bean
	public IRule ribbonRule(IClientConfig config){
//		return new RandomRule();//随机访问
		return new AvailabilityFilteringRule();//智能策略
	}

}
