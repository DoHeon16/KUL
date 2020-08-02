package Team9;

import java.util.Scanner;

public class menu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan=new Scanner(System.in);
		
		while(true) {
		
			String choice="0";
			System.out.println("1.회원가입		2.로그인		3.종료");
			choice=scan.nextLine();
				
				switch(choice) {
				case "1":
					signup a=new signup();
					a.SignUp();
					break;
				case "2":
					Login b=new Login();
					b.login();
					break;
				default: 					
					System.out.println("종료합니다."); 
					return;
				}
			
		}
		
		

	}

}
