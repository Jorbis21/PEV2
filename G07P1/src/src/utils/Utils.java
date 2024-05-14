package src.utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

import src.individuo.Individuo;

public class Utils {
	/*
	 * Hacemos un deepCopy porque queremos que la copia sea de los datos no de las
	 * referencias a objetos
	 * 
	 * @param valores
	 * 
	 * @ return ArrayList<Boolean>
	 */
	public static <T> ArrayList<T> deepCopy(ArrayList<T> values) {
		ArrayList<T> copyArr = new ArrayList<>();
		for (T value : values) {
			copyArr.add(value);
		}
		return copyArr;
	}

	/*
	 * Algoritmo de busqueda binaria para encontrar el intervalo en el que se encuentra el valor value
	 * 
	 * qi-1 < value < qi
	 * 
	 * @param value
	 * @param array
	 * @param start
	 * @param end
	 * 
	 * @return int
	 */
	static int findIntervalAux(ArrayList<Double> array, int low, int high, Double value)
	{
		if (low > high)
			return low;
		// Find the middle index
		int mid = low + (high - low) / 2;
		
		if(Double.compare(value, array.get(mid)) == 0)
			return mid;
		else if (Double.compare(value, array.get(mid)) < 0)
			return findIntervalAux(array, low,  mid - 1, value);
		else
			return findIntervalAux(array, mid + 1, high, value);
	}
	
	/*
	 * Algoritmo de busqueda binaria para encontrar el intervalo en el que se encuentra el valor value
	 * 
	 * qi-1 < value < qi
	 * 
	 * @param value
	 * @param array
	 * 
	 * @return int
	 */
	public static int findInterval(Double value, ArrayList<Double> array) {
		int low = 0, high = array.size();

		return findIntervalAux(array, low, high, value);
	}
	
	/*
	 * Recibe un ArrayList de individuos y la devuelve ordenada dependiendo del tipo de funcion
	 * 
	 * @param array
	 * 
	 * @return ArrayList<Individuo>
	 */
	public static ArrayList<Individuo> sortSample(ArrayList<Individuo> array){
		ArrayList<Individuo> ordered = new ArrayList<Individuo>();
    
    ordered = Individuo.isMaximizacion() ? 
      array.stream().sorted((i1, i2) -> Double.compare(i2.getFitness(), i1.getFitness())).collect(Collectors.toCollection(ArrayList::new)) :
      array.stream().sorted((i1, i2) -> Double.compare(i1.getFitness(), i2.getFitness())).collect(Collectors.toCollection(ArrayList::new));
		
		return ordered;
	}
}