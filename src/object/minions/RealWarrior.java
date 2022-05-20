package object.minions;

import object.basecard.Minion;
import player.Player;

public class RealWarrior extends Minion {

	public RealWarrior() {
		super("Real Warrior", "IF THIS MINION DIE BY BRAWL WHEN ATTACK WITH OTHER MINION WIN THE GAME", 1, 10, 3);
		// TODO Auto-generated constructor stub
		this.setCost(this.getLevel() * 10);
	}

	public RealWarrior(int attack, int health) {
		super("Real Warrior", "IF THIS MINION DIE BY BRAWL WHEN ATTACK WITH OTHER MINION WIN THE GAME", attack, health,
				3);
		// TODO Auto-generated constructor stub
		this.setCost(this.getLevel() * 10);
	}

	public void cast(Minion target) {
		int damage = 0;
		if (this.getAttack() > target.getHealth()) {
			target.getOwner().takeDamage(this.getAttack() - target.getHealth());
		}
		target.damageMinion(this.getAttack());
		this.damageMinion(target.getAttack());
		target.getOwner().takeDamage(damage);
		if (this.isDead()) {
			target.getOwner().takeDamage(target.getOwner().getHero().getHealth());
		}
	}

	@Override
	public void castPlayer(Player player) {
		// TODO Auto-generated method stub
		player.takeDamage(this.getAttack());
	}

}
