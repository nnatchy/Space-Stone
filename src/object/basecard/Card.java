package object.basecard;

public abstract class Card implements Cloneable {
	private String name;
	private String description;

	// Constructor
	protected Card(String name, String description) {
		this.setName(name);
		this.setDescription(description);
	}

	protected Card(String name) {
		this.setName(name);
	}

	// GETTER AND SETTER //
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setName(String name) {
		if (name.isBlank()) {
			this.name = "???";
		} else {
			this.name = name;
		}
	}

	public void setDescription(String description) {
		if (description.isBlank()) {
			this.description = "???";
		} else {
			this.description = description;
		}
	}

}
