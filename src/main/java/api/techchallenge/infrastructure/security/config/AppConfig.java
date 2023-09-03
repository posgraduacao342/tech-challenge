package api.techchallenge.infrastructure.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${url-webhook}")
    private String urlWebhook;

    @Value("${mp-token}")
    private String mpToken;


    public String getUrlWebhook() {
        return urlWebhook;
    }

 
    public String getMPToken() {
        return mpToken;
    }
}
