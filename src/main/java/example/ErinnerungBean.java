package example;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ErinnerungBean implements Serializable  {
    public String getNachricht() {
        return nachricht;
    }

    public void setNachricht(String nachricht) {
        this.nachricht = nachricht;
    }

    public Date getFrist() {
        return frist;
    }

    public void setFrist(Date frist) {
        this.frist = frist;
    }

    public List<String> getAdressen() {
        return adressen;
    }

    public void setAdressen(List<String> adressen) {
        this.adressen = adressen;
    }

    String nachricht;
    Date frist;
    List<String> adressen;
}
