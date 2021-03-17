package cz.neidl.farmapp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateClient {
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
