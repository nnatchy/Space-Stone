package object.basecard;

import Interface.AttackMinionAble;
import player.Player;

public abstract class Minion extends Card implements AttackMinionAble {
	protected int attack;
	protected int health;
	protected int level;
	protected int maxHealth;
	protected int maxAttack;
	protected int cost;
	protected boolean isDead;
	protected String imageUrl;
	protected String shopUrl;
	protected String boardUrl;
	protected int index;
	protected Player owner;

	// Constructor
	public Minion(String name, String description, int attack, int health, int level) {
		super(name, description);
		this.setHealth(health);
		this.setAttack(attack);
		this.setDead(false);
		this.setLevel(level);
		this.setMaxHealth(health);
		this.setMaxAttack(attack);
		this.setCost(this.getLevel() * 5);
		this.setImageUrl(name);
		this.setShopUrl(name);
		this.setBoardUrl(name);
		this.setIndex(0);
		this.setOwner(owner);
	}

	// Methods
	public void buffMinion(int buffAttack, int buffHealth) {
		this.setMaxHealth(this.maxHealth + buffHealth);
		this.setMaxAttack(this.maxAttack + buffAttack);
		this.setHealth(this.health + buffHealth);
		this.setAttack(this.attack + buffAttack);
	}

	public abstract void cast(Minion target);

	public abstract void castPlayer(Player player);

	public void damageForPlayer(int damage) {
		this.getOwner().takeDamage(damage);
	}

	public void healMinion(int healHealth) {
		if (healHealth + this.getHealth() >= this.getMaxHealth()) {
			this.setHealth(this.getMaxHealth());
		} else {
			this.setHealth(this.getHealth() + healHealth);
		}
	}

	public void damageMinion(int damage) {
		this.setHealth(this.getHealth() - damage);
	}

	// GETTER AND SETTER //
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health <= 0) {
			this.health = 0;
			isDead = true;
			this.getOwner().killMinion(this);
		} else {
			this.health = health;
		}
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		if (level > 3) {
			this.level = 3;
		} else {
			this.level = level;
		}
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getMaxAttack() {
		return maxAttack;
	}

	public void setMaxAttack(int maxAttack) {
		this.maxAttack = maxAttack;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = "MinionArt/" + imageUrl + ".jpg";
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = "Card/Minion/Shop/" + shopUrl + ".png";
	}

	public String getBoardUrl() {
		return boardUrl;
	}

	public void setBoardUrl(String boardUrl) {
		this.boardUrl = "Card/Minion/GameBoard/" + boardUrl + ".png";
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

}
