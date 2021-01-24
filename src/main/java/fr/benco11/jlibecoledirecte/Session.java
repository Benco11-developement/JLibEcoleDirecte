package fr.benco11.jlibecoledirecte;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import fr.benco11.jlibecoledirecte.exceptions.EcoleDirecteAccountTypeException;
import fr.benco11.jlibecoledirecte.exceptions.EcoleDirecteLoginException;
import fr.benco11.jlibecoledirecte.exceptions.EcoleDirecteUnknownConnectionException;
import fr.benco11.jlibecoledirecte.login.Account;
import fr.benco11.jlibecoledirecte.login.LoginJson;
import fr.benco11.jlibecoledirecte.student.Grade;
import fr.benco11.jlibecoledirecte.student.GradeDiscipline;
import fr.benco11.jlibecoledirecte.student.GradeJson;
import fr.benco11.jlibecoledirecte.utils.HttpUtils;

public class Session {
	private String pass;
	private String username;
	
	private String token;
	private int id;
	private int idLogin;
	
	private Account account;
	
	/**
	 * 
	 * @param username identifiant de l'utilisateur
	 * @param pass mot de passe de l'utilisateur
	 */

	public Session(String username, String pass) {
		this.username = username;
		this.pass = pass;
	}
	
	/**
	 * 
	 * Connecte la session et remplie les champs <code>pass</code>, <code>username</code>, <code>token</code>, <code>id</code>, <code>idLogin</code> et <code>account</code>
	 * @return <code>1</code> si la requête s'est bien effectuée sinon <code>0</code> si une erreur réseau s'est produite
	 * @throws EcoleDirecteLoginException une erreur lors de la connexion s'est produite
	 */
	
