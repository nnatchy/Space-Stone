package object.minions;

import object.basecard.Minion;
import player.Player;

public class George extends Minion {
	public George() {
		super("George", "HIGHSCHOOL GENIUS\n HEAL BACK TO MAX HEALTH EVERY TIME IT'S ATTACK", 1, 4, 1);
		// TODO Auto-generated constructor stub
		this.setCost(8);
	}

	public George(int attack, int health) {
		super("George", "HIGHSCHOOL GENIUS\n HEAL BACK TO MAX HEALTH EVERY TIME IT'S ATTACK", attack, health, 1);
		// TODO Auto-generated constructor stub
		this.setCost(8);
	}

	@Override
	public void cast(Minion target) {
		int damage = 0;
		if (this.getAttack() > target.getHealth()) {
			target.getOwner().takeDamage(this.getAttack() - target.getHealth());
		}
		target.damageMinion(this.getAttack());
		this.damageMinion(target.getAttack());
		target.getOwner().takeDamage(damage);
		if (!this.isDead()) {
			this.healMinion(this.getMaxHealth());
		}
	}

	@Override
	public void castPlayer(Player player) {
		// TODO Auto-generated method stub
		player.takeDamage(this.getAttack());
	}

}
