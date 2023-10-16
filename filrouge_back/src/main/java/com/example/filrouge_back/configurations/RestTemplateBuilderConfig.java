package com.example.filrouge_back.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateBuilderConfig {

    @Value("${api.tmdb.url}")
    private String tmdbUrl;

    @Value("${api.tmdb.token}")
    private String tmdbToken;

    @Value("${api.betaseries.url}")
    private String betaseriesUrl;

    @Value("${api.betaseries.version}")
    private String betaseriesVersion;

    @Value("${api.betaseries.key}")
    private String betaseriesKey;

    @Bean(name = "tmdb")
    RestTemplateBuilder tmdbRestTemplateBuilder(RestTemplateBuilderConfigurer configurer) {
        RestTemplateBuilder builder = configurer.configure(new RestTemplateBuilder());

        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(tmdbUrl);

        return builder
                .uriTemplateHandler(uriBuilderFactory)
                .defaultHeader("Authorization", "Bearer " + tmdbToken);

    }

    @Bean(name = "betaseries")
    RestTemplateBuilder betaseriesRestTemplateBuilder(RestTemplateBuilderConfigurer configurer) {
        RestTemplateBuilder builder = configurer.configure(new RestTemplateBuilder());

        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(betaseriesUrl);

        return builder
                .uriTemplateHandler(uriBuilderFactory)
                .defaultHeader("X-BetaSeries-Key", betaseriesKey)
                .defaultHeader("X-BetaSeries-Version", betaseriesVersion);
    }
}
