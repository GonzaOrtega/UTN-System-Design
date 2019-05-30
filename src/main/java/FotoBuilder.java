import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import exceptions.*;

public class FotoBuilder {
	Foto foto = new Foto();
	
	public Foto crearFoto() throws IOException {
			BufferedImage imagen = ImageIO.read(foto.getRuta());
			if(imagen == null) {
				throw new IOException("ERROR: no se encuentra el archivo.");
			}
	        foto.setImagen(imagen);
	        foto.normalizarImagen();
		return foto;
	}
	
	public FotoBuilder ruta(String ruta) {
		File fotoRuta = new File(ruta);
		foto.setRuta(fotoRuta);
		return this;
	}

}
