package domain;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class NormalizadorDePrueba implements Normalizador{
	public void normalizarImagen(Foto foto){
		BufferedImage imagenNormalizada = null;
        Graphics2D graphics2D = null;
		
		if(foto.imagen().getHeight()>=foto.imagen().getWidth()){
			int anchoNormalizado = this.normalizarSegun(foto.imagen().getHeight(),foto.imagen().getWidth());
			imagenNormalizada = new BufferedImage(anchoNormalizado,500,foto.imagen().getType());
			graphics2D = imagenNormalizada.createGraphics();
			graphics2D.drawImage(foto.imagen(), 0, 0, anchoNormalizado, 500, null);
		}else {
			int altoNormalizado = normalizarSegun(foto.imagen().getWidth(),foto.imagen().getHeight());
			imagenNormalizada = new BufferedImage(500,altoNormalizado,foto.imagen().getType());
			graphics2D = imagenNormalizada.createGraphics();
			graphics2D.drawImage(foto.imagen(), 0, 0, 500, altoNormalizado, null);
		}
        graphics2D.dispose();
		foto.setImagen(imagenNormalizada);
	}
	
	public int normalizarSegun(int nro,int nroANormalizar){
    	float porcentaje = (float) (500*100)/nro;
		float nroNormalizado = (nroANormalizar*porcentaje)/100;
		return (int) nroNormalizado;
	}
	
}
