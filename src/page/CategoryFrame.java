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
	
	//��ġ
	 final static int loginBtn_X = (int) (Main.SCREEN_WIDTH*0.7);
	 final static int SigUpBtn_X = loginBtn_X+160;
	 final static int LSbtn_Y = 70;

	//component
	 private JPanel top_panel = new JPanel();
	 private ImageIcon img_logo = new ImageIcon("src/imges/dok.png");	//�ΰ� �̹���
	 private JLabel logo = new JLabel("DoK");
	 private JButton btn_login = new JButton();
	 private JButton btn_signUp = new JButton();
	 private JButton[] btn_category = new JButton[4]; 
	 private String[] string_category = {"HOME","MOVIE","Reservation","MyPage"};	
	
	//font
	 protected Font Logo = new Font("Franklin Gothic Heavy", Font.PLAIN, 120);
	 protected Font font1 = new Font("�����ٸ����", Font.PLAIN, 20);
	 protected Font font2 = new Font("�����ٸ����", Font.BOLD, 40);
	 protected Font font3 = new Font("Franklin Gothic Heavy", Font.PLAIN, 30);
	
	 //user����
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
		 * //�α��� jbtnLogin.setText("�α���"); jbtnLogin.setFont(font1);
		 * jbtnLogin.setBounds(loginBtn_X, LSbtn_Y, 150, 50); add(jbtnLogin);
		 * 
		 * //ȸ������ jbtnSignUp.setText("ȸ������"); jbtnSignUp.setFont(font1);
		 * jbtnSignUp.setBounds(SigUpBtn_X, LSbtn_Y, 150, 50); add(jbtnSignUp);
		 */
		
		//ī�װ� 
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
			if(e.getSource()==btn_category[0]) {//Ȩ
				new DOKPage(user);
			}else if(e.getSource() == btn_category[1]) {//��ȭ
				new ChartPage(user);
			}else if(e.getSource() == btn_category[2]) {//����
				new Reservation_start_page(user);
				//new MovieSitPage();
			}else if(e.getSource() == btn_category[3]) {//���� ������
				new MyPage(user);
			}
			dispose();
		}
	
	
	}
}


	