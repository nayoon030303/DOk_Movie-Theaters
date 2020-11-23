package page;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReceiptPage extends JFrame {
	private final static int PaddingLeft = 50;
	private final static int PaddingTop = 175;
	
	//component
	private JPanel panel = new JPanel();
	
	//영수증
	JLabel movieName = new JLabel("영화 이름");	//변수에 선택한 영화 이름 받아오기
	JLabel date = new JLabel();		//현 시각
	JLabel sit = new JLabel();		//좌석 ex) C15
	JLabel adult = new JLabel();	//성인
	JLabel teen = new JLabel();		//청소년
	JLabel kids = new JLabel();		//애
	
	JButton ok = new JButton("확인");
		
	public ReceiptPage() {
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		setSize(500, 900);
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		//new ReceiptPage();
	}
}
