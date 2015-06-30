package org.demo.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class MessageSender {
	private JmsTemplate jmsTemplate;
	
	
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}


	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}


	public void send(final String message){
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				System.out.println("Send: " + message);
				Message m = session.createTextMessage(message);
				return m;
			}
		});
	}
}
