package org.demo;

import java.util.List;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Triangle/* implements InitializingBean, DisposableBean*/ implements BeanNameAware, Shape{
	private Point pointA;
	private Point pointB;
	private Point pointC;
	private String beanName;

	private List<Point> points;
	
	public List<Point> getPoints() {
		return points;
	}


	public void setPoints(List<Point> points) {
		this.points = points;
	}


	public String getBeanName() {
		return beanName;
	}


	public Point getPointA() {
		return pointA;
	}



	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}



	public Point getPointB() {
		return pointB;
	}



	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}



	public Point getPointC() {
		return pointC;
	}



	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}


	public Triangle() {
		// TODO Auto-generated constructor stub
	}


	public Triangle(Point pointA, Point pointB, Point pointC) {
		this.pointA = pointA;
		this.pointB = pointB;
		this.pointC = pointC;
	}

	public void draw(){
		System.out.println("Drawing triangle");
		System.out.println("point A ("+pointA.getX()+","+pointA.getY()+")");
		System.out.println("point B ("+pointB.getX()+","+pointB.getY()+")");
		System.out.println("point C ("+pointC.getX()+","+pointC.getY()+")");
		for(Point p: points){
			System.out.println("Point ("+p.getX()+"."+p.getY()+")");
		}
	}


/*	public void destroy() throws Exception {
		System.out.println("bean destroyed");
	}


	public void afterPropertiesSet() throws Exception {
		System.out.println("bean created");
	}*/
	
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
