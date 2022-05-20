package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Logic.GameBoard;
import Logic.GameLogic;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import object.basecard.Card;
import object.basecard.Minion;
import object.basecard.Spell;
import player.Player;

public class ShopController implements Initializable {
	@FXML
	private VBox chosenMinionCard;

	@FXML
	private Label cardNameLabel, cardCostLabel, cardDescriptionLabel, currentPlayer, currentPlayerMana;

	@FXML
	private GridPane cardGrid;

	@FXML
	private ImageView cardImage;

	@FXML
	private ScrollPane cardScroll;

	@FXML
	private Button buyButton, goMinionShop, goSpellShop;

	@FXML
	private Label shopName;
	private Image image;
	private MyListener myListener;

	private static int selectedCard;

	private static boolean isClicked = false;

	private int selectedCardIndex;

	public static boolean isMinion = false;

	public static String spellNameFromShop = "";

	public static boolean hasBuy = false;

	public static boolean hasBuyMinion = false;

	// Calculate Player mana after buy cards.
	public void calculateCost(ActionEvent event) {
		if (isClicked) {
			if (GameBoard.count > 0) {
				if (GameBoard.hasCast && hasBuyMinion) {
					cardCostLabel.setText("You have cast spell already");
				} else {
					Player player = GameLogic.checkPlayer();
					if (player.getMana() >= selectedCard) {
						int left = player.getMana() - selectedCard;
						if (isMinion) {
							if (player.getMinionBoard().size() < 5) {
								player.setMana(left);
								hasBuy = true;
								hasBuyMinion = true;
								currentPlayerMana.setText("Mana : " + player.getMana());
								player.spawnMinion(GameLogic.getAllMinions().get(selectedCardIndex));
								cardCostLabel.setText("Purchase Complete !");
							} else {
								currentPlayerMana.setText("Full Slot");
							}
						} else {
							if (!GameBoard.hasCast) {
								player.setMana(left);
								hasBuy = true;
								currentPlayerMana.setText("Mana : " + player.getMana());
								spellNameFromShop = GameLogic.getAllSpells().get(selectedCardIndex).getName();
								cardCostLabel.setText("Purchase Complete !");
							} else {
								cardCostLabel.setText("You have cast spell already");
							}

						}
					} else {
						cardCostLabel.setText("Not enough mana");
					}
				}

			}
		} else {
			cardCostLabel.setText("Please choose shop first !");
		}
	}

	// Close shop
	public void closeShop(ActionEvent event) throws IOException {
		Main.popUpStage.close();
		isClicked = false;
		GameBoard.gameBoard.refresh();
	}

	// Initialize Player Information
	public void initializePlayerInformation() {
		if (GameBoard.count == 0) {
			currentPlayer.setText("Waiting for game to start");
			currentPlayerMana.setText("0");
			cardCostLabel.setText("Please start the game first");
		} else {
			if (GameBoard.count % 2 == 1) {
				currentPlayer.setText("Player: 1");
				currentPlayerMana.setText("Mana : " + GameLogic.playerOne.getMana());
			} else {
				currentPlayer.setText("Player: 2");
				currentPlayerMana.setText("Mana : " + GameLogic.playerTwo.getMana());
			}
		}
	}

	// Find card in ArrayList of Minion and Spell
	public int findCard(Card card) {
		for (int i = 0; i < GameLogic.getAllMinions().size(); i++) {
			if (GameLogic.getAllMinions().get(i).getName().equals(card.getName())) {
				return i;
			}
		}
		for (int i = 0; i < GameLogic.getAllSpells().size(); i++) {
			if (GameLogic.getAllSpells().get(i).getName().equals(card.getName())) {
				return i;
			}
		}
		return -1;
	}

	// Set selected minion in Buy Box
	private void setChosenMinion(Minion minion) {
		cardNameLabel.setText(minion.getName());
		cardDescriptionLabel.setText(minion.getDescription());
		cardCostLabel.setText("COST : \t" + minion.getCost() + "\t" + Main.currency);
		selectedCard = minion.getCost();
		selectedCardIndex = findCard((Card) minion);
		isMinion = true;
		image = new Image(getClass().getClassLoader().getResourceAsStream(minion.getShopUrl()));
		cardImage.setImage(image);
	}

	// Set selected spell in Buy Box
	private void setChosenSpell(Spell spell) {
		cardNameLabel.setText(spell.getName());
		cardDescriptionLabel.setText(spell.getDescription());
		cardCostLabel.setText("COST : \t" + spell.getCost() + "\t" + Main.currency);
		selectedCard = spell.getCost();
		selectedCardIndex = findCard((Card) spell);
		isMinion = false;
		image = new Image(getClass().getClassLoader().getResourceAsStream(spell.getCardUrl()));
		cardImage.setImage(image);
	}

	// Set grid size
	public static void setGridSize(GridPane cardGrid) {
		// set grid width
		cardGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
		cardGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
		cardGrid.setMaxWidth(Region.USE_PREF_SIZE);

		// set grid height
		cardGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
		cardGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
		cardGrid.setMaxHeight(Region.USE_PREF_SIZE);
	}

	// Initialize Shop
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<Minion> minions = new ArrayList<>();
		ArrayList<Spell> spells = new ArrayList<>();
		minions.addAll(GameLogic.getAllMinions());
		spells.addAll(GameLogic.getAllSpells());
		shopName.setText("Card Shop");
		// show first picture of selected shop
		setChosenMinion(minions.get(0));

		// Go to Minion Shop
		goMinionShop.setOnMouseClicked((mouseClicked) -> {
			isClicked = true;
			initializePlayerInformation();
			shopName.setText("Minion Shop");
			setChosenMinion(minions.get(0));

			myListener = new MyListener() {
				@Override
				public void onClickListener(Card card) {
					ItemController.checkTypeCard(card);
					setChosenMinion(ItemController.minionInGrid);
				}
			};

			int column = 0;
			int row = 1;
			// set item for each row, col
			for (int i = 0; i < minions.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getClassLoader().getResource("Logic/Item.fxml"));
				try {
					AnchorPane anchorPane = new AnchorPane();
					anchorPane = fxmlLoader.load();
					ItemController itemController = fxmlLoader.getController();

					itemController.setDataCard((Card) minions.get(i), myListener);
					if (column == 3) {
						column = 0;
						row++;
					}

					cardGrid.add(anchorPane, column++, row); // (child,column,row)

					setGridSize(cardGrid);
					GridPane.setMargin(anchorPane, new Insets(10));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// Go to Spell Shop
		goSpellShop.setOnMouseClicked((mouseClicked) -> {
			isClicked = true;
			initializePlayerInformation();
			shopName.setText("Spell Shop");
			// show first picture of selected shop
			setChosenSpell(spells.get(0));
			myListener = new MyListener() {
				@Override
				public void onClickListener(Card card) {
					ItemController.checkTypeCard(card);
					setChosenSpell(ItemController.spellInGrid);
				}
			};

			int column = 0;
			int row = 1;
			// set item for each row, col
			for (int i = 0; i < spells.size(); i++) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getClassLoader().getResource("Logic/Item.fxml"));

				try {
					AnchorPane anchorPane = new AnchorPane();
					anchorPane = fxmlLoader.load();
					ItemController itemController = fxmlLoader.getController();
					itemController.setDataCard((Card) spells.get(i), myListener);
					if (column == 3) {
						column = 0;
						row++;
					}

					cardGrid.add(anchorPane, column++, row); // (child,column,row)

					setGridSize(cardGrid);
					GridPane.setMargin(anchorPane, new Insets(10));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
