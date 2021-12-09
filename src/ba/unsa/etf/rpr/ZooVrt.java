package ba.unsa.etf.rpr;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ZooVrt {
    private List<Zivotinja> zivotinje;

    public ZooVrt() {
        zivotinje = new ArrayList<>();
    }

    int broj() {
        return zivotinje.size();
    }

    String dajTabelu() {
        String tabela = "";
        for (Zivotinja zivotinja : zivotinje) {
            tabela += zivotinja.getIme() + " (";
            if (zivotinja instanceof SpecijalnaZivotinja) tabela += ((SpecijalnaZivotinja) zivotinja).getVrsta();
            else tabela += zivotinja.getClass().getSimpleName();
            tabela += ") : " + zivotinja.getId() + "\n";
        }
        return tabela;
    }

    void dodaj(Class vrsta, String ime, String id) throws DvostrukiIdException, NeispravanFormatIdaException {
        for (Zivotinja zivotinja : zivotinje) {
            if (zivotinja.getId().equals(id)) throw new DvostrukiIdException();
        }
        if (vrsta.getSimpleName().equals("Vuk")) zivotinje.add(new Vuk(id, ime));
        if (vrsta.getSimpleName().equals("DomaciPas")) zivotinje.add(new DomaciPas(id, ime));
        if (vrsta.getSimpleName().equals("Lav")) zivotinje.add(new Lav(id, ime));
        if (vrsta.getSimpleName().equals("Tigar")) zivotinje.add(new Tigar(id, ime));
        if (vrsta.getSimpleName().equals("DomacaMacka")) zivotinje.add(new DomacaMacka(id, ime));
    }

    void dodaj(Zivotinja novaZivotinja) throws DvostrukiIdException {
        for (Zivotinja zivotinja : zivotinje) {
            if (zivotinja.getId().equals(novaZivotinja.getId())) throw new DvostrukiIdException();
        }
        zivotinje.add(novaZivotinja);
    }

    void dodaj(Class vrsta, String ime) throws NeispravanFormatIdaException, DvostrukiIdException {
        String broj;
        if (zivotinje.size() == 0) broj = "1";
        else broj = "" + (Integer.parseInt(zivotinje.get(zivotinje.size()-1).getId().split("-")[1]) + 1);
        String imeZaId = ime.toLowerCase().replaceAll("[^a-zčćšđž]", "").
                replaceAll("č", "c").replaceAll("ć", "c").
                replaceAll("š", "s").replaceAll("đ", "d").
                replaceAll("ž", "z");
        dodaj(vrsta, ime, imeZaId + "-" + broj);
    }

    void dodaj(String vrsta, String ime, String id, Supplier<String> glasanje) throws NeispravanFormatIdaException {
        zivotinje.add(new SpecijalnaZivotinja(id, ime, vrsta, glasanje));
    }

    void obrisi(String id) {
        for (Zivotinja zivotinja : zivotinje) {
            if (zivotinja.getId().equals(id)) {
                zivotinje.remove(zivotinja);
                return;
            }
        }
    }

    Set<Zivotinja> koToTamoGovori(String glasanja) {
        Set<Zivotinja> oneKojeSeGlasaju = new HashSet<>();
        String[] rastavljenaGlasanja = glasanja.split(",");
        for (int i = 0; i < rastavljenaGlasanja.length; i++) {
            boolean nadjena = false;
            for (Zivotinja zivotinja : zivotinje) {
                if (zivotinja.glas().equals(rastavljenaGlasanja[i])) {
                    oneKojeSeGlasaju.add(zivotinja);
                    nadjena = true;
                    break;
                }
            }
            if (!nadjena) throw new IllegalArgumentException();
        }
        return oneKojeSeGlasaju;
    }
}
