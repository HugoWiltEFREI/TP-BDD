package tp.ecole.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitaire pour la gestion de la connexion JDBC à la base Ecole.
 *
 * =====================================================================
 * TODO (Question 1.2) :
 *   - Renseignez les constantes DB_URL, DB_USER et DB_PASSWORD
 *     avec les paramètres de connexion à VOTRE instance MySQL.
 *   - La méthode getConnection() doit retourner une connexion active.
 *   - Testez-la via la classe TestConnection dans le package tp.ecole.main.
 * =====================================================================
 */
public class ConnectionManager {

    // ---------------------------------------------------------------
    // TODO : renseignez les paramètres de connexion
    // ---------------------------------------------------------------
    private static final String DB_URL      = "jdbc:mysql://localhost:3306/ecole?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER     = "utilisateur_TP6";       // à modifier
    private static final String DB_PASSWORD = "Azerty123$";           // à modifier
    // ---------------------------------------------------------------

    /** Constructeur privé : classe utilitaire, pas d'instanciation. */
    private ConnectionManager() {}

    /**
     * Retourne une nouvelle connexion JDBC vers la base Ecole.
     *
     * @return une {@link Connection} ouverte
     * @throws SQLException si la connexion échoue
     *
     * TODO : complétez cette méthode (elle est déjà fonctionnelle si
     *        les constantes ci-dessus sont correctement renseignées).
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
