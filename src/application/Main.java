package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage stage;
	public static Scene scene;
	public static Parent root;

	public static Stage popUpStage;
	public static Scene popUpScene;
	public static Parent pop;

	public static final String currency = " Mana";

	private File directory;
	private File[] files;
	private ArrayList<File> songs;
	private Media media;
	public static MediaPlayer mediaPlayer;
	private int songNumber;
	public static boolean isMusicRunning = true;

	// Start Application
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
			addMusic();
			Parent root = FXMLLoader
					.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Logic/MainMenu.fxml")));
			scene = new Scene(root, 1280, 720);
			stage = primaryStage;
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Space Game");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	// Add music to application
	public void addMusic() {
		songs = new ArrayList<File>();
		directory = new File("assets/music");
		files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				songs.add(file);
				System.out.println("BGM is playing !");
			}
		}
		media = new Media(songs.get(songNumber).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	}

}
