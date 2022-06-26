package store.config;

import org.springframework.context.annotation.Bean;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

public class ThymeleafDialect {
    @Bean
    LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
