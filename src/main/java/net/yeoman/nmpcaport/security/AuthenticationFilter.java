package net.yeoman.nmpcaport.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.yeoman.nmpcaport.SpringApplicationContext;
import net.yeoman.nmpcaport.io.request.user.UserLoginRequestModel;
import net.yeoman.nmpcaport.services.UserService;
import net.yeoman.nmpcaport.shared.dto.UserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager){

        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException{

        try{
            UserLoginRequestModel credentials = new ObjectMapper()
                    .readValue(request.getInputStream(), UserLoginRequestModel.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getEmail(),
                            credentials.getPassword(),
                            new ArrayList<>()
                    )
            );

        }catch (IOException e ){
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                           HttpServletResponse response,
                                           FilterChain chain,
                                           Authentication auth) throws IOException, ServletException{

        String userName = ((User) auth.getPrincipal()).getUsername();

        Algorithm algorithm = Algorithm.HMAC512(SecurityConstants.getTokenSecret());
        String token = JWT.create()
                .withSubject(userName)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(algorithm);

        UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
        UserDto userDto = userService.getUser(userName);

        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        response.addHeader("UserID", userDto.getUserId());


    }

}
