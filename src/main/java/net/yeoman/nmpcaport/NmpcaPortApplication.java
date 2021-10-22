package net.yeoman.nmpcaport;

import net.yeoman.nmpcaport.security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NmpcaPortApplication {

    public static void main(String[] args) {
        SpringApplication.run(NmpcaPortApplication.class, args);
    }

    @Bean
    public SpringApplicationContext springApplicationContext(){

        return new SpringApplicationContext();
    }

    @Bean(name ="AppProperties")
    public AppProperties getAppProperties(){

        return new AppProperties();
    }

}
