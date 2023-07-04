package com.github.macgarcia.access.control.desktop.integracao;

import com.google.gson.Gson;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author macgarcia
 */
public class ClientApiBackup {

    private final String URL_API = "http://localhost:8081/api/";
    private final Client client;
    private final WebTarget webTarget;
    private final Gson gson;

    public ClientApiBackup() {
        client = ClientBuilder.newClient();
        webTarget = client.target(URL_API);
        gson = new Gson();
    }
    
    public IntegracaoResponse postIntegrarDados(final String json) {
        var response = webTarget.request(MediaType.APPLICATION_JSON)
                .post(Entity.json(json), String.class);
        client.close();
        return gson.fromJson(response, IntegracaoResponse.class);
    }

}
