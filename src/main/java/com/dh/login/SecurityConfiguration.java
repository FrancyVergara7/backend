package com.dh.login;

import com.dh.login.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.authorizeRequests().antMatchers("/**")
                .permitAll()
                .anyRequest()
                .authenticated().and()
                .csrf()
                .disable();

      http.headers().frameOptions().disable();
        /*http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated().and().csrf().disable();*/

      /*  http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "static/**").permitAll()

                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/vistaAdmin.html").hasRole("ADMIN")
                .antMatchers("/odontologos").hasRole("ADMIN")
                .antMatchers("/pacientes").hasRole("ADMIN")
                //.antMatchers(HttpMethod.GET, "/**").hasAnyRole(UsuarioRole.USER.name(), UsuarioRole.ADMIN.name())
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/vistaUsuario.html").hasRole("USER")
                .antMatchers("/turnos").hasRole("USER")
                //.antMatchers("/turnos").permitAll()
                .anyRequest()
                .authenticated().and()
                .formLogin()
                .and().logout();
*/

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        DaoAuthenticationProvider autProvider = new DaoAuthenticationProvider();
        autProvider.setPasswordEncoder(bCryptPasswordEncoder);
        autProvider.setUserDetailsService(usuarioService);
        auth.authenticationProvider(autProvider);
    }

}