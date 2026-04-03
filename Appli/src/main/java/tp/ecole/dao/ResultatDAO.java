package tp.ecole.dao;

import tp.ecole.model.Resultat;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface DAO pour l'entité Resultat (table RESULTATS).
 *
 * =====================================================================
 * TODO (Question 3.2) :
 *   Toutes les méthodes ci-dessous sont à implémenter dans
 *   la classe ResultatDAOImpl (même package).
 *
 * Note sur les transactions (Question 6) :
 *   Les méthodes enregistrerInscription et modifierNote seront appelées
 *   dans un contexte transactionnel géré par EcoleService.
 *   Pour cela, elles doivent accepter une Connection en paramètre
 *   (plutôt que d'en ouvrir une elles-mêmes) afin que la transaction
 *   soit contrôlée depuis la couche service.
 *   => Vous devez adapter les signatures une fois arrivés à cette partie.
 * =====================================================================
 */
public interface ResultatDAO {

    /**
     * Retourne tous les résultats d'un élève donné.
     *
     * @param numEleve l'identifiant de l'élève
     * @return liste des résultats (notes) de cet élève
     * @throws SQLException en cas d'erreur JDBC
     */
    List<Resultat> findByEleve(int numEleve) throws SQLException;

    /**
     * Inscrit un élève à un cours (INSERT dans RESULTATS, sans note).
     *
     * @param numEleve l'identifiant de l'élève
     * @param numCours l'identifiant du cours
     * @throws SQLException en cas d'erreur JDBC (doublon de clé, FK violée, etc.)
     */
    void enregistrerInscription(int numEleve, int numCours) throws SQLException;

    /**
     * Met à jour la note (POINTS) d'un élève pour un cours donné.
     *
     * @param numEleve l'identifiant de l'élève
     * @param numCours l'identifiant du cours
     * @param points   la nouvelle note (doit être comprise entre 0 et 20)
     * @throws SQLException en cas d'erreur JDBC ou de trigger refusant la note
     */
    void modifierNote(int numEleve, int numCours, double points) throws SQLException;
}
