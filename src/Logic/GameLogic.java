package Logic;

import java.util.ArrayList;

import Interface.BuffMinionAble;
import Interface.SpawnMinionAble;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import Interface.AttackMinionAble;
import object.basecard.Minion;
import object.basecard.Spell;
import object.minions.*;
import object.spells.*;
import player.Player;

public class GameLogic {

	public static Player playerOne = new Player(SceneController.hero1);
	public static Player playerTwo = new Player(SceneController.hero2);
	public static int indexPlayerOne;
	public static int indexPlayerTwo;

	// Check current player
	public static Player checkPlayer() {
		if (GameBoard.count % 2 == 1) {
			return playerOne;
		}
		return playerTwo;
	}

	// Get Level 1 Minions
	public static ArrayList<Minion> getLevelOneMinions() {
		ArrayList<Minion> levelOneMinions = new ArrayList<>();
		levelOneMinions.add(new Dog());
		levelOneMinions.add(new FireSpirit());
		levelOneMinions.add(new Gobuta());
		levelOneMinions.add(new Spider());
		levelOneMinions.add(new George());
		return levelOneMinions;
	}

	// Get Level 2 Minions
	public static ArrayList<Minion> getLevelTwoMinions() {
		ArrayList<Minion> levelTwoMinions = new ArrayList<>();
		levelTwoMinions.add(new HobGoblin());
		levelTwoMinions.add(new Knight());
		levelTwoMinions.add(new MuscleDude());
		levelTwoMinions.add(new SpiderCave());
		levelTwoMinions.add(new EarthSpirit());
		return levelTwoMinions;
	}

	// Get Level 3 Minions
	public static ArrayList<Minion> getLevelThreeMinions() {
		ArrayList<Minion> levelThreeMinions = new ArrayList<>();
		levelThreeMinions.add(new Dragon());
		levelThreeMinions.add(new Priest());
		levelThreeMinions.add(new Ragnaros());
		levelThreeMinions.add(new RealWarrior());
		levelThreeMinions.add(new Yog());
		return levelThreeMinions;
	}

	// Get Level 1 Spells
	public static ArrayList<Spell> getLevelOneSpells() {
		ArrayList<Spell> levelOneSpell = new ArrayList<>();
		levelOneSpell.add(new Heal());
		levelOneSpell.add(new Fireblast());
		levelOneSpell.add(new Innerrage());
		levelOneSpell.add(new Buff());
		levelOneSpell.add(new Quickrecruit());
		return levelOneSpell;
	}

	// Get Level 2 Spells
	public static ArrayList<Spell> getLevelTwoSpells() {
		ArrayList<Spell> levelTwoSpell = new ArrayList<>();
		levelTwoSpell.add(new Bindingheal());
		levelTwoSpell.add(new Massheal());
		levelTwoSpell.add(new Fireball());
		levelTwoSpell.add(new Firerings());
		levelTwoSpell.add(new Boink());

		return levelTwoSpell;
	}

	// Get Level 3 Spells
	public static ArrayList<Spell> getLevelThreeSpells() {
		ArrayList<Spell> levelThreeSpell = new ArrayList<>();
		levelThreeSpell.add(new Powerinfusion());
		levelThreeSpell.add(new InstantKill());
		levelThreeSpell.add(new Poison());
		levelThreeSpell.add(new Legend());
		levelThreeSpell.add(new Doom());

		return levelThreeSpell;
	}

	// Get All Minions
	public static ArrayList<Minion> getAllMinions() {
		ArrayList<Minion> allMinions = new ArrayList<>();
		allMinions.addAll(getLevelOneMinions());
		allMinions.addAll(getLevelTwoMinions());
		allMinions.addAll(getLevelThreeMinions());

		return allMinions;
	}

	// Get All Spells
	public static ArrayList<Spell> getAllSpells() {
		ArrayList<Spell> allSpells = new ArrayList<>();
		allSpells.addAll(getLevelOneSpells());
		allSpells.addAll(getLevelTwoSpells());
		allSpells.addAll(getLevelThreeSpells());

		return allSpells;
	}

