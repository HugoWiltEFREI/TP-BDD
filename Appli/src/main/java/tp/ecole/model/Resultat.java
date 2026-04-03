package tp.ecole.model;

/**
 * POJO représentant un résultat (inscription + note) de la base RESULTATS.
 * <p>
 * Schéma SQL de référence :
 * NUM_ELEVE  NUMERIC(6)  PK, FK -> ELEVES
 * NUM_COURS  NUMERIC(4)  PK, FK -> COURS
 * POINTS     NUMERIC(3)
 * <p>
 * =====================================================================
 * TODO (Question 2) :
 *   1. Ajoutez les attributs privés (numEleve, numCours, points).
 *      Vous pouvez également ajouter des champs calculés ou enrichis
 *      (ex. nomCours, nomEleve) pour faciliter l'affichage.
 *   2. Créez un constructeur sans argument et un constructeur complet.
 *   3. Générez (ou écrivez) les getters et setters pour chaque attribut.
 *   4. Redéfinissez toString() pour afficher le résultat de façon lisible.
 * <p>
 * =====================================================================
 */
public class Resultat {

    // TODO : déclarez les attributs privés (numEleve, numCours, points)
    private int num_eleve;
    private int num_cours;
    private int points;

    // TODO : constructeur par défaut
    public Resultat() {
        this.num_cours = 0;
        this.num_eleve = 0;
        this.points = 0;
    }

    // TODO : constructeur avec tous les paramètres
    public Resultat(int num_eleve, int num_cours, int points) {
        this.num_eleve = num_eleve;
        this.num_cours = num_cours;
        this.points = points;
    }

    // TODO : getters et setters
    public int getNum_cours() {
        return num_cours;
    }

    public void setNum_cours(int num_cours) {
        this.num_cours = num_cours;
    }

    public int getNum_eleve() {
        return num_eleve;
    }

    public void setNum_eleve(int num_eleve) {
        this.num_eleve = num_eleve;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    // TODO : toString()
    public String toString() {
        return "Resultats : num élève =" + getNum_eleve() + ", num cours =" + getNum_cours() + ", points =" + getPoints();
    }
}