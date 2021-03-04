# JLibEcoleDirecte by Benco11
Librairie EcoleDirecte en Java : JLibEcoleDirecte

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

⚠️ Si les identifiants sont invalides, une erreur `EcoleDirecteLoginException`sera levée !

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
			} catch (EcoleDirecteLoginException | EcoleDirecteUnknownConnectionException | EcoleDirecteAccountTypeException e) {
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
		    	System.out.println("Moyenne : " + session.getPeriodes().get(0).getEnsembleMatieres().getAverage());
				System.out.println("Appréciation : " + session.getPeriodes().get(0).getEnsembleMatieres().getAppreciationPP());
				System.out.println("Moyenne générale : " + session.getPeriodes().get(0).getEnsembleMatieres().getAverageClass());
        	} catch (EcoleDirecteLoginException | EcoleDirecteUnknownConnectionException | EcoleDirecteAccountTypeException e) {
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

