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
    		return false;
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

public ArrayList<Ligne> GetLignes(){
	try{
		ArrayList<Ligne> lignes = new ArrayList<Ligne>();
		String requete = "SELECT route_id,route_short_name,route_long_name,route_type,route_color FROM ROUTE";
		ResultSet result = Execquery(requete);
		
		Ligne li;
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
			 li = new Ligne(type,sname,lname,color,id);
			lignes.add(li);
			
		}
		
		return lignes;
	}
	catch(Exception exp){
		exp.printStackTrace();
		return null;
	}
}

public Map<Ligne,ArrayList<Station>> GetPlan(ArrayList<Ligne> lignes){
	try{
		ArrayList<Station> sts;
		ArrayList<String> lis;
		Map<Ligne,ArrayList<Station>> map = new HashMap<Ligne, ArrayList<Station>>();
		ComparatorStation firstInstance = new ComparatorStation();
		
		for(Ligne li : lignes){
			sts = new ArrayList<Station>();
			String requete = "SELECT  S.stop_id,S.stop_name,stop_sequence " +
					"FROM STOP_TIME ST,STOPS S "+
			"WHERE Trip_id = (SELECT trip_id from trips where route_id = '"+li.getId_route()+"' LIMIT 1)"+
			"AND S.stop_id = ST.stop_id";
			
			
			ResultSet result = Execquery(requete);
			Station st;
			String id;
			String name;
			int position;
			while(result.next()){
				id = result.getString(1);
				name = result.getString(2);
				position = Integer.parseInt(result.getString(3));
				st = new Station(name,id,position);
				sts.add(st);
			}
			
			Collections.sort(sts,firstInstance);
			map.put(li,sts);
		}
		
		return map;
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
