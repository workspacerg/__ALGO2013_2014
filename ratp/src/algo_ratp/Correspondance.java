package algo_ratp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Correspondance {
        
        private Map<Station,LigneCorrespondance> mapLigne;
        private static Correspondance INSTANCE = null;
        private Correspondance(){
                mapLigne = new HashMap<Station, LigneCorrespondance>();
        }
        
        public static Correspondance getInstance(){
                if(INSTANCE == null){
                        INSTANCE = new Correspondance();
                }
                
                return INSTANCE;
        }

        public Map<Station,LigneCorrespondance> getMapLigne() {
                return mapLigne;
        }
        
}