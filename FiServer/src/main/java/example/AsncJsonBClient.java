package example;

import com.sun.jersey.api.client.AsyncWebResource;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

public class AsncJsonBClient {

    private static final Logger logger = Logger.getLogger(AsncJsonBClient.class.getName());

    public static void main(String[] args)  {

        Jsonb jsonb = JsonbBuilder.create();
        ClientConfig cc = new DefaultClientConfig();
        AsyncWebResource service = Client.create(cc).asyncResource(UriBuilder.fromUri("http://localhost:8080/fc/rest/Erinnerungen").build());

        ErinnerungBean eb = new ErinnerungBean();
        eb.adressen = new ArrayList<String>();
        eb.frist = new Date();
        eb.nachricht = "This is it";
        String erinAsJson = jsonb.toJson(eb);
        service.entity(erinAsJson, MediaType.APPLICATION_JSON).post();
    }
}