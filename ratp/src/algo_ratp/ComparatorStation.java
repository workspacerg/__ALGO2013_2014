package algo_ratp;

import java.util.Comparator;


public class ComparatorStation implements Comparator<Station> {

	/**
	 * Comparaison de deux stations
	 * @return le résultat de la comparaison
	 */
		
        @Override
        public int compare(Station arg0, Station arg1) {
                return Integer.compare(arg0.getSequence(),arg1.getSequence());
        }

}

