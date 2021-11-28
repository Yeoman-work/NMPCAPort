package net.yeoman.nmpcaport.security;

import net.yeoman.nmpcaport.SpringApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class SecurityConstants {

    public static final long EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "YEOMAN";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";





    public static String getTokenSecret(){

        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
                return appProperties.getTokenSecret();
    }




}
