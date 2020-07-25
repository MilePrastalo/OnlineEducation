package rs.eeducation.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import rs.eeducation.jwt.JwtAuthenticationEntryPoint
import rs.eeducation.jwt.JwtRequestFilter
import rs.eeducation.service.JwtUserDetailsService


@EnableWebSecurity
class WebSecurityConfig(
        private var jwtRequestFilter: JwtRequestFilter,
        private var restAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
        private var jwtUserDetailsService: JwtUserDetailsService) : WebSecurityConfigurerAdapter() {

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager? {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }


    // configure AuthenticationManager so that it knows from where to load
    // user for matching credentials
    // Use BCryptPasswordEncoder
    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService?>(jwtUserDetailsService).passwordEncoder(passwordEncoder())
    }

    // Defining access rights to specific URLs
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http // comunication between client and server is stateless
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // for unauthorized request send 401 error
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and() // don't authenticate this particular request
                .authorizeRequests().antMatchers("/auth/**").permitAll().antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll() // all other requests need to be authenticated
                .anyRequest().authenticated().and()

        // intercept every request and add filter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
        http.csrf().disable()
    }
}
