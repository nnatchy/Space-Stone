package object.basecard;

public abstract class Spell extends Card {
	private int level;
	private int cost;
	private String url;
	private String cardUrl;

	// Constructor
	public Spell(String name, String description, int level) {
		super(name, description);
		this.setLevel(level);
		this.setCost(this.getLevel() * 3);
		this.setUrl(name);
		this.setCardUrl(name);
	}

	// Getter and Setter for each field.

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = "SpellArt/" + url + ".jpg";
	}

	public String getCardUrl() {
		return cardUrl;
	}

	public void setCardUrl(String cardUrl) {
		this.cardUrl = "Card/Spell/" + cardUrl + ".png";
	}

}