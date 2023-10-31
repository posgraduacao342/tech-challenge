package api.techchallenge.infrastructure.mp;

import org.springframework.web.reactive.function.client.WebClient;

public class HttpBase {

    protected final WebClient webClient;

    public HttpBase(WebClient.Builder webClientBuilder, String baseUrl) {
        this.webClient =
                webClientBuilder
                        .baseUrl(baseUrl)
                        .build();
    }
}
