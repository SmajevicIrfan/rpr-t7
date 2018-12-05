package ba.unsa.etf.rpr.tutoriajal7;

import java.util.ArrayList;
import java.util.List;

public class UN {
    private List<Drzava> drzave;

    public UN() {
        drzave = new ArrayList<>();
    }

    public List<Drzava> getDrzave() {
        return drzave;
    }

    public void setDrzave(List<Drzava> drzave) {
        this.drzave = drzave;
    }
}
