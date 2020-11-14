package page;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import User.User;

public class CategoryFrame extends JFrame {
	
	//위치
	 final static int loginBtn_X = (int) (Main.SCREEN_WIDTH*0.7);
	 final static int SigUpBtn_X = loginBtn_X+160;
	 final static int LSbtn_Y = 70;

	//component
	 private JPanel top_panel = new JPanel();
	 private ImageIcon img_logo = new ImageIcon("src/imges/dok.png");	//로고 이미지
	 private JLabel logo = new JLabel("DoK");
	 private JButton btn_login = new JButton();
	 private JButton btn_signUp = new JButton();
	 private JButton[] btn_category = new JButton[4]; 
	 private String[] string_category = {"HOME","MOVIE","Reservation","MyPage"};	
	
	//font
	 protected Font Logo = new Font("Franklin Gothic Heavy", Font.PLAIN, 120);
	 protected Font font1 = new Font("나눔바른고딕", Font.PLAIN, 20);
	 protected Font font2 = new Font("나눔바른고딕", Font.BOLD, 40);
	 protected Font font3 = new Font("Franklin Gothic Heavy", Font.PLAIN, 30);
	
	 //user정보
	 protected User user = new User();
	
	public CategoryFrame() {
		
	}
	
	public CategoryFrame(String str) {
		super(str);
		
		top_panel.setBackground(Color.WHITE);
		top_panel.setBounds(0, 0, Main.SCREEN_WIDTH, (int) (Main.SCREEN_HEIGHT*0.25));
		top_panel.setLayout(null);
		add(top_panel);
		
		//Logo 
		//logo.setIcon(img_logo);
		//logo.setBounds(Main.SCREEN_WIDTH/2 -100 ,30,200,100);
		//top_panel.add(logo);
		
		logo.setBounds(625, 10, 275, 150);
		logo.setFont(Logo);
		top_panel.add(logo);
		

		/*
		 * //로그인 jbtnLogin.setText("로그인"); jbtnLogin.setFont(font1);
		 * jbtnLogin.setBounds(loginBtn_X, LSbtn_Y, 150, 50); add(jbtnLogin);
		 * 
		 * //회원가입 jbtnSignUp.setText("회원가입"); jbtnSignUp.setFont(font1);
		 * jbtnSignUp.setBounds(SigUpBtn_X, LSbtn_Y, 150, 50); add(jbtnSignUp);
		 */
		
		//카테고리 
		for(int i=0; i<4; i++) {
			btn_category[i] = new JButton();
			btn_category[i].setText(string_category[i]);
			btn_category[i].setFont(font3);
			btn_category[i].setBounds(200 + (i * 270), 180, 275, 50);
			btn_category[i].setBackground(Color.WHITE);
			//setFocusPainted(false);
			//btn_category[i].setBorderPainted(false);
			top_panel.add(btn_category[i]);
			btn_category[i].addActionListener(new categoryEvent());
			
		}
		
	}

	class categoryEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==btn_category[0]) {//홈
				new DOKPage(user);
			}else if(e.getSource() == btn_category[1]) {//영화
				new ChartPage(user);
			}else if(e.getSource() == btn_category[2]) {//예매
				new Reservation_start_page(user);
				//new MovieSitPage();
			}else if(e.getSource() == btn_category[3]) {//마이 페이지
				new MyPage(user);
			}
			dispose();
		}
	
	
	}
}


	