package object.spells;

import Interface.BuffMinionAble;
import Logic.GameLogic;
import object.basecard.Minion;
import object.basecard.Spell;

public class Bindingheal extends Spell implements BuffMinionAble {
	public Bindingheal() {
		super("Binding Heal", "HEAL 5 HEALTH OF THE TARGET AND YOUR HERO", 2);
		// TODO Auto-generated constructor stub
		this.setCost(5);
	}

	@Override
	public void cast(Minion target) {
		// TODO Auto-generated method stub
		target.healMinion(5);
		if (Logic.GameBoard.count % 2 == 1) {
			GameLogic.playerOne.healDamage(5);
		} else {
			GameLogic.playerTwo.healDamage(5);
		}

	}

}
