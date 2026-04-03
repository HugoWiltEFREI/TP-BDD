package tp.ecole.model;

import java.time.LocalDate;

/**
 * POJO représentant un élève de la base ELEVES.
 * <p>
 * Schéma SQL de référence :
 * NUM_ELEVE  NUMERIC(6)  PK
 * NOM        VARCHAR(25)
 * PRENOM     VARCHAR(25)
 * LocalDate_NAISSANCE LocalDate
 * POIDS      NUMERIC(3)
 * ANNEE      NUMERIC(1)
 * GENRE      CHAR(1)
 * <p>
 * =====================================================================
 * TODO (Question 2) :
 *   1. Ajoutez les attributs privés correspondant aux colonnes ci-dessus.
 *   2. Créez un constructeur sans argument et un constructeur complet.
 *   3. Générez (ou écrivez) les getters et setters pour chaque attribut.
 *   4. Redéfinissez toString() pour afficher l'élève de façon lisible.
 * <p>
 * TODO : Répondre à la question :
 *   En quoi ce modèle objet diffère-t-il du modèle relationnel ?
 * =====================================================================
 */
// TODO : déclarez les attributs privés (numEleve, nom, prenom,
//         LocalDateNaissance, poids, annee, genre)
public class Eleve {
    private int numEleve;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private double poids;
    private int annee;
    private String genre;

    // TODO : constructeur par défaut
    public Eleve() {
        this.numEleve = 0;
        this.nom = "";
        this.prenom = "";
    }

    // TODO : constructeur avec tous les paramètres
    public Eleve(int numEleve, String nom, String prenom, LocalDate dateNaissance, double poids, int annee, String genre) {
        this.numEleve = numEleve;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.poids = poids;
        this.annee = annee;
        this.genre = genre;
    }

    // TODO : getters et setters
    public int getNumEleve() {
        return numEleve;
    }

    public void setNumEleve(int numEleve) {
        this.numEleve = numEleve;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // TODO : toString()
    @Override
    public String toString() {
        return String.format(
                "Eleve [ID=%d, nom=%s, prenom=%s, dateNaissance=%s, poids=%.2f, annee=%d, genre=%s]",
                getNumEleve(),
                getNom(),
                getPrenom(),
                getDateNaissance(),
                getPoids(),
                getAnnee(),
                getGenre()
        );
    }
}