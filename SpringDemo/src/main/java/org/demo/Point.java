package org.demo;

import org.springframework.beans.factory.BeanNameAware;

public class Point implements BeanNameAware{
	private int x;
	private int y;
	private String beanName;
	
	
	public String getBeanName() {
		return beanName;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Point(){}
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString(){
		return "x: " + x + " y: " +y;
	}
	
	public void myInit(){
		System.out.println(getBeanName() + " bean created");
	}
	
	public void myDestroy(){
		System.out.println(getBeanName() + " bean destroyed");
	}

	public void setBeanName(String name) {
		this.beanName = name;
	}
}
