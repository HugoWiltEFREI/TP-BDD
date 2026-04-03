package tp.ecole.model;

/**
 * POJO représentant un cours de la base COURS.
 * <p>
 * Schéma SQL de référence :
 * NUM_COURS  NUMERIC(4)  PK
 * NOM        VARCHAR(20)
 * NBHEURES   NUMERIC(2)
 * ANNEE      NUMERIC(1)
 * <p>
 * =====================================================================
 * TODO (Question 2) :
 *   1. Ajoutez les attributs privés correspondant aux colonnes ci-dessus.
 *   2. Créez un constructeur sans argument et un constructeur complet.
 *   3. Générez (ou écrivez) les getters et setters pour chaque attribut.
 *   4. Redéfinissez toString() pour afficher le cours de façon lisible.
 * =====================================================================
 */
public class Cours {

    // TODO : déclarez les attributs privés (numCours, nom, nbHeures, annee)
    private int num_cours;
    private String nom;
    private int nbHeures;
    private int annee;

    // TODO : constructeur par défaut
    public Cours() {
        this.num_cours = 0;
        this.nom = "";
        this.nbHeures = 0;
        this.annee = 0;
    }

    // TODO : constructeur avec tous les paramètres
    public Cours(int num_cours, String nom, int nbHeures, int annee) {
        this.num_cours = num_cours;
        this.nom = nom;
        this.nbHeures = nbHeures;
        this.annee = annee;
    }

    // TODO : getters et setters
    public int getNum_cours() {
        return this.num_cours;
    }

    public void setNum_cours() {
        this.num_cours = this.num_cours;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbHeures() {
        return this.nbHeures;
    }

    public void setNbHeures() {
        this.nbHeures = this.nbHeures;
    }

    public int getAnnee() {
        return this.annee;
    }

    public void setAnnee() {
        this.annee = this.annee;
    }

    // TODO : toString()
    @Override
    public String toString() {
        return "Cours : numéro =" + getNum_cours() + ", nom =" + getNom() + ", nombre d'heures =" + getNbHeures() +
                "année =" + getAnnee();
    }
}