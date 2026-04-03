package tp.ecole.dao;

import tp.ecole.model.Eleve;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface DAO pour l'entité Eleve.
 *
 * =====================================================================
 * TODO (Question 3.1) :
 *   Toutes les méthodes ci-dessous sont à implémenter dans
 *   la classe EleveDAOImpl (même package).
 *
 * Contraintes d'implémentation (Question 4) :
 *   - Utiliser ConnectionManager.getConnection() pour obtenir une connexion.
 *   - Utiliser des PreparedStatement (jamais de Statement simple).
 *   - Utiliser try-with-resources pour fermer PreparedStatement et ResultSet.
 *   - Mapper les ResultSet vers des objets Eleve.
 *   - Gérer les SQLException (propagation ou encapsulation dans DaoException).
 * =====================================================================
 */
public interface EleveDAO {

    /**
     * Retourne la liste de tous les élèves.
     *
     * @return liste (éventuellement vide) de tous les élèves
     * @throws SQLException en cas d'erreur JDBC
     */
    List<Eleve> findAll() throws SQLException;

    /**
     * Recherche un élève par son numéro.
     *
     * @param numEleve la clé primaire de l'élève
     * @return l'élève trouvé, ou {@code null} s'il n'existe pas
     * @throws SQLException en cas d'erreur JDBC
     */
    Eleve findById(int numEleve) throws SQLException;

    /**
     * Recherche les élèves dont le nom correspond (recherche partielle possible).
     *
     * @param nom le nom (ou début de nom) à rechercher
     * @return liste des élèves correspondants
     * @throws SQLException en cas d'erreur JDBC
     */
    List<Eleve> findByNom(String nom) throws SQLException;

    /**
     * Insère un nouvel élève dans la base.
     *
     * @param eleve l'élève à insérer (numEleve doit être renseigné)
     * @throws SQLException en cas d'erreur JDBC (doublon de clé, etc.)
     */
    void save(Eleve eleve) throws SQLException;

    /**
     * Met à jour un élève existant.
     *
     * @param eleve l'élève avec les nouvelles valeurs (identifié par numEleve)
     * @throws SQLException en cas d'erreur JDBC
     */
    void update(Eleve eleve) throws SQLException;

    /**
     * Supprime un élève de la base.
     *
     * @param numEleve la clé primaire de l'élève à supprimer
     * @throws SQLException en cas d'erreur JDBC (violation de contrainte, etc.)
     */
    void delete(int numEleve) throws SQLException;
}
