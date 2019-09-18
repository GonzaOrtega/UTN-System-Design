package domain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import domain.exceptions.NoSePuedeAbrirImagen;

public class Foto {

	private BufferedImage imagen;
	
	public Foto(File ruta){
		BufferedImage imagen;
		try {
			imagen = ImageIO.read(ruta);
		} catch (IOException e) {
			throw new NoSePuedeAbrirImagen("ERROR: no se encuentra el archivo.");
		}
        this.imagen= Normalizador.getInstance().normalizarImagen(imagen);
	}

}
