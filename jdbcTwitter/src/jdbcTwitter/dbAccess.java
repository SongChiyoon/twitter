package jdbcTwitter;

import java.sql.*;

public class dbAccess {
	Connection con;

	dbAccess() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/twitter", "root", "ss19484255");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} 
	
	public boolean disconnect(String id, String follower) {
		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate("delete from follow where ID = '" + id + "'and f_id = '" + follower + "';");
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void pwEdit(String id, String newPW) {
		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate("update user set pw = '" + newPW + "' where id = '" + id + "';");
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String follow(String id) { // 내가 팔로우 하고 있는 사람
		String follow = "";
		try {
			Statement stmt2 = con.createStatement();
			ResultSet rs = stmt2.executeQuery("select f_id from follow where id = '" + id + "';");
			ResultSetMetaData rsmd = rs.getMetaData();

			int colnum = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colnum; i++)
					follow = follow + rs.getString(i) + "\n";
			}
			rs.close();
			stmt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return follow;
	}

	public String Recommend(String id) { // 나를 팔로우 하고 있는 사람
		String recommend = "";
		try{
			CallableStatement cStmt = con.prepareCall("{call recommend(?)}");
			
			cStmt.setString(1,  id);
			ResultSet rs = cStmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colnum = rsmd.getColumnCount();
		
			while(rs.next()){
				for(int i = 1; i <= colnum; i++)
					recommend = recommend + rs.getString(i) + " / ";
				recommend += "\n-----------------------------------\n";
			}
			
			
			rs.close();
			cStmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return recommend;
	}

	public String allBoard(String id) {
		String nn = "";
		try {
			Statement stmt2 = con.createStatement();
			ResultSet rs = stmt2.executeQuery("select date, writer, post from board where ID = '" + id + "' or ID in "
					+ "(select f_id from follow where ID = '" + id + "') order by date desc;");
			ResultSetMetaData rsmd = rs.getMetaData();

			int colnum = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colnum; i++)
					nn = nn + rs.getString(i) + "\n";
				nn += "--------------------------------------------\n";
			}
			rs.close();
			stmt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nn;
	}
	public String individualInform(String id) {
		String nn = "";
		try {
			Statement stmt2 = con.createStatement();
			ResultSet rs = stmt2.executeQuery("select id, name, birth from user where ID = '" + id + "';");
			ResultSetMetaData rsmd = rs.getMetaData();

			int colnum = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colnum; i++)
					nn = nn + rs.getString(i) + " / ";
			}
			rs.close();
			stmt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nn;
	}

	public String IndividualBoard(String id) {
		String nn = "";
		try {
			Statement stmt2 = con.createStatement();
			ResultSet rs = stmt2
					.executeQuery("select date,writer, post from board where ID = '" + id + "' order by date desc;");
			ResultSetMetaData rsmd = rs.getMetaData();

			int colnum = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= colnum; i++){
					if(i == 2 && !rs.getString(i).equals(id)){
						nn += "@"+ rs.getString(i) + "\n";
						continue;
					}
					nn = nn + rs.getString(i) + "\n";
				}
				nn += "--------------------------------------------\n";
			}

			rs.close();
			stmt2.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nn;
	}

	public void addUser(String id, String name, int birth, String pw) {
		try {
			PreparedStatement pStmt = con.prepareStatement("insert into user values (?,?,?,?)");
			pStmt.setString(1, id);
			pStmt.setString(2, name);
			pStmt.setInt(3, birth);
			pStmt.setString(4, pw);
			pStmt.executeUpdate();
			pStmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void addfollow(String id, String fid) {
		try {
			PreparedStatement pStmt = con.prepareStatement("insert into follow values (?,?)");
			pStmt.setString(1, id);
			pStmt.setString(2, fid);
			pStmt.executeUpdate();
			pStmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		dbAccess a = new dbAccess();
		System.out.println(a.individualInform("a2c222"));
	}

	public void addPost(String owner, String writer, String post) {
		try {
			CallableStatement pStmt = con.prepareCall("{call addpost(?,?,?)}");
			pStmt.setString(1, owner);
			pStmt.setString(2, writer);
			pStmt.setString(3, post);
			pStmt.executeUpdate();
			pStmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean checkUser(String id, String pw) {
		try {
			CallableStatement cStmt = con.prepareCall("{call login(?,?)}");
			cStmt.setString(1, id);
			cStmt.setString(2, pw);
			ResultSet rs = cStmt.executeQuery();
			if (rs.next() == false) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean checkUser(String id) {
		try {
			CallableStatement cStmt = con.prepareCall("{call cccheck(?)}");
			cStmt.setString(1, id);
			ResultSet rs = cStmt.executeQuery();
			if (rs.next() == false) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkfollower(String id, String fid) {   //true면 친구가 맺어져 있는거다.
		try {
			CallableStatement cStmt = con.prepareCall("{call ccheck(?,?)}");
			cStmt.setString(1, id);
			cStmt.setString(2, fid);
			ResultSet rs = cStmt.executeQuery();
			if (rs.next() == false) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
