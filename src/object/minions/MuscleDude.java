
package object.minions;

import object.basecard.Minion;
import player.Player;

public class MuscleDude extends Minion {

	public MuscleDude() {
		super("Muscle Dude", "GUY WITH MUSCLE -2 ATTACK AND -2 HEALTH AFTER BRAWL", 9, 9, 2);
		// TODO Auto-generated constructor stub
	}

	public MuscleDude(int attack, int health) {
		super("Muscle Dude", "GUY WITH MUSCLE -2 ATTACK AND -2 HEALTH AFTER BRAWL", attack, health, 2);
		// TODO Auto-generated constructor stub
	}

	public void cast(Minion target) {
		int damage = 0;
		if (this.getAttack() > target.getHealth()) {
			target.getOwner().takeDamage(this.getAttack() - target.getHealth());
		}
		target.damageMinion(this.getAttack());
		this.damageMinion(target.getAttack());
		target.getOwner().takeDamage(damage);
		if (!this.isDead()) {
			this.damageMinion(2);
			this.setMaxHealth(this.getMaxHealth() - 2);
			this.setMaxAttack(this.getAttack() - 2);
			this.setAttack(this.getAttack() - 2);
		}
	}

	public void castPlayer(Player player) {
		player.takeDamage(this.getAttack());
		this.damageMinion(2);
		this.buffMinion(-2, 0);
	}

}
