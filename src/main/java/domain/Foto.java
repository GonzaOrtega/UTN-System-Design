package domain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.Transient;
import domain.exceptions.NoSePuedeAbrirImagen;

@Entity
public class Foto extends SuperClase {
	@Transient
	private BufferedImage imagen;
	
	private String ruta;
	
	public Foto(File ruta){
		this.ruta=ruta.toString();
		BufferedImage imagen;
		try {
			imagen = ImageIO.read(ruta);
		} catch (IOException e) {
			throw new NoSePuedeAbrirImagen("ERROR: no se encuentra el archivo.");
		}
        this.imagen= Normalizador.getInstance().normalizarImagen(imagen);
	}

}
