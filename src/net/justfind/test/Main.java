package net.justfind.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "applicationContext-provider.xml" });
        context.start();
        while (true) {
        	Thread.sleep(6000000);
		}
       
    }
}
