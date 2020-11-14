package theater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Movie.Movie;

public class DB_Theater {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private final static int AREA_NUM = 70;
	private Theater[] areas = new Theater[AREA_NUM];
	
	
	public DB_Theater() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/DOK?serverTimezone=Asia/Seoul","root","mirim2");
			st = con.createStatement();
		}catch (Exception e) {
			System.out.println("������ ���̽� ���� ����:"+e.getMessage());
		}
	}
	
	
	
	public boolean makeMovie_Theater(String area, String country, int numHall) {
		try{
			String SQL = "INSERT INTO theaterinfo(area,country, numHall)"
					+"VALUES(\""+area+"\",\""+country+"\","+numHall+");";
			//System.out.println(SQL);
			int success = st.executeUpdate(SQL);
			
			if(success == 1) { return true; }
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
		
	}
	
	public Theater[] getTheater() {
		try {
			String SQL = "SELECT* FROM theaterinfo";
			rs = st.executeQuery(SQL);
			int n = 0;
			while(rs.next()) {
				areas[n] = new Theater();
				areas[n].set_key(rs.getInt("_key"));
				areas[n].setArea(rs.getString("area"));
				areas[n].setCountry(rs.getString("country"));
				areas[n].setNumHall(rs.getInt("numHall"));
				
				n++;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("getArea�����ͺ��̽� �˻� ����:"+ e.getLocalizedMessage());
		}
		return areas;
		
	}
	
	public int countTheater() {
		int n = 0;
		try {
			String SQL = " SELECT COUNT(*)  FROM theaterinfo";
			rs = st.executeQuery(SQL);
			if(rs.next()) {
				n = rs.getInt("COUNT(*)");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return n;
	}
	
}
