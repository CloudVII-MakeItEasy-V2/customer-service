package org.group.customer.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

@Component
public class AddressValidator {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${smarty.api.url}")
    private String smartyApiUrl;

    @Value("${smarty.api.auth-id}")
    private String smartyAuthId;

    @Value("${smarty.api.auth-token}")
    private String smartyAuthToken;

    public boolean isValidAddress(String address) {
        String url = String.format("%s?auth-id=%s&auth-token=%s&street=%s",
                smartyApiUrl, smartyAuthId, smartyAuthToken, address);

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            return root.size() > 0;
        } catch (Exception e) {

            System.err.println("Address validation failed: " + e.getMessage());
            return false;
        }
    }
}
