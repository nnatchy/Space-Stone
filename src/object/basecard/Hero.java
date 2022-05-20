package object.basecard;

public abstract class Hero extends Card {
	private String name;
	private int health;
	private Spell power;

	// Constructor
	public Hero(String name, String description, int health, Spell power) {
		super(name, description);
		this.setHealth(health);
		this.setPower(power);
	}

	// GETTER AND SETTER FOR EACH FIELD
	public void setName(String name) {
		if (name.isBlank()) {
			this.name = "???";
		} else {
			this.name = name;
		}
	}

	public void setHealth(int health) {
		if (health < 0) {
			this.health = 0;
		} else {
			this.health = health;
		}
	}

	public void setPower(Spell power) {
		this.power = power;
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public Spell getPower() {
		return power;
	}

}
