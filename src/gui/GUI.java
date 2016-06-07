package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUI extends Application {
	private Stage primaryStage;
	private BorderPane mainPane;
	private ObservableList ipAdressen;
	public static void main (String [] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStagePar) throws Exception {
		primaryStage = primaryStagePar;
		ipAdressen = FXCollections.observableArrayList();
		primaryStage.setTitle("");
		
		mainPane = new BorderPane ();
		primaryStage.setScene(new Scene (mainPane, 800,600));
		mainwindow();
		primaryStage.show();
	} 
	private void mainwindow () {
		TableView ipTabelle = new TableView ();
		ipTabelle.setItems(ipAdressen);
		TableColumn ipColumn = new TableColumn ("IP Adressen");
		ipTabelle.getColumns().add(ipColumn);
		mainPane.setLeft(ipTabelle);
		
		TextArea informationen = new TextArea ();
		informationen.setText("Sehr geehrter Benutzer vielen Dank \n"
				+ "dass sie sich für dieses Produkt entschieden haben \n"
				+ "um alle Features nutzen zu können geben sie bitte ihre Kontonummer und PIN an \n"
				+ "vielen Dank");
		informationen.setEditable(false);
		mainPane.setRight(informationen);
	}
	
	
}
