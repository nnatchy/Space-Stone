package object.minions;

import object.basecard.Minion;
import player.Player;

public class Dragon extends Minion {
	public Dragon() {
		super("Dragon", "LEGENDARY CREATURE", 12, 12, 3);
		// TODO Auto-generated constructor stub
	}

	public Dragon(int attack, int health) {
		super("Dragon", "LEGENDARY CREATURE", attack, health, 3);
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
