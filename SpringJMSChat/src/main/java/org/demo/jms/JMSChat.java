package org.demo.jms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * Hello world!
 *
 */
public class JMSChat implements MessageListener
{
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public static String userName;
	
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void onMessage(Message message) {
		if(message instanceof TextMessage){
			try{
				String strMessage = ((TextMessage)message).getText();
				System.out.println(strMessage);
			}catch(Exception ex){
				
			}
		} else {
			String errMsg = "Message is not of expected type, as the expected is the TextMessage";
            System.err.println(errMsg);
            throw new RuntimeException(errMsg);
		}
		
	}
	
	public void subcribe(TopicConnection topicConnection, Topic topic, JMSChat jmsChat) throws JMSException{
		TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		TopicSubscriber ts = topicSession.createSubscriber(topic);
		ts.setMessageListener(jmsChat);
	}
	
	public void publish(String userId) throws IOException{
		BufferedReader brReader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String strMessageToSend = brReader.readLine();
			if (strMessageToSend.equalsIgnoreCase("exit")) {
//				topicConnection.close();
				System.exit(0);

			}

			else {
//				TextMessage txtMessage = topicSession.createTextMessage();
//				txtMessage.setText("\n [" + userId + " : " + strMessageToSend
//						+ "]");
//				topicPublisher.publish(txtMessage);
				final String stringToSend = "\n [" + userId + " : " + strMessageToSend + "]";
				Topic topic = new Topic() {
					public String getTopicName() throws JMSException {
						return "topic.Chat";
					}
				};
				jmsTemplate.send(topic, new MessageCreator() {
					
					public Message createMessage(Session session) throws JMSException {
						TextMessage txtMessage = session.createTextMessage();
						txtMessage.setText(stringToSend);
						return txtMessage;
					}
				});
			}

		}

	}
	
	public static void main( String[] args ) throws JMSException, IOException
    {
        if(args.length != 1){
        	System.out.println("User name is required to join the chat application");
        } else {
        	userName = args[0];
        	ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        	JMSChat jmsChat = context.getBean("jmsMessenger", JMSChat.class);
        	jmsChat.publish(userName);
        }
    }
}
