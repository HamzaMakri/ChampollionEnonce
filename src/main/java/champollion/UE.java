package champollion;

import java.util.ArrayList;

public class UE {

    private final String myIntitule;
    private int heuresCM;
    private int heuresTD;
    private int heuresTP;
    private Intervention seancesPlanifiees;

    public UE(String intitule) {
        myIntitule = intitule;
    }

    public String getIntitule() {
        return myIntitule;
    }

    public String getMyIntitule() {
        return myIntitule;
    }

    public int getHeuresCM() {
        return heuresCM;
    }

    public int getHeuresTD() {
        return heuresTD;
    }

    public int getHeuresTP() {
        return heuresTP;
    }

    public Intervention getSeancesPlanifiees() {
        return seancesPlanifiees;
    }
}
