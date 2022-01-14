package fr.benco11.jlibecoledirecte;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import com.google.gson.Gson;

import fr.benco11.jlibecoledirecte.exceptions.EcoleDirecteAccountTypeException;
import fr.benco11.jlibecoledirecte.exceptions.EcoleDirecteIOException;
import fr.benco11.jlibecoledirecte.exceptions.EcoleDirecteLoginException;
import fr.benco11.jlibecoledirecte.exceptions.EcoleDirectePeriodeException;
import fr.benco11.jlibecoledirecte.exceptions.EcoleDirecteUnknownConnectionException;
import fr.benco11.jlibecoledirecte.login.Account;
import fr.benco11.jlibecoledirecte.login.LoginJson;
import fr.benco11.jlibecoledirecte.student.*;
import fr.benco11.jlibecoledirecte.utils.HttpUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class Session {
	
	public static final String ECOLEDIRECTE_STUDENTS_SECTION_URL = "https://api.ecoledirecte.com/v3/eleves/";
	public static final String ECOLEDIRECTE_STUDENTS_NOTEGET_URL = "/notes.awp?verbe=get&";
	public static final String ECOLEDIRECTE_JSON_DATA_START_TOKEN = "data={\"token\": \"";
	
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
	 * Connecte la session et remplie avec les champs <code>pass</code> et <code>username</code>, les champs <code>token</code>, <code>id</code>, <code>idLogin</code> et <code>account</code>
	 * @return <code>1</code> si la requête s'est bien effectuée sinon <code>-1</code> si une erreur réseau s'est produite
	 * @throws EcoleDirecteLoginException une erreur lors de la connexion s'est produite
	 */
	
	public int connect() throws EcoleDirecteLoginException {
		try {
			String r = HttpUtils.sendRequest("https://api.ecoledirecte.com/v3/login.awp", "data={\"identifiant\": \"" + username + "\", \"motdepasse\": \"" + pass + "\"}", "POST", true, true);
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
	 * @throws EcoleDirecteIOException une erreur réseau a eu lieu
	 */
	
	public List<Grade> getAverageGrades() throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException, EcoleDirecteIOException {
		if(!account.getTypeCompte().equals("E"))
			throw new EcoleDirecteAccountTypeException(1);
		
		try {
			String r = HttpUtils.sendRequest(ECOLEDIRECTE_STUDENTS_SECTION_URL + id + ECOLEDIRECTE_STUDENTS_NOTEGET_URL, ECOLEDIRECTE_JSON_DATA_START_TOKEN + token + "\"}", "POST", true, true);
			GradeJson gradeJson = new Gson().fromJson(r, GradeJson.class);
			if(gradeJson.getCode() != 200) {
				throw new EcoleDirecteUnknownConnectionException();
			}
			
			ArrayList<Grade> averageGradesList = new ArrayList<>();
			gradeJson.getData().getPeriodes().forEach(e -> e.getEnsembleMatieres().getDisciplines().forEach(a -> {
					if(a.getAverage().length() > 0)
						averageGradesList.add(new Grade(a.getAverage(), a.getCoef(), a.getId()));
				}
			));
			return averageGradesList;
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new EcoleDirecteIOException();
		}
	}
	
	/**
	 * Récupère la liste des moyennes d'un trimestre
	 * @param periode le numéro du trimestre voulu
	 * @return la liste de toutes les moyennes de chaque matière dans le trimestre n°<code>periode</code>
	 * @throws EcoleDirecteAccountTypeException le compte utilisé n'est pas un compte élève
	 * @throws EcoleDirecteUnknownConnectionException une erreur inconnue a eu lieu
	 * @throws EcoleDirecteIOException une erreur réseau a eu lieu
	 * @throws EcoleDirectePeriodeException le trimestre n'a pas été trouvé
	 */
	
	public List<Grade> getAverageGrades(int periode) throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException, EcoleDirecteIOException, EcoleDirectePeriodeException {
		if(!account.getTypeCompte().equals("E"))
			throw new EcoleDirecteAccountTypeException(1);
		
		try {
			String r = HttpUtils.sendRequest(ECOLEDIRECTE_STUDENTS_SECTION_URL + id + ECOLEDIRECTE_STUDENTS_NOTEGET_URL, ECOLEDIRECTE_JSON_DATA_START_TOKEN + token + "\"}", "POST", true, true);
			GradeJson gradeJson = new Gson().fromJson(r, GradeJson.class);
			if(gradeJson.getCode() != 200) {
				throw new EcoleDirecteUnknownConnectionException();
			}
			
			ArrayList<Grade> averageGradesList = new ArrayList<>();
			
			Optional<GradePeriode> opPeriode = gradeJson.getData().getPeriodes().stream().filter(e -> e.getCodePeriode().equalsIgnoreCase("A00" + periode)).findFirst();
			if(!opPeriode.isPresent())
				throw new EcoleDirectePeriodeException();
			opPeriode.get().getEnsembleMatieres().getDisciplines().forEach(e -> {
				if(e.getAverage().length() > 0)
					averageGradesList.add(new Grade(e.getAverage(), e.getCoef(), e.getId()));
			});
			return averageGradesList;
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new EcoleDirecteIOException();
		}
	}
	
	/**
	 * Récupère la liste des moyennes minimum de chaque matière parmis tous les trimestres
	 * @return la liste de toutes les moyennes minimum de chaque matière dans tous les trimestres
	 * @throws EcoleDirecteAccountTypeException le compte utilisé n'est pas un compte élève
	 * @throws EcoleDirecteUnknownConnectionException une erreur inconnue a eu lieu
	 * @throws EcoleDirecteIOException une erreur réseau a eu lieu
	 */
	
	public List<Grade> getMinAverageGrades() throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException, EcoleDirecteIOException {
		if(!account.getTypeCompte().equals("E"))
			throw new EcoleDirecteAccountTypeException(1);
		
		try {
			String r = HttpUtils.sendRequest(ECOLEDIRECTE_STUDENTS_SECTION_URL + id + ECOLEDIRECTE_STUDENTS_NOTEGET_URL, ECOLEDIRECTE_JSON_DATA_START_TOKEN + token + "\"}", "POST", true, true);
			GradeJson gradeJson = new Gson().fromJson(r, GradeJson.class);
			if(gradeJson.getCode() != 200) {
				throw new EcoleDirecteUnknownConnectionException();
			}
			
			ArrayList<Grade> averageGradesList = new ArrayList<>();
			gradeJson.getData().getPeriodes().forEach(e -> e.getEnsembleMatieres().getDisciplines().forEach(a -> {
					if(a.getMinAverage().length() > 0)
						averageGradesList.add(new Grade(a.getMinAverage(), a.getCoef(), a.getId()));
				}
			));
			return averageGradesList;
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new EcoleDirecteIOException();
		}
	}
	
	/**
	 * Récupère la liste des moins bonnes moyennes d'un trimestre
	 * @param periode le numéro du trimestre voulu
	 * @return la liste de toutes les moyennes minimum de chaque matière dans le trimestre n°<code>periode</code>
	 * @throws EcoleDirecteAccountTypeException le compte utilisé n'est pas un compte élève
	 * @throws EcoleDirecteUnknownConnectionException une erreur inconnue a eu lieu
	 * @throws EcoleDirecteIOException une erreur réseau a eu lieu
	 * @throws EcoleDirectePeriodeException le trimestre n'a pas été trouvé
	 */
	
	public List<Grade> getMinAverageGrades(int periode) throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException, EcoleDirecteIOException, EcoleDirectePeriodeException {
		if(!account.getTypeCompte().equals("E"))
			throw new EcoleDirecteAccountTypeException(1);
		
		try {
			String r = HttpUtils.sendRequest(ECOLEDIRECTE_STUDENTS_SECTION_URL + id + ECOLEDIRECTE_STUDENTS_NOTEGET_URL, ECOLEDIRECTE_JSON_DATA_START_TOKEN + token + "\"}", "POST", true, true);
			GradeJson gradeJson = new Gson().fromJson(r, GradeJson.class);
			if(gradeJson.getCode() != 200) {
				throw new EcoleDirecteUnknownConnectionException();
			}
			
			ArrayList<Grade> averageGradesList = new ArrayList<>();
			
			Optional<GradePeriode> opPeriode = gradeJson.getData().getPeriodes().stream().filter(e -> e.getCodePeriode().equalsIgnoreCase("A00" + periode)).findFirst();
			if(!opPeriode.isPresent())
				throw new EcoleDirectePeriodeException();
			
			opPeriode.get().getEnsembleMatieres().getDisciplines().forEach(e -> {
				if(e.getMinAverage().length() > 0)
					averageGradesList.add(new Grade(e.getMinAverage(), e.getCoef(), e.getId()));
			});
			return averageGradesList;
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new EcoleDirecteIOException();
		}
	}
	
	/**
	 * Récupère la liste des moyennes maximum de chaque matière parmis tous les trimestres
	 * @return la liste de toutes les moyennes maximum de chaque matière dans tous les trimestres
	 * @throws EcoleDirecteAccountTypeException le compte utilisé n'est pas un compte élève
	 * @throws EcoleDirecteUnknownConnectionException une erreur inconnue a eu lieu
	 * @throws EcoleDirecteIOException une erreur réseau a eu lieu
	 */
	
	public List<Grade> getMaxAverageGrades() throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException, EcoleDirecteIOException {
		if(!account.getTypeCompte().equals("E"))
			throw new EcoleDirecteAccountTypeException(1);
		
		try {
			String r = HttpUtils.sendRequest(ECOLEDIRECTE_STUDENTS_SECTION_URL + id + ECOLEDIRECTE_STUDENTS_NOTEGET_URL, ECOLEDIRECTE_JSON_DATA_START_TOKEN + token + "\"}", "POST", true, true);
			GradeJson gradeJson = new Gson().fromJson(r, GradeJson.class);
			if(gradeJson.getCode() != 200) {
				throw new EcoleDirecteUnknownConnectionException();
			}
			
			ArrayList<Grade> averageGradesList = new ArrayList<>();
			gradeJson.getData().getPeriodes().forEach(e -> e.getEnsembleMatieres().getDisciplines().forEach(a -> {
					if(a.getMaxAverage().length() > 0)
						averageGradesList.add(new Grade(a.getMaxAverage(), a.getCoef(), a.getId()));
				}
			));
			return averageGradesList;
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new EcoleDirecteIOException();
		}
	}
	
	/**
	 * Récupère la liste des meilleures moyennes d'un trimestre
	 * @param periode le numéro du trimestre voulu
	 * @return la liste de toutes les moyennes maximum de chaque matière dans le trimestre n°<code>periode</code>
	 * @throws EcoleDirecteAccountTypeException le compte utilisé n'est pas un compte élève
	 * @throws EcoleDirecteUnknownConnectionException une erreur inconnue a eu lieu
	 * @throws EcoleDirectePeriodeException le trimestre n'a pas été trouvé
	 * @throws EcoleDirecteIOException une erreur réseau a eu lieu
	 */
	
	public List<Grade> getMaxAverageGrades(int periode) throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException, EcoleDirectePeriodeException, EcoleDirecteIOException {
		if(!account.getTypeCompte().equals("E"))
			throw new EcoleDirecteAccountTypeException(1);
		
		try {
			String r = HttpUtils.sendRequest(ECOLEDIRECTE_STUDENTS_SECTION_URL + id + ECOLEDIRECTE_STUDENTS_NOTEGET_URL, ECOLEDIRECTE_JSON_DATA_START_TOKEN + token + "\"}", "POST", true, true);
			GradeJson gradeJson = new Gson().fromJson(r, GradeJson.class);
			if(gradeJson.getCode() != 200) {
				throw new EcoleDirecteUnknownConnectionException();
			}
			
			ArrayList<Grade> averageGradesList = new ArrayList<>();
			
			Optional<GradePeriode> optional = gradeJson.getData().getPeriodes().stream().filter(e -> e.getCodePeriode().equalsIgnoreCase("A00" + periode)).findFirst();
			
			if(!optional.isPresent())
				throw new EcoleDirectePeriodeException();
			
			optional.get().getEnsembleMatieres().getDisciplines().forEach(e -> {
				if(e.getMaxAverage().length() > 0)
					averageGradesList.add(new Grade(e.getMaxAverage(), e.getCoef(), e.getId()));
			});
			return averageGradesList;
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new EcoleDirecteIOException();
		}
	}
	
	/**
	 * Récupère l'ensemble des matières d'un trimestre
	 * @param periode le numéro du trimestre voulu
	 * @return la liste des matières dans le trimestre numéro <code>periode</code>
	 * @throws EcoleDirecteAccountTypeException le compte utilisé n'est pas un compte élève
	 * @throws EcoleDirecteUnknownConnectionException une erreur inconnue a eu lieu
	 * @throws EcoleDirecteIOException une erreur réseau a eu lieu
	 * @throws EcoleDirectePeriodeException le trimestre n'a pas pû être trouvé
	 */
	
	public List<GradeDiscipline> getDisciplines(int periode) throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException, EcoleDirecteIOException, EcoleDirectePeriodeException {
		if(!account.getTypeCompte().equals("E"))
			throw new EcoleDirecteAccountTypeException(1);
		
		try {
			String r = HttpUtils.sendRequest(ECOLEDIRECTE_STUDENTS_SECTION_URL + id + ECOLEDIRECTE_STUDENTS_NOTEGET_URL, ECOLEDIRECTE_JSON_DATA_START_TOKEN + token + "\"}", "POST", true, true);
			GradeJson disciplineJson = new Gson().fromJson(r, GradeJson.class);
			if(disciplineJson.getCode() != 200) {
				throw new EcoleDirecteUnknownConnectionException();
			}
			
			Optional<GradePeriode> optional = disciplineJson.getData().getPeriodes().stream().filter(e -> e.getCodePeriode().equals("A00" + periode)).findFirst();
			if(!optional.isPresent())
				throw new EcoleDirectePeriodeException();
			
			return optional.get().getEnsembleMatieres().getDisciplines().stream().collect(Collectors.toList());
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new EcoleDirecteIOException();
		}
	}
	
	/**
	 * Renvoie la liste des trimestres
	 * @return la liste des trimestres
	 * @throws EcoleDirecteAccountTypeException le compte utilisé n'est pas un compte élève
	 * @throws EcoleDirecteUnknownConnectionException une erreur inconnue a eu lieu
	 * @throws EcoleDirecteIOException une erreur réseau a eu lieu
	 */
	
	public List<GradePeriode> getPeriodes() throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException, EcoleDirecteIOException {
		if(!account.getTypeCompte().equals("E"))
			throw new EcoleDirecteAccountTypeException(1);
		
		try {
			String r = HttpUtils.sendRequest(ECOLEDIRECTE_STUDENTS_SECTION_URL + id + ECOLEDIRECTE_STUDENTS_NOTEGET_URL, ECOLEDIRECTE_JSON_DATA_START_TOKEN + token + "\"}", "POST", true, true);
			GradeJson disciplineJson = new Gson().fromJson(r, GradeJson.class);
			if(disciplineJson.getCode() != 200) {
				throw new EcoleDirecteUnknownConnectionException();
			}
			
			Object[] periodes = disciplineJson.getData().getPeriodes().toArray();
			return Arrays.asList(Arrays.copyOf(periodes, periodes.length, GradePeriode[].class));
		} catch (IOException e) {
			e.printStackTrace();
			throw new EcoleDirecteIOException();
		}
	}
	
	/**
	 * Renvoie un trimestre
	 * @param periode numéro du trimestre
	 * @return un trimestre
	 * @throws EcoleDirecteAccountTypeException le compte utilisé n'est pas un compte élève
	 * @throws EcoleDirecteUnknownConnectionException une erreur inconnue a eu lieu
	 * @throws EcoleDirecteIOException une erreur réseau a eu lieu
	 * @throws EcoleDirectePeriodeException le trimestre n'a pas pû être trouvé
	 */
	public GradePeriode getPeriode(int periode) throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException, EcoleDirecteIOException, EcoleDirectePeriodeException {
		if(!account.getTypeCompte().equals("E"))
			throw new EcoleDirecteAccountTypeException(1);
		
		try {
			String r = HttpUtils.sendRequest(ECOLEDIRECTE_STUDENTS_SECTION_URL + id + ECOLEDIRECTE_STUDENTS_NOTEGET_URL, ECOLEDIRECTE_JSON_DATA_START_TOKEN + token + "\"}", "POST", true, true);
			GradeJson disciplineJson = new Gson().fromJson(r, GradeJson.class);
			if(disciplineJson.getCode() != 200) {
				throw new EcoleDirecteUnknownConnectionException();
			}
			
			Object[] periodes = disciplineJson.getData().getPeriodes().toArray();
			return Arrays.asList(Arrays.copyOf(periodes, periodes.length, GradePeriode[].class)).stream().filter(e -> e.getCodePeriode().equals("A00" + periode)).findFirst().orElseThrow(EcoleDirectePeriodeException::new);
		} catch (IOException e) {
			e.printStackTrace();
			throw new EcoleDirecteIOException();
		}
	}
	
	/**
	 * Renvoie les données de la vie scolaire
	 * @return l'objet de type <code>SchoolLifeData</code> correspondant aux données de la vie scolaire
	 * @throws EcoleDirecteAccountTypeException le compte utilisé n'est pas un compte élève
	 * @throws EcoleDirecteUnknownConnectionException une erreur inconnue a eu lieu
	 * @throws EcoleDirecteIOException une erreur réseau a eu lieu
	 */
	
	@Nullable
	public SchoolLifeData getSchoolLifeData() throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException, EcoleDirecteIOException {
		if(!account.getTypeCompte().equals("E"))
			throw new EcoleDirecteAccountTypeException(1);
		try {
			String r = HttpUtils.sendRequest(ECOLEDIRECTE_STUDENTS_SECTION_URL + id + "/viescolaire.awp?verbe=get", ECOLEDIRECTE_JSON_DATA_START_TOKEN + token + "\"}", "POST", true, true);
			SchoolLifeJson schoolLifeJson = new Gson().fromJson(r, SchoolLifeJson.class);
			if(schoolLifeJson.getCode() != 200) {
				throw new EcoleDirecteUnknownConnectionException();
			}
			return schoolLifeJson.getData();
		} catch (IOException e) {
			e.printStackTrace();
			throw new EcoleDirecteIOException();
		}
	}

	/**
	 * Renvoie les cours d'une journée à une date
	 * @return le set de valeur <code>Cours</code> correspondant aux cours de cette journée
	 * @throws EcoleDirecteAccountTypeException le compte utilisé n'est pas un compte élève
	 * @throws EcoleDirecteUnknownConnectionException une erreur inconnue a eu lieu
	 * @throws EcoleDirecteIOException une erreur réseau a eu lieu
	 */
	public Set<Cours> getEmploiDuTemps(String dateDebut, String dateFin) throws EcoleDirecteAccountTypeException, EcoleDirecteUnknownConnectionException, EcoleDirecteIOException {
		if(!account.getTypeCompte().equals("E")) throw new EcoleDirecteAccountTypeException(1);

		JSONObject data = new JSONObject();
		data.put("dateDebut", dateDebut);
		data.put("dateFin", dateFin);
		data.put("token", this.token);

		try {
			String r = HttpUtils.sendRequest("https://api.ecoledirecte.com/v3/E/" + this.id + "/emploidutemps.awp?verbe=get&", "data=" + data.toString(), "POST", true, true);
			System.out.println(new JSONObject(r).getJSONArray("data").get(0).toString());
			EmploiDuTempsJson emploiDuTempsJson = new Gson().fromJson(r, EmploiDuTempsJson.class);
			if(emploiDuTempsJson.getCode() != 200) throw new EcoleDirecteUnknownConnectionException();

			return emploiDuTempsJson.getData();
		} catch (IOException e) {
			e.printStackTrace();
			throw new EcoleDirecteIOException();
		}
	}
}
