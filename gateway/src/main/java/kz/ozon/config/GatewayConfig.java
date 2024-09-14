package kz.ozon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.util.UUID;

@Configuration
public class GatewayConfig {

    @Value("${auth-service.url}")
    private String authorizationUrl;

    @Value("${central-service.url}")
    private String centralUrl;


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                .route(generateUuid(), predicateSpec -> predicateSpec
                        .path("/api/auth/register")
                        .and()
                        .method(HttpMethod.POST)
                        .uri(authorizationUrl)
                )

                .route(generateUuid(), predicateSpec -> predicateSpec
                        .path("/api/auth/login")
                        .and()
                        .method(HttpMethod.POST)
                        .uri(authorizationUrl)
                )

                .route(generateUuid(), predicateSpec -> predicateSpec
                        .path("/api/auth/token")
                        .and()
                        .method(HttpMethod.POST)
                        .uri(authorizationUrl)
                )

                .route(generateUuid(), predicateSpec -> predicateSpec
                        .path("/api/auth/refresh")
                        .and()
                        .method(HttpMethod.POST)
                        .uri(authorizationUrl)
                )

                .route(generateUuid(), predicateSpec -> predicateSpec
                        .path("/api/auth/logout")
                        .and()
                        .method(HttpMethod.POST)
                        .uri(authorizationUrl)
                )

                .route(generateUuid(), predicateSpec -> predicateSpec
                        .path("/users/{id}")
                        .and()
                        .method(HttpMethod.GET)
                        .uri(authorizationUrl)
                )

                .route(generateUuid(), predicateSpec -> predicateSpec
                        .path("/users/{id}")
                        .and()
                        .method(HttpMethod.PATCH)
                        .uri(authorizationUrl)
                )

                .route(generateUuid(), predicateSpec -> predicateSpec
                        .path("/api/hello/user")
                        .and()
                        .method(HttpMethod.GET)
                        .uri(authorizationUrl)
                )

                .route(generateUuid(), predicateSpec -> predicateSpec
                        .path("/api/hello/admin")
                        .and()
                        .method(HttpMethod.GET)
                        .uri(authorizationUrl)
                )

                .route(generateUuid(), predicateSpec -> predicateSpec
                        .path("users/{id}")
                        .and()
                        .method(HttpMethod.DELETE)
                        .uri(authorizationUrl)
                )

                .route(generateUuid(), predicateSpec -> predicateSpec
                        .path("users")
                        .and()
                        .method(HttpMethod.GET)
                        .uri(authorizationUrl)
                )


                .build();
    }

    private String generateUuid() {
        return UUID.randomUUID().toString();
    }

}
