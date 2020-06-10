package me.inonecloud.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        Log log = LogFactory.getLog(getClass());

        ClientHttpRequestInterceptor requestInterceptor = (HttpRequest request, byte[] body, ClientHttpRequestExecution excution) -> {
            log.info(String.format("request to URI %s with HTTP verb '%s'", request.getURI(), request.getMethod().toString()));
            ClientHttpResponse response = excution.execute(request, body);
            if (response.getStatusCode().is2xxSuccessful()) {
                log.info(String.format("response to URI %s has code '%s'", request.getURI(), response.getStatusCode()));
            } else {
                log.error(String.format("response to URI %s has code '%s' and message: '%s'", request.getURI(), response.getStatusCode(), response.getStatusText()));
            }
            return response;
        };

        return new RestTemplateBuilder()
                .additionalInterceptors(requestInterceptor).build();
    }
}