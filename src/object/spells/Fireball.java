package object.spells;

import Interface.AttackMinionAble;
import object.basecard.Minion;
import object.basecard.Spell;

public class Fireball extends Spell implements AttackMinionAble {

	public Fireball() {
		super("Fireball", "REDUCE 6 HEALTH OF THE TARGET", 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cast(Minion target) {
		// TODO Auto-generated method stub
		target.damageMinion(6);
	}

}
