# TP6 – Application École : Intégration JDBC/DAO

## Structure du projet

```
src/main/java/tp/ecole/
│
├── util/
│   └── ConnectionManager.java     ← [TODO] Configurez ici la connexion (Q1)
│
├── model/                         ← [TODO] Créez vos POJOs (Q2)
│   ├── Eleve.java
│   ├── Cours.java
│   └── Resultat.java
│
├── dao/                           ← [TODO] Implémentez les DAOs (Q3, Q4)
│   ├── EleveDAO.java              ← Interface (fournie)
│   ├── EleveDAOImpl.java          ← [TODO] À implémenter
│   ├── ResultatDAO.java           ← Interface (fournie)
│   └── ResultatDAOImpl.java       ← [TODO] À implémenter
│
├── service/                       ← [TODO] Implémentez la logique métier (Q6)
│   └── EcoleService.java
│
└── ui/                            ← Fourni – ne pas modifier
    ├── MainMenu.java              ← Application console principale
    ├── TestConnection.java        ← Test de connexion (Q1)
    ├── MainTestDAO.java           ← Test CRUD DAO (Q5)
    └── MainServiceTest.java       ← Test transactions (Q6, Q7)
```

## Démarrage rapide

### 1. Prérequis
- Java 17+
- Maven 3.6+
- MySQL avec la base `Ecole` initialisée (script `ecole.sql`)

### 2. Configuration de la connexion

Ouvrez `src/main/java/tp/ecole/util/ConnectionManager.java` et renseignez :
```java
private static final String DB_URL      = "jdbc:mysql://localhost:3306/ecole?useSSL=false&serverTimezone=UTC";
private static final String DB_USER     = "votre_nom_utilisateur";
private static final String DB_PASSWORD = "votre_mot_de_passe";
```

Puis implémentez la méthode `getConnection()`.

### 3. Ordre de travail recommandé

| Étape | Fichier(s) à compléter | Question du TP |
|-------|------------------------|----------------|
| 1 | `ConnectionManager` | Q1 |
| 2 | `Eleve`, `Cours`, `Resultat` | Q2 |
| 3 | `EleveDAOImpl`, `ResultatDAOImpl` (interfaces déjà fournies) | Q3, Q4 |
| 4 | Lancer `TestConnection`, `MainTestDAO` | Q1, Q5 |
| 5 | `EcoleService` | Q6 |
| 6 | Lancer `MainServiceTest` | Q6, Q7 |
| 7 | `MainMenu` (l'application finale) | — |

### 4. Compilation et exécution

```bash
# Compiler le projet
mvn compile

# Lancer le test de connexion
mvn exec:java -Dexec.mainClass="tp.ecole.ui.TestConnection"

# Lancer les tests DAO
mvn exec:java -Dexec.mainClass="tp.ecole.ui.MainTestDAO"

# Lancer les tests de service (transactions)
mvn exec:java -Dexec.mainClass="tp.ecole.ui.MainServiceTest"

# Lancer l'application principale
mvn exec:java -Dexec.mainClass="tp.ecole.ui.MainMenu"
```

## Rappel de l'architecture en couches

```
┌─────────────────────────────────────────┐
│           Couche UI (console)           │
│  MainMenu, TestConnection, MainTestDAO  │
│         (fournie – sans SQL)            │
└────────────────────┬────────────────────┘
                     │ utilise
┌────────────────────▼────────────────────┐
│         Couche Service (métier)         │
│              EcoleService               │
│      (gestion des transactions)         │
└────────────────────┬────────────────────┘
                     │ utilise
┌────────────────────▼────────────────────┐
│            Couche DAO (accès BD)        │
│   EleveDAO / EleveDAOImpl               │
│   ResultatDAO / ResultatDAOImpl         │
│   (PreparedStatement, try-with-res.)    │
└────────────────────┬────────────────────┘
                     │ utilise
┌────────────────────▼────────────────────┐
│           Couche Modèle (POJO)          │
│          Eleve, Cours, Resultat         │
└────────────────────┬────────────────────┘
                     │
┌────────────────────▼────────────────────┐
│        MySQL – Base de données Ecole    │
└─────────────────────────────────────────┘
```
