package object.minions;

import object.basecard.Minion;
import player.Player;

public class Priest extends Minion {

	public Priest() {
		super("Priest", "HIGH CLASS PRIEST\n HEAL OPPONENT TO 15 HEALTH EVERY TIME IT'S ATTACK", 20, 20, 3);
		// TODO Auto-generated constructor stub
	}

	public Priest(int attack, int health) {
		super("Priest", "HIGH CLASS PRIEST\n HEAL OPPONENT TO 15 HEALTH EVERY TIME IT'S ATTACK", attack, health, 3);
		// TODO Auto-generated constructor stub
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
		target.getOwner().healDamage(15);
	}

	public void cast(Player player) {

	}

	@Override
	public void castPlayer(Player player) {
		// TODO Auto-generated method stub
		player.takeDamage(this.getAttack());
		player.healDamage(15);
	}

}
