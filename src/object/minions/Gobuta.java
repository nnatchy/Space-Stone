package object.minions;

import object.basecard.Minion;
import player.Player;

public class Gobuta extends Minion {
	public Gobuta() {
		super("Gobuta", "STUPID GOBLIN", 1, 3, 1);
		// TODO Auto-generated constructor stub
	}

	public Gobuta(int attack, int health) {
		super("Gobuta", "STUPID GOBLIN", attack, health, 1);
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
