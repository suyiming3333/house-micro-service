package com.mooc.house.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mooc.house.api.config.NewRuleConfig;




@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@Controller
//单独使用ribbOnClient发送请求
//@RibbonClient(name="user",configuration=NewRuleConfig.class)//使用负载均衡的策略
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	//服务发现客户端
	@Autowired
	private DiscoveryClient discoveryClient;

	/**
	 * 返回某个服务的信息
	 * @return
	 */
	@RequestMapping("index1")
	@ResponseBody
	public List<ServiceInstance> getReister(){
	  return discoveryClient.getInstances("user");
	}
	
}	
	
	