	// Get Buff Spells
	public static ArrayList<String> getBuffSpells() {
		ArrayList<String> buffSpells = new ArrayList<>();
		buffSpells.add(new Buff().getName());
		buffSpells.add(new Heal().getName());
		buffSpells.add(new Bindingheal().getName());
		buffSpells.add(new Massheal().getName());
		buffSpells.add(new Powerinfusion().getName());
		buffSpells.add(new Innerrage().getName());
		return buffSpells;
	}

	// Get Spell that can be casted without any target
	public static ArrayList<String> getVoidSpells() {
		ArrayList<String> voidSpells = new ArrayList<>();
		voidSpells.add(new Quickrecruit().getName());
		voidSpells.add(new Legend().getName());
		voidSpells.add(new Boink().getName());
		return voidSpells;
	}

	// Casting spell by checking name of card
	public static void spellAction(String name) {
		Player player = checkPlayer();
		ArrayList<String> buffSpells = getBuffSpells();
		ArrayList<String> voidSpells = getVoidSpells();
		for (Spell spell : getAllSpells()) {
			if (spell.getName().equals(name)) {
				if (player == playerOne) {
					// Player 1
					// Check buff spells
					if (buffSpells.contains(spell.getName())) {
						if (player.getMinionBoard().size() != 0) {
							Minion healMinion = player.getMinionBoard().get(indexPlayerOne);
							if (spell instanceof BuffMinionAble) {
								((BuffMinionAble) spell).cast(healMinion);
							} else {
								if (playerTwo.getMinionBoard().size() == 0) {
									((AttackMinionAble) spell).cast(healMinion);
								} else {
									Minion targetMinion = playerTwo.getMinionBoard().get(indexPlayerTwo);
									((AttackMinionAble) spell).cast(targetMinion);
								}
							}
						} else {
							GameBoard.hasCast = false;
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Warning");
							alert.setHeaderText("You must have Minion");
							alert.showAndWait();
						}
						// Check Void spells
					} else if (voidSpells.contains(spell.getName())) {
						if (spell instanceof SpawnMinionAble) {
							((SpawnMinionAble) spell).cast();
						} else {
							Boink boink = new Boink();
							boink.cast();
						}
					} else {
						Minion target = playerTwo.getMinionBoard().get(indexPlayerTwo);
						((AttackMinionAble) spell).cast(target);
					}
				} else {
					// Player 2
					// Check buff spells
					if (buffSpells.contains(spell.getName())) {
						if (player.getMinionBoard().size() != 0) {
							Minion healMinion = player.getMinionBoard().get(indexPlayerTwo);
							if (spell instanceof BuffMinionAble) {
								((BuffMinionAble) spell).cast(healMinion);
							} else {
								if (playerOne.getMinionBoard().size() == 0) {
									((AttackMinionAble) spell).cast(healMinion);
								} else {
									Minion targetMinion = playerTwo.getMinionBoard().get(indexPlayerOne);
									((AttackMinionAble) spell).cast(targetMinion);
								}
							}
						} else {
							GameBoard.hasCast = false;
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Warning");
							alert.setHeaderText("You must have Minion");
							alert.showAndWait();
						}
						// Check Void spells
					} else if (voidSpells.contains(spell.getName())) {
						if (spell instanceof SpawnMinionAble) {
							((SpawnMinionAble) spell).cast();
						} else {
							Boink boink = new Boink();
							boink.cast();
						}
					} else {
						Minion target = playerOne.getMinionBoard().get(indexPlayerOne);
						((AttackMinionAble) spell).cast(target);
					}
				}
			}
		}
	}

	// Minion attack each other method
	public static void minionAction() {
		if (Logic.GameBoard.count % 2 == 1) {
			Minion player1Minion = playerOne.getMinionBoard().get(indexPlayerOne);
			Minion target = playerTwo.getMinionBoard().get(indexPlayerTwo);
			if (playerTwo.getMinionBoard().size() > 0) {
				player1Minion.cast(target);
			} else {
				player1Minion.castPlayer(playerTwo);
			}
		} else {
			Minion player2Minion = playerTwo.getMinionBoard().get(indexPlayerTwo);
			Minion target = playerOne.getMinionBoard().get(indexPlayerOne);
			if (playerOne.getMinionBoard().size() > 0) {
				player2Minion.cast(target);
			} else {
				player2Minion.castPlayer(playerTwo);
			}
		}
	}

}
