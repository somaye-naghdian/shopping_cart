package view;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"view","dao","service"})
public class BeanConfiguration {
    @Bean
    public SessionFactory sessionFactory() {

        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();

    }

}
