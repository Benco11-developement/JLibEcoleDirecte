package fr.benco11.jlibecoledirecte.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
	/**
	 * Effectue une requête HTTP et renvoie le résultat
	 * @param address adresse du serveur ciblé
	 * @param body contenu du message envoyé si <code>output</code> est <code>true</code> et si la méthode est <code>POST</code>
	 * @param method méthode utilisé, soit <code>POST</code> soit <code>GET</code>
	 * @param input si <code>true</code> alors la réponse sera réceptionnée
	 * @param output si <code>true</code> alors <code>body</code> sera envoyé
	 * @return le résultat de la requête
	 * @throws IOException une erreur réseau s'est produite
	 */
	
	public static String sendRequest(String address, String body, String method, boolean input, boolean output) throws IOException {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(address).openConnection();
			connection.setRequestMethod(method);
			connection.setDoInput(input);
			connection.setDoOutput(output);
			connection.setRequestProperty("user-agent", "Mozilla/5.0 (X11; Linux x86_64; rv:77.0) Gecko/20100101 Firefox/77.0");
			PrintWriter pw = null;
			if(output && method.equalsIgnoreCase("POST")) {
				pw = new PrintWriter(connection.getOutputStream());
				pw.write(body);
				pw.flush();
			}
			
			StringBuilder result = null;
			
			if(input) {
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF8"));
				result = new StringBuilder();
				String f = br.readLine();
				while(f != null) {
					result.append(f).append("\n");
					f = br.readLine();
				}
				
				br.close();
			}
			if(pw != null)
				pw.close();
			connection.disconnect();
			return (result != null) ? result.toString() : "";
			
		} catch (IOException e) {
			throw new IOException();
		}
	}
}
