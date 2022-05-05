package shoppingCart.APIGateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	
	@Bean
	  public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r ->  r.path("/product/**")
                        .uri("http://localhost:8083/"))

                .route(r -> r.path("/profile/**")
                        .uri("http://localhost:8082/"))
                
                .route(r -> r.path("/cart/**")
                        .uri("http://localhost:8084/"))
                
                .route(r -> r.path("/order/**")
                        .uri("http://localhost:8085/"))
                
                .route(r -> r.path("/payment/**")
                        .uri("http://localhost:8086/"))
                
                .build();
    }


}
