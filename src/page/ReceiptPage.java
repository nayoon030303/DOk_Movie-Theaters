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
	
	//������
	JLabel movieName = new JLabel("��ȭ �̸�");	//������ ������ ��ȭ �̸� �޾ƿ���
	JLabel date = new JLabel();		//�� �ð�
	JLabel sit = new JLabel();		//�¼� ex) C15
	JLabel adult = new JLabel();	//����
	JLabel teen = new JLabel();		//û�ҳ�
	JLabel kids = new JLabel();		//��
	
	JButton ok = new JButton("Ȯ��");
		
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
