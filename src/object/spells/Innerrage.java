package object.spells;

import Interface.AttackMinion;
import object.basecard.Minion;
import object.basecard.Spell;

public class Innerrage extends Spell implements AttackMinion {

	public Innerrage() {
		super("Inner Rage", "SET MINION ATTACK EQUAL TO THEIR HEALTH", 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cast(Minion target) {
		// TODO Auto-generated method stub
		target.setAttack(target.getHealth());
	}

}
