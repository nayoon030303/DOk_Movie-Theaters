package page;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Movie.DB_MovieArea;
import Movie.MovieArea;
import User.User;
import page.CategoryFrame;
import page.Main;
import page.ReservationCheckPage;
import ticket.Ticket;

public class MovieSitPage extends CategoryFrame implements Runnable {
	private final static int PaddingLeft = 150;
	private final static int PaddingTop = 125;

	// component
	private JPanel panel = new JPanel();
	private JLabel screen = new JLabel("SCREEN");
	private JButton[][] sit = new JButton[9][24];
	private int[][] int_selectedSit = new int[9][24];// ���õ� �ڼ��� 1
	private JButton gray = new JButton(); // gray�� ��ư
	private String[] str_number = { "0", "1", "2", "3", "4", "5", "6", "7", "8" };
	private JLabel adult = new JLabel("����");
	private JComboBox comboboxAdult = new JComboBox(str_number);
	private JLabel teen = new JLabel("û�ҳ�");
	private JComboBox comboboxTeen = new JComboBox(str_number);
	private JLabel kids = new JLabel("���");
	private JComboBox comboboxKids = new JComboBox(str_number);
	private JButton next = new JButton();
	private JLabel[] row = new JLabel[24];
	private JLabel[] column = new JLabel[9];
	private JLabel[] selectRow = new JLabel[24];
	private JLabel[] selectColumn = new JLabel[9];
	private Vector<String> seatName = new Vector<String>();

	private ImageIcon imgNext = new ImageIcon("src/imges/next.png");

	private int num_adult = 0;
	private int num_teen = 0;
	private int num_kids = 0;
	private String select = "";
	private int count = 0;
	private int selectCount = 0;

	private MovieArea movieArea;
	private Ticket ticket = new Ticket();
	private String seatState;

	// Design
	private Font sit_font = new Font("�����ٸ����", Font.BOLD, 15);
	private Font people = new Font("�����ٸ����", Font.PLAIN, 25);

	// DB
	private DB_MovieArea connect_movieArea = new DB_MovieArea();

	public MovieSitPage(User user, MovieArea movieArea) {
		super("��ȭ �¼� ����");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null); // ���̾ƿ� null
		setVisible(true);

		// ���� ����
		this.user = user;
		this.movieArea = movieArea;
		seatState = movieArea.getSeatState();

		// ticket�� ���� set
		ticket.setUserID(user.getUserID());
		ticket.setMovieareaKey(movieArea.get_key());

		gray.setBackground(Color.LIGHT_GRAY);

		// ��ȭ �¼� ����
		StringTokenizer str = new StringTokenizer(seatState, "/");
		String[] strI = new String[str.countTokens()];
		int n = 0;
		while (str.hasMoreElements()) {
			strI[n] = str.nextToken();
			n++;
		}

		// ����� �¼��� ǥ��
		for (int i = 0; i < n; i++) {// 9
			String[] strArray = strI[i].split("");
			int c = 0;
			for (String s : strArray) {// 24
				if (s.equals("1")) {
					int_selectedSit[i][c] = 1;
				}
				c += 1;
			}
		}

