package connection.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class IoConnection {

	public static void main(String[] args) throws Exception{
		
		URL url = new URL("http","localhost", 8080, "/dummy");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json; utf-8");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("header", "header-example");
		conn.setDoOutput(true);
		
		try(OutputStream os = conn.getOutputStream()){
			byte[] request = new String("{\"Roma\":\"RM\"}").getBytes("utf-8");
			os.write(request, 0, request.length);
		};
		
		int responseCode = conn.getResponseCode();
		System.out.println("RESPONSE CODE: "+responseCode);
		
		try(BufferedReader br = new BufferedReader(
				new InputStreamReader(conn.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			System.out.println(response.toString());
		}
	}
}