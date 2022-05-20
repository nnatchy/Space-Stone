package object.spells;

import java.util.ArrayList;

import Interface.AttackMinion;
import object.basecard.Minion;
import object.basecard.Spell;

public class Doom extends Spell implements AttackMinion {

	public Doom() {
		super("Doom", "DESTROY ALL MINIONS IN TARGET PLAYER FIELD", 3);
		// TODO Auto-generated constructor stub
		this.setCost(20);
	}

	// DESTROY ALL MINIONS
	@Override
	public void cast(Minion target) {
		// TODO Auto-generated method stub
		target.getOwner().setMinionBoard(new ArrayList<Minion>());
	}
}
