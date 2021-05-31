package dataAccess.memory;

import java.util.List;

import entitys.Identifiable;

public abstract class IndexSortTemplate {

	/**
	 *  Obtiene por búsqueda binaria, la posición que ocupa, o ocuparía,  un objeto en la estructura.
	 *	@param Id - id del objeto a buscar.
	 * 	@param data - Estructura de datos con elementos identificables (poseen un id utilizado para la búsqueda y ordenación).
	 *	@return - la posición, en base 1, que ocupa un objeto o la que ocuparía (negativo).
	 */
	protected int indexSort(List<Identifiable> data, String id) {
		int start = 0;
		int end = data.size() - 1;
		while (start <= end) {
			int half = (start + end) / 2;	
			int comparison = data.get(half).getId().compareTo(id);		
			if (comparison == 0) {
				return half + 1; 		// Indices base 1
			}
			if (comparison < 0) {
				start = half + 1;
			} 
			else {
				end = half - 1;
			}
		}
		return -(end + 2); 				// Indices base 1
	}

} 
