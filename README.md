# JLibEcoleDirecte by Benco11
Librairie EcoleDirecte en Java : JLibEcoleDirecte

![Nombre de téléchargements](https://img.shields.io/github/downloads/Benco11-developement/JLibEcoleDirecte/total?color=%2300e636&label=T%C3%A9l%C3%A9chargements&style=flat-square "téléchargements")&nbsp;&nbsp;&nbsp;&nbsp; ![Nombre de vulnérabilités](https://img.shields.io/snyk/vulnerabilities/github/Benco11-developement/JLibEcoleDirecte?color=%23e60000&label=Vuln%C3%A9rabilit%C3%A9s&style=flat-square "vulnérabilités")&nbsp;&nbsp;&nbsp;&nbsp; ![Dernier commit](https://img.shields.io/github/last-commit/Benco11-developement/JLibEcoleDirecte?color=%230800ff&label=Dernier%20commit&style=flat-square "commit")&nbsp;&nbsp;&nbsp;&nbsp; ![Jitpack](https://img.shields.io/jitpack/v/github/Benco11-developement/JLibEcoleDirecte?color=%237700ff&label=Jitpack&style=flat-square "jitpack")&nbsp;&nbsp;&nbsp;&nbsp; ![License](https://img.shields.io/github/license/Benco11-developement/JLibEcoleDirecte?color=%2300aeff&label=License&style=flat-square "license")&nbsp;&nbsp;&nbsp;&nbsp; ![Stars](https://img.shields.io/github/stars/Benco11-developement/JLibEcoleDirecte?color=%236fff00&label=Stars&style=flat-square "stars")

## Fonctionnalités
🔐 Authentification pour les comptes **Élèves**  
📑 Récupération des **notes**  
👦🏻 Récupération des **informations du profil**  
🏫 Récupération des **informations de la classe**  
🏃🏽 Récupération des données de **vie scolaire**  

## Dépendances

➡️ Gradle :

```
repositories {
	maven { url 'https://jitpack.io' }
}

dependencies {
	implementation 'com.github.Benco11-developement:JLibEcoleDirecte:{Version}'
}
```

➡️ Maven :

```
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>

<dependency>
	<groupId>com.github.Benco11-developement</groupId>
	<artifactId>JLibEcoleDirecte</artifactId>
	<version>Version</version>
</dependency>
````
		
## Comment l'utiliser

➡️ Pour commencer, il vous faudra créer un objet `Session` qui prend en paramètres : `identifiant`,`pass`.
Pour initialiser la session, utilisez `Session#connect`. 

⚠️ Si les identifiants sont invalides, une erreur `EcoleDirecteLoginException` sera levée !

## Quelques exemples

Dans l'exemple ci-dessous, on essaye de calculer la moyenne générale du trimestre 2 de l'élève  :

    import fr.benco11.jlibecoledirecte.Session;
    import fr.benco11.jlibecoledirecte.exceptions.EcoleDirecteAccountTypeException;
    import fr.benco11.jlibecoledirecte.exceptions.EcoleDirecteLoginException;
    import fr.benco11.jlibecoledirecte.exceptions.EcoleDirecteUnknownConnectionException;
    
    class AverageNote {
	    public static void main(String[] args) {
    		Session session = new Session("Jean Martin-Dupont", "VilebrequinTeCisaille");
			try {
				session.connect();
	    		System.out.println(session.getAverageGrades(2).stream().mapToDouble(e -> Double.valueOf(e.getNote())).average().getAsDouble());
			} catch (EcoleDirecteLoginException | EcoleDirecteUnknownConnectionException | EcoleDirecteAccountTypeException | EcoleDirecteIOException | EcoleDirectePeriodeException e) {
	    		e.printStackTrace();
			}
	    }
    }


⚠️ Si et seulement si le trimestre est fini, on peut utiliser `GradeSetMatieres#getAppreciationPP`, `GradeSetMatieres#getAverage`, `GradeSetMatieres#getAverageClass`, `GradeSetMatieres#getMinAverage` et `GradeSetMatieres#getMaxAverage`.

    import fr.benco11.jlibecoledirecte.Session;
    import fr.benco11.jlibecoledirecte.exceptions.EcoleDirecteAccountTypeException;
    import fr.benco11.jlibecoledirecte.exceptions.EcoleDirecteLoginException;
    import fr.benco11.jlibecoledirecte.exceptions.EcoleDirecteUnknownConnectionException;
       
    class AverageNote {
        public static void main(String[] args) {
        	Session session = new Session("Jean Martin-Dupont", "VilebrequinTeCisaille");
        	try {
		    	session.connect();
		    	GradePeriode periode = session.getPeriode(1); // On met en cache le résultat pour éviter de multiples requêtes
				System.out.println("Moyenne : " + periode.getEnsembleMatieres().getAverage());
				System.out.println("Appréciation : " + periode.getEnsembleMatieres().getAppreciationPP());
				System.out.println("Moyenne générale : " + periode.getEnsembleMatieres().getAverageClass());
        	} catch (EcoleDirecteLoginException | EcoleDirecteAccountTypeException | EcoleDirecteUnknownConnectionException | EcoleDirecteIOException | EcoleDirectePeriodeException e) {
			e.printStackTrace();
		}
        }
       
    }
	
Vous pouvez aussi récupérer le nom de la classe de l'élève :

    import fr.benco11.jlibecoledirecte.Session;
    import fr.benco11.jlibecoledirecte.exceptions.EcoleDirecteLoginException;
    
    class ClassName {
	    public static void main(String[] args) {
    		Session session = new Session("Jean Martin-Dupont", "VilebrequinTeCisaille");
    		try {
    	   		session.connect();
    	    	System.out.println(session.getAccount().getProfile().getClasse().getLibelle());
    		} catch (EcoleDirecteLoginException e) {
    	    	e.printStackTrace();
    		}
	    }
    }
	
## Autres

📖 Une documentation est à votre disposition (javadoc) dans le dossier "javadoc".   
💾 Les versions de la librairie se trouvent dans le dossier "versions" (certaines versions peuvent venir à manquer car présentant des failles de sécurité) 

