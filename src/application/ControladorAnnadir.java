package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorAnnadir {
	private Stage primaryStage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Button btnAtras;

    @FXML
    private TextField nombreAnnadir;

    @FXML
    private TextField dineroPersonaje;

    @FXML
    private ComboBox<String> selectorDeClase;

    @FXML
    private Button btnAnnadir;
    
    @FXML
    private Button btnMostrarClases;

    @FXML
    void volver(ActionEvent event) {
    	Parent root;
    	try {
			primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/VistaBusqueda.fxml"));
			scene = new Scene(root);
			primaryStage.setTitle("Añadir personajes");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
	void initialize() {
		selectorDeClase.setItems(FXCollections.observableArrayList("Caballero", "Ladrón", "Mago", "Guerrero", "Marginado"));
	}

    @FXML
    void annadir(ActionEvent event) {
    	String respuesta = "";
    	
    	try {
        	respuesta = Main.annadir(nombreAnnadir.getText(), selectorDeClase.getValue(), Float.parseFloat(dineroPersonaje.getText().replace(',', '.')));

			respuesta = "Personaje añadido correctamente";
		} catch (Exception e) {
			respuesta = "Hubo algún error y el personaje no se ha añadido";
		}
    	
    	nombreAnnadir.setText(null);
    	dineroPersonaje.setText(null);
    	selectorDeClase.getSelectionModel().clearSelection();
    	
    	mostrarAlertInfo(event, respuesta);
    }

    @FXML
    void mostrarClases(ActionEvent event) {
    	String respuesta = "";
    	
    	respuesta = ClasePersonaje.mostrarClases();
    	
    	mostrarAlertInfo(event, respuesta);
    }
    
    void mostrarAlertInfo(ActionEvent event, String texto) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Personajes");
		alert.setTitle("Alerta!!");
		alert.setContentText(texto);
		alert.showAndWait();
	}

}

