package com.in28minutes.microservices.currency_exchange_service;


import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class CircuitBreakerController {


    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    //@Retry(name="sampleApi",fallbackMethod = "hardCodedResponse")
   // @CircuitBreaker(name = "default",fallbackMethod = "hardCodedResponse")
   // @RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public String sampleApi(){
        logger.info("Sample API call is received");
//        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8082/some-dummy-url",String.class);
//        return forEntity.getBody();
        return "Sample API";
    }

    public String hardCodedResponse (Exception exception){
        return "Fall Back Response";
    }
}
