package gui;

import geteilt.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * TI3 ADP, SS16 
 * Gruppe: Julian Magierski (julian.magierski@haw-hamburg.de)
 * Kristian Exß (kristian.exss@haw-hamburg) 
 * Aufgabenblatt 7: Hashfunktionen
 */

public class GUI extends Application {
	
	private Stage primaryStage;
	
	private BorderPane pane;
	
	private ObservableList<?> ipAdressen;
	
	private int width;
	
	private int heigth;
	
	private Controller controller;

	public GUI (Stage primaryStagePar) {
		controller = new Controller(); 
		width = 800;
		heigth = 600;
		try {
			start(primaryStagePar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage primaryStagePar) throws Exception {
		primaryStage = primaryStagePar;
		primaryStage.setTitle("Algorithmen und Datenstrukuren SS16: Hashfunktionen");
		pane = new BorderPane();
		primaryStage.setScene(new Scene (pane, width,heigth));
		anzahlDerDaten();
		primaryStage.show();
	} 
	
	private void anzahlDerDaten() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(-50, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(5);
		Label beschreibung = new Label("Hier die Anzahl der Web-Logs eingeben:");
		GridPane.setConstraints(beschreibung, 0, 0);
		grid.getChildren().add(beschreibung);
		TextField anzahlField = new TextField();
		anzahlField.setPromptText("Anzahl der Web-Logs");
		anzahlField.getText();
		GridPane.setConstraints(anzahlField, 0, 1);
		grid.getChildren().add(anzahlField);
		Button okayButton = new Button("Okay");
		okayButton.setOnAction(new EventHandler<ActionEvent>() {
@Override
    public void handle(ActionEvent e) {
				if ((beschreibung.getText() != null && !beschreibung.getText().isEmpty())) {
					String ziffern = beschreibung.getText();
					int ziffer;
					boolean korrekteEingabe = true;
					for (int i = 0; i < ziffern.length(); i++) {
						ziffer = (int) ziffern.charAt(i);
						if ((ziffer < 48 || ziffer > 57)) {
							korrekteEingabe = false;
							break;
						}
					}
					if (korrekteEingabe) {
						int anzahl = Integer.parseInt(ziffern);
						controller.starte(anzahl);
					} else {

					}
				}
			}
		});

		GridPane.setConstraints(okayButton, 1, 1);
		grid.getChildren().add(okayButton);
		grid.setAlignment(Pos.CENTER);
		pane.setCenter(grid);
	}
	
	private void mainWindow () {
		BorderPane borderPane = new BorderPane();
		ipAdressen = FXCollections.observableArrayList();
		TableView ipTabelle = new TableView ();
		ipTabelle.setItems(ipAdressen);
		TableColumn ipColumn = new TableColumn ("IP Adressen");
		ipTabelle.getColumns().add(ipColumn);
		borderPane.setLeft(ipTabelle);
		
		TextArea informationen = new TextArea ();
		informationen.setText("Sehr geehrter Benutzer vielen Dank \n"
				+ "dass sie sich für dieses Produkt entschieden haben \n"
				+ "um alle Features nutzen zu können geben sie bitte ihre Kontonummer und PIN an \n"
				+ "vielen Dank");
		informationen.setEditable(false);
		borderPane.setRight(informationen);
	}
	
	
}
