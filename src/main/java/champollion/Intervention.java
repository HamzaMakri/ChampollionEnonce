package champollion;

import java.util.Date;

public class Intervention {
    private Date debut;
    private int duree;
    private boolean annulee;
    public int heureDebut;
    private TypeIntervention type;
    private final Salle lieu;
    private final UE matiere;
    private final Enseignant intervenant;

    public Intervention(Date debut, int duree, boolean annulee, int heureDebut, TypeIntervention type, Salle lieu, UE matiere, Enseignant intervenant) {
        this.debut = debut;
        this.duree = duree;
        this.annulee = false;
        this.heureDebut = heureDebut;
        this.type = type;
        this.lieu = lieu;
        this.matiere = matiere;
        this.intervenant = intervenant;
    }

    // ----------------- AJOUT DE FONCTIONS -----------------

    public double dureeEquivalentTD() throws Exception {
        switch (type){
            case TP :
                return getDuree() * 0.75 ;

            case TD :
                return this.getDuree();

            case CM :
                return this.getDuree() * 1.5;

            default:
                throw new Exception("Type Incorrect");
        }
    }

    //  ----------------- GETTERS -----------------

    public Date getDebut() {
        return debut;
    }

    public int getDuree() {
        return duree;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public boolean isAnnulee() {
        return annulee;
    }

    public TypeIntervention getType() {
        return type;
    }

    public Enseignant getIntervenant() {
        return intervenant;
    }

    public Salle getLieu() {
        return lieu;
    }

    public UE getMatiere() {
        return matiere;
    }
    //  ----------------- SETTERS -----------------

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setAnnulee(boolean annulee) {
        this.annulee = annulee;
    }

    public void setType(TypeIntervention type) {
        this.type = type;
    }

}
