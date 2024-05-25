package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField player1NameField;

    @FXML
    private TextField player2NameField;

    @FXML
    private Button startGameButton;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void initialize() {
        startGameButton.setOnAction(e -> handleStartGame());
    }

    private void handleStartGame() {
        String player1 = player1NameField.getText();

        if (player1.isEmpty()) {
            // Display an error message
            Logger.error("Player's name not provided!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/chessboard.fxml"));
            Pane gamePane = loader.load();

            BoardGameController gameController = loader.getController();
//            gameController.setPlayerNames(player1, player2);

            Scene gameScene = new Scene(gamePane);
            primaryStage.setScene(gameScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
