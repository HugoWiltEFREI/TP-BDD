package tp.ecole.dao;

import tp.ecole.model.Eleve;
import tp.ecole.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EleveDAOImpl implements EleveDAO {

    @Override
    public List<Eleve> findAll() throws SQLException {
        List<Eleve> eleves = new ArrayList<>();
        String sql = "SELECT * FROM ELEVES";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Eleve eleve = mapResultSetToEleve(rs);
                eleves.add(eleve);
            }
        }
        return eleves;
    }

    @Override
    public Eleve findById(int numEleve) throws SQLException {
        String sql = "SELECT * FROM ELEVES WHERE NUM_ELEVE = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, numEleve);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEleve(rs);
                }
            }
        }
        return null; //L'élève est nul
    }

    @Override
    public List<Eleve> findByNom(String nom) throws SQLException {
        List<Eleve> eleves = new ArrayList<>();
        String sql = "SELECT * FROM ELEVES WHERE NOM LIKE ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, "%" + nom + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Eleve eleve = mapResultSetToEleve(rs);
                    eleves.add(eleve);
                }
            }
        }
        return eleves;
    }

    @Override
    public void save(Eleve eleve) throws SQLException {
        String sql = "INSERT INTO ELEVES (NUM_ELEVE, NOM, PRENOM, DATE_NAISSANCE, POIDS, ANNEE, GENRE) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, eleve.getNumEleve());
            stmt.setString(2, eleve.getNom());
            stmt.setString(3, eleve.getPrenom());
            stmt.setObject(4, eleve.getDateNaissance());
            stmt.setDouble(5, eleve.getPoids());
            stmt.setInt(6, eleve.getAnnee());
            stmt.setString(7, eleve.getGenre());

            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Eleve eleve) throws SQLException {
        String sql = "UPDATE ELEVES SET NOM = ?, PRENOM = ?, DATE_NAISSANCE = ?, POIDS = ?, ANNEE = ?, GENRE = ? WHERE NUM_ELEVE = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, eleve.getNom());
            stmt.setString(2, eleve.getPrenom());
            stmt.setObject(3, eleve.getDateNaissance());
            stmt.setDouble(4, eleve.getPoids());
            stmt.setInt(5, eleve.getAnnee());
            stmt.setString(6, eleve.getGenre());
            stmt.setInt(7, eleve.getNumEleve());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int numEleve) throws SQLException {
        String sql = "DELETE FROM ELEVES WHERE NUM_ELEVE = ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, numEleve);
            stmt.executeUpdate();
        }
    }

    private Eleve mapResultSetToEleve(ResultSet rs) throws SQLException {
        Eleve eleve = new Eleve();
        eleve.setNumEleve(rs.getInt("NUM_ELEVE"));
        eleve.setNom(rs.getString("NOM"));
        eleve.setPrenom(rs.getString("PRENOM"));
        eleve.setDateNaissance(rs.getObject("DATE_NAISSANCE", java.time.LocalDate.class));
        eleve.setPoids(rs.getDouble("POIDS"));
        eleve.setAnnee(rs.getInt("ANNEE"));
        eleve.setGenre(rs.getString("GENRE"));
        return eleve;
    }
}