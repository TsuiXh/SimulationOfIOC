package org.tsui.test;

import org.tsui.spring.BeanFactory;
import org.tsui.spring.ClassPathXmlApplicationContext;
import org.tsui.spring.HelloSpring;

public class Test {
	public static void main(String[] args) {
		//加载配置文件获取bean工厂
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		//通过id获取bean . IOC Inversion of Control 控制反转
		HelloSpring hs = (HelloSpring)factory.getBean("helloSpring");
		hs.sayHello();
	}
}
