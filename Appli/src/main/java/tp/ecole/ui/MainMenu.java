package tp.ecole.ui;

import tp.ecole.dao.EleveDAO;
import tp.ecole.dao.EleveDAOImpl;
import tp.ecole.dao.ResultatDAO;
import tp.ecole.dao.ResultatDAOImpl;
import tp.ecole.model.Cours;
import tp.ecole.model.Eleve;
import tp.ecole.model.Resultat;
import tp.ecole.service.EcoleService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Point d'entrée principal de l'application – menu console interactif.
 *
 * Cette classe constitue la COUCHE INTERFACE UTILISATEUR.
 * Elle ne contient AUCUN code SQL : toutes les opérations passent
 * par les DAOs ou le service métier.
 *
 * Les étudiants n'ont PAS à modifier cette classe.
 * Elle appellera leurs implémentations de DAO et de service.
 */
public class MainMenu {

    private static final EleveDAO   eleveDAO   = new EleveDAOImpl();
    private static final ResultatDAO resultatDAO = new ResultatDAOImpl();
    private static final EcoleService service   = new EcoleService();
    private static final Scanner scanner        = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║     Application École – TP6 JDBC/DAO     ║");
        System.out.println("╚══════════════════════════════════════════╝");

        boolean running = true;
        while (running) {
            afficherMenu();
            String choix = scanner.nextLine().trim();
            System.out.println();
            try {
                switch (choix) {
                    case "1" -> listerEleves();
                    case "2" -> rechercherEleve();
                    case "3" -> listerResultatsEleve();
                    case "4" -> inscrireEtNoter();
                    case "5" -> modifierNote();
                    case "0" -> {
                        System.out.println("Au revoir !");
                        running = false;
                    }
                    default  -> System.out.println("Choix invalide, veuillez réessayer.");
                }
            } catch (UnsupportedOperationException e) {
                System.out.println("[TODO] Cette fonctionnalité n'est pas encore implémentée : " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("[ERREUR SQL] " + e.getMessage());
            }
            System.out.println();
        }
        scanner.close();
    }

    // ----------------------------------------------------------------
    // Menu
    // ----------------------------------------------------------------

    private static void afficherMenu() {
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│  1. Lister tous les élèves               │");
        System.out.println("│  2. Rechercher un élève par nom          │");
        System.out.println("│  3. Voir les résultats d'un élève        │");
        System.out.println("│  4. Inscrire un élève à un cours + note  │");
        System.out.println("│  5. Modifier une note                    │");
        System.out.println("│  0. Quitter                              │");
        System.out.println("└──────────────────────────────────────────┘");
        System.out.print("Votre choix : ");
    }

    // ----------------------------------------------------------------
    // Actions
    // ----------------------------------------------------------------

    private static void listerEleves() throws SQLException {
        System.out.println("=== Liste de tous les élèves ===");
        List<Eleve> eleves = eleveDAO.findAll();
        if (eleves.isEmpty()) {
            System.out.println("Aucun élève trouvé.");
        } else {
            eleves.forEach(System.out::println);
            System.out.printf("→ %d élève(s) au total.%n", eleves.size());
        }
    }

    private static void rechercherEleve() throws SQLException {
        System.out.print("Entrez le nom (ou début de nom) à rechercher : ");
        String nom = scanner.nextLine().trim();
        List<Eleve> eleves = eleveDAO.findByNom(nom);
        if (eleves.isEmpty()) {
            System.out.println("Aucun élève trouvé pour « " + nom + " ».");
        } else {
            eleves.forEach(System.out::println);
        }
    }

    private static void listerResultatsEleve() throws SQLException {
        System.out.print("Numéro de l'élève : ");
        int numEleve = lireEntier();
        List<Resultat> resultats = resultatDAO.findByEleve(numEleve);
        if (resultats.isEmpty()) {
            System.out.println("Aucun résultat pour l'élève n°" + numEleve + ".");
        } else {
            System.out.println("=== Résultats de l'élève n°" + numEleve + " ===");
            resultats.forEach(System.out::println);
        }
    }

    private static void inscrireEtNoter() throws SQLException {
        System.out.print("Numéro de l'élève  : ");
        int numEleve = lireEntier();
        System.out.print("Numéro du cours    : ");
        int numCours = lireEntier();
        System.out.print("Note (0-20)        : ");
        double note = lireDouble();

        service.inscrireEtAttribuerNote(numEleve, numCours, note);
        System.out.printf("✓ Élève %d inscrit au cours %d avec la note %.1f%n",
                numEleve, numCours, note);
    }

    private static void modifierNote() throws SQLException {
        System.out.print("Numéro de l'élève  : ");
        int numEleve = lireEntier();
        System.out.print("Numéro du cours    : ");
        int numCours = lireEntier();
        System.out.print("Nouvelle note (0-20) : ");
        double note = lireDouble();

        resultatDAO.modifierNote(numEleve, numCours, note);
        System.out.printf("✓ Note de l'élève %d au cours %d mise à jour : %.1f%n",
                numEleve, numCours, note);
    }

    // ----------------------------------------------------------------
    // Utilitaires de lecture
    // ----------------------------------------------------------------

    private static int lireEntier() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Valeur entière invalide, réessayez : ");
            }
        }
    }

    private static double lireDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.print("Valeur décimale invalide, réessayez : ");
            }
        }
    }
}
