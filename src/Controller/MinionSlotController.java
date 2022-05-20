package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import object.basecard.Minion;

public class MinionSlotController {

	@FXML
	private Label minionSlotAttack;

	@FXML
	private Label minionSlotHealth;

	@FXML
	private ImageView minionSlotImage;

	private Minion minion;
	private MyListener myListener;

	@FXML
	private AnchorPane minionSlotAnchorPane;

	@FXML
	private Label minionSlotName;

	@FXML
	private void click(MouseEvent mouseEvent) {
		myListener.onClickListener(minion);
	}

	// Set Data to minion
	public void setData(Minion minion, MyListener myListener) {
		minionSlotAnchorPane.setMaxWidth(170);
		this.setMinion(minion);
		this.myListener = myListener;
		minionSlotName.setText(minion.getName());
		minionSlotAttack.setText("ATK:" + minion.getAttack());
		minionSlotHealth.setText("HP:" + minion.getHealth());
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(minion.getImageUrl()));
		minionSlotImage.setImage(image);
	}

	public Minion getMinion() {
		return minion;
	}

	public void setMinion(Minion minion) {
		this.minion = minion;
	}

}