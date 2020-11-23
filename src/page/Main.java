package page;

import User.User;

public class Main {	
	
	public static final int SCREEN_WIDTH = 1500;
	public static final int SCREEN_HEIGHT = 1000;
	
	static User user = new User();
	
	
	public static void main(String[] args) {
		
		new DOKPage(user);
		//new  Reservation_start_page(user);
		//new LoginPage();
		//new MovieSitPage();
		//new SignUpPage();
		//new Reservation_start_page();
		//new MyPage(user);
		//new ChartPage(user);
		//new P_InformationPage(user);
		//new ChartPage();
		//new ChangePage();
	}

}
