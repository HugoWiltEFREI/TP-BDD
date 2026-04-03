package tp.ecole.ui;

import tp.ecole.util.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe de test de la connexion JDBC (Question 1.2).
 *
 * Exécutez cette classe AVANT d'implémenter les DAOs pour vérifier
 * que ConnectionManager.getConnection() fonctionne correctement.
 *
 * Résultat attendu en console si la connexion réussit :
 * [OK] Connexion à la base Ecole réussie !
 * Catalogue : ecole
 */
public class TestConnection {

    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println("Tentative de connexion à la base Ecole...");
        try (Connection conn = ConnectionManager.getConnection()) {
            System.out.println("[OK] Connexion à la base Ecole réussie !");
            System.out.println("Catalogue : " + conn.getCatalog());
            System.out.println("Auto-commit actuel : " + conn.getAutoCommit());
        } catch (UnsupportedOperationException e) {
            System.out.println("[TODO] ConnectionManager pas encore implémenté : " +
                    e.getMessage());
        } catch (SQLException e) {
            System.err.println("[ERREUR] Connexion échouée : " + e.getMessage());
            System.err.println("Vérifiez l'URL, le nom d'utilisateur et le mot de passe dans ConnectionManager.");
        }
    }
}
