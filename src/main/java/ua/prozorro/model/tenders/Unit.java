package ua.prozorro.model.tenders;

public class Unit {
	//@SerializedName("code")
	//@Expose
	private String code;
	//@SerializedName("name")
	//@Expose
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Unit{");
		sb.append("code='").append(code).append('\'');
		sb.append(", name='").append(name).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
