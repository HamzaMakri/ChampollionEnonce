package champollion;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");		
	}


	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
	}

	@Test
	public void testAjouteHeures() {
                // 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);


		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

                // 20h TD pour UML
                untel.ajouteEnseignement(uml, 0, 20, 0);

		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");

	}

	/*
	*
	*  ================ TESTS AJOUTES  ================
	*
	 */

	@Test
	public void testAjouteEnseignement(){
		untel.ajouteEnseignement(java,0,0,0);

		assertFalse(untel.getServicesPrevusList().isEmpty());
	}

	@Test
	public void testAjouteIntervention() throws Exception {

		untel.ajouteIntervention(new Intervention(new Date(), 3, false, 8, TypeIntervention.TP, new Salle("B019", 35), java, untel));
		Enseignant untel2 = new Enseignant("untel", "untel@gmail.com");


		assertFalse(untel.getInterventionsPlanifieesList().isEmpty());

		// Si l'on ajoute une intervention pour un enseignant different, une erreur doit être lancée

		Exception exception = assertThrows(Exception.class, () -> {
			untel.ajouteIntervention(new Intervention(new Date(), 3, false, 8, TypeIntervention.TP, new Salle("B019", 35), java, untel2));
		});
	}

	@Test
	public void testHeuresPrevues(){

		assertEquals(untel.heuresPrevues(), 0, "Lorsque l'enseignant n'a pas de service prévu, heurePrevues() != 0 ");

		untel.getServicesPrevusList().add(new ServicePrevu(20,20,0, java, untel));
		untel.getServicesPrevusList().add(new ServicePrevu(0,20,10, uml, untel));

		assertEquals(78, untel.heuresPrevues(), "heuresPrevues() ne calcul pas le bon total");
	}

	@Test
	public void testHeuresPrevuesUE(){
		assertEquals(untel.heuresPrevuesPourUE(java), 0, "Lorsque l'enseignant n'a pas de service prévu pour l'UE java, heurePrevuesUE() != 0 ");

		untel.getServicesPrevusList().add(new ServicePrevu(20,20,0, java, untel));
		untel.getServicesPrevusList().add(new ServicePrevu(0,20,10, uml, untel));

		assertEquals(50, untel.heuresPrevuesPourUE(java), "heuresPrevuesPourUE() ne calcul pas le bon total");
	}


	@Test
	public void testEnSousService() throws Exception {
		Salle salle19 = new Salle("B019", 35);

		untel.ajouteEnseignement(java, 0, 5, 10);
		untel.ajouteEnseignement(uml, 10, 0, 0);

		untel.ajouteIntervention(new Intervention(new Date(), 10, false, 9, TypeIntervention.TP, salle19,java, untel ));
		untel.ajouteIntervention(new Intervention(new Date(), 10, false, 9, TypeIntervention.CM, salle19,uml, untel ));

		assertTrue(untel.enSousService());

		untel.ajouteIntervention(new Intervention(new Date(), 5, false, 9, TypeIntervention.TD, salle19,java, untel ));

		assertFalse(untel.enSousService());

	}

	@Test
	public void testResteAPlanifier() throws Exception {
		Salle salle19 = new Salle("B019", 35);

		untel.ajouteEnseignement(java, 0, 5, 10);
		untel.ajouteEnseignement(uml, 10, 0, 30);

		untel.ajouteIntervention(new Intervention(new Date(), 10, false, 9, TypeIntervention.TP, salle19,java, untel ));
		untel.ajouteIntervention(new Intervention(new Date(), 10, false, 9, TypeIntervention.CM, salle19,uml, untel ));

		assertEquals(5, untel.resteAPlanifier(java, TypeIntervention.TD));
		assertEquals(23, untel.resteAPlanifier(uml, TypeIntervention.TP));
	}



}
