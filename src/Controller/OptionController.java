package Controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OptionController {
	@FXML
	private Button returnToGame;

	@FXML
	private Button returnToMainMenu;

	// Return to Board Game
	public void returnToGame(ActionEvent event) throws IOException {
		Logic.GameBoard.optionPopUp = (Stage) returnToGame.getScene().getWindow();
		Logic.GameBoard.optionPopUp.close();
	}

	// Return to Main Menu
	public void returnToMainMenu(ActionEvent event) throws IOException {
		Logic.GameBoard.optionPopUp = (Stage) returnToGame.getScene().getWindow();
		Logic.GameBoard.optionPopUp.close();
		Main.root = FXMLLoader.load(getClass().getClassLoader().getResource("Logic/MainMenu.fxml"));
		Main.scene = new Scene(Main.root);
		Main.stage.setScene(Main.scene);
		Main.stage.setResizable(false);
		Main.stage.setTitle("SPACESTONE");
		Main.stage.show();
		Logic.GameBoard.gameBoard.refresh();
		Logic.GameBoard.gameBoard = null;
		Logic.GameBoard.count = 0;
		Logic.GameBoard.hasCast = false;
	}

	// Play and pause music button
	public void playMusic() {
		if (Main.isMusicRunning) {
			Main.mediaPlayer.pause();
			Main.isMusicRunning = false;
		} else {
			Main.mediaPlayer.play();
			Main.isMusicRunning = true;
		}
	}

}