	public int connect() throws EcoleDirecteLoginException {
		try {
			String r = HttpUtils.sendRequest("http://api.ecoledirecte.com/v3/login.awp", "data={\"identifiant\": \"" + username + "\", \"motdepasse\": \"" + pass + "\"}", "POST", true, true);
			LoginJson loginJson = new Gson().fromJson(r, LoginJson.class);
			if(loginJson.getCode() != 200) {
				throw new EcoleDirecteLoginException(loginJson.getMessage());
			}
			
			token = loginJson.getToken();
			account = loginJson.getLoginData().getAccount();
			id = account.getId();
			idLogin = account.getIdLogin();
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Renvoie un objet de type <code>Account</code>
	 * @return l'objet de type <code>Account</code> qui représente le compte de l'utilisateur
	 */
	
	public Account getAccount() {
		return account;
	}
	
	/**
	 * Renvoie l'identifiant
	 * @return l'identifiant de la session
	 */

	public String getUsername() {
		return username;
	}

	/**
	 * Renvoie le token
	 * @return le token de la session
	 */
	
	public String getToken() {
		return token;
	}
	
	/**
	 * Renvoie l'id
	 * @return l'id de la session
	 */
	
	public int getId() {
		return id;
	}

	/**
	 * Renvoie le idLogin
	 * @return le idlogin de la session
	 */
	
	public int getIdLogin() {
		return idLogin;
	}
	
	/**
	 * Récupère la liste des moyennes de chaque matière parmis tous les trimestres
	 * @return la liste de toutes les moyennes de chaque matière dans tous les trimestres
	 * @throws EcoleDirecteAccountTypeException le compte utilisé n'est pas un compte élève
	 * @throws EcoleDirecteUnknownConnectionException une erreur inconnue a eu lieu
	 */
	
	public List<Grade> getAverageGrades() throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException {
		if(!account.getTypeCompte().equals("E"))
			throw new EcoleDirecteAccountTypeException(1);
		
		try {
			String r = HttpUtils.sendRequest("https://api.ecoledirecte.com/v3/eleves/" + id + "/notes.awp?verbe=get&", "data={\"token\": \"" + token + "\"}", "POST", true, true);
			GradeJson gradeJson = new Gson().fromJson(r, GradeJson.class);
			if(gradeJson.getCode() != 200) {
				throw new EcoleDirecteUnknownConnectionException();
			}
			
			ArrayList<Grade> averageGradesList = new ArrayList<Grade>();
			gradeJson.getData().getPeriodes().forEach(e -> e.getEnsembleMatieres().getDisciplines().forEach(a -> {
					if(a.getAverage().length() > 0)
						averageGradesList.add(new Grade(a.getAverage(), a.getCoef(), a.getId()));
				}
			));
			return averageGradesList;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Récupère la liste des moyennes d'un trimestre
	 * @param periode le numéro du trimestre voulu
	 * @return la liste de toutes les moyennes de chaque matière dans le trimestre n°<code>periode</code>
	 * @throws EcoleDirecteAccountTypeException le compte utilisé n'est pas un compte élève
	 * @throws EcoleDirecteUnknownConnectionException une erreur inconnue a eu lieu
	 */
	
	public List<Grade> getAverageGrades(int periode) throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException {
		if(!account.getTypeCompte().equals("E"))
			throw new EcoleDirecteAccountTypeException(1);
		
		try {
			String r = HttpUtils.sendRequest("https://api.ecoledirecte.com/v3/eleves/" + id + "/notes.awp?verbe=get&", "data={\"token\": \"" + token + "\"}", "POST", true, true);
			GradeJson gradeJson = new Gson().fromJson(r, GradeJson.class);
			if(gradeJson.getCode() != 200) {
				throw new EcoleDirecteUnknownConnectionException();
			}
			
			ArrayList<Grade> averageGradesList = new ArrayList<Grade>();
			gradeJson.getData().getPeriodes().stream().filter(e -> e.getCodePeriode().equalsIgnoreCase("A00" + periode)).findFirst().get().getEnsembleMatieres().getDisciplines().forEach(e -> {
				if(e.getAverage().length() > 0)
					averageGradesList.add(new Grade(e.getAverage(), e.getCoef(), e.getId()));
			});
			return averageGradesList;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Récupère la liste des moyennes minimum de chaque matière parmis tous les trimestres
	 * @return la liste de toutes les moyennes minimum de chaque matière dans tous les trimestres
	 * @throws EcoleDirecteAccountTypeException le compte utilisé n'est pas un compte élève
	 * @throws EcoleDirecteUnknownConnectionException une erreur inconnue a eu lieu
	 */
	
	public List<Grade> getMinAverageGrades() throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException {
		if(!account.getTypeCompte().equals("E"))
			throw new EcoleDirecteAccountTypeException(1);
		
		try {
			String r = HttpUtils.sendRequest("https://api.ecoledirecte.com/v3/eleves/" + id + "/notes.awp?verbe=get&", "data={\"token\": \"" + token + "\"}", "POST", true, true);
			GradeJson gradeJson = new Gson().fromJson(r, GradeJson.class);
			if(gradeJson.getCode() != 200) {
				throw new EcoleDirecteUnknownConnectionException();
			}
			
			ArrayList<Grade> averageGradesList = new ArrayList<Grade>();
			gradeJson.getData().getPeriodes().forEach(e -> e.getEnsembleMatieres().getDisciplines().forEach(a -> {
					if(a.getMinAverage().length() > 0)
						averageGradesList.add(new Grade(a.getMinAverage(), a.getCoef(), a.getId()));
				}
			));
			return averageGradesList;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Récupère la liste des moins bonnes moyennes d'un trimestre
	 * @param periode le numéro du trimestre voulu
	 * @return la liste de toutes les moyennes minimum de chaque matière dans le trimestre n°<code>periode</code>
	 * @throws EcoleDirecteAccountTypeException le compte utilisé n'est pas un compte élève
	 * @throws EcoleDirecteUnknownConnectionException une erreur inconnue a eu lieu
	 */
	
	public List<Grade> getMinAverageGrades(int periode) throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException {
		if(!account.getTypeCompte().equals("E"))
			throw new EcoleDirecteAccountTypeException(1);
		
		try {
			String r = HttpUtils.sendRequest("https://api.ecoledirecte.com/v3/eleves/" + id + "/notes.awp?verbe=get&", "data={\"token\": \"" + token + "\"}", "POST", true, true);
			GradeJson gradeJson = new Gson().fromJson(r, GradeJson.class);
			if(gradeJson.getCode() != 200) {
				throw new EcoleDirecteUnknownConnectionException();
			}
			
			ArrayList<Grade> averageGradesList = new ArrayList<Grade>();
			gradeJson.getData().getPeriodes().stream().filter(e -> e.getCodePeriode().equalsIgnoreCase("A00" + periode)).findFirst().get().getEnsembleMatieres().getDisciplines().forEach(e -> {
				if(e.getMinAverage().length() > 0)
					averageGradesList.add(new Grade(e.getMinAverage(), e.getCoef(), e.getId()));
			});
			return averageGradesList;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Récupère la liste des moyennes maximum de chaque matière parmis tous les trimestres
	 * @return la liste de toutes les moyennes maximum de chaque matière dans tous les trimestres
	 * @throws EcoleDirecteAccountTypeException le compte utilisé n'est pas un compte élève
	 * @throws EcoleDirecteUnknownConnectionException une erreur inconnue a eu lieu
	 */
	
	public List<Grade> getMaxAverageGrades() throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException {
		if(!account.getTypeCompte().equals("E"))
			throw new EcoleDirecteAccountTypeException(1);
		
		try {
			String r = HttpUtils.sendRequest("https://api.ecoledirecte.com/v3/eleves/" + id + "/notes.awp?verbe=get&", "data={\"token\": \"" + token + "\"}", "POST", true, true);
			GradeJson gradeJson = new Gson().fromJson(r, GradeJson.class);
			if(gradeJson.getCode() != 200) {
				throw new EcoleDirecteUnknownConnectionException();
			}
			
			ArrayList<Grade> averageGradesList = new ArrayList<Grade>();
			gradeJson.getData().getPeriodes().forEach(e -> e.getEnsembleMatieres().getDisciplines().forEach(a -> {
					if(a.getMaxAverage().length() > 0)
						averageGradesList.add(new Grade(a.getMaxAverage(), a.getCoef(), a.getId()));
				}
			));
			return averageGradesList;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Récupère la liste des meilleures moyennes d'un trimestre
	 * @param periode le numéro du trimestre voulu
	 * @return la liste de toutes les moyennes maximum de chaque matière dans le trimestre n°<code>periode</code>
	 * @throws EcoleDirecteAccountTypeException le compte utilisé n'est pas un compte élève
	 * @throws EcoleDirecteUnknownConnectionException une erreur inconnue a eu lieu
	 */
	
	public List<Grade> getMaxAverageGrades(int periode) throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException {
		if(!account.getTypeCompte().equals("E"))
			throw new EcoleDirecteAccountTypeException(1);
		
		try {
			String r = HttpUtils.sendRequest("https://api.ecoledirecte.com/v3/eleves/" + id + "/notes.awp?verbe=get&", "data={\"token\": \"" + token + "\"}", "POST", true, true);
			GradeJson gradeJson = new Gson().fromJson(r, GradeJson.class);
			if(gradeJson.getCode() != 200) {
				throw new EcoleDirecteUnknownConnectionException();
			}
			
			ArrayList<Grade> averageGradesList = new ArrayList<Grade>();
			gradeJson.getData().getPeriodes().stream().filter(e -> e.getCodePeriode().equalsIgnoreCase("A00" + periode)).findFirst().get().getEnsembleMatieres().getDisciplines().forEach(e -> {
				if(e.getMaxAverage().length() > 0)
					averageGradesList.add(new Grade(e.getMaxAverage(), e.getCoef(), e.getId()));
			});
			return averageGradesList;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Récupère l'ensemble des matières d'un trimestre
	 * @param periode le numéro du trimestre voulu
	 * @return la liste des matières dans le trimestre numéro <code>periode</code>
	 * @throws EcoleDirecteAccountTypeException le compte utilisé n'est pas un compte élève
	 * @throws EcoleDirecteUnknownConnectionException une erreur inconnue a eu lieu
	 */
	
	public List<GradeDiscipline> getDisciplines(int periode) throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException {
		if(!account.getTypeCompte().equals("E"))
			throw new EcoleDirecteAccountTypeException(1);
		
		try {
			String r = HttpUtils.sendRequest("https://api.ecoledirecte.com/v3/eleves/" + id + "/notes.awp?verbe=get&", "data={\"token\": \"" + token + "\"}", "POST", true, true);
			GradeJson disciplineJson = new Gson().fromJson(r, GradeJson.class);
			if(disciplineJson.getCode() != 200) {
				throw new EcoleDirecteUnknownConnectionException();
			}
			
			return disciplineJson.getData().getPeriodes().stream().filter(e -> e.getCodePeriode().equals("A00" + periode)).findFirst().get().getEnsembleMatieres().getDisciplines().stream().collect(Collectors.toList());
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
