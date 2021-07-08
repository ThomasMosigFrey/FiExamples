package jee.example.example;

import javax.ws.rs.core.Response;

public interface Erinnerung {
    public Response erinnerungErfassen(ErinnerungBean erinnerung);
    public Response erinnerungVerschicken(String id, String emailAdresse);
    public Response erinnerungLoeschen(String id);
    public Response erinnerungAendern(String id, ErinnerungBean erinnerung);
}
