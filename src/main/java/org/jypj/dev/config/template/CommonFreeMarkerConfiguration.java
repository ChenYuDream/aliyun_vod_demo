package org.jypj.dev.config.template;

import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * @author ChenYu
 * @create 2017-12-4 11:03:39
 */
@Configuration
@EnableConfigurationProperties(FreeMarkerProperties.class)
public class CommonFreeMarkerConfiguration {
    private FreeMarkerProperties properties;

    public CommonFreeMarkerConfiguration(FreeMarkerProperties properties) {
        this.properties = properties;
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver() {
            @Override
            protected Class<?> requiredViewClass() {
                return CommonFreeMarkerView.class;
            }
        };
        this.properties.applyToViewResolver(resolver);
        return resolver;
    }
}
