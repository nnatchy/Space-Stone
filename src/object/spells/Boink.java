package object.spells;

import Logic.GameLogic;
import object.basecard.Spell;
import player.Player;

public class Boink extends Spell {

	public Boink() {
		super("Boink", "DAMAGE OPPONENT HERO 5 DAMAGE", 2);
		// TODO Auto-generated constructor stub
		this.setCost(8);
	}

	// Deal 5 Damage to Opponent
	public void cast() {
		// TODO Auto-generated method stub
		Player player = GameLogic.checkPlayer();
		if (Logic.GameLogic.playerOne == player) {
			Logic.GameLogic.playerTwo.takeDamage(5);
		} else {
			Logic.GameLogic.playerOne.takeDamage(5);
		}
	}

}
