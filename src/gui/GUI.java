package gui;

import geteilt.Controller;
import geteilt.Element;
import geteilt.Hashtabelle;
import geteilt.Ip;
import geteilt.Log;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 * TI3 ADP, SS16 
 * Gruppe: Julian Magierski (julian.magierski@haw-hamburg.de)
 * Kristian Exﬂ (kristian.exss@haw-hamburg) 
 * Aufgabenblatt 7: Hashfunktionen
 */

public class GUI extends Application {
	
	private Stage primaryStage;
	
	private BorderPane pane;
	
	private int width;
	
	private int heigth;
	
	private Controller controller;
	
	private Hashtabelle hashtabelle;
	public GUI (Stage primaryStagePar) {
		 hashtabelle = new Hashtabelle();
		controller = new Controller(hashtabelle); 
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
		Label rueckmeldung = new Label("");
		grid.getChildren().add(rueckmeldung);
		GridPane.setConstraints(rueckmeldung, 0, 2);
		okayButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if ((anzahlField.getText() != null && !anzahlField.getText().isEmpty())) {
					String ziffern = anzahlField.getText();
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
						if (anzahl > 10000000 && anzahl <= 0) {
							korrekteEingabe = false;
						} else {
						
						rueckmeldung.setText("Bitte warten..");
						controller.starte(anzahl);
						mainWindow();
						}
					} else {
						System.out.println("1");
						rueckmeldung.setText("Die Anzahl sollte aus Ziffern bestehen.\n N > 0 && N <= 10 000 000");
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
		pane.getChildren().clear();
		BorderPane borderPane = new BorderPane();
		final ObservableList<Ip> ipAdressen = 
				FXCollections.observableArrayList(hashtabelle.getIpAddressen());
		TableView<Ip> tableView = new TableView <>();
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	
	    TableColumn<Ip, String> ipColumn = new TableColumn<>("IP Adressen");
	    ipColumn.setCellValueFactory(new PropertyValueFactory<Ip, String>("ip"));
	   ipColumn.setMinWidth(100);
	    tableView.setItems(ipAdressen);
	    tableView.getColumns().add(ipColumn);
	    
	    TableView<Log> tableViewLogs = new TableView<>();
		tableViewLogs
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		TableColumn<Log, String> logsColumn = new TableColumn<>(
				"Logs");
		logsColumn.setMinWidth(400);
		logsColumn.setCellValueFactory(new PropertyValueFactory<Log, String>(
						"logEintrag"));
						tableViewLogs.getColumns().add(logsColumn);
		borderPane.setCenter(tableViewLogs);
	    tableView.setOnMousePressed(new EventHandler<MouseEvent>() {
	        @Override 
	        public void handle(MouseEvent event) {
				if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					String iP = tableView.getSelectionModel().getSelectedItem().getIp();
					final ObservableList<Log> logs = FXCollections.observableArrayList(hashtabelle.suchen(iP));
					tableViewLogs.setItems(logs);
	            }
	        }
	    });
		borderPane.setLeft(tableView);
		pane.setCenter(borderPane);
	}
	
	
}
