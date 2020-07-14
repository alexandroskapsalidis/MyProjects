package com.hellokoding.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                
//                .antMatchers("/adminpage/**").hasRole("ROLE_ADMIN")
                .antMatchers("/resources/**" , "/home/**" , "/loginpage/**" ,"/welcome/**").permitAll()
                .antMatchers("/resources/**","/home/**" , "/registration" , "/welcome/**").permitAll()
                .antMatchers("/resources/**", "/viewstudents").permitAll()
                .antMatchers("/resources/**", "/addappointment").permitAll()
                .antMatchers("/resources/**", "/viewAppointments").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/home")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/welcome", true)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager(); 
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//
//        auth.inMemoryAuthentication()
//			.withUser("adminadmin").password("$2a$10$3zt.Yuwh4P8MBAKqwFZyROzZ8u4L4VRyhQt7t1JgeUTgoGNjlGrtC").authorities("ROLE_ADMIN");
//       
//    }
 
    

}
