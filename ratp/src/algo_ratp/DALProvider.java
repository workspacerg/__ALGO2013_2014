package algo_ratp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DALProvider {
	
private DALProvider()
{
	
}
 
private static DALProvider INSTANCE = null;
private final static String DBPath = "localhost/dataratp";
private String username;
private String password;
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

public void initIdentifiers(String userN,String password){
	this.username = userN;
	this.password = password;
}

public boolean connect() { 
    try {
    	if(this.username == null || this.password == null)
    	{
    		System.out.println("Veuillez entrer vos identifiants de connexion à la BDD.");
    		throw new InstantiationException();
    	}
    	
    	Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection connect = DriverManager.getConnection("jdbc:mysql://" + this.DBPath, this.username, this.password);
        // On test la création d'une requete
        connect.createStatement();
        connection = connect;
        return true;
    } catch (ClassNotFoundException notFoundException) {
        notFoundException.printStackTrace();
        System.out.println("Erreur de connexion");
    } catch (SQLException sqlException) {
        sqlException.printStackTrace();
        System.out.println("Erreur de connexion");
    } catch (InstantiationException e) {
    	System.out.println("Erreur de connexion");
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		System.out.println("Erreur de connexion");
		e.printStackTrace();
	}
    
    return false;
}

public void close(){
	 try {
         connection.close();
     } catch (SQLException e) {
         e.printStackTrace();
     }
}

private String GetTripIdToPlan(){
	try{
		StringBuilder sb = new StringBuilder();
		String requete = "SELECT TRIP_id from stop_time group by trip_id having count(*) > 1";
			
			
		ResultSet result = Execquery(requete);
		while(result.next())
			sb.append("'"+result.getString(1)+"',");
		
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	catch(Exception exp){
		exp.printStackTrace();
		return null;
	}
}

public Map<Ligne,ArrayList<Station>> GetPlan(){
	try{
			
			Map<String,ArrayList<Station>> map = new HashMap<String, ArrayList<Station>>();		
			//String requete = "SELECT S.stop_id,S.stop_name,stop_sequence FROM STOP_TIME ST,STOPS S WHERE Trip_id = (SELECT distinct t.trip_id from trips t,stop_time st where t.trip_id = st.trip_id AND route_id = '"+li.getId_route()+"' HAVING COUNT(*) > 1) AND S.stop_id = ST.stop_id";
			String requete = String.format("SELECT s.stop_id,s.stop_name,stop_sequence,trip_id FROM STOP_TIME st,STOPS s WHERE st.stop_id = s.stop_id AND trip_id IN (%s)",GetTripIdToPlan());
			
			
			ResultSet result = Execquery(requete);
			Station st;
			String id;
			String name;
			int position;
			String trip_id;
			
			while(result.next()){
				id = result.getString(1);
				name = result.getString(2);
				position = Integer.parseInt(result.getString(3));
				trip_id = result.getString(4);
				st = new Station(name,id,position);
				
				if(map.containsKey(trip_id))
					map.get(trip_id).add(st);
				else
				{
					ArrayList<Station> arrst = new ArrayList<Station>();
					arrst.add(st);
					map.put(trip_id, arrst);
				}
			}
			
		return GetPlanToTripMap(map);
	}
	catch(Exception exp){
		exp.printStackTrace();
		return null;
	}
}

private Map<Ligne,ArrayList<Station>> GetPlanToTripMap(Map<String,ArrayList<Station>> map){
	if(map == null)
		return null;
	
	Map<Ligne,ArrayList<Station>> mapSt = new HashMap<Ligne,ArrayList<Station>>();
	Map<String,Ligne> mapLi = GetRoutesByTripsId(map.keySet());
	ComparatorStation comparator = new ComparatorStation();
	int i = 0;
	for(Entry<String,ArrayList<Station>> ent : map.entrySet()){
		ArrayList<Station> arrSt = ent.getValue();
		Collections.sort(arrSt,comparator);
		mapSt.put(mapLi.get(ent.getKey()),arrSt);
		i++;
		System.out.println(i + " --->" + mapSt.size());
	}
	
	return mapSt;
}

private Map<String,Ligne> GetRoutesByTripsId(Set<String> trip_id){
	try{
		StringBuilder sb = new StringBuilder();
		for(String s : trip_id){
			sb.append("'"+s+"',");
		}
		sb.deleteCharAt(sb.length()-1);
		
		Map<String,Ligne> mapLi = new HashMap<String,Ligne>();
		
		String requete = "SELECT r.route_id,route_short_name,route_long_name,route_type,route_color,t.trip_id from trips t,route r " +
				" WHERE r.route_id = t.route_id AND t.trip_id IN ("+sb.toString()+")";
						
		ResultSet result = Execquery(requete);
		String id;
		String sname;
		String lname;
		Type type;
		String color;
		while(result.next()){
		     id = result.getString(1);
			 sname = result.getString(2);
			 lname = result.getString(3);
			 type = Type.values()[result.getInt(4)];
			 color = result.getString(5);
			 if(sname != null && id != null)
			   mapLi.put(result.getString(6),new Ligne(type,sname,lname,color,id));
		}
		
		return mapLi;
	}
	catch(Exception exp){
		exp.printStackTrace();
		return null;
	}
}

private static ResultSet Execquery(String request) {
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
