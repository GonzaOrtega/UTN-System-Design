import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrendaBuilder{
	
	Prenda prenda = new Prenda();
	
	public Prenda crearPrenda(){
		if(this.tieneAlgunParametroNulo()) {
			throw new TieneParametrosNulosException("WARNING: no se pudo crear la prenda ya que tiene parametros sin instanciar.");
		}
		return prenda;
	}
	
	public PrendaBuilder conColorPrimario(Color colorPrimario){
		this.prenda.colorPrimario=colorPrimario;
		return this;
	}
	
	public PrendaBuilder conColorSecundario(Color colorSecundario){
		this.prenda.colorSecundario=colorSecundario;
		return this;
	}
	
	public PrendaBuilder conTipo(TipoPrenda tipo){
		this.prenda.tipo=tipo;
		return this;
	}
	
	public PrendaBuilder conTela(Material tela){
		if(prenda.tipo == null) {
			throw new MaterialNoPermitidoException("WARNING: debe definir primero el tipo.");
		}
		if(prenda.tipo.materialesProhibidos.contains(tela)) {
			throw new MaterialNoPermitidoException("WARNING: el material " + tela + " no es valido para el tipo de prenda ingresado.");
		}
		this.prenda.tela=tela;
		return this;
	}
	
	public boolean tieneAlgunParametroNulo(){
		List parametros = new ArrayList(Arrays.asList(prenda.tela,prenda.colorPrimario,prenda.tipo));
		return parametros.stream().anyMatch(parametro -> parametro == null);
	}

}
