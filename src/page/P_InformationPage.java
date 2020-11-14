package page;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Movie.DB_MovieInfo;
import User.DB_userInfo;
import User.User;

public class P_InformationPage extends JFrame{
	
	//pos
	private final static int PaddingLeft = 30;
	private final static int PaddingTop = 150;
	private final static int USER_WIDTH = 250;
	private final static int USER_Padding = 140;

	//객체
	private User user;
	
	//component
	private JPanel panel = new JPanel();
	//JLabel profile = new JLabel("프로필");
	//JLabel name = new JLabel("이름");
	//JButton btn_change = new JButton("+");
	JLabel id = new JLabel("아이디");
	JLabel currnet_password = new JLabel("기존 비밀번호");
	JLabel new_password = new JLabel("신규 비밀번호");
	JLabel birthday = new JLabel("생년월일");
	JLabel phone = new JLabel("전화번호");
	JLabel taste = new JLabel("영화 취향");
	
	JLabel userID = new JLabel();
	JPasswordField input_current_password = new JPasswordField();
	JPasswordField input_new_password = new JPasswordField();
	JLabel userBirthday = new JLabel();
	JTextField input_phone = new JTextField();
	String[] string_taste = {"공포", "드라마", "로맨스", "스릴러", "애니메이션", "액션", "SF"};
	JCheckBox[] checkbox_taste = new JCheckBox[7];
	JButton btn_finish = new JButton();

	private ImageIcon imgRe = new ImageIcon("src/imges/RE.png");

	//design
	Font lfont = new Font("나눔바른고딕", Font.BOLD, 20);
	Font rfont = new Font("나눔바른고딕", Font.PLAIN, 17);


	

	public P_InformationPage(User user) {
		
		this.user = user;
		/*
		 * //프로필 사진 profile.setBounds(PaddingLeft, PaddingTop, 200, 200);
		 * profile.setOpaque(true); profile.setBackground(Color.RED);
		 * panel.add(profile);
		 * 
		 * //이름 name.setBounds(PaddingLeft, PaddingTop + 210, 200, 50);
		 * name.setOpaque(true); name.setBackground(Color.GREEN);
		 * name.setText(user.getUserName()); panel.add(name);
		 */
		
		//아이디
		id.setBounds(PaddingLeft, PaddingTop, 130, 50);
		id.setFont(lfont);
		id.setHorizontalAlignment(JLabel.CENTER);
		panel.add(id);
		
		userID.setBounds(PaddingLeft+USER_Padding, PaddingTop, USER_WIDTH, 50);
		userID.setOpaque(true);
		userID.setBackground(Color.WHITE);
		userID.setText(user.getUserID());
		userID.setFont(rfont);
		userID.setHorizontalAlignment(JLabel.CENTER);
		panel.add(userID);
		
		//current 비밀번호
		currnet_password.setBounds(PaddingLeft, PaddingTop+60, 130, 50);
		currnet_password.setFont(lfont);
		currnet_password.setHorizontalAlignment(JLabel.CENTER);
		panel.add(currnet_password);
		
		input_current_password.setBounds(PaddingLeft+USER_Padding, PaddingTop+60, USER_WIDTH, 50);
		input_current_password.setOpaque(true);
		input_current_password.setBackground(Color.YELLOW);
		input_current_password.setFont(rfont);
		input_current_password.setHorizontalAlignment(JLabel.CENTER);
		panel.add(input_current_password);
		
		//new 비밀번호
		new_password.setBounds(PaddingLeft, PaddingTop+120, 130, 50);
		new_password.setFont(lfont);
		new_password.setHorizontalAlignment(JLabel.CENTER);
		panel.add(new_password);
		
		input_new_password.setBounds(PaddingLeft+USER_Padding, PaddingTop+120, USER_WIDTH, 50);
		input_new_password.setOpaque(true);
		input_new_password.setBackground(Color.YELLOW);
		input_new_password.setFont(rfont);
		input_new_password.setHorizontalAlignment(JLabel.CENTER);
		panel.add(input_new_password);
		
		//생년월일 : yyyy-mm-dd
		birthday.setBounds(PaddingLeft, PaddingTop + 180, 130, 50);
		birthday.setFont(lfont);
		birthday.setHorizontalAlignment(JLabel.CENTER);
		panel.add(birthday);
		
		userBirthday.setBounds(PaddingLeft+USER_Padding, PaddingTop + 180, USER_WIDTH, 50);
		userBirthday.setFont(rfont);
		userBirthday.setHorizontalAlignment(JLabel.CENTER);
		userBirthday.setOpaque(true);
		userBirthday.setBackground(Color.YELLOW);
		userBirthday.setText(user.getUseryymmdd());
		panel.add(userBirthday);
		
		
		//전화번호
		phone.setBounds(PaddingLeft , PaddingTop + 240, 130, 50);
		phone.setFont(lfont);
		phone.setHorizontalAlignment(JLabel.CENTER);
		panel.add(phone);
		
		input_phone.setBounds(PaddingLeft+USER_Padding , PaddingTop + 240, USER_WIDTH, 50);
		input_phone.setOpaque(true);
		input_phone.setBackground(Color.YELLOW);
		input_phone.setText(user.getUserPhone());
		input_phone.setFont(rfont);
		input_phone.setHorizontalAlignment(JLabel.CENTER);
		panel.add(input_phone);
			
		//영화 취향
		taste.setBounds(PaddingLeft, PaddingTop + 300, 130, 50);
		taste.setFont(lfont);
		taste.setHorizontalAlignment(JLabel.CENTER);
		panel.add(taste);
		
		
		int x = 0;
		int y= PaddingTop+240;
		for(int i = 0; i < checkbox_taste.length; i++) {
			checkbox_taste[i] = new JCheckBox(string_taste[i]);
			if(i%2==0) {
				x = PaddingLeft+USER_Padding;
				y+=60;
			}else {
				x = PaddingLeft+USER_Padding+150;
			}
			checkbox_taste[i].setBounds(x, y , 130, 50);
			checkbox_taste[i].setOpaque(true);
			checkbox_taste[i].setBackground(Color.WHITE);
			checkbox_taste[i].setFont(rfont);
			panel.add(checkbox_taste[i]);
		}
		
		//수정
		btn_finish.setBounds(175, PaddingTop + 600, 150, 50);
		btn_finish.setOpaque(false);
		btn_finish.setIcon(imgRe);
		btn_finish.addActionListener(new BtnEvent());
		panel.add(btn_finish);
		
		add(panel);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		setSize(500, 900);
		setResizable(false);;
		setVisible(false);
	}
	class BtnEvent implements ActionListener{
		private String currentPassword = input_current_password.getText();
		private String newPassword = input_new_password.getText();
		private String Phone = input_phone.getText();
		
		//private User user = new User();
		
		DB_userInfo connection = new DB_userInfo();
	
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String id = user.getUserID();
			String current_pw = input_current_password.getText(); 
			String new_pw = input_new_password.getText(); 
			String phone = input_phone.getText();
			
			
			// TODO Auto-generated method stub
			if(e.getSource() == btn_finish)
			{
				if(id.isEmpty() && new_pw.isEmpty() && phone.isEmpty()) {
					JOptionPane.showMessageDialog(null, "정보를 입력해주세요");
				}else if(current_pw.equals(user.getUserPassword())){
					JOptionPane.showMessageDialog(null, "기존 비밀번호가 일치 하지 않습니다.");
				}
				else {
					connection.updateUserInfo(id,new_pw, phone);
					user = connection.getUserInfo(id);
					dispose();
					new MyPage(user);
				}
					
				
				
			}
		}
		
	}

}
