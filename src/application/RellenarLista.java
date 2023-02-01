package application;

import java.util.ArrayList;

public class RellenarLista {

	public static ArrayList<Personajes> generarPersonajes(int numeroDePersonajes) {
		ArrayList<Personajes> listaDevolver = new ArrayList<Personajes>();
		ClasePersonaje clase;

		for (int i = 0; i < numeroDePersonajes; i++) {
			clase = generarClase();

			listaDevolver.add(new Personajes(devolverNombre(), clase.getNivelVitalidad(), clase.getNivelFuerza(),
					clase.getNivelDestreza(), clase.getNivelMagia(), (float) (((int) Math.floor(Math.random() * 9999999)))));
		}
		
		return listaDevolver;
	}

	private static String devolverNombre() {
//		// Generador de nombres orcos.
//
//Pueden ser del clan de la mano blanca.
//
//_ _ _ _ _ _ _ 2 letras, 1 espacio y 5 letras
//
//Estos orcos no pueden tener nombres con dos vocales o dos consonantes seguidas.
//
//Puede ser del clan del ojete de sauron
//
//_ _ _ _ _ _ _ _ 8 letras
//
//en este caso el máximo de consonantes seguidas es 2.

		String nombredeorcoString = "";
		String consonanteString = "qwrtypsdfghjklñzxcvbnm";
		String vocaleString = "aeiou";
		String letraString = "qwertyuiopasdfghjklñzxcvbnm";
		int letra = 0;
		boolean ultimaletraconsonante = false;
		boolean ultimavocal = false;
		byte clan = 0;
		int consonantes = 0;
		int contador = 0;

		clan = (byte) Math.floor(Math.random() * 2);

		if (clan == 1) {
			do {
				if (nombredeorcoString.length() == 2) {
					nombredeorcoString += ' ';
				} else {
					letra = (int) Math.floor(Math.random() * 27);
					if (!ultimaletraconsonante && consonanteString.indexOf(letraString.charAt(letra)) > -1) {
						ultimaletraconsonante = true;
						ultimavocal = false;
						nombredeorcoString += letraString.charAt(letra);
					} else if (!ultimavocal && vocaleString.indexOf(letraString.charAt(letra)) > -1) {
						ultimaletraconsonante = false;
						ultimavocal = true;
						nombredeorcoString += letraString.charAt(letra);
					} else {
						contador--;
					}
				}
				contador++;
			} while (contador < 8);

		} else {
			do {
				letra = (int) Math.floor(Math.random() * 27);
				if (consonantes < 2 && consonanteString.indexOf(letraString.charAt(letra)) > -1) {
					ultimaletraconsonante = true;
					nombredeorcoString += letraString.charAt(letra);
					consonantes++;
				} else if (vocaleString.indexOf(letraString.charAt(letra)) > -1) {
					ultimaletraconsonante = false;
					nombredeorcoString += letraString.charAt(letra);
					consonantes = 0;
				} else {
					contador--;
				}
				contador++;
			} while (contador < 8);

		}

		return nombredeorcoString;
	}

	private static ClasePersonaje generarClase() {
		ClasePersonaje clase = null;
		int opcionClase;

		opcionClase = (int) Math.floor(Math.random() * 5);

		switch (opcionClase) {
		case 0:
			clase = new ClasePersonaje(10, 14, 7, 3);
			break;
		case 1:
			clase = new ClasePersonaje(8, 7, 16, 3);
			break;
		case 2:
			clase = new ClasePersonaje(7, 8, 5, 14);
			break;
		case 3:
			clase = new ClasePersonaje(14, 14, 3, 3);
			break;
		case 4:
			clase = new ClasePersonaje(9, 9, 9, 9);
			break;
		}

		return clase;
	}
}
