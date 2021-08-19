package com.test.springtest.config.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PATTERN_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/",
            "/index.html",
            "/login**",
            "/oauth2/**"    // OAuth2-URL : /oauth2/authorization/{id}
    };

    //static resources ("/css/**", "/js/**", "/images/**", /webjars/**, "/favicon.*", "/*/icon-*")
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(PATTERN_WHITE_LIST).permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/kakao").hasAuthority("KAKAO")
                .antMatchers("/naver").hasAuthority("NAVER")
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint().userService(new CustomOAuth2UserService())
                .and()
                .defaultSuccessUrl("/loginSuccess")
                .failureUrl("/loginFailure")
                .and()
//                .oauth2ResourceServer()
//                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(OAuth2ClientProperties oAuth2ClientProperties) {
        List<ClientRegistration> registrations = oAuth2ClientProperties.getRegistration()
                .keySet().stream().map(client ->
                        getRegistration(oAuth2ClientProperties, client)).filter(Objects::nonNull)
                .collect(Collectors.toList()
                );
        return new InMemoryClientRegistrationRepository(registrations);
    }

    private ClientRegistration getRegistration(OAuth2ClientProperties clientProperties, String client) {
        if ("google".equals(client)) {
            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get(client);
            return CommonOAuth2Provider.GOOGLE.getBuilder(client).clientId(registration.getClientId())
                    .clientSecret(registration.getClientSecret())
                    .scope("email", "profile").build();
        }
        if ("facebook".equals(client)) {
            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get(client);
            return CommonOAuth2Provider.FACEBOOK.getBuilder(client).clientId(registration.getClientId())
                    .clientSecret(registration.getClientSecret())
                    .userInfoUri("https://graph.facebook.com/me?fields=id,name,email,link")
                    .scope("email").build();
        }
        if ("kakao".equals(client)) {
            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get(client);
            return CustomOAuth2Provider.KAKAO.getBuilder(client).clientId(registration.getClientId())
                    .clientSecret(registration.getClientSecret())
                    .scope("").build();
        }
        if ("naver".equals(client)) {
            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get(client);
            return CustomOAuth2Provider.NAVER.getBuilder(client).clientId(registration.getClientId())
                    .clientSecret(registration.getClientSecret())
                    .scope("").build();
        }

        return null;
    }


}