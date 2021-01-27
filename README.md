# JLibEcoleDirecte by Benco11
Librairie EcoleDirecte en Java : JLibEcoleDirecte

## Fonctionnalités
  🔐 Authentification pour les comptes **Élèves** 
  📑 Récupération des **notes** 
  👦🏻 Récupération des **informations du profil** 
  🏫 Récupération des **informations de la classe** 
  🏃🏽 Récupération des données de **vie scolaire** 

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


⚠️ Si le trimestre est fini, il faut multiplier la note par le coefficient et diviser par la somme des coefficients :

    import fr.benco11.jlibecoledirecte.Session;
    import fr.benco11.jlibecoledirecte.exceptions.EcoleDirecteAccountTypeException;
    import fr.benco11.jlibecoledirecte.exceptions.EcoleDirecteLoginException;
    import fr.benco11.jlibecoledirecte.exceptions.EcoleDirecteUnknownConnectionException;
       
    class AverageNote {
        public static void main(String[] args) {
        	Session session = new Session("Jean Martin-Dupont", "VilebrequinTeCisaille");
        	try {
		    session.connect();
		    double coef = session.getAverageGrades(1).stream().mapToDouble(e -> Double.valueOf(e.getCoef().replace(",", "."))).sum();
		    System.out.println(session.getAverageGrades(1).stream().mapToDouble(e -> Double.valueOf(e.getNote().replace(",", ".")) * Double.valueOf(e.getCoef().replace(",", "."))).sum() / coef);
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
💾 Les versions de la librairie se trouvent dans le dossier "versions" (certaines versions peuvent venir à manquer car présentant des failles de sécurités)

