package page;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import User.User;


public class MyPage extends CategoryFrame{
	
	//pos
	private final static int POS_X_CENTER = Main.SCREEN_WIDTH/2-50;
	private final static int POS_X_LEFT = Main.SCREEN_WIDTH/2-550;
	private final static int POS_X_RIGHT = Main.SCREEN_WIDTH/2-200+400+50;
	private final static int PaddingTop = 50;

	
	//component
	
	private JPanel panel = new JPanel();
	private JLabel profile_img = new JLabel();
	private JLabel name = new JLabel();
	private JLabel id = new JLabel("아이디");
	private JLabel birthday = new JLabel("생년월일");
	private JLabel phone = new JLabel("전화번호");
	private JLabel taste = new JLabel("영화 취향");
	private JButton btn_modify_Info = new JButton();
	private JButton btn_modify_Profile = new JButton();
	private JLabel userInfo = new JLabel("개인 정보");
	private JLabel record_movie = new JLabel();
	private JLabel record_movieInfo = new JLabel();
	
	
	//Design
	Font font1 = new Font("휴먼둥근헤드라인", Font.PLAIN, 25);
	Font font2 = new Font("휴먼둥근헤드라인", Font.PLAIN, 15);
	
	ImageIcon imgInfo = new ImageIcon("src/imges/info.png");
	ImageIcon imgRecord = new ImageIcon("src/imges/record.png");
	ImageIcon imgReInfo = new ImageIcon("src/imges/re_info.png");
	ImageIcon imgReProfile = new ImageIcon("src/imges/re_profile.png");
	//프로필 사진들
	ImageIcon userIcon;

	
	Color purple = new Color(82, 12, 139);

	
	public MyPage() {}
	public MyPage(User user) {
		
		super("마이 페이지");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);//창크기 조정x
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);//레이아웃 null
		setVisible(true);
		
		this.user = user;
		
		//프로필 사진
		String str ="src/imges/"+user.getUserProfile()+".png";
		userIcon = new ImageIcon(str);
		profile_img.setBounds(POS_X_LEFT, PaddingTop, 400, 400);
		profile_img.setOpaque(false);
		profile_img.setBackground(Color.RED);
		profile_img.setIcon(userIcon);
		panel.add(profile_img);
		
		//사용자 이름
		name.setBounds(POS_X_LEFT + 100, PaddingTop + 420, 200, 50);
		name.setText("이름: "+user.getUserName());
		name.setFont(font1);
		name.setHorizontalAlignment(JLabel.CENTER);
		panel.add(name);
		
		//프로필 수정btn
		btn_modify_Profile.setBounds(POS_X_LEFT + 100,PaddingTop + 525,200,50);
		btn_modify_Profile.setIcon(imgReProfile);
		btn_modify_Profile.setBorderPainted(false);
		btn_modify_Profile.addActionListener(new BtnEvent());
		panel.add(btn_modify_Profile);
		
		//개인 정보
		userInfo.setBounds(POS_X_CENTER - 50,PaddingTop,150,50);
		userInfo.setOpaque(false);
		userInfo.setIcon(imgInfo);
		panel.add(userInfo);
		
		//아이디
		id.setBounds(POS_X_CENTER, PaddingTop+60, 300, 40);
		id.setOpaque(true);
		id.setBackground(Color.WHITE);
		id.setFont(font2);
		id.setText("아이디: "+user.getUserID());
		panel.add(id);
		
		//생년월일 : yyyy-mm-dd
		birthday.setBounds(POS_X_CENTER, PaddingTop + 120, 300, 40);
		birthday.setOpaque(true);
		birthday.setBackground(Color.WHITE);
		birthday.setFont(font2);
		birthday.setText("생년월일: "+user.getUseryymmdd());
		panel.add(birthday);
		
		//전화번호
		phone.setBounds(POS_X_CENTER, PaddingTop + 180, 300, 40);
		phone.setOpaque(true);
		phone.setBackground(Color.WHITE);
		phone.setFont(font2);
		phone.setText("전화번호: "+user.getUserPhone());
		panel.add(phone);
		
		//영화 취향
		taste.setBounds(POS_X_CENTER, PaddingTop + 240, 300, 40);
		taste.setOpaque(true);
		taste.setBackground(Color.WHITE);
		taste.setFont(font2);
		taste.setText("영화취향: "+user.getUserTaste1());
		panel.add(taste);
		
		//개인정보수정btn
		btn_modify_Info.setBounds(POS_X_CENTER + 400, PaddingTop, 200, 50);
		btn_modify_Info.setIcon(imgReInfo);
		btn_modify_Info.setBorderPainted(false);
		btn_modify_Info.addActionListener(new BtnEvent());
		panel.add(btn_modify_Info);
		
		//최근 예매한 영화 label
		record_movie.setBounds(POS_X_CENTER - 50, PaddingTop + 300, 250, 50);
		record_movie.setOpaque(false);
		record_movie.setIcon(imgRecord);
		panel.add(record_movie);
		
		//최근 예매한 영화
		record_movieInfo.setText("아직 최근 예매하신 내역이 없으시네요 !");
		record_movieInfo.setHorizontalAlignment(JLabel.CENTER);
		record_movieInfo.setBounds(POS_X_CENTER - 50, PaddingTop + 365, 650, 250);
		record_movieInfo.setFont(font2);
		record_movieInfo.setBorder(new LineBorder(purple, 1));
		panel.add(record_movieInfo);
		
		
		//Panel
		panel.setBackground(Color.WHITE);
		panel.setBounds(0,(int) (Main.SCREEN_HEIGHT*0.25),Main.SCREEN_WIDTH,(int)(Main.SCREEN_HEIGHT*0.75));
		panel.setLayout(null);
		add(panel);
	}
	
	public class BtnEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btn_modify_Info) {
				String pw = JOptionPane.showInputDialog(null,"비밀번호를 입력해주세요");
				if(pw.equals(user.getUserPassword())) {
					new UserInformationPage(user);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
				}
				
			}
			
			if(e.getSource() == btn_modify_Profile) {
				new ChangeProfile(user);
				dispose();
			}
		}
		
	}
	
}