package com.doctorbari.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.doctorbari.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	    @Autowired
		private UserService userService;

		@Autowired
		private AuthenticationUser authenticationUser;

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(authenticationProvider());
		}

		private static final String[] PUBLIC_MATCHERS = {
				"/assets/**",
				"/custom/**",
				"/dist/**",
				"/img/**",
				"/plugins/**",
				"/",
				"/login",
				"/ajax/searchStudent",
				"/ajax/generate-report",
				"/user/saveContact",
				"/userSearch",
				"/blog",
				"/reg",
				"/user/saveReg",
				"/resetpass",
                "/user/resetPassword"
				
			};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();


		http
		.authorizeRequests().
	    antMatchers(PUBLIC_MATCHERS).
		permitAll()
		.antMatchers("/login").hasRole("USER")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/doctor/**").hasRole("DOCTOR")
	    .anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/authenticateTheUser")
			.successHandler(authenticationUser)
			.permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

}
