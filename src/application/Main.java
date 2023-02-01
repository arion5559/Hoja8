package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	private static ArrayList<Personajes> listaPersonajes = new ArrayList<Personajes>();

	@Override
	public void start(Stage primaryStage) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/VistaBusqueda.fxml"));
// Establecemos el título de la ventana
			primaryStage.setTitle("Búsqueda de personajes");
// Establecemos el ancho y el alto
			primaryStage.setScene(new Scene(root));
// Mostramos la ventana
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		listaPersonajes.add(new Personajes("Wonwoo", 9, 9, 9, 9, (float) 300.0));
//		listaPersonajes.add(new Personajes("Santiago", 10, 14, 7, 3, (float) 20));
//		listaPersonajes.add(new Personajes("Sonia", 14, 14, 3, 3, (float) 4000));
		listaPersonajes.addAll(RellenarLista.generarPersonajes(40));
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static ListView<Personajes> buscar(String nombrePersonaje, ListView<Personajes> listaCoincidentes) {
		int contador = 0;
		ObservableList<Personajes> coincidentes = FXCollections.observableArrayList();
		try {
			if (nombrePersonaje != null) {
				if (listaPersonajes.size() != 0) {
					do {
						if (listaPersonajes.get(contador).getNombre().contains(nombrePersonaje)
								|| listaPersonajes.get(contador).getNombre().toLowerCase()
										.contains(nombrePersonaje.toLowerCase())
								|| listaPersonajes.get(contador).getNombre().toUpperCase()
										.contains(nombrePersonaje.toUpperCase())) {
							coincidentes.add(listaPersonajes.get(contador));
						}

						contador++;

					} while (contador < listaPersonajes.size());

				}

				listaCoincidentes.setItems(coincidentes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listaCoincidentes;
	}

	public static String annadir(String nombre, String nombreClase, float dinero) {
		String correcto = "";
		ClasePersonaje clase = null;

		try {
			clase = seleccionarClase(nombreClase);
			listaPersonajes.add(new Personajes(nombre, clase.getNivelVitalidad(), clase.getNivelFuerza(),
					clase.getNivelDestreza(), clase.getNivelMagia(), dinero));

			correcto = "Personaje añadido correctamente";
		} catch (Exception e) {
			correcto = "Hubo algún error y el personaje no se ha añadido";
		}

		return correcto;
	}
	
	public static ListView<Personajes> eliminarSeleccion(ArrayList<Personajes> listaEliminar, ListView<Personajes> listaCoincidentes) {
		ObservableList<Personajes> coincidentes = FXCollections.observableArrayList();
		
		listaPersonajes.removeAll(listaEliminar);
		
		coincidentes.addAll(listaPersonajes);		
		return listaCoincidentes;
	}

	private static ClasePersonaje seleccionarClase(String opcionClase) {
		ClasePersonaje clase = null;

		switch (opcionClase) {
		case "Caballero":
			clase = new ClasePersonaje(10, 14, 7, 3);
			break;
		case "Ladrón":
			clase = new ClasePersonaje(8, 7, 16, 3);
			break;
		case "Mago":
			clase = new ClasePersonaje(7, 8, 5, 14);
			break;
		case "Guerrero":
			clase = new ClasePersonaje(14, 14, 3, 3);
			break;
		case "Marginado":
			clase = new ClasePersonaje(9, 9, 9, 9);
			break;
		}

		return clase;
	}

}
