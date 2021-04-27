package presentation;

public class Prueba {

	Usuario[] datos;
	
	void tratamiento(String textoUsuarios) {
		int cont = 0;
		String[] trozosTextoUsuarios = textoUsuarios.split(";");
		
		
		for (String trozo : trozosTextoUsuarios) {			
			
			String[] atributos = trozo.split(",,");
					
			datos[cont] = new Usuario(new Nif(atributos[0]),
											atributos[1],
											atributos[2],
											crearDireccion(atributos[3]),
											new Mail(atributos[4]),
											crearFecha(atributos[5]),
											crearFecha(atributos[6]),
											new ClaveAcceso(atributos[7]),
											crearRol(atributos[8])
											);
			cont++;
		}
		
	}

	private RolUsuario crearRol(String text) {	
		return RolUsuario.valueOf(text);
	}

	private Object crearFecha(String atributoCompuesto) {
		String[] trozosFecha = atributoCompuesto.split(".");
		return new Fecha(trozosFecha[0], trozosFecha[1],trozosFecha[2]);

	}

	private DireccionPostal crearDireccion(String atributoCompuesto) {
		String[] trozosDireccion = atributoCompuesto.split(",");
		return new DireccionPostal(trozosDireccion[0], trozosDireccion[1],trozosDireccion[2], trozosDireccion[3]) ;
	}
	
	
}
