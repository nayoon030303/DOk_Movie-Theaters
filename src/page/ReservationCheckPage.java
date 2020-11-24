package page;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Movie.MovieArea;
import User.User;
import page.CategoryFrame.windowAdapter;
import ticket.Ticket;

public class ReservationCheckPage extends JFrame implements ActionListener, Runnable {
	private final static int PaddingLeft = 50;
	private final static int PaddingTop = 175;
	private final static int PRICE_Y = 50;
	
	//component
	private JPanel panel = new JPanel();
	
	private JLabel[] people = new JLabel[3];
	private JLabel[] peoplePrice = new JLabel[3];
	private JLabel result = new JLabel();
	private JLabel selectSit = new JLabel("선택하신 좌석입니다.");
	private JLabel sit = new JLabel();
	private JLabel goContineu = new JLabel("결제를 진행하시겠습니까 ?");
	private JButton sure = new JButton();
	
	//이미지
	private ImageIcon imgSure = new ImageIcon("src/imges/sure.png");
	
	private Ticket ticket;
	private User user;
	private MovieArea movieArea;
	int resultPrice;
	
	
	//font
	Font bold_font = new Font("나눔바른고딕", Font.BOLD, 25);
	Font plain_font = new Font("나눔바른고딕", Font.PLAIN, 20);
	Font result_font = new Font("나눔바른고딕", Font.BOLD, 30);
	
	public ReservationCheckPage(User user,int num_adult, int num_teen, int num_kids, Vector<String> seat_name, Ticket ticket, MovieArea movieArea) {
		
		//금액 측정
		int adultPrice = num_adult * 10000;
		int teenPrice = num_teen * 8000;
		int kidsPrice = num_kids * 5000;
		resultPrice = (adultPrice + teenPrice + kidsPrice);
		
		//데이터 
		this.user = user;
		this.ticket = ticket;
		this.movieArea = movieArea;
		
		//Price
		for(int i = 0; i < people.length; i++) {
			people[i] = new JLabel();
			people[i].setBounds(PaddingLeft, PaddingTop + (75 * i), 150, 50);
			people[i].setFont(bold_font);
			people[i].setHorizontalAlignment(JLabel.LEFT);
			panel.add(people[i]);
		
		}
		people[0].setText("성인 " + num_adult + "매");
		people[1].setText("청소년 " + num_teen + "매");
		people[2].setText("어린이 " + num_kids + "매");
		
		for(int i = 0; i < peoplePrice.length; i++) {
			peoplePrice[i] = new JLabel();
			peoplePrice[i].setBounds(PaddingLeft + 200, PaddingTop + (75 * i), 200, 50);
			peoplePrice[i].setFont(plain_font);
			peoplePrice[i].setHorizontalAlignment(JLabel.RIGHT);
			panel.add(peoplePrice[i]);
		
		}
		peoplePrice[0].setText(adultPrice + "원");
		peoplePrice[1].setText(teenPrice + "원");
		peoplePrice[2].setText(kidsPrice + "원");
		
		result.setText(resultPrice + "원");
		result.setBounds(PaddingLeft + 200, PaddingTop + 225, 200, 60);
		result.setFont(result_font);
		result.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(result);
		
		//좌석 확인
		selectSit.setBounds(PaddingLeft, PaddingTop + 350, 250, 50);
		selectSit.setFont(bold_font);
		selectSit.setHorizontalAlignment(JLabel.LEFT);
		panel.add(selectSit);
		
		
		
		String[] seat_Name = new String[seat_name.size()];
		for(int i=0; i<seat_name.size(); i++) {
			seat_Name[i] = (String)seat_name.get(i);
		}
		Arrays.sort(seat_Name);//정렬 오름차순
		sit.setText(Arrays.toString(seat_Name));
		sit.setBounds(PaddingLeft, PaddingTop + 425, 400, 50);
		sit.setFont(plain_font);
		panel.add(sit);
		
		//다음 페이지로 이동
		goContineu.setBounds(PaddingLeft + 1, PaddingTop + 525, 300, 50);
		goContineu.setFont(bold_font);
		goContineu.setHorizontalAlignment(JLabel.LEFT);
		panel.add(goContineu);
		
		sure.setIcon(imgSure);
		sure.setBounds(PaddingLeft + 150,PaddingTop + 600,150,50);
		sure.setBorderPainted(false);
		panel.add(sure);
		
		sure.addActionListener(this);
		
		
		add(panel);
		
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		setSize(500, 900);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new windowAdapter());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sure) {
			ticket.setPrice(resultPrice);
			ticket.setSeatWhere(sit.getText());
			Thread t1 = new Thread(new PayPage(user,ticket, movieArea));
			//new PayPage(user,ticket, movieArea);
			dispose();
		}
	}
	class windowAdapter extends WindowAdapter{

		@Override
		public void windowClosing(WindowEvent e) {
	        int result = JOptionPane.showConfirmDialog(null,"예매를 취소하시겠습니까?");
	        if (result==JOptionPane.OK_OPTION) {
	     	   dispose();
	     	   new DOKPage(user);
	        }
		}  
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
