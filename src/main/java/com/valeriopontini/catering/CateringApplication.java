package com.valeriopontini.catering;
import static com.valeriopontini.catering.model.Role.ADMIN_ROLE;


import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableWebSecurity
@RestController
@SpringBootApplication
public class CateringApplication extends WebSecurityConfigurerAdapter{

	
	public static void main(String[] args) {
		SpringApplication.run(CateringApplication.class, args);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
				.antMatchers("/", "/oauth2/authorization/github", "/index/**","/buffet/**","/chef/**", "/ingrediente/**", "/piatti/**", "/error", "/webjars/**", "/*.css", "/*.png", "/*.jpeg").permitAll()
				.antMatchers(HttpMethod.POST, "/azioni/**", "/chefForm", "/piattoForm", "/ingredienteForm", "/buffetForm", "/logout" ).hasAnyAuthority(ADMIN_ROLE)
				.antMatchers(HttpMethod.GET, "/azioni/**", "/chefForm", "/piattoForm", "/ingredienteForm", "/buffetForm", "/logout").hasAnyAuthority(ADMIN_ROLE)
				.anyRequest().authenticated()
				.and()
				.oauth2Login()
				.defaultSuccessUrl("/login")
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/index")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.clearAuthentication(true).permitAll()
				.and()
				.exceptionHandling()
			    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
		
		
	}
}
