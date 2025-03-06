package com.jsp.jdbc.playerdb;

import java.util.Scanner;

import com.jsp.jdbc.playdb.dao.PlayerDao;



public class App {
	public static void main(String[] args) {	
		Scanner sc = new Scanner(System.in);
		PlayerDao player = new PlayerDao();

		int choice=0;

		do {
			System.out.println();
			System.out.println("1. Add Player\n2. Find All Player By Country");
			System.out.println("3. Find Player By IPL Team\n4. Find Player Between Age");
			System.out.println("5. Update Player Salary By Id\n6. Update Salaray Between Age");
			System.out.println("7. Delete All Player By Country\n8. Delete Player By Id");
			System.out.println("9. Exit");
			System.out.println("Enter the Choice");

			choice = sc.nextInt();

			switch (choice)
			{
			case 1: {
				player.addPlayer();
				break;	
			}
			case 2:{
				player.findAllPlayerByCountry();
				break;
			}
			case 3:{
				player.findPlayerByIplTeam();
				break;
			}
			case 4:{
				player.findPlayerBetweenAge();
				break;
			}
			case 5:{
				player.updatePlayerSalaryById();
				break;
			}
			case 6:{
				player.updateSalaryBetweenAge();
				break;
			}

			case 7:{
				player.deleteAllPlayerByCountry();
				break;

			}

			case 8:{
				player.deletePlayerById();
				break;

			}
			case 9:{
				System.out.println("Existed from App");
				System.exit(0);

			}
			default:
				System.out.println("invalid choice");
			}

		}
		while (choice!=9);
		sc.close();

	}


}