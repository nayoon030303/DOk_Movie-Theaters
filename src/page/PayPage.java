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
	private JLabel choice = new JLabel("���� ����� �������ּ���.");
	private ButtonGroup g = new ButtonGroup();
	private JRadioButton card = new JRadioButton("�ſ� ī��", true);
	private JRadioButton cash = new JRadioButton("������ �Ա�");
	
	//ī�� ����
	private JLabel cardCompany = new JLabel("ī��縦 �������ּ���");
	private JLabel depositBank = new JLabel("�Ա������� �������ּ���");
	private String[] Company = {"���� ī��", "�츮 ī��", "�ϳ� ī��", "���� ī��", "�Ե� ī��", "�Ｚ ī��", "NH ī��", "BC ī��"};
	private String[] bank = {"��������","�츮����","��������","KEB�ϳ�(����)����","�������","�뱸����"};
	private JLabel userName = new JLabel("������ ���� �Է��ϼ���");
	private JTextField inputName = new JTextField();
	private JComboBox check_company = new JComboBox(Company);
	private JComboBox check_bank = new JComboBox(bank);
	private JLabel cardNumber = new JLabel("ī�� ��ȣ�� �Է��ϼ���");
	private JTextField input_carNumber = new JTextField();
	private JLabel cardPassword = new JLabel("ī�� ��й�ȣ 4�ڸ� �Է����ּ���");
	private JTextField input_carPassword = new JTextField();
	private JButton finish = new JButton();

	private ImageIcon imgFinish = new ImageIcon("src/imges/finish.png");
	
	//design
	Font compo = new Font("�����ٸ����", Font.BOLD, 20);
	Font input = new Font("�����ٸ����", Font.PLAIN, 12);

	public PayPage() {
		
		//���� ��� ���� ���̺�
		choice.setBounds(PaddingLeft, PaddingTop, 275, 50);
		choice.setFont(compo);
		panel.add(choice);
		
		//���� �׷����� ��� A or B�� �����
		g.add(card);
		g.add(cash);
		
		//A : ī�� ����
		card.setBounds(PaddingLeft+10, PaddingTop + 50, 100, 50);
		card.setFont(input);
		card.setBackground(Color.WHITE);
		card.addActionListener(new RadioBtnEvent());
		panel.add(card);
		
		//B : ���� ����
		cash.setBounds(PaddingLeft + 150, PaddingTop + 50, 100, 50);
		cash.setFont(input);
		cash.setBackground(Color.WHITE);
		cash.addActionListener(new RadioBtnEvent());
		panel.add(cash);
		
		//ī��� ���� 
		cardCompany.setBounds(PaddingLeft, PaddingTop + 165, 275, 50);
		cardCompany.setFont(compo);
		panel.add(cardCompany);
		
		//ī��� ���� : JComboBox
		check_company.setBounds(PaddingLeft+10, PaddingTop + 240, 300, 35);
		check_company.setFont(input);
		panel.add(check_company);
		
		
		//�Ա� ���� �����ϱ�
		depositBank.setVisible(false);
		depositBank.setBounds(PaddingLeft, PaddingTop + 165, 275, 50);
		depositBank.setFont(compo);
		panel.add(depositBank);
		
		//���� ����
		check_bank.setBounds(PaddingLeft+10, PaddingTop + 240, 300, 35);
		check_bank.setFont(input);
		panel.add(check_bank);
		
		//������ �� �Է�
		userName.setVisible(false);
		userName.setBounds(PaddingLeft, PaddingTop + 315, 275, 50);
		userName.setFont(compo);
		panel.add(userName);
		
		//������ �� �Է� : JTextField
		inputName.setVisible(false);
		inputName.setBounds(PaddingLeft + 10, PaddingTop + 390, 300, 35);
		inputName.setFont(input);
		panel.add(inputName);
		
		//ī�� ��ȣ �Է�
		cardNumber.setBounds(PaddingLeft, PaddingTop + 315, 275, 50);
		cardNumber.setFont(compo);
		panel.add(cardNumber);
		
		//ī�� ��ȣ �Է� : JTextField
		input_carNumber.setBounds(PaddingLeft + 10, PaddingTop + 390, 300, 35);
		input_carNumber.setFont(input);
		panel.add(input_carNumber);
		
		//ī�� ��й�ȣ �Է� 4��
		cardPassword.setBounds(PaddingLeft, PaddingTop + 465, 300, 50);
		cardPassword.setFont(compo);
		panel.add(cardPassword);
		
		//ī�� ��й�ȣ �Է� 4�� : JTextField
		input_carPassword.setBounds(PaddingLeft + 10, PaddingTop + 540, 300, 35);
		input_carPassword.setFont(input);
		panel.add(input_carPassword);
		
		//���� Ȯ��
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
				//ī��
				cardCompany.setVisible(true);
				check_company.setVisible(true);
				cardNumber.setVisible(true);
				input_carNumber.setVisible(true);
				cardPassword.setVisible(true);
				input_carPassword.setVisible(true);
				
				//����
				depositBank.setVisible(false);
				check_bank.setVisible(false);
				userName.setVisible(false);
				inputName.setVisible(false);
			}else {
				//����
				depositBank.setVisible(true);
				check_bank.setVisible(true);
				userName.setVisible(true);
				inputName.setVisible(true);
				
				//ī��
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
