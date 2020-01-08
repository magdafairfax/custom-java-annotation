package com.magda.config;

import com.magda.core.ColorOptions;
import com.magda.core.Truck;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CarConfiguration {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @ColorOptions(color = ColorOptions.Color.GREEN)
    public Truck oldTruck() {
        return new Truck();
    }

    @Bean
    @ColorOptions(color = ColorOptions.Color.RED)
    public Truck newTruck() {
        return new Truck();
    }
}
