package Logic;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import application.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Modality;
import javafx.stage.Stage;
import object.basecard.Hero;
import object.basecard.Minion;
import object.heroes.BadWitch;
import object.heroes.LightHawk;
import object.heroes.LightPaladin;
import object.heroes.OgreKing;
import object.heroes.RedMage;

public class SceneController implements Initializable {
	public static Hero hero1;
	public static Hero hero2;
	@FXML
	private Button toPlayBtn, backToMain, heroPreview, playButton;

	@FXML
	private Button redMage, lightPaladin, ogreKing, lightHawk, badWitch, redMage1, lightPaladin1, ogreKing1, lightHawk1,
			badWitch1;

	@FXML
	private Label chooseHeroPlayer1, chooseHeroPlayer2;

	@FXML
	private Slider volumeSlider = new Slider();

	/*
	 * Switch Scene
	 */
	public void switchToSelectHero(ActionEvent event) throws IOException, InterruptedException {
		Main.root = FXMLLoader.load(getClass().getClassLoader().getResource("Logic/SelectHero.fxml"));
		Main.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Main.scene = new Scene(Main.root);
		Main.stage.setScene(Main.scene);
		Main.stage.setResizable(false);
		Main.stage.setTitle("Select Hero");
		Main.stage.show();
	}

	public void switchToMainMenu(ActionEvent event) throws IOException {
		Main.root = FXMLLoader.load(getClass().getClassLoader().getResource("Logic/MainMenu.fxml"));
		Main.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Main.scene = new Scene(Main.root);
		Main.stage.setScene(Main.scene);
		Main.stage.setTitle("Main Menu");
		Main.stage.setResizable(false);
		Main.stage.show();
	}

	// check if both users choose hero, then continue to next scene
	public void switchToGameBoard(ActionEvent event) throws IOException {
		Main.root = FXMLLoader.load(getClass().getClassLoader().getResource("Logic/GameBoard.fxml"));
		Main.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		boolean canPass = true;
		if (chooseHeroPlayer1.getText().length() < 1) {
			chooseHeroPlayer1.setText("Player 1 please choose Hero");
			canPass = false;
		}
		if (chooseHeroPlayer2.getText().length() < 1) {
			chooseHeroPlayer2.setText("Player 2 please choose Hero");
			canPass = false;
		}
		if (canPass && chooseHeroPlayer2.getText().length() != 27 && chooseHeroPlayer1.getText().length() != 27) {
			Main.scene = new Scene(Main.root);
			Main.stage.setScene(Main.scene);
			Main.stage.setResizable(false);
			Main.stage.setTitle("Game Board");
			Main.stage.show();
			GameLogic.playerOne.setMinionBoard(new ArrayList<Minion>());
			GameLogic.playerTwo.setMinionBoard(new ArrayList<Minion>());
			GameLogic.playerOne.setHero(hero1);
			GameLogic.playerTwo.setHero(hero2);
			GameLogic.playerOne.resetHealth();
			GameLogic.playerTwo.resetHealth();
			GameBoard.gameBoard.refresh();
		}

	}

	// Exit Application
	public void exit(ActionEvent event) throws IOException {
		Main.root = FXMLLoader.load(getClass().getClassLoader().getResource("Logic/MainMenu.fxml"));
		Main.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Main.stage.close();
	}

	// Show pop up
	public void showHeroPreview(ActionEvent event) throws IOException {
		Main.popUpStage = new Stage();
		Main.pop = FXMLLoader.load(getClass().getClassLoader().getResource("Logic/HeroPreview.fxml"));
		Main.popUpStage.setScene(new Scene(Main.pop));
		Main.popUpStage.initModality(Modality.APPLICATION_MODAL);
		Main.popUpStage.initOwner(Main.stage);
		Main.popUpStage.setResizable(false);
		Main.popUpStage.setTitle("HeroPreview");
		Main.popUpStage.showAndWait();
//		ImageView image = new ImageView(new Image(getClass().getClassLoader().getResource("Card/Hero/Red Mage.png").toString()));
//		p1.setImage(image);
	}

	public void showHowToPlay(ActionEvent event) throws IOException {
		Main.popUpStage = new Stage();
		Main.pop = FXMLLoader.load(getClass().getClassLoader().getResource("Logic/HowToPlay.fxml"));
		Main.popUpStage.setScene(new Scene(Main.pop));
		Main.popUpStage.initModality(Modality.APPLICATION_MODAL);
		Main.popUpStage.initOwner(Main.stage);
		Main.popUpStage.setResizable(false);
		Main.popUpStage.setTitle("HowToPlay");
		Main.popUpStage.showAndWait();
	}

	public void backToMain(ActionEvent event) throws IOException {
		Main.popUpStage = (Stage) backToMain.getScene().getWindow();
		Main.popUpStage.close();
	}

	// Player choose hero
	public void chooseHeroForPlayerOne(ActionEvent event1) throws IOException {
		if (event1.getSource() == redMage) {
			hero1 = new RedMage();
		} else if (event1.getSource() == lightPaladin) {
			hero1 = new LightPaladin();
		} else if (event1.getSource() == ogreKing) {
			hero1 = new OgreKing();
		} else if (event1.getSource() == lightHawk) {
			hero1 = new LightHawk();
		} else if (event1.getSource() == badWitch) {
			hero1 = new BadWitch();
		}
		chooseHeroPlayer1.setText(hero1.getName().strip());
	}

	public void chooseHeroForPlayerTwo(ActionEvent event2) throws IOException {
		if (event2.getSource() == redMage1) {
			hero2 = new RedMage();
		} else if (event2.getSource() == lightPaladin1) {
			hero2 = new LightPaladin();
		} else if (event2.getSource() == ogreKing1) {
			hero2 = new OgreKing();
		} else if (event2.getSource() == lightHawk1) {
			hero2 = new LightHawk();
		} else if (event2.getSource() == badWitch1) {
			hero2 = new BadWitch();
		}
		chooseHeroPlayer2.setText(hero2.getName().strip());
	}

	// Music
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				// TODO Auto-generated method stub
				Main.mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
			}
		});
	}

	// Play and pause music button
	public void playMedia() {
		if (Main.isMusicRunning) {
			Main.mediaPlayer.pause();
			Main.isMusicRunning = false;
		} else {
			Main.mediaPlayer.play();
			Main.isMusicRunning = true;
		}
	}
}
