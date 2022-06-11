package lc.proyecto.certificacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true
	)
@EnableWebSecurity
public class ConfiguracionSeguridad {

	@Bean
	public PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filtro(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests( authorize -> authorize
					.mvcMatchers("/", "/registro","/login","/inicio","/detalle", "/index").permitAll()
					.mvcMatchers("/administrador/**").hasAuthority("ADMINISTRADOR")					
					.mvcMatchers("/postulante/**").hasAuthority("POSTULANTE")					
					.anyRequest().authenticated() 
		)
		.formLogin(form -> form 
					.loginPage("/login") 
					.defaultSuccessUrl("/", true) 
					.permitAll()
		)
		.logout(logout -> logout 
					.logoutRequestMatcher(
						new AntPathRequestMatcher("/salir", "GET")
					)						
		)
	;
	return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() { 
		// ignora los requisitos de seguridad para las siguientes carpetas y sus archivos  
		return (web) -> web.ignoring().antMatchers("/img/**","/imagen/**","/css/**","/js/**");
	}
}
