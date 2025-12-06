package com.luna.beanregistrardemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import( MessageServiceRegistrar.class)
class ModernConfig {

}
