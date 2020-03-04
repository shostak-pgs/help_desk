package com.help_desk_app.config.web;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled=true)
@ComponentScan("com.help_desk_app")
public class WebConfig implements WebMvcConfigurer {
    private final static String PREFIX = "/WEB-INF/view";
    private final static String SUFFIX = ".html";
    private final static String PATTERN_URL = "/view/**";
    private final static String FORMAT = "UTF-8";
    private final static String RESOURCE_LOCATION ="/view/";

    private final ApplicationContext context;

    public WebConfig(final ApplicationContext context) {
        this.context = context;
    }

    /**
     * Registrations the handler for serving static resources
     * @param registry the registrator
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PATTERN_URL).addResourceLocations(RESOURCE_LOCATION);
    }

     @Override
     public void addCorsMappings(CorsRegistry registry) {
          registry.addMapping("/**")
                  .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE")
                  .allowedHeaders("*")
                  .exposedHeaders(HttpHeaders.LOCATION, "Token")
                  .allowedOrigins("http://localhost:3000");
     }

    /**
     * Create URL's added prefix and suffix for final URL's value
     * @return the thymeleaf resolver
     */
    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(context);
        resolver.setPrefix(PREFIX);
        resolver.setSuffix(SUFFIX);
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

    /**
     * Create the main class for the execution of templates
     * @return the {@link org.thymeleaf.ITemplateEngine}
     */
    @Bean
    public TemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setEnableSpringELCompiler(true);
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    /**
     * Set the class for processing Thymeleaf templates as a result of the execution of controllers
     * @return the {@link ThymeleafViewResolver}
     */
    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding(FORMAT);
        return resolver;
    }
}

