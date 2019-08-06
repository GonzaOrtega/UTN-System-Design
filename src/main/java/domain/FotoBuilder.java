package domain;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FotoBuilder {
	Foto foto = new Foto();
	Normalizador normalizador= new NormalizadorDePrueba();
	
	public Foto crearFoto() throws IOException {
			BufferedImage imagen = ImageIO.read(foto.getRuta());
			if(imagen == null) {
				throw new IOException("ERROR: no se encuentra el archivo.");
			}
	        foto.setImagen(imagen);
	        normalizador.normalizarImagen(foto);
	     return foto;
	}
		
	public FotoBuilder ruta(String ruta) {
		File fotoRuta = new File(ruta);
		foto.setRuta(fotoRuta);
		return this;
	}

}
