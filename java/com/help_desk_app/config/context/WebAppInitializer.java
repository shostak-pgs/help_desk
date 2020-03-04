package com.help_desk_app.config.context;

import com.help_desk_app.config.hibernate.HibernateUtils;
import com.help_desk_app.config.react.ReactConfig;
import com.help_desk_app.config.security.EncoderConfig;
import com.help_desk_app.config.security.WebSecurityConfig;
import com.help_desk_app.config.web.WebConfig;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.ServletRegistration;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * Specify {@code @Configuration} and/or {@code @Component} classes for the
     * {@linkplain #createRootApplicationContext() root application context}.
     * @return the configuration for the root application context, or {@code null}
     * if creation and registration of a root context is not desired
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {HibernateUtils.class, WebConfig.class,
                ReactConfig.class, EncoderConfig.class, WebSecurityConfig.class};
    }

    /**
     * Specify {@code @Configuration} and/or {@code @Component} classes for the
     * {@linkplain #createServletApplicationContext() Servlet application context}.
     * @return the configuration for the Servlet application context, or
     * {@code null} if all configuration is specified through root config classes.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    /**
     * Specify the servlet mapping(s) for the {@code DispatcherServlet} &mdash;
     * for example {@code "/"}, {@code "/app"}, etc.
     */
    @NotNull
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * Configures the servlet to throw exceptions when there is no handler
     * @param registration the{@link ServletRegistration}
     */
    @Override
    public void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }
}

