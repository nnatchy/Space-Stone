package object.minions;

import object.basecard.Minion;
import player.Player;

public class Spider extends Minion {
	public Spider() {
		super("Spider", "SPOOKY SPIDER", 2, 2, 1);
		// TODO Auto-generated constructor stub
	}

	public Spider(int attack, int health) {
		super("Spider", "SPOOKY SPIDER", attack, health, 1);
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
