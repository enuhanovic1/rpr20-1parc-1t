package ba.unsa.etf.rpr;

abstract public class Zivotinja {
    private String id;
    String ime;

    public Zivotinja(String id, String ime) throws NeispravanFormatIdaException {
        if (ime.trim().equals("")) throw new IllegalArgumentException("Ime ne može biti prazno");
        if (id.trim().equals("")) throw new IllegalArgumentException("Id ne može biti prazan");
        String[] komponente = id.split("-");
        if (komponente.length != 2) throw new NeispravanFormatIdaException();
        String ocekivano = ime.toLowerCase().replaceAll("[^a-zčćšđž]", "").
                replaceAll("č", "c").replaceAll("ć", "c").
                replaceAll("š", "s").replaceAll("đ", "d").
                replaceAll("ž", "z");
        if (!komponente[0].equals(ocekivano)) throw new NeispravanFormatIdaException();
        String ocekivan = komponente[1].replaceAll("[^0-9]", "");
        if (ocekivan == "" || !komponente[1].equals(ocekivan)) throw new NeispravanFormatIdaException();
        this.id = id;
        this.ime = ime;
    }

    public String getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public void setId(String id) throws NeispravanFormatIdaException {
        String[] komponente = id.split("-");
        if (komponente.length != 2) throw new NeispravanFormatIdaException();
        String ocekivano = ime.toLowerCase().replaceAll("[^a-zčćšđž]", "").
                replaceAll("č", "c").replaceAll("ć", "c").
                replaceAll("š", "s").replaceAll("đ", "d").
                replaceAll("ž", "z");
        if (!komponente[0].equals(ocekivano)) throw new NeispravanFormatIdaException();
        String ocekivan = komponente[1].replaceAll("[^0-9]", "");
        if (ocekivan == "" || !komponente[1].equals(ocekivan)) throw new NeispravanFormatIdaException();
        this.id = id;
    }

    public void setIme(String ime) throws NeispravanFormatIdaException {
        String[] komponente = id.split("-");
        if (komponente.length != 2) throw new NeispravanFormatIdaException();
        String ocekivano = ime.toLowerCase().replaceAll("[^a-zčćšđž]", "").
                replaceAll("č", "c").replaceAll("ć", "c").
                replaceAll("š", "s").replaceAll("đ", "d").
                replaceAll("ž", "z");
        if (!komponente[0].equals(ocekivano)) throw new NeispravanFormatIdaException();
        String ocekivan = komponente[1].replaceAll("[^0-9]", "");
        if (ocekivan == "" || !komponente[1].equals(ocekivan)) throw new NeispravanFormatIdaException();
        this.ime = ime;
    }

    public abstract String glas();
}