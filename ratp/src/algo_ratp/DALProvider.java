package algo_ratp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DALProvider {
	private DALProvider()
	{
		this.connect();
	}
 
private static DALProvider INSTANCE = null;
private final static String DBPath = "data-ratp";
private static Connection connection;
 
/** Point d'accès pour l'instance unique du singleton */
public static DALProvider getInstance()
{	
	if (INSTANCE == null)
	{ 
		INSTANCE = new DALProvider();
	}
	return INSTANCE;
}

private void connect() { 
    try {
        Class.forName("org.sqlite.JDBC");
        Connection connect = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
        // On test la création d'une requete
        connection.createStatement();
        connection = connect;
    } catch (ClassNotFoundException notFoundException) {
        notFoundException.printStackTrace();
        System.out.println("Erreur de connexion");
    } catch (SQLException sqlException) {
        sqlException.printStackTrace();
        System.out.println("Erreur de connexion");
    }
}

public void close(){
	 try {
         connection.close();
         INSTANCE = null;
     } catch (SQLException e) {
         e.printStackTrace();
     }
}

public Map<String, ArrayList<Ligne>> GetLignes(){
	try{
		Map<String, ArrayList<Ligne>> lignes = new HashMap<String,ArrayList<Ligne>>();
		String requete = "SELECT route_id,route_short_name,route_long_name,route_type,route_color FROM ROUTES";
		ResultSet result = DALProvider.query(requete);
		
		Ligne li;
		String id;
		String sname;
		String lname;
		Type type;
		String color;
		while(result.next()){
			 id = result.getString(0);
			 sname = result.getString(1);
			 lname = result.getString(2);
			 type = Type.values()[result.getInt(3)];
			 color = result.getString(4);
			 li = new Ligne(type,sname,lname,color,id);
			if(lignes.containsKey(lname))
				lignes.get(lname).add(li);
			else{
				ArrayList<Ligne> listl = new ArrayList<Ligne>();
				listl.add(li);
				lignes.put(lname,listl);
			}
		}
		
		return lignes;
	}
	catch(Exception exp){
		exp.printStackTrace();
		return null;
	}
}

public Map<String,ArrayList<Station>> GetStations(){
	try{
		Map<String,ArrayList<Station>> stations = new HashMap<String,ArrayList<Station>>();
		String requete = "SELECT stop_id,stop_name,stop_lat,stop_long,parent_station FROM STOPS";
		ResultSet result = DALProvider.query(requete);
		
		Station st;
		String id;
		String name;
		double lat;
		double lon;
		String parent_id;
		while(result.next()){
			id = result.getString(0);
			name = result.getString(1);
			lat = Double.parseDouble(result.getString(2));
			lon = Double.parseDouble(result.getString(3));
			parent_id = result.getString(4);
			st = new Station(id,name,lat,lon,parent_id);
			if(stations.containsKey(name))
				stations.get(name).add(st);
			else
			{
				ArrayList<Station> sts = new ArrayList<Station>();
				sts.add(st);
				stations.put(name, sts);
			}
		}
		
		return stations;
	}
	catch(Exception exp){
		return null;
	}
	
}

private static ResultSet query(String request) {
    ResultSet resultat = null;
    try {
    	Statement statement = connection.createStatement();
        resultat = statement.executeQuery(request);
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Erreur dans la requete : " + request);
    }
    return resultat;

}

}
