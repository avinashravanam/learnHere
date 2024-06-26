package com.example.demo.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.example.demo.service.CustomOAuth2Service;
import com.example.demo.service.UserService;
import com.example.demo.utils.CustomOAuth2User;




@Configuration
@EnableWebMvc
public class SecurityConfig {

    @Autowired
    private CustomOAuth2Service oauthUserService;

    @Autowired
    private UserService userService;
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
		
//		CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
//		XorCsrfTokenRequestAttributeHandler delegate = new XorCsrfTokenRequestAttributeHandler();
//		// set the name of the attribute the CsrfToken will be populated on
//		delegate.setCsrfRequestAttributeName(null);
//		CsrfTokenRequestHandler requestHandler = delegate::handle;

         http.csrf(csrf -> csrf.disable())
              .authorizeHttpRequests(requests -> requests
              .requestMatchers("/").permitAll()
              .anyRequest().authenticated())
//            .exceptionHandling(e -> e
// 		      .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
               .oauth2Login(Customizer.withDefaults())                          //It checks in application properties when we use Customizer.withDefaults
               .oauth2Login(oauth2 -> oauth2.loginPage("/"))          //without this it creates a default login page 
               .oauth2Login(oauth2 -> oauth2.userInfoEndpoint(userInfo -> userInfo.userService(this.oauthUserService)))
               .oauth2Login(oauth2 -> oauth2.successHandler(successHandler()));
;

//               .formLogin(Customizer.withDefaults())
//               .logout(l -> l.logoutSuccessUrl("/").permitAll());
         
//               .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            		    // Added this:
//            		     .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())); 
//        
        return http.build();

    }

	private AuthenticationSuccessHandler successHandler() {
		
		return (request, response, authentication) -> {

            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            // OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            System.out.println("Can save in database");
            System.out.println(oAuth2User);
            userService.storeOauthPostLogin(oAuth2User.getName());
            response.setStatus(HttpStatus.OK.value());
            response.sendRedirect("/");
        };
	}
	
	

}


