package fr.benco11.jlibecoledirecte.student;

public class SchoolLifeJson {
	private SchoolLifeData data;
	private int code;
	
	public int getCode() {
		return code;
	}
	public SchoolLifeJson(SchoolLifeData data) {
		this.data = data;
	}
	public SchoolLifeData getData() {
		return data;
	}
}
