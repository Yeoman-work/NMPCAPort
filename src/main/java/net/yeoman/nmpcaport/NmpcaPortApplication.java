package net.yeoman.nmpcaport;

import net.yeoman.nmpcaport.security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//
//        return new BCryptPasswordEncoder();
//    }

}
