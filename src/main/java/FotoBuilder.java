import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FotoBuilder {
	Foto foto = new Foto();
	
	public Foto crearFoto() {
		try {
	        foto.setImagen(ImageIO.read(foto.getRuta()));
	        foto.normalizarImagen();
		}
		catch(IOException e){
            System.out.println("ERROR:" + e.getMessage());
		}
		return foto;
	}
	
	public FotoBuilder ruta(String ruta) {
		File fotoRuta = new File(ruta);
		foto.setRuta(fotoRuta);
		return this;
	}

}
