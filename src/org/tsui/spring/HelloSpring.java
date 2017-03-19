package org.tsui.spring;

public class HelloSpring {
	private String msg;
	
	public void sayHello() {
		System.out.println("This is spring hello-world " + msg);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
