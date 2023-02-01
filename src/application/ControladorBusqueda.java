package application;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControladorBusqueda {
	private Stage primaryStage;
	private Scene scene;
	private Parent root;

	@FXML
	private Button btnBusqueda;

	@FXML
	private TextField nombrePersonaje;

	@FXML
	private Button annadirPersonaje;

	@FXML
	private ListView<Personajes> lista;

	@FXML
	private Button btnEliminar;

	@FXML
	void Buscar(ActionEvent event) {

		lista = Main.buscar(nombrePersonaje.getText(), lista);

		nombrePersonaje.setText(null);

	}

	void mostrarAlertInfo(ActionEvent event, String texto) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Personajes");
		alert.setTitle("Alerta!!");
		alert.setContentText(texto);
		alert.showAndWait();
	}

	@FXML
	void mostrarAnnadir(ActionEvent event) {
		try {
			primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/VistaAnnadir.fxml"));
			scene = new Scene(root);
			primaryStage.setTitle("Búsqueda de personajes");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void eliminar(ActionEvent event) {
		ObservableList<Personajes> listaObservable = lista.getSelectionModel().getSelectedItems();
		ArrayList<Personajes> consulta = new ArrayList<Personajes>();

		listaObservable = lista.getSelectionModel().getSelectedItems();
		consulta = new ArrayList<Personajes>(listaObservable);
		lista = Main.eliminarSeleccion(consulta, lista);
	}
}
