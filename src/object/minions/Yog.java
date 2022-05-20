package object.minions;

import Interface.AttackMinion;
import object.basecard.Minion;
import object.spells.Doom;
import player.Player;

public class Yog extends Minion implements AttackMinion {

	public Yog() {
		super("Yog", "MYSTERIOUS CREATURE\nDESTROY ALL MINION WHEN ATTACK CANNOT INFLICT DAMAGE", 0, 30, 3);
		// TODO Auto-generated constructor stub'
		this.setCost(40);
	}

	public Yog(int attack, int health) {
		super("Yog", "MYSTERIOUS CREATURE\nDESTROY ALL MINION WHEN ATTACK CANNOT INFLICT DAMAGE", attack, health, 3);
		// TODO Auto-generated constructor stub
		this.setCost(40);
	}

	public void cast(Minion target) {
		this.damageMinion(target.getAttack());
		Doom doom = new Doom();
		doom.cast(target);
	}

	@Override
	public void castPlayer(Player player) {
		// TODO Auto-generated method stub
		player.takeDamage(0);
	}

}
