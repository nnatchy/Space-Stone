package object.spells;

import Interface.AttackMinion;
import object.basecard.Minion;
import object.basecard.Spell;

public class Poison extends Spell implements AttackMinion {

	public Poison() {
		super("Poison", "REDUCE HEALTH OF ALL OPPNENT MINIONS BY TARGET MINION ATTACK THEN DESTROY THAT MINION ", 3);
		// TODO Auto-generated constructor stub
		this.setCost(12);
	}

	@Override
	public void cast(Minion target) {
		// TODO Auto-generated method stub
		for (Minion minion : target.getOwner().getMinionBoard()) {
			minion.damageMinion(target.getAttack());
		}
		target.setHealth(0);
	}

}
