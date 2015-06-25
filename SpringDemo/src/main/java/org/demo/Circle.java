package org.demo;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class Circle implements Shape, ApplicationEventPublisherAware {
	
	private Point center;
	@Autowired
	private MessageSource messageSource;
	private ApplicationEventPublisher publisher;
	
	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public Point getCenter() {
		return center;
	}

	@Resource(name="pointC")
	public void setCenter(@Qualifier(value="pointA") Point center) {
		this.center = center;
	}

	public void draw() {
		System.out.println(this.messageSource.getMessage("drawing.circle", null, new Locale("vi")));
		System.out.println(this.messageSource.getMessage("drawing.point", new Object[]{center.getX(),center.getY()}, new Locale("vi")));
//		System.out.println(this.messageSource.getMessage("greeting", null, "Default Greeting", null));
		DrawEvent drawEvent = new DrawEvent(this);
		publisher.publishEvent(drawEvent);
	}
	
	@PostConstruct
	public void initializeCircle(){
		System.out.println("Init of Circle");
	}
	
	@PreDestroy
	public void destroyCircle(){
		System.out.println("Destroy of Circle");
	}

	public void setApplicationEventPublisher(
			ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}
}
