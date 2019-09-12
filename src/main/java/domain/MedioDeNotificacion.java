package domain;

public interface MedioDeNotificacion {
	public void notificarNuevasSugerencias(Usuario unUsuario);
	public void notificarAlertaMeterologica(Evento unEvento, Usuario unUsuario);
}
