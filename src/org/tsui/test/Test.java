package org.tsui.test;

import org.tsui.spring.BeanFactory;
import org.tsui.spring.ClassPathXmlApplicationContext;
import org.tsui.spring.HelloSpring;

public class Test {
	public static void main(String[] args) {
		//���������ļ���ȡbean����
		BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		//ͨ��id��ȡbean . IOC Inversion of Control ���Ʒ�ת
		HelloSpring hs = (HelloSpring)factory.getBean("helloSpring");
		hs.sayHello();
	}
}
