package Controller;

import Logic.GameLogic;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import object.basecard.Card;
import object.basecard.Minion;
import object.basecard.Spell;

public class ItemController {
	@FXML
	private Label smallCostLabel;

	@FXML
	private ImageView smallCardImage;

	@FXML
	private Label smallCardNameLabel;

	@FXML
	private void click(MouseEvent mouseEvent) {
		myListener.onClickListener(card);
	}

	private Card card;
	public static Minion minionInGrid;
	public static Spell spellInGrid;
	private MyListener myListener;
	public static boolean isMinion = false;
	public static boolean isSpell = false;

	// Set information to card
	public void setDataCard(Card card, MyListener myListener) {
		this.setCard(card);
		this.myListener = myListener;
		checkTypeCard(card);
		if (isMinion) {
			smallCostLabel.setText("" + minionInGrid.getCost() + Main.currency);
			smallCardNameLabel.setText("  " + minionInGrid.getName());
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(minionInGrid.getImageUrl()));
			smallCardImage.setImage(image);
		}
		if (isSpell) {
			smallCostLabel.setText("" + spellInGrid.getCost() + Main.currency);
			smallCardNameLabel.setText("  " + spellInGrid.getName());
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(spellInGrid.getUrl()));
			smallCardImage.setImage(image);
		}
	}

	// Check type card whether it is spell or minion
	public static void checkTypeCard(Card card) {
		for (Minion x : GameLogic.getAllMinions()) {
			if (x.getName().equals(card.getName())) {
				isMinion = true;
				minionInGrid = x;
				isSpell = false;
			}
		}
		for (Spell x : GameLogic.getAllSpells()) {
			if (x.getName().equals(card.getName())) {
				isSpell = true;
				spellInGrid = x;
				isMinion = false;
			}
		}
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

}
