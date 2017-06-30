//package com.sap.ngp.sample.config;
//
//import static org.springframework.http.HttpMethod.*;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//
//import com.sap.xs2.security.commons.SAPOfflineTokenServicesCloud;
//
//@Configuration
//@EnableWebSecurity
//@EnableResourceServer
//public class WebSecurityConfig extends ResourceServerConfigurerAdapter {
//
//    private static final String XSAPPNAME = "sample-repo-app-d054341";
//    public static final String DISPLAY_SCOPE = XSAPPNAME + ".Display";
//    public static final String UPDATE_SCOPE = XSAPPNAME + ".Update";
//
//    //configure Spring Security, demand authentication and specific scopes
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        String hasScopeUpdate = "#oauth2.hasScope('" + UPDATE_SCOPE + "')";
//        String hasScopeDisplay = "#oauth2.hasScope('" + DISPLAY_SCOPE + "')";
//
//        // @formatter:off
//        http
//        .sessionManagement()
//        // session is created by approuter
//        .sessionCreationPolicy(SessionCreationPolicy.NEVER)
//        .and()
//        // demand specific scopes depending on intended request
//        .authorizeRequests()
//        // enable OAuth2 checks
//        .antMatchers(GET, "/greeting").permitAll() //allow anyone (unauthenticated users) to access 
//        //.antMatchers(GET, "/writedb").access(hasScopeUpdate)
//        //.antMatchers(GET, "/getdb").access(hasScopeDisplay)
//        .antMatchers(GET, "/writedb").permitAll()
//        .antMatchers(GET, "/getdb").permitAll()
//        //.antMatchers(GET, "/writedb").authenticated()
//        //.antMatchers(GET, "/getdb").authenticated()
//        .antMatchers(GET, "/sendrabbit").authenticated()
//        //                .antMatchers(DELETE, "/api/v1.0/ads/**").access(hasScopeUpdate)
//        //                .antMatchers(GET, "/api/v1.0/ads/**").access(hasScopeDisplay)
//        .anyRequest().denyAll(); // deny anything not configured above
//        // @formatter:on
//    }
//
//    // configure offline verification which checks if any provided JWT was properly signed
//    @Bean
//    protected SAPOfflineTokenServicesCloud offlineTokenServices() {
//        return new SAPOfflineTokenServicesCloud();
//    }
//}
