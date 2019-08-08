package domain;

public interface MedioDeNotifiacion {
	public void notificarNuevasSugerencias(Usuario unUsuario);
	public void notificarAlertaMeterologica(Evento unEvento, Usuario unUsuario);
}
