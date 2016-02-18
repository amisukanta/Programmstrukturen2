package viergewinnt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Dieses Programm ist ein Nachbau des berühmten Spiels "Vier gewinnt".
 * Dabei treten zwei Spieler gegeneinander an. Der eine Spieler besitzt rote
 * Spielsteine, der andere gelbe. Zwischen den Spielern befindet sich ein
 * Spielbrett mit Schächten, in die die Spieler ihre Spielsteine abwechselnd
 * einwerfen können.
 * Die eingeworfenen Spielsteine sind dabei für jeden Spieler jederzeit
 * ersichtlich.
 * Ziel eines jeden Spielers ist es eine Reihe aus mind. 4 seiner Steine
 * zu bilden. Eine Reihe kann entweder vertikal, horizontal oder diagonal
 * gebildet werden.
 * 
 * @author Jan Riemer 
 */
public class Viergewinnt extends Application {
    
    /**
     * Baut die Szene auf und zeigt die Stage an.
     * @param stage Die anzuzeigende Stage.
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Vier gewinnt");
        stage.show();
    }

    /**
     * Einstiegspunkt des Programms.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        // hier rufen wir die tests der einzelnen Klassen auf
        CellManager.startTestSuite();
    }
    
}
