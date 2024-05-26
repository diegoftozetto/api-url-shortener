package com.example.api_url_shortener.service;

import com.example.api_url_shortener.entities.UrlEntity;
import com.example.api_url_shortener.repository.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String url, String requestUrl) {
        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, url, LocalDateTime.now().plusMinutes(1)));

        return requestUrl.replace("shorten-url", id);
    }

    public HttpHeaders redirect(String id) {
        var url = urlRepository.findById(id);

        if (url.isEmpty()) {
            return null;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.get().getFullUrl()));
        return headers;
    }
}

