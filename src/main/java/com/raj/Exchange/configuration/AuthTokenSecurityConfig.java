package com.raj.Exchange.configuration;

import com.raj.Exchange.model.LogTable;
import com.raj.Exchange.repository.LogingRepository;
import net.minidev.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;

@Configuration
//@EnableWebSecurity
@PropertySource("classpath:application.properties")
@Order(1)
public class AuthTokenSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${howtodoinjava.http.auth.tokenName}")
    private String authHeaderName;

    //TODO: retrieve this token value from data source
    @Value("${howtodoinjava.http.auth.tokenValue}")
    private String authHeaderValue;

    @Autowired
    LogingRepository logingRepository;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        PreAuthTokenHeaderFilter filter = new PreAuthTokenHeaderFilter(authHeaderName);
        if (filter.isHeaderIsNull()) {
            System.out.println("filter null " + filter.getRequestUrl());
        }

        filter.setAuthenticationManager(new AuthenticationManager() {

            @Override
            public Authentication authenticate(Authentication authentication)
                    throws AuthenticationException {


                String principal = (String) authentication.getPrincipal();


                System.out.println(principal);
                System.out.println(authentication.getCredentials());
                System.out.println("url=" + filter.getRequestUrl());
                System.out.println("method=" + filter.getRequestMethod());
                LogTable logTable = new LogTable();
                logTable.setName(filter.getRequestUrl() + filter.getRequestMethod());
                //logTable.setName("GET");
                logingRepository.save(logTable);
                if (!authHeaderValue.equals(principal)) {
                    System.out.println("Bad" + filter.getRequestUrl());
//                    LogTable logTable= new LogTable();
//                    logTable.setName(filter.getRequestUrl() + filter.getRequestMethod());
//                    logingRepository.save(logTable);
                    throw new BadCredentialsException("The API key was not found "
                            + "or not the expected value.");
                }

//                LogTable logTable= new LogTable();
//                logTable.setName(filter.getRequestUrl() + filter.getRequestMethod());
//                logingRepository.save(logTable);

                System.out.println(true);
                authentication.setAuthenticated(true);
                return authentication;
            }
        });
        System.out.println("url=" + filter.getRequestUrl());
        httpSecurity.
                antMatcher("/api/**")
                .csrf()
                .disable()
                .cors()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter)
                .addFilterBefore(

                        new ExceptionTranslationFilter(

                                new Http403ForbiddenEntryPoint()

                        ) {

                        },

                        filter.getClass()

                )
                .authorizeRequests()
                .anyRequest()
                .authenticated();

    }

}
