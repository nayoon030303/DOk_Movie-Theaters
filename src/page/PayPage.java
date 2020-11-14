package page;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import page.MyPage.BtnEvent;

public class PayPage extends JFrame {
	private final static int PaddingLeft = 50;
	private final static int PaddingTop = 175;
	private final static int PRICE_Y = 50;
	
	//component
	private JPanel panel = new JPanel();
	private JLabel choice = new JLabel("결제 방법을 선택해주세요.");
	private ButtonGroup g = new ButtonGroup();
	private JRadioButton card = new JRadioButton("신용 카드", true);
	private JRadioButton cash = new JRadioButton("무통장 입금");
	
	//카드 결제
	private JLabel cardCompany = new JLabel("카드사를 선택해주세요");
	private JLabel depositBank = new JLabel("입금은행을 선택해주세요");
	private String[] Company = {"국민 카드", "우리 카드", "하나 카드", "신한 카드", "롯데 카드", "삼성 카드", "NH 카드", "BC 카드"};
	private String[] bank = {"농협은행","우리은행","신한은행","KEB하나(외한)은행","기업은행","대구은행"};
	private JLabel userName = new JLabel("예금주 명을 입력하세요");
	private JTextField inputName = new JTextField();
	private JComboBox check_company = new JComboBox(Company);
	private JComboBox check_bank = new JComboBox(bank);
	private JLabel cardNumber = new JLabel("카드 번호를 입력하세요");
	private JTextField input_carNumber = new JTextField();
	private JLabel cardPassword = new JLabel("카드 비밀번호 4자를 입력해주세요");
	private JTextField input_carPassword = new JTextField();
	private JButton finish = new JButton();

	private ImageIcon imgFinish = new ImageIcon("src/imges/finish.png");
	
	//design
	Font compo = new Font("나눔바른고딕", Font.BOLD, 20);
	Font input = new Font("나눔바른고딕", Font.PLAIN, 12);

	public PayPage() {
		
		//결제 방법 선택 레이블
		choice.setBounds(PaddingLeft, PaddingTop, 275, 50);
		choice.setFont(compo);
		panel.add(choice);
		
		//라디오 그룹으로 묶어서 A or B로 만들기
		g.add(card);
		g.add(cash);
		
		//A : 카드 결제
		card.setBounds(PaddingLeft+10, PaddingTop + 50, 100, 50);
		card.setFont(input);
		card.setBackground(Color.WHITE);
		card.addActionListener(new RadioBtnEvent());
		panel.add(card);
		
		//B : 현금 결제
		cash.setBounds(PaddingLeft + 150, PaddingTop + 50, 100, 50);
		cash.setFont(input);
		cash.setBackground(Color.WHITE);
		cash.addActionListener(new RadioBtnEvent());
		panel.add(cash);
		
		//카드사 선택 
		cardCompany.setBounds(PaddingLeft, PaddingTop + 165, 275, 50);
		cardCompany.setFont(compo);
		panel.add(cardCompany);
		
		//카드사 선택 : JComboBox
		check_company.setBounds(PaddingLeft+10, PaddingTop + 240, 300, 35);
		check_company.setFont(input);
		panel.add(check_company);
		
		
		//입금 은행 선택하기
		depositBank.setVisible(false);
		depositBank.setBounds(PaddingLeft, PaddingTop + 165, 275, 50);
		depositBank.setFont(compo);
		panel.add(depositBank);
		
		//은행 선택
		check_bank.setBounds(PaddingLeft+10, PaddingTop + 240, 300, 35);
		check_bank.setFont(input);
		panel.add(check_bank);
		
		//예금주 명 입력
		userName.setVisible(false);
		userName.setBounds(PaddingLeft, PaddingTop + 315, 275, 50);
		userName.setFont(compo);
		panel.add(userName);
		
		//예금주 명 입력 : JTextField
		inputName.setVisible(false);
		inputName.setBounds(PaddingLeft + 10, PaddingTop + 390, 300, 35);
		inputName.setFont(input);
		panel.add(inputName);
		
		//카드 번호 입력
		cardNumber.setBounds(PaddingLeft, PaddingTop + 315, 275, 50);
		cardNumber.setFont(compo);
		panel.add(cardNumber);
		
		//카드 번호 입력 : JTextField
		input_carNumber.setBounds(PaddingLeft + 10, PaddingTop + 390, 300, 35);
		input_carNumber.setFont(input);
		panel.add(input_carNumber);
		
		//카드 비밀번호 입력 4자
		cardPassword.setBounds(PaddingLeft, PaddingTop + 465, 300, 50);
		cardPassword.setFont(compo);
		panel.add(cardPassword);
		
		//카드 비밀번호 입력 4자 : JTextField
		input_carPassword.setBounds(PaddingLeft + 10, PaddingTop + 540, 300, 35);
		input_carPassword.setFont(input);
		panel.add(input_carPassword);
		
		//결제 확인
		finish.setBounds(250-75, PaddingTop + 600, 150, 50);
		finish.setIcon(imgFinish);
		finish.setBorderPainted(false);
		panel.add(finish); 
		
		
		add(panel);
		
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		setSize(500, 900);
		setVisible(true);
		setResizable(false);
	}
	class RadioBtnEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == card) {
				//카드
				cardCompany.setVisible(true);
				check_company.setVisible(true);
				cardNumber.setVisible(true);
				input_carNumber.setVisible(true);
				cardPassword.setVisible(true);
				input_carPassword.setVisible(true);
				
				//현금
				depositBank.setVisible(false);
				check_bank.setVisible(false);
				userName.setVisible(false);
				inputName.setVisible(false);
			}else {
				//현금
				depositBank.setVisible(true);
				check_bank.setVisible(true);
				userName.setVisible(true);
				inputName.setVisible(true);
				
				//카드
				cardCompany.setVisible(false);
				check_company.setVisible(false);
				cardNumber.setVisible(false);
				input_carNumber.setVisible(false);
				cardPassword.setVisible(false);
				input_carPassword.setVisible(false);
			}
		}		
	}
}
