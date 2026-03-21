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
            return response.body();
        }catch (IOException | InterruptedException e) {
            throw new RuntimeException("HTTP GET request failhou", e);
        }
    }
    public static void post(String url, String key , String nome ,String email , String pass ,String log){
        try {
            String data = "key="+key+"&nome="+nome+"&email="+email+"&senha="+pass+"&log="+"1";
            System.out.println(data);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url+"/post"))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(data))
                    .build();
            HttpResponse<String> response = client.send( request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
            System.out.println("post Completo");
        }catch (IOException | InterruptedException e) {
            System.out.println("post ERROR");
            throw new RuntimeException("HTTP GET request failhou", e);

        }
    }
}

