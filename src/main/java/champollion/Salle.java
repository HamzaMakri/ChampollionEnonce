package champollion;

import java.util.ArrayList;

public class Salle {
    public String intitule;
    public int capacite;
    public ArrayList<Intervention> occupations = new ArrayList<>();

    public Salle(String intitule, int capacite) {
        this.intitule = intitule;
        this.capacite = capacite;
    }

    public String getIntitule() {
        return intitule;
    }

    public int getCapacite() {
        return capacite;
    }

    public ArrayList<Intervention> getOccupations() {
        return occupations;
    }
}
