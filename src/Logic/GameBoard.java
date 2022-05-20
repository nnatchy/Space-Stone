package Logic;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import Controller.MinionSlotController;
import Controller.MyListener;
import Controller.ShopController;
import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import object.basecard.Card;
import object.basecard.Minion;
import player.Player;

public class GameBoard implements Initializable {

	public static GameBoard gameBoard;

	public static int count = 0;
	private int turnChange = 1;
	private String turnOwner;
	public static boolean stopMusic = false;

	public static String heroUrl = "Card/Hero/";

	public static String skillUrl = "SpellArt/";

	private Card currentSelect = null;
	private Card previousSelect = null;

	@FXML
	private ImageView backgroundImage;

	@FXML
	private Label chosenMinionAttack;

	@FXML
	private Label chosenMinionHealth;

	@FXML
	private Button endTurn;

	@FXML
	private Button option;

	@FXML
	private Pane skillPlayer2, skillPlayer1;

	@FXML
	private HBox playerOneBox, playerTwoBox;

	@FXML
	private Pane player1;

	@FXML
	private GridPane player1Grid;

	@FXML
	private HBox player1Hbox;

	@FXML
	private Pane player1Skill;

	@FXML
	private Pane player2;

	@FXML
	private GridPane player2Grid;

	@FXML
	private HBox player2Hbox;

	@FXML
	private Pane player2Skill;

	@FXML
	private Label playerTurn;

	@FXML
	private Button shop;

	@FXML
	private Label turnCount;

	@FXML
	private ImageView chosenMinionImage;

	@FXML
	private Label player1Health, player2Health, player1Mana, player2Mana;

	public static Stage optionPopUp;

	private Image image;
	private MyListener myListener;
	private boolean hasBrawl = false;
	public static boolean hasCast = false;
	private boolean isHeal = false;
	private Alert alert = new Alert(AlertType.ERROR);

	@FXML
	private Button brawlButton;

	// Start game and end the player turn
	public void startGame(ActionEvent event) throws IOException {
		if (count == 0) {
			// Initialize player mana and set player 1 as the start player
			GameLogic.playerOne.setMana(5);
			GameLogic.playerTwo.setMana(5);
			playerTurn.setText("Player: 1 Turn");
			this.setTurnOwner("Player1");
			turnCount.setText("Turn : 1");
		}
		count++;
		turnChange++;
		changingBackground();
		changeMana();
		if (count == 1) {
			// Change text in label to End Turn
			endTurn.setText("End Turn");
			Platform.runLater(new Runnable() {
				public void run() {
					initializeHero();
					initializeHeroSkill();
					GameLogic.playerOne.setHero(SceneController.hero1);
					GameLogic.playerTwo.setHero(SceneController.hero2);
				}
			});
		} else {
			// Reset every boolean when the round start
			hasCast = false;
			hasBrawl = false;
			ShopController.hasBuy = false;
			ShopController.hasBuyMinion = false;
			changePlayerTurn();
			refresh();
		}
	}

	// Change player mana per round
	public void changeMana() {
		if (count == 1) {
			GameLogic.playerOne.setMana(7);
			GameLogic.playerTwo.setMana(5);
			player1Mana.setText(" Mana : " + GameLogic.playerOne.getMana());
			player2Mana.setText(" Mana : " + GameLogic.playerTwo.getMana());
		} else {
			int plus = 0;
			int turn = turnChange / 2;
			if (turn > 0 && turn <= 7) {
				plus = 2;
			} else if (turn > 7 && turn <= 15) {
				plus = 4;
			} else {
				plus = 5;
			}
			if (this.getTurnOwner() == "Player2") {
				GameLogic.playerOne.setMana(GameLogic.playerOne.getMana() + plus);
			} else {
				GameLogic.playerTwo.setMana(GameLogic.playerTwo.getMana() + plus);
			}
			player1Mana.setText(" Mana : " + GameLogic.playerOne.getMana());
			player2Mana.setText(" Mana : " + GameLogic.playerTwo.getMana());
			turnCount.setText("Turn : " + turn);
		}

	}

	// Change player turn label
	public void changePlayerTurn() {
		if (count % 2 == 1) {
			playerTurn.setText("Player: 1 Turn");
			this.setTurnOwner("Player1");
		} else {
			playerTurn.setText("Player: 2 Turn");
			this.setTurnOwner("Player2");
		}
	}

	// initialize player hero
	public void initializeHero() {
		ImageView selectedHero1 = new ImageView(new Image(getClass().getClassLoader()
				.getResource(heroUrl + SceneController.hero1.getName() + ".png").toString()));
		ImageView selectedHero2 = new ImageView(new Image(getClass().getClassLoader()
				.getResource(heroUrl + SceneController.hero2.getName() + ".png").toString()));
		selectedHero1.setFitWidth(107);
		selectedHero1.setFitHeight(107);
		selectedHero2.setFitWidth(107);
		selectedHero2.setFitHeight(107);
		player1.getChildren().add(selectedHero1);
		player2.getChildren().add(selectedHero2);
		player1Health.setText(" Health : " + SceneController.hero1.getHealth());
		player2Health.setText(" Health : " + SceneController.hero2.getHealth());
	}

