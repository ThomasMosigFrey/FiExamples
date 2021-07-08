package example;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationPath("/rest")
@Path("/Erinnerungen")
public class ErinnerungsService extends Application implements  Erinnerung {
    public static final Logger logger = Logger.getLogger(ErinnerungsService.class.getCanonicalName());
    public static final HashMap<String, ErinnerungBean> hash = new HashMap<String, ErinnerungBean>();

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Override
    public Response erinnerungErfassen(ErinnerungBean erinnerung) {
        try {
            speichereErinnerung(erinnerung);
        } catch (Error e) {
            logger.log(Level.SEVERE,"Error creating Erinnerung: for {0}", new Object[]{e.getMessage()});
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch(RuntimeException er) {
            throw er;
        } finally {
            return Response.ok().build();
        }
    }

    @POST
    @Path("send/{id}/{emailAdresse}")
    @Override
    public Response erinnerungVerschicken(@PathParam("id") String id, @PathParam("emailAdresse") String emailAdresse) {
        try {
            sendeErinnerung(id, emailAdresse);
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Error sending Erinnerung: for {0}", new Object[]{e.getMessage()});
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            return Response.ok().build();
        }
    }

    @DELETE
    @Path("{id}")
    @Override
    public Response erinnerungLoeschen(@PathParam("id") String id) {
        try {
            loescheErinnerung(id);
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Error deleting Erinnerung: for {0}", new Object[]{e.getMessage()});
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            return Response.ok().build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Override
    public Response erinnerungAendern(@PathParam("id") String id, ErinnerungBean erinnerung) {
        try {
            aendereErinnerung(id, erinnerung);
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Error updating Erinnerung: for {0}", new Object[]{e.getMessage()});
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            return Response.ok().build();
        }
    }


    private void loescheErinnerung(String id) {
        hash.remove(id);
        logger.log(Level.INFO,"Erinnerung geloescht: {0}", id);
    }

    private void aendereErinnerung(String id, ErinnerungBean erinnerung) {
        hash.replace(id, erinnerung);
        logger.log(Level.INFO,"Erinnerung geaendert: {0}", id);
    }

    private void speichereErinnerung(ErinnerungBean erinnerung) {
        hash.put(""+erinnerung.hashCode(), erinnerung);
        logger.log(Level.INFO,"Erinnerung gespeichert: {0}", erinnerung.hashCode());
    }

    private void sendeErinnerung(String id, String email) {
        logger.log(Level.INFO,"Erinnerung gesandt: {0}, {1}", new Object[]{id,email});
    }

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(ErinnerungsService.class);
        return classes;
    }
}
