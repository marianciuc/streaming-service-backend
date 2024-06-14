package zut.wi.streamingservice.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import zut.wi.streamingservice.enums.RoleEnum;

import static zut.wi.streamingservice.enums.Routes.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                    .requestMatchers(AUTHENTICATION.getRoute()).permitAll()
                    .requestMatchers("/admin/**").hasAuthority(RoleEnum.ADMIN.name())
                    .requestMatchers("/subscribed/**")
                        .hasAnyAuthority(RoleEnum.SUBSCRIBED_USER.name(), RoleEnum.MODERATOR.name(), RoleEnum.ADMIN.name())
                    .requestMatchers("/moderator/**")
                        .hasAnyAuthority(RoleEnum.MODERATOR.name(), RoleEnum.ADMIN.name())
                    .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
