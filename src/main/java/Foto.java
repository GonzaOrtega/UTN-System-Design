import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import exceptions.*;

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

	
	public BufferedImage getImagen() {
		return imagen;
	}
	
	public void normalizarImagen(){
		BufferedImage imagenNormalizada = null;
        Graphics2D graphics2D = null;
		
		if(imagen.getHeight()>=imagen.getWidth()){
			int anchoNormalizado = normalizarSegun(imagen.getHeight(),imagen.getWidth());
			imagenNormalizada = new BufferedImage(anchoNormalizado,500,imagen.getType());
			graphics2D = imagenNormalizada.createGraphics();
			graphics2D.drawImage(imagen, 0, 0, anchoNormalizado, 500, null);
		}else {
			int altoNormalizado = normalizarSegun(imagen.getWidth(),imagen.getHeight());
			imagenNormalizada = new BufferedImage(500,altoNormalizado,imagen.getType());
			graphics2D = imagenNormalizada.createGraphics();
			graphics2D.drawImage(imagen, 0, 0, 500, altoNormalizado, null);
		}
        graphics2D.dispose();
		this.setImagen(imagenNormalizada);
	}
	
	public int normalizarSegun(int nro,int nroANormalizar){
    	float porcentaje = (float) (500*100)/nro;
		float nroNormalizado = (nroANormalizar*porcentaje)/100;
		return (int) nroNormalizado;
	}
}
