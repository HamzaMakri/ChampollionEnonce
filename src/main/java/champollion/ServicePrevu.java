package champollion;

public class ServicePrevu {

    public int volumeCM;
    public int volumeTD;
    public int volumeTP;
    public UE ue;
    private final Enseignant enseignant;


    public ServicePrevu(int volumeCM, int volumeTD, int volumeTP, UE ue, Enseignant enseignant) {
        this.volumeCM = volumeCM;
        this.volumeTD = volumeTD;
        this.volumeTP = volumeTP;
        this.ue = ue;
        this.enseignant = enseignant;
    }

    public UE getUe() {
        return ue;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public int getVolumeCM() {
        return volumeCM;
    }

    public int getVolumeTP() {
        return volumeTP;
    }

    public int getVolumeTD() {
        return volumeTD;
    }
}