	// initialize player hero skill
	public void initializeHeroSkill() {
		ImageView selectedSkill1 = new ImageView(new Image(getClass().getClassLoader()
				.getResource(skillUrl + SceneController.hero1.getPower().getName() + ".jpg").toString()));
		ImageView selectedSkill2 = new ImageView(new Image(getClass().getClassLoader()
				.getResourceAsStream(skillUrl + SceneController.hero2.getPower().getName() + ".jpg")));
		selectedSkill1.setFitWidth(70);
		selectedSkill1.setFitHeight(70);
		selectedSkill2.setFitWidth(70);
		selectedSkill2.setFitHeight(70);
		player1Skill.getChildren().add(selectedSkill1);
		player2Skill.getChildren().add(selectedSkill2);
	}

	// change background per round
	public void changingBackground() {
		if (count % 3 == 2) {
			image = new Image("/pic/gameBoard1.jpg");
			backgroundImage.setImage(image);
		} else if (count % 3 == 1) {
			image = new Image("/pic/gameBoard2.jpg");
			backgroundImage.setImage(image);
		} else {
			image = new Image("/pic/gameBoard3.jpg");
			backgroundImage.setImage(image);
		}
	}

	// POP UP

	public void optionPopUp(ActionEvent event) throws IOException {
		optionPopUp = new Stage();
		Main.pop = FXMLLoader.load(getClass().getClassLoader().getResource("Logic/Option.fxml"));
		optionPopUp.setScene(new Scene(Main.pop));
		optionPopUp.initModality(Modality.APPLICATION_MODAL);
		optionPopUp.initOwner(Main.stage);
		optionPopUp.setResizable(false);
		optionPopUp.setTitle("Option");
		optionPopUp.showAndWait();
	}

	public void goToShop(ActionEvent event) throws IOException {
		Main.popUpStage = new Stage();
		Main.pop = FXMLLoader.load(getClass().getClassLoader().getResource("Logic/Shop.fxml"));
		Main.popUpStage.setScene(new Scene(Main.pop));
		Main.popUpStage.initModality(Modality.APPLICATION_MODAL);
		Main.popUpStage.initOwner(Main.stage);
		Main.popUpStage.setResizable(false);
		Main.popUpStage.setTitle("Shop");
		Main.popUpStage.showAndWait();
	}

	// Set image, attack, health for selected Minion on field
	private void setChosenMinion(Minion minion) {
		Player player = GameLogic.checkPlayer();
		if (player == GameLogic.playerOne) {
			GameLogic.indexPlayerOne = minion.getIndex();
		} else {
			GameLogic.indexPlayerTwo = minion.getIndex();
		}
		image = new Image(getClass().getClassLoader().getResourceAsStream(minion.getBoardUrl()));
		chosenMinionImage.setImage(image);
		chosenMinionAttack.setText(minion.getAttack() + "");
		chosenMinionHealth.setText(minion.getHealth() + "");
	}

	// initialize scene
	@SuppressWarnings("static-access")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		player1Grid.getChildren().clear();
		player2Grid.getChildren().clear();

