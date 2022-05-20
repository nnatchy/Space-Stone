
package object.minions;

import object.basecard.Minion;
import player.Player;

public class EarthSpirit extends Minion {
	public EarthSpirit() {
		super("Earth Spirit", "Spirit of Earth", 3, 15, 2);
		// TODO Auto-generated constructor stub
		this.setCost(13);
	}

	public EarthSpirit(int attack, int health) {
		super("Earth Spirit", "Spirit of Earth", attack, health, 2);
		// TODO Auto-generated constructor stub
		this.setCost(13);
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
