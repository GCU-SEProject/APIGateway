package com.example.apigatewayservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(
            RouteLocatorBuilder builder,
            @Value("${services.videosearch.url}") String videoUrl,
            @Value("${services.encyclopedia.url}") String encUrl,
            @Value("${services.news.url}") String newsUrl,
            @Value("${services.game.url}") String gameUrl,
            @Value("${services.monitoring.url}") String monitorUrl
    ) {
        return builder.routes()

                // Video 검색
                .route("video-search", r -> r
                        .path("/search/video/**")
                        .filters(f -> f
                                .removeRequestHeader("Cookie")
                                .rewritePath("/video-service/(?<segment>.*)", "/${segment}")
                        )
                        .uri(videoUrl)
                )

                // Encyclopedia 검색
                .route("encyclopedia", r -> r
                        .path("/search/encyclopedia/**")
                        .filters(f -> f
                                .removeRequestHeader("Cookie")
                                .rewritePath("/encyclopedia-service/(?<segment>.*)", "/${segment}")
                        )
                        .uri(encUrl)
                )

                // News 검색
                .route("news", r -> r
                        .path("/search/news/**")
                        .filters(f -> f
                                .removeRequestHeader("Cookie")
                                .rewritePath("/news-service/(?<segment>.*)", "/${segment}")
                        )
                        .uri(newsUrl)
                )

                // Game 검색
                .route("game", r -> r
                        .path("/ame-service/**")
                        .filters(f -> f
                                .removeRequestHeader("Cookie")
                                .rewritePath("/game-service/(?<segment>.*)", "/${segment}")
                        )
                        .uri(gameUrl)
                )

                // Monitoring 조회
                .route("monitoring", r -> r
                        .path("/monitor/**")
                        .filters(f -> f
                                .removeRequestHeader("Cookie")
                                .rewritePath("/monitor-service/(?<segment>.*)", "/${segment}")
                        )
                        .uri(monitorUrl)
                )

                .build();
    }
}