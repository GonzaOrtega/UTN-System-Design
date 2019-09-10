package domain;

import java.awt.image.BufferedImage;
import java.io.File;

public class Foto {

	private BufferedImage imagen;
	private File ruta;
	
	public File getRuta() {
		return ruta;
	}

	public void setRuta(File ruta) {
		this.ruta = ruta;
	}

	public void setImagen(BufferedImage foto) {
		this.imagen = foto;
	}
	
	public BufferedImage imagen() {
		return imagen;
	}

}
