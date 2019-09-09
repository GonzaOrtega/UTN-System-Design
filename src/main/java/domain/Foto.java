package domain;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import domain.exceptions.*;

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
