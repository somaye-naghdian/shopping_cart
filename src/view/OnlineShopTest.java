package view;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OnlineShopTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
         Startup startup = (Startup) applicationContext.getBean("startup");
         startup.start();

       /* StartUp startup = new StartUp();
        startup.start();*/
    }
}
