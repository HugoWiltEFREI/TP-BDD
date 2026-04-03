package tp.ecole.ui;

import tp.ecole.service.EcoleService;

import java.sql.SQLException;

/**
 * Test du service transactionnel EcoleService (Questions 6 et 7).
 *
 * Ce programme effectue deux tests :
 *
 * TEST A – Note valide (doit réussir) :
 *   Inscrit l'élève n°1 au cours n°3 (si non déjà inscrit) avec la note 14.
 *   => L'inscription ET la note doivent être persistées.
 *
 * TEST B – Note invalide (doit échouer et rollback) :
 *   Inscrit l'élève n°2 au cours n°3 avec la note 25 (hors de [0-20]).
 *   => Si un trigger de contrôle existe (TP4), la transaction doit
 *      être annulée : ni l'inscription ni la note ne doivent apparaître.
 *
 * Après chaque test, vérifiez manuellement en base avec :
 *   SELECT * FROM RESULTATS WHERE NUM_COURS = 3;
 */
public class MainServiceTest {

    public static void main(String[] args) {
        EcoleService service = new EcoleService();

        // -------------------------------------------------------
        // TEST A : transaction avec note valide
        // -------------------------------------------------------
        System.out.println("=== TEST A : inscription avec note valide (14) ===");
        try {
            service.inscrireEtAttribuerNote(1, 3, 14.0);
            System.out.println("[OK] Élève 1 inscrit au cours 3 avec la note 14.");
        } catch (UnsupportedOperationException e) {
            System.out.println("[TODO] EcoleService non implémenté : " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("[ERREUR] Transaction annulée : " + e.getMessage());
        }

        System.out.println();

        // -------------------------------------------------------
        // TEST B : transaction avec note invalide  (Question 7)
        // -------------------------------------------------------
        System.out.println("=== TEST B : inscription avec note INVALIDE (25) ===");
        try {
            service.inscrireEtAttribuerNote(2, 3, 25.0);
            System.out.println("[?] Service terminé sans exception.");
            System.out.println("    Si aucun trigger n'existe, l'inscription a peut-être réussi.");
            System.out.println("    Vérifiez en base : SELECT * FROM RESULTATS WHERE NUM_ELEVE=2 AND NUM_COURS=3;");
        } catch (UnsupportedOperationException e) {
            System.out.println("[TODO] EcoleService non implémenté : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("[OK – ROLLBACK] Exception interceptée : " + e.getMessage());
            System.out.println("    Vérifiez que l'élève 2 n'est PAS inscrit au cours 3.");
        }

        System.out.println("\n=== FIN DES TESTS SERVICE ===");
    }
}
