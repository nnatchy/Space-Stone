package player;

import java.util.ArrayList;

import object.basecard.Hero;
import object.basecard.Minion;

public class Player {
	private ArrayList<Minion> minionBoard = new ArrayList<>();
	private Hero hero;
	private int health;
	private int mana;

	// Constructor
	public Player(Hero hero) {
		this.setMinionBoard(minionBoard);
		this.setHero(hero);
		this.setMana(5);
		this.setHealth(hero.getHealth());
	}

	// Methods

	public void resetHealth() {
		this.setHealth(hero.getHealth());
	}

	public void takeDamage(int damage) {
		this.setHealth(this.getHealth() - damage);
	}

	public void healDamage(int heal) {
		if (this.getHealth() + heal > this.getHero().getHealth()) {
			this.setHealth(this.getHero().getHealth());
		} else {
			this.setHealth(this.getHealth() + heal);
		}

	}

	public void setIndexMinion() {
		int c = 0;
		for (Minion minion : this.getMinionBoard()) {
			minion.setIndex(c);
			c++;
		}
	}

	public void spawnMinion(Minion minion) {
		// TODO Auto-generated method stub
		if (this.getMinionBoard().size() < 5) {
			this.minionBoard.add(minion);
			setIndexMinion();
			minion.setOwner(this);
		}
	}

	public void killMinion(Minion minion) {
		this.minionBoard.remove(minion);
	}
	// GETTER AND SETTERS //

	public void setMinionBoard(ArrayList<Minion> minionBoard) {
		if (minionBoard.size() < 5) {
			this.minionBoard = minionBoard;
		}
	}

	public ArrayList<Minion> getMinionBoard() {
		// TODO Auto-generated method stub
		return minionBoard;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health < 0) {
			this.health = 0;
		} else {
			this.health = health;
		}
	}

}
