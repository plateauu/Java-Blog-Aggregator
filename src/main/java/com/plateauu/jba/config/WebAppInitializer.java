package com.plateauu.jba.config;

import org.springframework.core.env.AbstractEnvironment;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class WebAppInitializer implements WebApplicationInitializer {


    public static final String DEV = "dev";
    public static final String PROD = "prod";

    private final Logger log = Logger.getLogger(getClass().getName());

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {


        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, PROD);

//        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, DEV);

        rootContext.register(RootContext.class, RootContextDev.class, WebSecurityConfig.class);

        servletContext.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
        dispatcherServlet.register(WebConfig.class);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        FilterRegistration.Dynamic encoding = servletContext.addFilter("characterEncodingFilter", CharacterEncodingFilter.class);

        Map<String, String> params = new HashMap<>();
        params.put("encoding", "UTF-8");
        params.put("forceEncoding", "true");
        encoding.setInitParameters(params);

        encoding.addMappingForUrlPatterns(null, true, "/*");


//        OpenEntityManagerInViewFilter entityManagerInViewFilter = new OpenEntityManagerInViewFilter();
//        entityManagerInViewFilter.setEntityManagerFactoryBeanName("emf");
//        entityManagerInViewFilter.setPersistenceUnitName("transactionManager");
//
//        FilterRegistration.Dynamic filter = servletContext.addFilter("ENTITY_MANAGER_FILTER_NAME", entityManagerInViewFilter);
//        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false, "/*");


    }
}