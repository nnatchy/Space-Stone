package object.minions;

import object.basecard.Minion;
import player.Player;

public class Ragnaros extends Minion {

	public Ragnaros() {
		super("Ragnaros", "MYSTICAL FIRE GOD\nDEAL 8 DAMAGE TO MINION WHEN ATTACK", 8, 8, 3);
		// TODO Auto-generated constructor stub
	}

	public Ragnaros(int attack, int health) {
		super("Ragnaros", "MYSTICAL FIRE GOD\nDEAL 8 DAMAGE TO MINION WHEN ATTACK", attack, health, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cast(Minion target) {
		int damage = 0;
		if (8 > target.getHealth()) {
			target.getOwner().takeDamage(8 - target.getHealth());
		}
		target.damageMinion(this.getAttack());
		target.getOwner().takeDamage(damage);
	}

	@Override
	public void castPlayer(Player player) {
		// TODO Auto-generated method stub
		player.takeDamage(8);
	}

}
