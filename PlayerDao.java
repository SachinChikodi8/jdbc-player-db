package com.jsp.jdbc.playdb.dao;
 	
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PlayerDao {

	Scanner sc = new Scanner(System.in);

	public static Connection BuildConnection() {
		Connection conn =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_playerdb?user=root&password=Hanzo@2591");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public void addPlayer() {
		Connection conn = null;
		try {
			conn=BuildConnection();
			PreparedStatement pst = conn.prepareStatement("INSERT INTO player VALUES(?,?,?,?,?,?)");
			System.out.println("Enter Player Id: ");
			pst.setInt(1,sc.nextInt());
			System.out.println("Enter Player Name: ");
			pst.setString(2,sc.next());
			System.out.println("Enter Player Age: ");
			pst.setInt(3,sc.nextInt());
			System.out.println("Enter Player Country: ");
			pst.setString(4,sc.next());
			System.out.println("Enter Player IPL Team: ");
			pst.setString(5,sc.next());
			System.out.println("Enter Player IPL Salary: ");
			pst.setInt(6,sc.nextInt());

			int rowsInserted = pst.executeUpdate();

			if(rowsInserted>0) {
				System.out.println(rowsInserted+" data inserted");
			}else {
				System.out.println("Data not inserted");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void findAllPlayerByCountry() {
		Connection conn=null;
		try {
			conn=BuildConnection();
			PreparedStatement pst = conn.prepareStatement("SELECT playerName FROM player WHERE playerCountry=?");
			System.out.println("Enter Player Country: ");
			pst.setString(1,sc.next());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void findPlayerByIplTeam() {
		Connection conn = null;
		try {
			conn=BuildConnection();
			PreparedStatement pst = conn.prepareStatement("SELECT playerName FROM player WHERE iplTeam =?");
			System.out.println("Enter the IPL Team: ");
			pst.setString(1, sc.next());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void findPlayerBetweenAge() {
		Connection conn =null;
		try {
			conn=BuildConnection();
			PreparedStatement pst = conn.prepareStatement("SELECT playerName FROM player WHERE playerAge>? and playerAge<?");
			System.out.println("Enter the Ages lower range for players: ");
			pst.setInt(1, sc.nextInt());
			System.out.println("Enter the Ages upper range for players: ");
			pst.setInt(2, sc.nextInt());

			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void updatePlayerSalaryById() {
		Connection conn = null;
		try {
			conn=BuildConnection();
			String s = "UPDATE player SET iplSalary=iplSalary*(1+(?/100)) WHERE playerId=?";
			PreparedStatement pst = conn.prepareStatement(s);
			System.out.println("Enter Increment/Decrement value along with ID to update the data: ");
			System.out.println("Increment/Decrement percentage: ");
			pst.setInt(1, sc.nextInt());
			System.out.println("Enter player ID: ");
			pst.setInt(2, sc.nextInt());
			int rowsUpdated=pst.executeUpdate();
			
			
			
			if(rowsUpdated>0) {
				System.out.println(rowsUpdated+" updated succesfully");
			}else {
				System.out.println("Table record is not updated");
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void updateSalaryBetweenAge() {
		Connection conn = null;
		try {
			conn=BuildConnection();
			String s = "UPDATE player SET iplSalary=iplSalary*(1+(?/100)) WHERE playerAge>=? and playerAge<=?";
			PreparedStatement pst = conn.prepareStatement(s);
			System.out.println("Enter Increment value along with the player age you want to update: ");
			System.out.println("Increment percentage: ");
			pst.setInt(1, sc.nextInt());
			System.out.println("Lower Age Limit: ");
			pst.setInt(2, sc.nextInt());
			System.out.println("Upper Age Limit");
			pst.setInt(3, sc.nextInt());
			int rowsUpdated=pst.executeUpdate();
			if(rowsUpdated>0) {
				System.out.println(rowsUpdated+" updated succesfully");
			}else {
				System.out.println("Table record is not updated");
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void deleteAllPlayerByCountry() {
		Connection conn=null;
		try {
			conn=BuildConnection();
			String s = "DELETE FROM player WHERE playerCountry=?";
			PreparedStatement pst = conn.prepareStatement(s);
			System.out.println("Enter player country name whose record you want to delete: ");
			pst.setString(1, sc.next());
			int rowsUpdated=pst.executeUpdate();
			if(rowsUpdated>0) {
				System.out.println(rowsUpdated+" record deleted succesfully");
			}else {
				System.out.println("Player record is not deleted");
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void deletePlayerById() {
		Connection conn=null;
		try {
			conn=BuildConnection();
			String s = "DELETE FROM player WHERE playerId=?";
			PreparedStatement pst = conn.prepareStatement(s);
			System.out.println("Enter player ID whose record you want to delete: ");
			pst.setInt(1, sc.nextInt());
			int rowsUpdated=pst.executeUpdate();
			if(rowsUpdated>0) {
				System.out.println(rowsUpdated+" record deleted succesfully");
			}else {
				System.out.println("Player record is not deleted");
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
