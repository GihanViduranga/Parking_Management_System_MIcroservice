/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.paymentservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GatewayConfig {
    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }
}
