package connection.nio;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NioConnection {

	public static void main(String[] args) throws Exception{
		
		HttpRequest request = 
				HttpRequest.newBuilder(new URI("http://localhost:8080/dummy"))
					.headers("Content-Type", "application/json; utf-8","Accept", "application/json", "header", "header-example")
					.POST(HttpRequest.BodyPublishers.ofString("{\"Roma\":\"RM\"}"))
					.build();
		
		HttpResponse<String> response = 
				HttpClient.newBuilder()
					.build()
					.send(request, HttpResponse.BodyHandlers.ofString());
		
		System.out.println(response.body());
	
	}
}
