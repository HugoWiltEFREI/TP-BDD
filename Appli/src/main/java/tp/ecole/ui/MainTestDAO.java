package tp.ecole.ui;

import tp.ecole.dao.EleveDAO;
import tp.ecole.dao.EleveDAOImpl;
import tp.ecole.model.Eleve;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe de test des opérations CRUD via EleveDAO (Question 5).
 *
 * Ce scénario de test vérifie successivement :
 *   1. Insertion d'un nouvel élève via save()
 *   2. Listage de tous les élèves via findAll()
 *   3. Recherche par nom via findByNom()
 *   4. Mise à jour du nom via update()
 *   5. Suppression via delete()
 *   6. Vérification que l'élève n'existe plus via findById()
 *
 * Règle : ce fichier ne contient AUCUN code SQL.
 *         Toutes les opérations passent par EleveDAOImpl.
 */
public class MainTestDAO {

    public static void main(String[] args) {
        EleveDAO eleveDAO = new EleveDAOImpl();

        // ---- Données de test ----
        // Numéro élevé pour ne pas entrer en conflit avec les données existantes
        int numEleve = 999;

        System.out.println("=== TEST DAO : EleveDAOImpl ===\n");

        try {
            // -------------------------------------------------
            // ÉTAPE 1 : Insertion
            // -------------------------------------------------
            System.out.println("--- Étape 1 : Insertion d'un élève de test ---");
            Eleve nouvelEleve = new Eleve(); // TODO : adapter selon votre constructeur
            // TODO : renseignez les attributs de nouvelEleve
            //   nouvelEleve.setNumEleve(numEleve);
            //   nouvelEleve.setNom("TestNom");
            //   nouvelEleve.setPrenom("TestPrenom");
            //   nouvelEleve.setAnnee(1);
            //   ... etc.
            eleveDAO.save(nouvelEleve);
            System.out.println("[OK] Élève inséré : " + nouvelEleve);

            // -------------------------------------------------
            // ÉTAPE 2 : Liste complète
            // -------------------------------------------------
            System.out.println("\n--- Étape 2 : Liste de tous les élèves ---");
            List<Eleve> tous = eleveDAO.findAll();
            tous.forEach(e -> System.out.println("  " + e));
            System.out.printf("[OK] %d élève(s) en base.%n", tous.size());

            // -------------------------------------------------
            // ÉTAPE 3 : Recherche par nom
            // -------------------------------------------------
            System.out.println("\n--- Étape 3 : Recherche par nom 'Test' ---");
            List<Eleve> trouves = eleveDAO.findByNom("Test");
            trouves.forEach(e -> System.out.println("  Trouvé : " + e));

            // -------------------------------------------------
            // ÉTAPE 4 : Mise à jour
            // -------------------------------------------------
            System.out.println("\n--- Étape 4 : Modification du nom ---");
            // TODO : modifier un attribut de nouvelEleve
            //   nouvelEleve.setNom("NomModifie");
            eleveDAO.update(nouvelEleve);
            Eleve apresModif = eleveDAO.findById(numEleve);
            System.out.println("[OK] Après modification : " + apresModif);

            // -------------------------------------------------
            // ÉTAPE 5 : Suppression
            // -------------------------------------------------
            System.out.println("\n--- Étape 5 : Suppression ---");
            eleveDAO.delete(numEleve);
            System.out.println("[OK] Élève n°" + numEleve + " supprimé.");

            // -------------------------------------------------
            // ÉTAPE 6 : Vérification
            // -------------------------------------------------
            Eleve verifie = eleveDAO.findById(numEleve);
            if (verifie == null) {
                System.out.println("[OK] Vérification : l'élève n'existe plus en base.");
            } else {
                System.out.println("[ECHEC] L'élève existe encore en base : " + verifie);
            }

        } catch (UnsupportedOperationException e) {
            System.out.println("[TODO] Méthode non implémentée : " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("[ERREUR SQL] " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n=== FIN DES TESTS DAO ===");
    }
}
