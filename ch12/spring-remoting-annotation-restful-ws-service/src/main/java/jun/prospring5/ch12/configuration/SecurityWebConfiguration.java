package jun.prospring5.ch12.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityWebConfiguration
        extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("prospring5")
                .password("{noop}prospring5")
                .roles("REMOTE")
                .and()
                .withUser("tester")
                .password("{noop}tester")
                .roles("TESTER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(
            HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/singer/**").hasRole("REMOTE")
                .antMatchers("/api/ping/**").hasRole("TESTER")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
