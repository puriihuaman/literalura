package com.puriihuaman.literalura.service;

import com.puriihuaman.literalura.commons.enums.ApiError;
import com.puriihuaman.literalura.exception.ApiRequestException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FetchingData {
    private static final String BASE_URL = "https://gutendex.com/books/";
    
    public static String getData(final Integer page) {
        HttpResponse<String> response;
        
        try {
            HttpClient client = HttpClient.newHttpClient();
            URI uri = getURI(page);
            HttpRequest request = HttpRequest.newBuilder(uri).build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            return response.body();
        } catch (IOException | InterruptedException ex) {
            throw new ApiRequestException(ApiError.EXTERNAL_SERVICE_UNAVAILABLE);
        }
    }
    
    public static URI getURI(final Integer page) {
        String url = page == null ? BASE_URL : "%s?page=%d".formatted(BASE_URL, page);
        return URI.create(url);
    }
}