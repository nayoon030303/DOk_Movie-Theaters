package page;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReservationCheckPage extends JFrame implements ActionListener {
	private final static int PaddingLeft = 50;
	private final static int PaddingTop = 175;
	private final static int PRICE_Y = 50;
	
	//component
	private JPanel panel = new JPanel();
	
	private JLabel NoP = new JLabel();	//Number of People
	
	private JLabel[] people = new JLabel[3];
	private JLabel[] peoplePrice = new JLabel[3];
	private JLabel result = new JLabel();
	
	private JLabel selectSit = new JLabel("�����Ͻ� �¼��Դϴ�.");
	private JLabel sit = new JLabel("�¼�");
	
	private JLabel sure = new JLabel("������ �����Ͻðڽ��ϱ� ?");
	private JButton sureBtn = new JButton();

	private ImageIcon imgSure = new ImageIcon("src/imges/sure.png");
	
	//Design
	Font bold_font = new Font("�����ٸ����", Font.BOLD, 25);
	Font plain_font = new Font("�����ٸ����", Font.PLAIN, 20);
	Font result_font = new Font("�����ٸ����", Font.BOLD, 30);
	
	public ReservationCheckPage(int num_adult, int num_teen, int num_kids) {
		//�ݾ� ����
		int adultPrice = num_adult * 10000;
		int teenPrice = num_teen * 8000;
		int kidsPrice = num_kids * 5000;
		int resultPrice = (adultPrice + teenPrice + kidsPrice);
		
		//Price
		for(int i = 0; i < people.length; i++) {
			people[i] = new JLabel();
			people[i].setBounds(PaddingLeft, PaddingTop + (75 * i), 150, 50);
			people[i].setFont(bold_font);
			people[i].setHorizontalAlignment(JLabel.LEFT);
			panel.add(people[i]);
		
		}
		people[0].setText("���� " + num_adult + "��");
		people[1].setText("û�ҳ� " + num_teen + "��");
		people[2].setText("��� " + num_kids + "��");
		
		for(int i = 0; i < peoplePrice.length; i++) {
			peoplePrice[i] = new JLabel();
			peoplePrice[i].setBounds(PaddingLeft + 200, PaddingTop + (75 * i), 200, 50);
			peoplePrice[i].setFont(plain_font);
			peoplePrice[i].setHorizontalAlignment(JLabel.RIGHT);
			panel.add(peoplePrice[i]);
		
		}
		peoplePrice[0].setText(adultPrice + "��");
		peoplePrice[1].setText(teenPrice + "��");
		peoplePrice[2].setText(kidsPrice + "��");
		
		result.setText(resultPrice + "��");
		result.setBounds(PaddingLeft + 200, PaddingTop + 225, 200, 60);
		result.setFont(result_font);
		result.setHorizontalAlignment(JLabel.RIGHT);
		panel.add(result);
		
		//�¼� Ȯ��
		selectSit.setBounds(PaddingLeft, PaddingTop + 350, 250, 50);
		selectSit.setFont(bold_font);
		selectSit.setHorizontalAlignment(JLabel.LEFT);
		panel.add(selectSit);
		
		sit.setBounds(PaddingLeft, PaddingTop + 425, 75, 50);
		sit.setFont(plain_font);
		sit.setHorizontalAlignment(JLabel.CENTER);
		panel.add(sit);
		
		//���� �������� �̵�
		sure.setBounds(PaddingLeft + 1, PaddingTop + 525, 300, 50);
		sure.setFont(bold_font);
		sure.setHorizontalAlignment(JLabel.LEFT);
		panel.add(sure);
		
		sureBtn.setIcon(imgSure);
		sureBtn.setBounds(PaddingLeft + 150,PaddingTop + 600,150,50);
		sureBtn.setBorderPainted(false);
		panel.add(sureBtn);
		
		sureBtn.addActionListener(this);
		
		
		add(panel);
		
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		
		setSize(500, 900);
		setVisible(true);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == sureBtn) {
			new PayPage();
			dispose();
		}
	}
}
