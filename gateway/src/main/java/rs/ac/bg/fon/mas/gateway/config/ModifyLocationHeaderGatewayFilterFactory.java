/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.mas.gateway.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 *
 * @author Predrag
 */
@Component
public class ModifyLocationHeaderGatewayFilterFactory extends AbstractGatewayFilterFactory<ModifyLocationHeaderGatewayFilterFactory.Config> {

    private static final Logger logger = LoggerFactory.getLogger(ModifyLocationHeaderGatewayFilterFactory.class);

    
    public ModifyLocationHeaderGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                exchange.getResponse().getHeaders().computeIfPresent("Location", (key, value) -> {
                    String locationValue = value.get(0);
                    logger.debug("Response location: " + locationValue);
                    try {
                        URI locationUri = new URI(value.get(0));
                        logger.debug("Response location contains [" + config.getPath() + "] : " + locationUri.getPath().equalsIgnoreCase(config.getPath()));
                        if (locationUri.getPath().startsWith(config.getPath())) {
                            URI requestUri = exchange.getRequest().getURI();
                            logger.debug("Request URI: " + requestUri.toString());
                            URI newLocationUri = new URI(
                                    requestUri.getScheme(),
                                    locationUri.getUserInfo(),
                                    requestUri.getHost(),
                                    requestUri.getPort(),
                                    locationUri.getPath(),
                                    locationUri.getQuery(),
                                    locationUri.getFragment()
                            );
                            locationValue = newLocationUri.toString();
                        }

                    } catch (URISyntaxException ex) {
                        logger.error("Error while setting response location!");
                    }

                    return List.of(locationValue);

                });
            }));
        };
    }

    static class Config {

        private String path;

        public Config(String path) {
            this.path = path;
        }

        public Config() {
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

    }

}
