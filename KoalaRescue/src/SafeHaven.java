import java.util.ArrayList;

public class SafeHaven {

    private ArrayList<Koala> safeKoala;

    public SafeHaven()
    {
        safeKoala = new ArrayList<Koala>();
    }

    public SafeHaven(ArrayList<Koala> newSafeKoala)
    {
        safeKoala = newSafeKoala;
    }

    public ArrayList<Koala> getSafeKoala() {
        return safeKoala;
    }

    public void setSafeKoala(ArrayList<Koala> safeKoala) {
        this.safeKoala = safeKoala;
    }


}
