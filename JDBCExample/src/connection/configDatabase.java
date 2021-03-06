package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Scanner;

public class configDatabase {
	private static Scanner input = new Scanner(System.in);
    private static Connection connection;
    private static ResultSet rs;
    private static java.sql.Statement statement;
    private final static String driver="com.mysql.cj.jdbc.Driver";
    private final static String url="jdbc:mysql://localhost:3306/sakila";
	private final static String user="root";
	private final static String password="ydong1Wang66";
    private final static String select="select * from actor";
    private final static String add="insert into actor values(?,?,?,?)";
    private final static String delete="delete from actor where actor_id=?";
    private final static String update="update actor set first_name=? , last_name=?, last_update=? where actor_id = ?;";
    public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			System.out.println("loading driver successful...");
			connection=DriverManager.getConnection(url,user,password);
			System.out.println("connect to DB successful...");
			String s=null;
			int i=0;
			do{
				try{
				System.out.println("choose a option by 1,2 or 3\n 1.add data\n 2.delete data\n 3.update data\n 4.display table");
				 i=input.nextInt();
				}catch(Exception e1){
					i=0;
				}
				switch(i){
				case 1: addDB();
				break;
				case 2: deleteDB();
				break;
				case 3: updateDB();
				break;
				case 4 :displayDB();
				default:
					break;
				
				}
				do{
				input=new Scanner(System.in);
			   	System.out.println("continue action ? y/n");
				 s=input.next();
				}while((!s.equalsIgnoreCase("y")) && (!s.equalsIgnoreCase("n")));
				if(s.equalsIgnoreCase("y"))
				{
					continue;
				}else{
					break;
				}
			}while(true);
			System.out.println("database closing");
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addDB() {
	System.out.println("enter actor_id");
	int id=input.nextInt();
	System.out.println("enter first name");
	String firstname=input.next();
	System.out.println("enter lastname ");
	String lastname=input.next();
	
	java.util.Date date=new java.util.Date();
    Timestamp	time=new Timestamp(date.getTime());
	
	try{
	java.sql.PreparedStatement pstmt=connection.prepareStatement(add);
    pstmt.setInt(1, id);
	pstmt.setString(2, firstname);
	pstmt.setString(3,lastname);
	pstmt.setTimestamp(4, time);
	pstmt.executeUpdate();
	pstmt.close();
	System.out.println("add data success..........");
	}catch(Exception e){
		e.printStackTrace();
		System.out.println("add failed");
	}
	}

	public static void deleteDB() {
	  System.out.println("enter the actor id you want to delete");
	  int id=input.nextInt();
	  try{
	  java.sql.PreparedStatement pstmt=connection.prepareStatement(delete);
	  pstmt.setInt(1, id);
	  pstmt.executeUpdate();
	  System.out.println("delete record successful....");
	  pstmt.close();
	  }
	  catch(Exception e){
		  e.printStackTrace();
		  System.out.println("delete fail");
	  }
		
	}

	public static void updateDB() {
		 System.out.println("enter the actor id you want to edit");
		  int id=input.nextInt();
		   System.out.println("enter firstname:");
		   String firstname=input.next();
		   System.out.println("enter lastname");
		   String lastname=input.next();
		   java.util.Date date=new java.util.Date();
		    Timestamp	time=new Timestamp(date.getTime());
		    try{
		    	java.sql.PreparedStatement pstmt= connection.prepareStatement(update);
		    	pstmt.setString(1, firstname);
		    	pstmt.setString(2,lastname);
		    	pstmt.setTimestamp(3, time);
		        pstmt.setInt(4, id);
		    	int count=pstmt.executeUpdate();
		    	
		    	
		    	System.out.println("update successful, effected line number"+count);
		    	pstmt.close();
		    }catch(Exception e){
		    	e.printStackTrace();
		    	System.out.println("update failed...");
		    	
		    }
		
	}

	public static void displayDB() throws Exception{
	
		statement=connection.createStatement();
		rs=statement.executeQuery(select);
		System.out.println("actor_table");
		System.out.println("actor_id\t\t"+"firstname\t\t"+"lastname\t\t"+"lastupdate");
		while (rs.next()){
			
			System.out.println(rs.getString(1)+" \t\t "+rs.getString(2)+"\t\t "+rs.getString(3)+"\t\t"+rs.getTimestamp(4));	
		}
	}
}
