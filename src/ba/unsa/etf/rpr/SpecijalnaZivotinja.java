package ba.unsa.etf.rpr;

import java.util.function.Supplier;

public class SpecijalnaZivotinja extends Zivotinja {
    private String vrsta;
    private Supplier<String> glasanje;

    public SpecijalnaZivotinja(String id, String ime, String vrsta, Supplier<String> glasanje) throws NeispravanFormatIdaException {
        super(id, ime);
        this.vrsta = vrsta;
        this.glasanje = glasanje;
    }

    public String getVrsta() {
        return vrsta;
    }

    @Override
    public String glas() {
        return glasanje.get();
    }
}
