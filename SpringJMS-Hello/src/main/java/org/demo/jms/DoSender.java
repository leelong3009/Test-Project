package org.demo.jms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class DoSender 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        MessageSender sender = context.getBean("messageSender", MessageSender.class);
        
//        Map messages = new HashMap<String, String>();
//        messages.put("Hello", "World");
//        messages.put("country", "India");
//        messages.put("state", "Maharashtra");
//        messages.put("city", "Pune");
        for(int i =0; i<6; i++){
        	sender.send("hi, how are you " + i);
        }
        System.exit(1);
//        System.out.println("Message send to jms queue: " + messages);
    }
}
