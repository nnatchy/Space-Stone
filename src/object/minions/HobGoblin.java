
package object.minions;

import object.basecard.Minion;
import player.Player;

public class HobGoblin extends Minion {
	public HobGoblin() {
		super("Hob Goblin", "STUPID GOBLIN BUT WITH MUSCLE", 5, 5, 2);
		// TODO Auto-generated constructor stub
	}

	public HobGoblin(int attack, int health) {
		super("Hob Goblin", "STUPID GOBLIN BUT WITH MUSCLE", attack, health, 2);
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