		// panel
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, (int) (Main.SCREEN_HEIGHT * 0.25), Main.SCREEN_WIDTH, (int) (Main.SCREEN_HEIGHT * 0.75));
		panel.setLayout(null);
		add(panel);

		screen.setBounds(PaddingLeft + 100, PaddingTop - 75, 1000, 40);
		screen.setOpaque(true);
		screen.setBackground(Color.LIGHT_GRAY);
		screen.setFont(people);
		screen.setHorizontalAlignment(JLabel.CENTER);
		panel.add(screen);

		// ����
		for (int i = 0; i < row.length; i++) {
			row[i] = new JLabel((i + 1) + "");

			if (i < 4) {
				row[i].setBounds(PaddingLeft + (i * 42), PaddingTop + 455, 40, 40);
			} else if (i < 20) {
				row[i].setBounds(PaddingLeft + 100 + (i * 42), PaddingTop + 455, 40, 40);
			} else {
				row[i].setBounds(PaddingLeft + 200 + (i * 42), PaddingTop + 455, 40, 40);
			}

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

		// ����
		for (int i = 0; i < column.length; i++) {
			column[i].setBounds(PaddingLeft - 50, PaddingTop + (i * 50), 40, 40);
			column[i].setFont(sit_font);
			column[i].setHorizontalAlignment(JLabel.CENTER);
			panel.add(column[i]);
		}

		// �¼�
		for (int i = 0; i < sit.length; i++) {
			for (int j = 0; j < sit[i].length; j++) {
				sit[i][j] = new JButton();

				if (j < 4) {
					sit[i][j].setBounds(PaddingLeft + (j * 42), PaddingTop + (i * 50), 40, 40);
				} else if (j < 20) {
					sit[i][j].setBounds(PaddingLeft + 100 + (j * 42), PaddingTop + (i * 50), 40, 40);
				} else {
					sit[i][j].setBounds(PaddingLeft + 200 + (j * 42), PaddingTop + (i * 50), 40, 40);
				}

				if (int_selectedSit[i][j] == 1) {// ����� �¼��� ������
					sit[i][j].setBackground(Color.BLACK);
					sit[i][j].setEnabled(false);
				} else {
					sit[i][j].setBackground(Color.LIGHT_GRAY);
					sit[i][j].addActionListener(new BtnEvent());
				}

				panel.add(sit[i][j]);
			}
		}

		adult.setBounds(PaddingLeft + 25, PaddingTop + 525, 60, 40);
		adult.setFont(people);
		adult.setHorizontalAlignment(JLabel.CENTER);
		panel.add(adult);

		comboboxAdult.setBounds(PaddingLeft + 100, PaddingTop + 525, 150, 30);
		comboboxAdult.setFont(sit_font);
		panel.add(comboboxAdult);

		teen.setBounds(PaddingLeft + 275, PaddingTop + 525, 150, 40);
		teen.setFont(people);
		teen.setHorizontalAlignment(JLabel.CENTER);
		panel.add(teen);

		comboboxTeen.setBounds(PaddingLeft + 400, PaddingTop + 525, 150, 30);
		comboboxTeen.setFont(sit_font);
		panel.add(comboboxTeen);

		kids.setBounds(PaddingLeft + 560, PaddingTop + 525, 150, 40);
		kids.setFont(people);
		kids.setHorizontalAlignment(JLabel.CENTER);
		panel.add(kids);

		comboboxKids.setBounds(PaddingLeft + 700, PaddingTop + 525, 150, 30);
		comboboxKids.setFont(sit_font);
		panel.add(comboboxKids);

		next.setBounds(PaddingLeft + 1100, PaddingTop + 525, 150, 50);
		next.setIcon(imgNext);
		next.setBorderPainted(false);
		next.addActionListener(new BtnEvent());
		panel.add(next);

	}

	class BtnEvent implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			num_adult = comboboxAdult.getSelectedIndex();
			num_teen = comboboxTeen.getSelectedIndex();
			num_kids = comboboxKids.getSelectedIndex();
			count = num_adult + num_teen + num_kids;

			seatState = "";// �ʱ�ȭ
			for (int i = 0; i < sit.length; i++) {
				for (int j = 0; j < sit[i].length; j++) {
					if (e.getSource() == sit[i][j] && (sit[i][j].getBackground() == gray.getBackground())) {
						// System.out.println("���");
						if (num_adult == 0 && num_teen == 0 && num_kids == 0) {
							JOptionPane.showMessageDialog(null, "�ο��� �������ּ���");
						} else {
							if (count <= selectCount) {
								JOptionPane.showMessageDialog(null, "�� �̻� �����Ͻ� �� �����ϴ�.");
							} else {
								sit[i][j].setBackground(new Color(82, 12, 139));
								int_selectedSit[i][j] = 1;

								switch (i) {
								case 0:
									select = "A" + (j + 1);
									break;
								case 1:
									select = "B" + (j + 1);
									break;
								case 2:
									select = "C" + (j + 1);
									break;
								case 3:
									select = "D" + (j + 1);
									break;
								case 4:
									select = "E" + (j + 1);
									break;
								case 5:
									select = "F" + (j + 1);
									break;
								case 6:
									select = "G" + (j + 1);
									break;
								case 7:
									select = "H" + (j + 1);
									break;
								case 8:
									select = "I" + (j + 1);
									break;
								}
								seatName.add(select);
								selectCount++;
							}

						}
					} else if (e.getSource() == sit[i][j]) {
						int_selectedSit[i][j] = 0;
						selectCount -= 1;
						seatName.remove(selectCount);
						sit[i][j].setBackground(Color.LIGHT_GRAY);
					}
					seatState += int_selectedSit[i][j];
				}
				seatState += '/';
			}

			// ����
			if (e.getSource() == next) {

				if (num_adult == 0 && num_teen == 0 && num_kids == 0) {
					JOptionPane.showMessageDialog(null, "�ο��� �������ּ���");
				} else {
					ticket.setSeatCount(selectCount);
					movieArea.setSeatState(seatState);
					// ����� �¼���
					new ReservationCheckPage(user, num_adult, num_teen, num_kids, seatName, ticket, movieArea);
					dispose();
				}
			}

		}
	}

	@Override
	public void run() {
		while (true) {

			// MovieArea ������ ��� �޾ƿ���
			MovieArea movieAreaE = connect_movieArea.getMovieArea(movieArea.get_key());
			seatState = movieAreaE.getSeatState();
			try {
				Thread.sleep(1000);
				// ��ȭ �¼� ����
				StringTokenizer str = new StringTokenizer(seatState, "/");
				String[] strI = new String[str.countTokens()];
				int n = 0;
				// ���پ� �迭�� �ֱ� (9)
				while (str.hasMoreElements()) {
					strI[n] = str.nextToken();
					n++;
				}

				// ����� �¼��� ǥ��
				for (int i = 0; i < n; i++) {// 9
					String[] strArray = strI[i].split("");
					int c = 0;
					for (String s : strArray) {// 24
						if (s.equals("1")) {
							int_selectedSit[i][c] = 1;
						}
						c += 1;
					}
				}

				// �¼�
				for (int i = 0; i < sit.length; i++) {
					for (int j = 0; j < sit[i].length; j++) {

						if (int_selectedSit[i][j] == 1) {// ����� �¼��� ������
							sit[i][j].setBackground(Color.BLACK);
							sit[i][j].setEnabled(false);
							
						} else {
							sit[i][j].setBackground(Color.LIGHT_GRAY);
						}

					}

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

}