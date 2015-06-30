package org.demo.jms;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.TextMessage;

public class MessageListener implements javax.jms.MessageListener{

	public void onMessage(Message message) {
		if(message instanceof TextMessage){
			try{
				String text = ((TextMessage)message).getText();
				System.out.println("Receive: " + text);
			}catch(Exception ex){
				
			}
			
		}else if (message instanceof MapMessage){
			
		}
		
	}

}
