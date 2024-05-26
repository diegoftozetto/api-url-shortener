package com.example.api_url_shortener.controller;

import com.example.api_url_shortener.dto.ShortenUrlRequestDto;
import com.example.api_url_shortener.dto.ShortenUrlResponseDto;
import com.example.api_url_shortener.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {

    public final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponseDto> shortenUrl(@RequestBody ShortenUrlRequestDto shortenUrlRequestDto,
                                                            HttpServletRequest httpServletRequest) {
        var redirectUrl = urlService.shortenUrl(shortenUrlRequestDto.url(), httpServletRequest.getRequestURL().toString());
        return ResponseEntity.ok(new ShortenUrlResponseDto(redirectUrl));
    }

    @GetMapping("{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {
        var headers = urlService.redirect(id);
        if (headers == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
}
