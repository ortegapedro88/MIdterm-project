package MidtermProject.Project.security;


import MidtermProject.Project.services.AccountHolderService;
import MidtermProject.Project.services.AdminService;
import MidtermProject.Project.services.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {


    @Autowired
    private AccountHolderService accountHolderService;
    @Autowired
    private AdminService adminService;
    @Autowired
    ThirdPartyService thirdPartyService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic();

        http.authorizeRequests()
                /*
                .mvcMatchers(HttpMethod.POST, "/admin/say-hi").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/create-user").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/modify-password").hasAnyRole("ADMIN", "USER")
                .mvcMatchers(HttpMethod.GET, "/users-only").hasRole("USER")
                .mvcMatchers(HttpMethod.GET, "/users/**").hasRole("USER")
                .mvcMatchers(HttpMethod.POST, "/users/**").hasRole("USER")
                                 */
                .anyRequest().permitAll();

        http.csrf().disable();
        return http.build();


    }
}
