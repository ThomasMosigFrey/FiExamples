package example;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.InvocationCallback;
import jakarta.ws.rs.client.WebTarget;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.core.MediaType;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class AsncJsonBClient {

    private static final Logger logger = Logger.getLogger(AsncJsonBClient.class.getName());
    private static final String ERINNERUNGS_SERVICE = "http://localhost:8080/fc/rest/Erinnerungen/async";


    public static void main(String[] args) throws URISyntaxException {

        Jsonb jsonb = JsonbBuilder.create();
        ErinnerungBean eb = new ErinnerungBean();
        eb.adressen = new ArrayList<String>();
        eb.frist = new Date();
        eb.nachricht = "This is it";
        String erinAsJson = jsonb.toJson(eb);

        // call to async service
        ErinnerungBeanInvocationCallback erinnerungBeanInvocationCallback = new ErinnerungBeanInvocationCallback();
        WebTarget fiTarget = ClientBuilder.newClient().target(ERINNERUNGS_SERVICE);

        Entity<String> entity = Entity.entity(erinAsJson, MediaType.APPLICATION_JSON);
        Future<String> response = fiTarget.request().async().get(erinnerungBeanInvocationCallback);
    }

    private static class ErinnerungBeanInvocationCallback implements InvocationCallback<String> {
        ErinnerungBeanInvocationCallback() {
        }

        @Override
        public void completed(String r) {
        }

        @Override
        public void failed(Throwable t) {
            t.printStackTrace();
        }
    }
}