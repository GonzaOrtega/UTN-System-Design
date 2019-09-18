package domain;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Normalizador{
	
	// ------------------- Metodos p/ obtener instancia -------------------

	private static class NormalizadorHolder {
		private static final Normalizador INSTANCE = new Normalizador();
	}

	public static Normalizador getInstance() {
		return NormalizadorHolder.INSTANCE;
	}
	
	// 
	
	public BufferedImage normalizarImagen(BufferedImage foto){
		BufferedImage imagenNormalizada = null;
        Graphics2D graphics2D = null;
		
		if(foto.getHeight()>=foto.getWidth()){
			int anchoNormalizado = this.normalizarSegun(foto.getHeight(),foto.getWidth());
			imagenNormalizada = new BufferedImage(anchoNormalizado,500,foto.getType());
			graphics2D = imagenNormalizada.createGraphics();
			graphics2D.drawImage(foto, 0, 0, anchoNormalizado, 500, null);
		}else {
			int altoNormalizado = normalizarSegun(foto.getWidth(),foto.getHeight());
			imagenNormalizada = new BufferedImage(500,altoNormalizado,foto.getType());
			graphics2D = imagenNormalizada.createGraphics();
			graphics2D.drawImage(foto, 0, 0, 500, altoNormalizado, null);
		}
        graphics2D.dispose();
		return imagenNormalizada;
	}
	
	public int normalizarSegun(int nro,int nroANormalizar){
    	float porcentaje = (float) (500*100)/nro;
		float nroNormalizado = (nroANormalizar*porcentaje)/100;
		return (int) nroNormalizado;
	}
	
}
