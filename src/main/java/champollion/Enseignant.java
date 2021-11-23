package champollion;

import java.util.ArrayList;

public class Enseignant extends Personne {

    // TODO : rajouter les autres méthodes présentes dans le diagramme UML
    private final ArrayList<ServicePrevu> servicesPrevusList = new ArrayList<>();
    private final ArrayList<Intervention> interventionsPlanifieesList = new ArrayList<>();

    public Enseignant(String nom, String email) {
        super(nom, email);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        // TODO: Implémenter cette méthode
        int somme = 0;
        for (ServicePrevu s : servicesPrevusList){
            somme = (int) Math.round(somme + (s.getVolumeTP()*0.75) + s.getVolumeCM() + s.getVolumeTD());
        }
        return somme;
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        // TODO: Implémenter cette méthode

        int somme = 0;
        for (ServicePrevu s : servicesPrevusList){
            if (s.getUe() == ue){
                somme = (int) Math.round(somme + (s.getVolumeTP()*0.75) + (s.getVolumeCM()*1.5) + s.getVolumeTD());
            }
        }
        return somme;
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        // TODO: Implémenter cette méthode
        servicesPrevusList.add(new ServicePrevu(volumeCM,volumeTD,volumeTP,ue,this));
    }



    public boolean enSousService() throws Exception {
        int nbHeuresPrevues = heuresPrevues();
        double nbHeuresRealisees = 0;

        for (Intervention inter : interventionsPlanifieesList){
            nbHeuresRealisees = nbHeuresRealisees + inter.dureeEquivalentTD();
        }

        if( nbHeuresPrevues-nbHeuresRealisees > 0){
            return true;
        }else{
            return false;
        }

    }

    public void ajouteIntervention(Intervention inter){
        interventionsPlanifieesList.add(inter);
    }

    public int resteAPlanifier(UE ue, TypeIntervention type) throws Exception {
        double sommeInter = 0;

        for(Intervention inter : interventionsPlanifieesList){
            if (inter.getType().equals(type) && inter.getMatiere().equals(ue)){
                sommeInter = sommeInter + inter.dureeEquivalentTD();
            }
        }

        for (ServicePrevu servicePrevu : servicesPrevusList) {
            if (servicePrevu.getUe().equals(ue)) {
                switch (type) {
                    case TP:
                        sommeInter = sommeInter - servicePrevu.getVolumeTP();
                        break;
                    case TD:
                        sommeInter = sommeInter - servicePrevu.getVolumeTD();
                        break;
                    case CM:
                        sommeInter = sommeInter - servicePrevu.getVolumeCM();
                        break;
                }
            }
        }
        return (int) Math.round(sommeInter);
    }

    public ArrayList<ServicePrevu> getServicesPrevusList() {
        return servicesPrevusList;
    }

    public ArrayList<Intervention> getInterventionsPlanifieesList() {
        return interventionsPlanifieesList;
    }
}
