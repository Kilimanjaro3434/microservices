package org.example.resttemplate;

import org.example.resttemplate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Component
public class Communication {
    private final RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";
    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getAllUsers() {

        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<User>>() {
                        });

        List<User> allUsers = responseEntity.getBody();
        headers.set(HttpHeaders.COOKIE, responseEntity.getHeaders()
                .getFirst(HttpHeaders.SET_COOKIE));
        return allUsers;
    }

    public String saveUser(User user) {
        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(URL, HttpMethod.POST, httpEntity, String.class);
        return responseEntity.getBody();
    }

    public String editUser(User user) {
        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(URL, HttpMethod.PUT, httpEntity, String.class);
        return responseEntity.getBody();
    }

    public String deleteUser(Long id) {
        HttpEntity<User> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate
                .exchange(URL + "/" + id, HttpMethod.DELETE, httpEntity, String.class);
        return responseEntity.getBody();
    }
}
