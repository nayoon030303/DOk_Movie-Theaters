package page;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MovieSitPage extends CategoryFrame {
	private final static int PaddingLeft = 150;
	private final static int PaddingTop = 125;
	
	//component
	private JPanel panel = new JPanel();
	
	JLabel screen = new JLabel("SCREEN");
	
	JButton[][] sit = new JButton[24][9];
	String[] number = {"0","1", "2", "3", "4", "5", "6", "7", "8"};
	JLabel adult = new JLabel("성인");
	JComboBox combobox_adult = new JComboBox(number);
	JLabel teen = new JLabel("청소년");
	JComboBox combobox_teen = new JComboBox(number);
	JLabel kids = new JLabel("아이");
	JComboBox combobox_kids = new JComboBox(number);
	JButton next = new JButton();
	
	JLabel[] row = new JLabel[24];
	JLabel[] column = new JLabel[9];
	
	private ImageIcon imgNext = new ImageIcon("src/imges/next.png");
	
	int num_adult = 0;
	int num_teen = 0;
	int num_kids = 0;
	
	//Design
	Font sit_font = new Font("나눔바른고딕", Font.BOLD, 15);
	Font people = new Font("나눔바른고딕", Font.PLAIN, 25);
	
		
	public MovieSitPage() {
		super("영화 좌석 선택");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);	//레이아웃 null
		setVisible(true);
		
		//panel
		panel.setBackground(Color.WHITE);
		panel.setBounds(0,(int) (Main.SCREEN_HEIGHT*0.25),Main.SCREEN_WIDTH,(int)(Main.SCREEN_HEIGHT*0.75));
		panel.setLayout(null);
		add(panel);
		
		screen.setBounds(PaddingLeft + 100, PaddingTop - 75, 1000, 40);
		screen.setOpaque(true);
		screen.setBackground(Color.LIGHT_GRAY);
		screen.setFont(people);
		screen.setHorizontalAlignment(JLabel.CENTER);
		panel.add(screen);
		
		
		for(int i = 0; i < row.length; i++) {
			row[i] = new JLabel((i + 1) + "");
			
			if(i < 4) {
				row[i].setBounds(PaddingLeft + (i * 42), PaddingTop + 455, 40, 40);
			}else if(i < 20) {
				row[i].setBounds(PaddingLeft + 100 + (i * 42), PaddingTop + 455, 40, 40);
			}else {
				row[i].setBounds(PaddingLeft + 200 + (i * 42), PaddingTop + 455, 40, 40);
			}
			
			row[i].setBackground(Color.ORANGE);
			row[i].setFont(sit_font);
			row[i].setHorizontalAlignment(JLabel.CENTER);
			panel.add(row[i]);
		}
		
		column[0] = new JLabel("A");
		column[1] = new JLabel("B");
		column[2] = new JLabel("C");
		column[3] = new JLabel("D");
		column[4] = new JLabel("E");
		column[5] = new JLabel("F");
		column[6] = new JLabel("G");
		column[7] = new JLabel("H");
		column[8] = new JLabel("I");
		
		for(int i = 0; i < column.length; i++) {
			column[i].setBounds(PaddingLeft - 50, PaddingTop + (i * 50), 40, 40);
			column[i].setFont(sit_font);
			column[i].setHorizontalAlignment(JLabel.CENTER);
			panel.add(column[i]);
		}
		
		
		for(int i = 0; i < sit.length; i++) {
			for(int j = 0; j < sit[i].length; j++) {
				sit[i][j] = new JButton();
				
				if(i < 4) {
					sit[i][j].setBounds(PaddingLeft + (i * 42), PaddingTop + (j * 50), 40, 40);
				}else if(i < 20) {
					sit[i][j].setBounds(PaddingLeft + 100 + (i * 42), PaddingTop + (j * 50), 40, 40);
				}else {
					sit[i][j].setBounds(PaddingLeft + 200 + (i * 42), PaddingTop + (j * 50), 40, 40);
				}
				
				sit[i][j].setBackground(Color.LIGHT_GRAY);
				panel.add(sit[i][j]);
			}
		}
		
		adult.setBounds(PaddingLeft + 25, PaddingTop + 525, 60, 40);
		adult.setFont(people);
		adult.setHorizontalAlignment(JLabel.CENTER);
		panel.add(adult);
		
		combobox_adult.setBounds(PaddingLeft + 100, PaddingTop + 525, 150, 30);
		combobox_adult.setFont(sit_font);
		panel.add(combobox_adult);
		
		teen.setBounds(PaddingLeft + 275, PaddingTop + 525, 150, 40);
		teen.setFont(people);
		teen.setHorizontalAlignment(JLabel.CENTER);
		panel.add(teen);
		
		combobox_teen.setBounds(PaddingLeft + 400, PaddingTop + 525, 150, 30);
		combobox_teen.setFont(sit_font);
		panel.add(combobox_teen);
		
		kids.setBounds(PaddingLeft + 625, PaddingTop + 525, 60, 40);
		kids.setFont(people);
		kids.setHorizontalAlignment(JLabel.CENTER);
		panel.add(kids);
		
		combobox_kids.setBounds(PaddingLeft + 700, PaddingTop + 525, 150, 30);
		combobox_kids.setFont(sit_font);
		panel.add(combobox_kids);
		
		next.setBounds(PaddingLeft + 1100, PaddingTop + 525, 150, 50);
		next.setIcon(imgNext);
		next.setBorderPainted(false);
		next.addActionListener(new BtnEvent());
		panel.add(next);

	}
	class BtnEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == next) {
				num_adult = combobox_adult.getSelectedIndex();
				num_teen = combobox_teen.getSelectedIndex();
				num_kids = combobox_kids.getSelectedIndex();

				if(num_adult == 0 && num_teen == 0 && num_kids == 0) {
					JOptionPane.showMessageDialog(null, "인원을 선택해주세요");
				}else{
					new ReservationCheckPage(num_adult,num_teen,num_kids);
				}				
			}				
		}
	}
}