		if (gameBoard == null) {
			this.gameBoard = this;
		}

	}

	// action method

	public void brawl(ActionEvent event) throws IOException {
		Player player = GameLogic.checkPlayer();
		alert.setTitle("Warning");
		if (this.previousSelect == null) {
			alert.setHeaderText("Please select Minion to brawl");
			alert.showAndWait();
		} else if (((Minion) this.previousSelect).getOwner() == ((Minion) this.currentSelect).getOwner()) {
			alert.setHeaderText("Please choose new target");
			alert.showAndWait();
			this.previousSelect = null;
			this.currentSelect = null;
		} else if (((Minion) this.previousSelect).getOwner() != player) {
			alert.setHeaderText("Please choose minion on your side");
			alert.showAndWait();
			this.previousSelect = null;
			this.currentSelect = null;
		} else if (!hasBrawl) {
			if (player == GameLogic.playerOne) {
				GameLogic.indexPlayerOne = ((Minion) previousSelect).getIndex();
				GameLogic.indexPlayerTwo = ((Minion) currentSelect).getIndex();
			} else {
				GameLogic.indexPlayerTwo = ((Minion) previousSelect).getIndex();
				GameLogic.indexPlayerOne = ((Minion) currentSelect).getIndex();
			}
			GameLogic.minionAction();
			hasBrawl = true;
			refresh();
		} else {
			alert.setHeaderText("You have brawled already");
			alert.showAndWait();
		}

	}

	// direct attack button when the opponent field is empty
	public void directAttackSkill(ActionEvent event) throws IOException {
		boolean hasPass = false;
		if (count > 0) {
			Player player = GameLogic.checkPlayer();
			alert.setTitle("Warning");
			if (!hasBrawl) {
				if (player == GameLogic.playerOne) {
					if (GameLogic.playerOne.getMinionBoard().size() == 0) {
						alert.setHeaderText("You don't have minions on your field");
						alert.showAndWait();
					} else {
						if (GameLogic.playerTwo.getMinionBoard().size() == 0) {
							for (Minion minion : player.getMinionBoard()) {
								minion.castPlayer(GameLogic.playerTwo);
							}
							hasPass = true;
						} else {
							alert.setHeaderText("Still has minions on opponent field");
							alert.showAndWait();
						}
					}

				} else {
					if (GameLogic.playerTwo.getMinionBoard().size() == 0) {
						alert.setHeaderText("You don't have minions on your field");
						alert.showAndWait();
						hasBrawl = false;
					} else {
						if (GameLogic.playerOne.getMinionBoard().size() == 0) {
							for (Minion minion : player.getMinionBoard()) {
								minion.castPlayer(GameLogic.playerOne);
							}
							hasPass = true;
						} else {
							alert.setHeaderText("Still has minions on opponent field");
							alert.showAndWait();
						}
					}

				}
				if (hasPass) {
					hasBrawl = true;
					refresh();
				}

			} else {
				alert.setHeaderText("You have use your attack already");
				alert.showAndWait();
			}
		} else {
			alert.setHeaderText("Start the game first");
			alert.showAndWait();
		}
	}

	// cast bought skill from shop
	public void skillShop(ActionEvent event) throws IOException {
		if (ShopController.hasBuy && !ShopController.isMinion) {
			Player player = GameLogic.checkPlayer();
			alert.setTitle("Warning");
			if (GameLogic.getVoidSpells().contains(ShopController.spellNameFromShop)) {
				skillCasting(player);
			} else {
				if (this.currentSelect == null) {
					alert.setHeaderText("Please choose minion to cast your hero skill to");
				} else if (ShopController.spellNameFromShop.equals("Heal")) {
					if (((Minion) this.currentSelect).getOwner() != player) {
						alert.setHeaderText("Please choose minion on your side");
					} else {
						isHeal = true;
					}
				}
				skillCasting(player);
			}

		}
	}

	// cast hero player skill
	public void skillHeroAttack(ActionEvent event) throws IOException {
		if (count > 0) {
			Player player = GameLogic.checkPlayer();
			alert.setTitle("Warning");
			if (player.getHero().getPower().getName().equals("Quick Recruit")) {
				skillCasting(player);
			} else {
				if (this.currentSelect == null) {
					alert.setHeaderText("Please choose minion to cast your hero skill to");
					alert.showAndWait();
				} else if (player.getHero().getPower().getName().equals("Heal")) {
					if (((Minion) this.currentSelect).getOwner() != player) {
						alert.setHeaderText("Please choose minion on your side");
						alert.showAndWait();
					} else {
						isHeal = true;
					}
					skillCasting(player);
				} else if (this.currentSelect != null) {
					skillCasting(player);
				}
			}
		} else {
			alert.setHeaderText("Please start the game first");
			alert.showAndWait();
		}

	}

	// Check type of spell
	public void skillCasting(Player player) throws IOException {
		if (!hasCast) {
			ArrayList<String> voidSpells = GameLogic.getVoidSpells();
			if (player.getHero().getPower().getName().equals("Quick Recruit")
					|| voidSpells.contains(ShopController.spellNameFromShop)) {
				if (ShopController.hasBuy && !player.getHero().getPower().getName().equals("Quick Recruit")) {
					GameLogic.spellAction(ShopController.spellNameFromShop);
					ShopController.hasBuy = false;
					hasCast = true;
				} else if (ShopController.hasBuy && player.getHero().getPower().getName().equals("Quick Recruit")) {
					GameLogic.spellAction(ShopController.spellNameFromShop);
					ShopController.hasBuy = false;
					hasCast = true;
				} else if (!hasCast) {
					GameLogic.spellAction(player.getHero().getPower().getName());
				}
				hasCast = true;
				refresh();
			} else {
				if (player.getHero().getPower().getName().equals("Inner Rage")
						|| ShopController.spellNameFromShop.equals("Inner Rage")) {
					GameLogic.indexPlayerOne = ((Minion) this.currentSelect).getIndex();
					GameLogic.indexPlayerTwo = ((Minion) this.currentSelect).getIndex();
				} else {
					if (player == GameLogic.playerOne) {
						if (isHeal) {
							GameLogic.indexPlayerOne = ((Minion) this.currentSelect).getIndex();
						} else {
							GameLogic.indexPlayerTwo = ((Minion) this.currentSelect).getIndex();
						}
					} else if (player == GameLogic.playerTwo) {
						if (isHeal) {
							GameLogic.indexPlayerTwo = ((Minion) this.currentSelect).getIndex();
						} else {
							GameLogic.indexPlayerOne = ((Minion) this.currentSelect).getIndex();
						}
					}
				}

				if (ShopController.hasBuy && ShopController.spellNameFromShop.length() > 0) {
					GameLogic.spellAction(ShopController.spellNameFromShop);
					ShopController.hasBuy = false;

				} else {
					GameLogic.spellAction(player.getHero().getPower().getName());

				}
				hasCast = true;
				refresh();
			}
		} else {
			alert.setHeaderText("You have use your skill already");
			alert.showAndWait();
		}
	}

	// Refresh scene
	public void refresh() throws IOException {
		player1Grid.getChildren().clear();
		player2Grid.getChildren().clear();
		myListener = new MyListener() {

			@Override
			public void onClickListener(Card card) {
				if (currentSelect == null) {
					currentSelect = card;
				}

				previousSelect = currentSelect;

				currentSelect = card;

				setChosenMinion((Minion) card);
			}
		};
		int c = 0;
		// Refresh Player 1
		GameLogic.playerOne.setIndexMinion();
		for (int i = 0; i < GameLogic.playerOne.getMinionBoard().size(); i++) {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getClassLoader().getResource("Logic/MinionSlot.fxml"));
			try {
				AnchorPane anchorPane = fxmlLoader.load();
				MinionSlotController itemController = fxmlLoader.getController();
				if (GameLogic.playerOne.getMinionBoard().get(i).getHealth() > 0) {
					itemController.setData(GameLogic.playerOne.getMinionBoard().get(i), myListener);
					player1Grid.add(anchorPane, c++, 1);
					ShopController.setGridSize(player1Grid);
					GridPane.setMargin(anchorPane, new Insets(10));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Refresh Player 2

		GameLogic.playerTwo.setIndexMinion();
		for (int i = 0; i < GameLogic.playerTwo.getMinionBoard().size(); i++) {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getClassLoader().getResource("Logic/MinionSlot.fxml"));
			try {
				AnchorPane anchorPane = fxmlLoader.load();
				MinionSlotController itemController = fxmlLoader.getController();
				if (GameLogic.playerTwo.getMinionBoard().get(i).getHealth() > 0) {
					itemController.setData(GameLogic.playerTwo.getMinionBoard().get(i), myListener);

					player2Grid.add(anchorPane, c++, 1);
					ShopController.setGridSize(player2Grid);
					GridPane.setMargin(anchorPane, new Insets(10));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		player1Mana.setText(" Mana : " + GameLogic.playerOne.getMana());
		player2Mana.setText(" Mana : " + GameLogic.playerTwo.getMana());
		player1Health.setText(" Health : " + GameLogic.playerOne.getHealth());
		player2Health.setText(" Health : " + GameLogic.playerTwo.getHealth());

		if (GameLogic.playerOne.getHealth() == 0 || GameLogic.playerTwo.getHealth() == 0) {
			endGame();
		}
	}

	// End Game Alert when one player health reach 0
	public void endGame() throws IOException {
		String gameResult = "";
		if (GameLogic.playerOne.getHealth() == 0) {
			gameResult = "PLAYER 2 WINS!";
		} else if (GameLogic.playerTwo.getHealth() == 0) {
			gameResult = "PLAYER 1 WINS!";
		}
		String dialogueString = "The game result is: " + gameResult + "\n\nClick ok to go back to Main Menu";
		Alert alertEnd = new Alert(AlertType.INFORMATION);
		alertEnd.setTitle("Game Has Ended!");
		alertEnd.setHeaderText("WE HAVE A WINNER !");
		alertEnd.setContentText(dialogueString);
		Optional<ButtonType> result = alertEnd.showAndWait();
		if (result.get() == ButtonType.OK) {
			Main.root = FXMLLoader.load(getClass().getClassLoader().getResource("Logic/MainMenu.fxml"));
			Main.scene = new Scene(Main.root);
			Main.stage.setScene(Main.scene);
			Main.stage.setResizable(false);
			Main.stage.setTitle("SPACESTONE");
			Main.stage.show();
			Logic.GameBoard.count = 0;
			player1Grid.getChildren().clear();
			player2Grid.getChildren().clear();
		}
	}

	// GETTER AND SETTER
	public String getTurnOwner() {
		return turnOwner;
	}

	public void setTurnOwner(String turnOwner) {
		this.turnOwner = turnOwner;
	}

}
