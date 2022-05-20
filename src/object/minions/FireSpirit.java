package object.minions;

import object.basecard.Minion;
import player.Player;

public class FireSpirit extends Minion {
	public FireSpirit() {
		super("Fire Spirit", "Spirit of Fire", 3, 1, 1);
		// TODO Auto-generated constructor stub
	}

	public FireSpirit(int attack, int health) {
		super("Fire Spirit", "Spirit of Fire", attack, health, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cast(Minion target) {
		// TODO Auto-generated method stub
		int damage = 0;
		if (this.getAttack() > target.getHealth()) {
			target.getOwner().takeDamage(this.getAttack() - target.getHealth());
		}
		target.damageMinion(this.getAttack());
		this.damageMinion(target.getAttack());
		target.getOwner().takeDamage(damage);
	}

	@Override
	public void castPlayer(Player player) {
		// TODO Auto-generated method stub
		player.takeDamage(this.getAttack());
	}

}
