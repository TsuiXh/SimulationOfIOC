package org.tsui.spring;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class ClassPathXmlApplicationContext implements BeanFactory{

	private Map<String, Object> beans = new HashMap<>();
	
	public ClassPathXmlApplicationContext(String fileName) {
		try {
			File file = new File("src/" + fileName);
			
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);
			//��ȡ���е�bean�ڵ㣨ÿһ��bean�ڵ����һ������
			List<Node> nodes = doc.selectNodes("beans/bean");
			for (Node node:nodes) {
				String key = node.valueOf("@id");
				String className = node.valueOf("@class");
				Class<?> clz = Class.forName(className);
				Object obj = clz.newInstance();
				List<Node> properties = node.selectNodes("property");
				for (Node temp: properties) {
					//��ȡ����ֵ
					String pname = temp.valueOf("@name");
					String pvalue = temp.valueOf("@value");
					//�����������ƻ�ȡ��������
					Field field = clz.getDeclaredField(pname);
					//�����������ƻ�ȡset��������
					String methodName = "set" + pname.substring(0, 1).toUpperCase() + pname.substring(1);
					//���ݷ������Լ��������ͻ�ȡ��������
					Method method = clz.getMethod(methodName, field.getType());
					method.invoke(obj, pvalue);
				}
				
				beans.put(key, obj);
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchFieldException
				| SecurityException | NoSuchMethodException | DocumentException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Object getBean(String id) {
		return beans.get(id);
	}

}
