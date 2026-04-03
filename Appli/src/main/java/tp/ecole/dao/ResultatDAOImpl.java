package tp.ecole.dao;

import tp.ecole.model.Resultat;
import tp.ecole.util.ConnectionManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultatDAOImpl implements ResultatDAO {

    @Override
    public List<Resultat> findByEleve(int numEleve) throws SQLException {
        List<Resultat> resultats = new ArrayList<>();
        String sql = "SELECT * FROM RESULTATS WHERE NUM_ELEVE = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, numEleve);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Resultat resultat = mapResultSetToResultat(rs);
                    resultats.add(resultat);
                }
            }
        }
        return resultats;
    }

    @Override
    public void enregistrerInscription(int numEleve, int numCours) throws SQLException {
        String sql = "INSERT INTO RESULTATS (NUM_ELEVE, NUM_COURS, POINTS) VALUES (?, ?, ?)";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, numEleve);
            stmt.setInt(2, numCours);
            stmt.setDouble(3, 0.0); // Note initiale à 0

            stmt.executeUpdate();
        }
    }

    @Override
    public void modifierNote(int numEleve, int numCours, double points) throws SQLException {
        String sql = "UPDATE RESULTATS SET POINTS = ? WHERE NUM_ELEVE = ? AND NUM_COURS = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDouble(1, points);
            stmt.setInt(2, numEleve);
            stmt.setInt(3, numCours);

            stmt.executeUpdate();
        }
    }

    // Méthode utilitaire pour mapper un ResultSet en objet Resultat
    private Resultat mapResultSetToResultat(ResultSet rs) throws SQLException {
        Resultat resultat = new Resultat();
        resultat.setNum_eleve(rs.getInt("NUM_ELEVE"));
        resultat.setNum_cours(rs.getInt("NUM_COURS"));
        resultat.setPoints((int) rs.getDouble("POINTS"));
        return resultat;
    }
}