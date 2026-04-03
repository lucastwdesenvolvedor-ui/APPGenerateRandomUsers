package org.api.getApi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientLib {

    private static final HttpClient client = HttpClient.newHttpClient();
    public static String get(String url) throws IOException, InterruptedException{
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send( request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Erro HTTP");
            }
            return response.body();

        }catch (IOException | InterruptedException e) {
            throw new RuntimeException("HTTP GET request failhou", e);
        }
    }
    public static String post(String url, String key, String nome, String email, String senha, String rota) {
        try {

            String data = "key=" + key +
                    "&nome=" + nome +
                    "&email=" + email +
                    "&senha=" + senha;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url + rota))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(data))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200 && response.statusCode() != 201) {
                throw new RuntimeException("Erro HTTP: " + response.body());
            }

            return response.body();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

