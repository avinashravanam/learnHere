package com.example.demo.Security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;




@Configuration
@EnableWebMvc
public class SecurityConfig {

	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
		
//		CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
//		XorCsrfTokenRequestAttributeHandler delegate = new XorCsrfTokenRequestAttributeHandler();
//		// set the name of the attribute the CsrfToken will be populated on
//		delegate.setCsrfRequestAttributeName(null);
//		CsrfTokenRequestHandler requestHandler = delegate::handle;

         http.csrf(csrf -> csrf.disable())
              .authorizeHttpRequests(requests -> requests
              .requestMatchers("/", "/home", "/error", "/webjars/**").permitAll()
              .anyRequest().authenticated())
              .exceptionHandling(e -> e
            		  .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
               )
               .oauth2Login(Customizer.withDefaults())             //checks in application property
               .oauth2Login(oauth2 -> oauth2.successHandler(successHandler()))
               .formLogin(Customizer.withDefaults())
               .logout(l -> l.logoutSuccessUrl("/").permitAll());
         
//               .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            		    // Added this:
//            		     .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())); 
//        
        return http.build();

    }

	private AuthenticationSuccessHandler successHandler() {
		
		return (request, response, authentication) -> {
            response.setStatus(HttpStatus.OK.value());
            response.sendRedirect("/");
        };
	}
	
	

}


