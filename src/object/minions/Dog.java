package object.minions;

import object.basecard.Minion;
import player.Player;

public class Dog extends Minion {
	public Dog() {
		super("Dog", "GOOD DOGGO\n +3 ATTACK WHEN BRAWL", 1, 2, 1);
		// TODO Auto-generated constructor stub
	}

	public Dog(int attack, int health) {
		super("Dog", "GOOD DOGGO\\n +3 ATTACK WHEN BRAWL", attack, health, 1);
	}

	@Override
	public void cast(Minion target) {
		int damage = 0;
		if (this.getAttack() + 3 > target.getHealth()) {
			target.getOwner().takeDamage(this.getAttack() + 3 - target.getHealth());
		}
		target.damageMinion(this.getAttack() + 3);
		this.damageMinion(target.getAttack());
		target.getOwner().takeDamage(damage);
	}

	@Override
	public void castPlayer(Player player) {
		// TODO Auto-generated method stub
		player.takeDamage(this.getAttack());
	}

}
