package algo_ratp;

import java.util.Comparator;

public class ComparatorStation implements Comparator<Station> {

        @Override
        public int compare(Station arg0, Station arg1) {
                return Integer.compare(arg0.getSequence(),arg1.getSequence());
        }

}

