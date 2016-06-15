package gui;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * TI3 ADP, SS16 
 * Gruppe: Julian Magierski (julian.magierski@haw-hamburg.de)
 * Kristian Exﬂ (kristian.exss@haw-hamburg) 
 * Aufgabenblatt 7: Hashfunktionen
 */

public class Main extends Application  {
	
	 public static void main(String[] args) {
		launch();
	 }

	@Override
	public void start(Stage primaryStage) throws Exception {
		new GUI(primaryStage);	
	}
}
