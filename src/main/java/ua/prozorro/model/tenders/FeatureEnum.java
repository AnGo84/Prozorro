package ua.prozorro.model.tenders;

public class FeatureEnum {

	private double value;
	private String title;
	private String description;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("FeatureEnum{");
		sb.append("value=").append(value);
		sb.append(", title='").append(title).append('\'');
		sb.append(", description='").append(description).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
