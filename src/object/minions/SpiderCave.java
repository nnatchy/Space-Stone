package object.minions;

import object.basecard.Minion;
import player.Player;

public class SpiderCave extends Minion {

	public SpiderCave() {
		super("Spider Cave", "HUGE STINKY SPIDER\nIF BRAWL WITH MINION KILL THAT MINION", 5, 1, 2);
		// TODO Auto-generated constructor stub
	}

	public SpiderCave(int attack, int health) {
		super("Spider Cave", "HUGE STINKY SPIDER\nIF BRAWL WITH MINION KILL THAT MINION", attack, health, 2);
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
		if (!target.isDead()) {
			target.damageMinion(target.getHealth());
		}
	}

	@Override
	public void castPlayer(Player player) {
		// TODO Auto-generated method stub
		player.takeDamage(this.getAttack());
	}

}
