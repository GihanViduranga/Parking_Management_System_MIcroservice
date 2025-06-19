/*
 * Copyright (c) 2025
 * Gihan Viduranga - 2025
 */

package lk.ijse.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(AuthenticationFilter.Config config) {
        return ((exchange, chain) -> {
            if (isValidate(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "missing authorization header");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getForObject("http://localhost:8082/auth/api/v1/auth/validateToken?token=" + authHeader, String.class);
                } catch (Exception e) {
                    throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "un authorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    private boolean isValidate(ServerHttpRequest request) {
        List<String> publicPaths = List.of("/api/v1/user/register", "/api/v1/auth/authenticate");
        return publicPaths.stream().noneMatch(path -> request.getURI().getPath().contains(path));
    }

    public static class Config {

    }
}