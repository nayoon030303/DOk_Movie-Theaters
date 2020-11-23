package page;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import Area.Area;
import Area.DB_Area;
import Movie.DB_MovieArea;
import Movie.DB_MovieInfo;
import Movie.Movie;
import Movie.MovieArea;
import User.User;
import page.CategoryFrame;
import page.DOKPage;
import page.Main;
import page.MovieSitPage;
import theater.DB_Theater;
import theater.Theater;

public class Reservation_start_page extends CategoryFrame implements ActionListener, Runnable {
	private final static int PaddingLeft = 40;
	private final static int PaddingTop = 100;
	private final static double Panel_Height = 700;

	// 날짜
	private Calendar cal = Calendar.getInstance();

	// size
	private Dimension size = new Dimension();// 사이즈를 지정하기 위한 객체 생성

	// component
	private JPanel panel = new JPanel();
	private JPanel moviePanel = new JPanel();
	private JPanel areaPanel = new JPanel();
	private JPanel timePanel = new JPanel();

	// 영화
	private JLabel titleMovie = new JLabel("영화");
	private JLabel iconMovie = new JLabel();
	private JPanel movieListPanel = new JPanel();
	private JPanel schedulePanel = new JPanel();
	// private JLabel[] oster = new JLabel[3];

	// 지역
	private JLabel titleArea = new JLabel("지역");
	private JLabel iconArea = new JLabel();
	private JButton SEOUL = new JButton("서울");
	private JButton GYEONGGI = new JButton("경기");
	private JKeyButton[] seoulArea;
	private JKeyButton[] gyeonggiArea;
	private JScrollPane TimescrollPanel;

	// 시간
	private JKeyButton[] dayAndDayofTable = new JKeyButton[7];
	private JKeyButton[] content = new JKeyButton[4];
	JKeyButton[] btn_movie;
	private JLabel[] yearMonthTable = new JLabel[7];
	private JLabel noSchedule = new JLabel();
	// DB
	private DB_MovieInfo movie_connect = new DB_MovieInfo();
	private DB_MovieArea moviearea_connect = new DB_MovieArea();
	private DB_Theater theater_connect = new DB_Theater();
	private DB_Area area_connect = new DB_Area();

	//
	private Movie[] movie;
	private Vector<MovieArea> movieAreas = new Vector<MovieArea>();
	private Theater[] theater;
	private Area[] area;
	private String movieName;
	private int movieKey;
	private String areaKey;
	private String countryKey;
	private int theaterKey;
	private int checkYear;
	private int checkMonth;
	private int checkDay;
	private int checkDayofweek;
	private int year;
	private int month;
	private int day;
	private int dayofweek;
	private int endDate;
	private boolean isCheckButton = false;
	private boolean isNoSchedule = false;
	private boolean isReset = false;
	private String str = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm");
	
	
	Color purple = new Color(82, 12, 139);

	String weeksName = "";

	// count
	private int movieCount = 0;
	private int countryCount = 0;
	private int movieNum;

	private String escape1 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	private String escape2 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	
	// 이미지
	private ImageIcon imgSeoul = new ImageIcon("src/img/seoul.png");
	private ImageIcon imgGyeonggi = new ImageIcon("src/img/gyeonggi.png");
	private ImageIcon imgMovie = new ImageIcon("src/img/clapperboard.png");
	private ImageIcon imgArea = new ImageIcon("src/img/worldwide.png");
	private ImageIcon imgNoSchedule = new ImageIcon("src/img/noSchedule.png");

	// Design
	Font namefont = new Font("나눔바른고딕", Font.PLAIN, 17);
	Font boldfont = new Font("나눔바른고딕", Font.BOLD, 20);
	Font font1 = new Font("나눔바른고딕", Font.PLAIN, 25);
	Font font2 = new Font("나눔바른고딕", Font.PLAIN, 20);

	public Reservation_start_page() {
	}

	public Reservation_start_page(User user) {
		super("예매");

		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null); // 레이아웃 null
		setVisible(true);
		setBackground(Color.WHITE);

		// 날짜
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH); // 월
		day = cal.get(Calendar.DATE);// 날짜
		dayofweek = cal.get(Calendar.DAY_OF_WEEK);// 요일
		endDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		areaKey = "서울";

		// 데어터 연결
		area = area_connect.getArea();
		theater = theater_connect.getTheater(areaKey);
		movie = movie_connect.getMovieInfoAll("open_day");
		this.user = user;

		// 서울,경기
		seoulArea = new JKeyButton[theater.length / 2];
		gyeonggiArea = new JKeyButton[theater.length / 2];

		// 영화 패널
		moviePanel.setBounds(PaddingLeft, PaddingTop, 400, 600);
		moviePanel.setOpaque(true);
		moviePanel.setBackground(Color.WHITE);
		moviePanel.setLayout(null);
		panel.add(moviePanel);

		titleMovie.setBounds(5, 5, 60, 40);
		titleMovie.setFont(font1);
		titleMovie.setHorizontalAlignment(JLabel.CENTER);
		moviePanel.add(titleMovie);

		iconMovie.setIcon(imgMovie);
		iconMovie.setBounds(67, 0, 50, 50);
		iconMovie.setOpaque(true);
		iconMovie.setBackground(Color.WHITE);
		moviePanel.add(iconMovie);

		// 영화 패널
		movieListPanel.setOpaque(true);
		movieListPanel.setBackground(Color.DARK_GRAY);
		movieListPanel.setLayout(null);
		movieListPanel.setPreferredSize(size);

		// 영화 추가 ////////////////////////////////////////////////////

		movieNum = 14;// 14개의 영화만 상영
		size.setSize(400, movieNum * 50);
		btn_movie = new JKeyButton[movieNum];
		for (int i = 0; i < movieNum; i++) {
			btn_movie[i] = new JKeyButton();
			btn_movie[i].setHorizontalAlignment(JButton.LEFT);
			btn_movie[i].setMovieKey(movie[i].get_key());
			btn_movie[i].setText(movie[i].getM_name());
			btn_movie[i].setBounds(0, 50 * i, 400, 50);
			btn_movie[i].setBorder(new LineBorder(purple, 1));
			btn_movie[i].setFont(namefont);
			btn_movie[i].setOpaque(true);
			btn_movie[i].setBackground(Color.WHITE);
			btn_movie[i].addActionListener(this);
			movieListPanel.add(btn_movie[i]);
		}

		JScrollPane sp = new JScrollPane(movieListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setBounds(0, 75, 400, 500);
		sp.setBorder(new LineBorder(purple, 1));
		moviePanel.add(sp);

		// 지역
		areaPanel.setBounds(PaddingLeft + 425, PaddingTop, 450, 600);
		areaPanel.setOpaque(true);
		areaPanel.setBackground(Color.WHITE);
		areaPanel.setLayout(null);
		panel.add(areaPanel);

		titleArea.setBounds(5, 5, 60, 40);
		titleArea.setFont(font1);
		titleArea.setHorizontalAlignment(JLabel.CENTER);
		areaPanel.add(titleArea);

		iconArea.setIcon(imgArea);
		iconArea.setBounds(67, 0, 50, 50);
		iconArea.setOpaque(true);
		iconArea.setBackground(Color.WHITE);
		areaPanel.add(iconArea);

		// 서울
		SEOUL.setBounds(0, 75, 225, 40);
		SEOUL.setOpaque(true);
		SEOUL.setBackground(purple);
		SEOUL.setBorder(new LineBorder(purple, 1));
		SEOUL.setForeground(Color.WHITE);
		SEOUL.setFont(font1);
		SEOUL.addActionListener(new Reservation_area_Event());
		areaPanel.add(SEOUL);

		// 경기
		GYEONGGI.setBounds(225, 75, 225, 40);
		GYEONGGI.setOpaque(true);
		GYEONGGI.setBackground(Color.WHITE);
		GYEONGGI.setBorder(new LineBorder(purple, 1));
		GYEONGGI.setFont(font1);
		GYEONGGI.addActionListener(new Reservation_area_Event());
		areaPanel.add(GYEONGGI);

		// 서울 지역
		for (int i = 0; i < seoulArea.length; i++) {
			// btn_seoullist[i] = new JButton(theater[i].getCountry());
			seoulArea[i] = new JKeyButton();
			seoulArea[i].setTheaterKey(theater[i].get_key());// theater_key
			seoulArea[i].setText(theater[i].getCountry());
			seoulArea[i].setBounds(10, 150 + (i * 60), 100, 40);
			seoulArea[i].setFocusPainted(false);
			seoulArea[i].setBorderPainted(false);
			seoulArea[i].setOpaque(true);
			seoulArea[i].setBackground(Color.WHITE);
			seoulArea[i].setFont(font2);
			seoulArea[i].addActionListener(new Reservation_country_Event());
			areaPanel.add(seoulArea[i]);
		}

		// 경기 지역
		theater = theater_connect.getTheater("경기");
		for (int i = 0; i < gyeonggiArea.length; i++) {
			gyeonggiArea[i] = new JKeyButton();
			gyeonggiArea[i].setTheaterKey(theater[i].get_key());// theater_key
			gyeonggiArea[i].setText(theater[i].getCountry());
			theater[i].get_key();
			gyeonggiArea[i].setVisible(false);
			gyeonggiArea[i].setBounds(10, 150 + (i * 60), 100, 40);
			gyeonggiArea[i].setFocusPainted(false);
			gyeonggiArea[i].setBorderPainted(false);
			gyeonggiArea[i].setOpaque(true);
			gyeonggiArea[i].setBackground(Color.WHITE);
			gyeonggiArea[i].setFont(font2);
			gyeonggiArea[i].addActionListener(new Reservation_country_Event());
			areaPanel.add(gyeonggiArea[i]);
		}

		// 시간표
		timePanel.setBounds(PaddingLeft + 900, PaddingTop, 550, 600);
		timePanel.setOpaque(true);
		timePanel.setBackground(Color.WHITE);
		timePanel.setLayout(null);
		panel.add(timePanel);
		//-----------------------------------------------------------------
		// 날짜, 요일 마우스 위로 가져다 대면 연도 출력
		for (int i = 0; i < yearMonthTable.length; i++) {
			yearMonthTable[i] = new JLabel("XXXX년" + "XX월");
			yearMonthTable[i].setBounds(0 + (i * (500 / 7)), 0, 125, 50);
			yearMonthTable[i].setVisible(false);
			yearMonthTable[i].setOpaque(true);
			yearMonthTable[i].setFont(namefont);
			yearMonthTable[i].setHorizontalAlignment(JLabel.CENTER);
			yearMonthTable[i].setForeground(Color.WHITE);
			yearMonthTable[i].setBackground(purple);
			timePanel.add(yearMonthTable[i]);
		}

		// 날짜
		for (int i = 0; i < dayAndDayofTable.length; i++) {
			switch (dayofweek) {
			case 1:
				weeksName = "일";
				break;
			case 2:
				weeksName = "월";
				break;
			case 3:
				weeksName = "화";
				break;
			case 4:
				weeksName = "수";
				break;
			case 5:
				weeksName = "목";
				break;
			case 6:
				weeksName = "금";
				break;
			case 7:
				weeksName = "토";
				break;
			}
			dayAndDayofTable[i] = new JKeyButton(day + "*" + weeksName);
			dayAndDayofTable[i].setYear(year);
			dayAndDayofTable[i].setMonth(month);
			dayAndDayofTable[i].setDay(day);
			dayAndDayofTable[i].setDayofweek(dayofweek);
			dayAndDayofTable[i].setBounds(0 + (i * (500 / 7)), 50, (500 / 7), (450 / 8));
			dayAndDayofTable[i].setOpaque(true);
			dayAndDayofTable[i].setBorder(new LineBorder(purple, 1));
			dayAndDayofTable[i].setBackground(Color.WHITE);
			dayAndDayofTable[i].setFont(boldfont);
			dayAndDayofTable[i].addActionListener(new Movie_day_Event());
			dayAndDayofTable[i].addMouseListener(new EventAdaptor());
			timePanel.add(dayAndDayofTable[i]);
			dayofweek += 1;
			day += 1;
			if (day > endDate) {
				day = 1;
				month += 1;
				if (month > 11) {
					month = 0;
					year += 1;
				}
			}
			if (dayofweek >= 8) {
				dayofweek = 1;
			}
		}
		//-------------------
		
		schedulePanel.setBounds(0, 150, 500, 400);
		schedulePanel.setBackground(Color.WHITE);
		schedulePanel.setLayout(null);
		schedulePanel.setPreferredSize(size);

		noSchedule.setIcon(imgNoSchedule);
		noSchedule.setBounds(0, 0, 500, 500);
		noSchedule.setVisible(false);
		noSchedule.setOpaque(true);
		noSchedule.setBackground(Color.WHITE);
		schedulePanel.add(noSchedule);

		TimescrollPanel = new JScrollPane(schedulePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		TimescrollPanel.setBounds(0, 125, 525, 450);
		TimescrollPanel.setBorder(new LineBorder(purple, 1));
		timePanel.add(TimescrollPanel);

		// 영화 시간표
		for (int i = 0; i < 4; i++) {
			content[i] = new JKeyButton();
			content[i].setOpaque(true);
			content[i].setBackground(Color.WHITE);
			content[i].setFont(namefont);
			content[i].setVisible(false);
			content[i].addActionListener(new MovieTime_Event());
			content[i].setBounds(0, 0 + (i * 125), 520, 125);
			schedulePanel.add(content[i]);
		}

		// Panel
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, (int) (Main.SCREEN_HEIGHT * 0.25), Main.SCREEN_WIDTH, (int) (Main.SCREEN_HEIGHT * 0.75));
		panel.setLayout(null);
		add(panel);

	}

	// 지역버튼
	class Reservation_area_Event implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == SEOUL) {
				for (int i = 0; i < seoulArea.length; i++) {
					SEOUL.setBackground(new Color(82, 12, 139));
					SEOUL.setForeground(Color.WHITE);
					GYEONGGI.setOpaque(true);
					GYEONGGI.setBackground(Color.WHITE);
					GYEONGGI.setForeground(Color.BLACK);
					seoulArea[i].setVisible(true);
					gyeonggiArea[i].setVisible(false);
					gyeonggiArea[i].setBackground(Color.WHITE);
					gyeonggiArea[i].setForeground(Color.BLACK);
					areaKey = "서울";
				}
			} else if (e.getSource() == GYEONGGI) {
				for (int i = 0; i < gyeonggiArea.length; i++) {
					GYEONGGI.setBackground(new Color(82, 12, 139));
					GYEONGGI.setForeground(Color.WHITE);
					SEOUL.setOpaque(true);
					SEOUL.setBackground(Color.WHITE);
					SEOUL.setForeground(Color.BLACK);
					seoulArea[i].setVisible(false);
					gyeonggiArea[i].setVisible(true);
					seoulArea[i].setBackground(Color.WHITE);
					seoulArea[i].setForeground(Color.BLACK);
					areaKey = "경기";
				}
			}

		}
	}

	// 영화 날짜 버튼
	class Movie_day_Event implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			for (int i = 0; i < dayAndDayofTable.length; i++) {
				dayAndDayofTable[i].setBackground(Color.WHITE);
				dayAndDayofTable[i].setForeground(Color.BLACK);
				if (e.getSource() == dayAndDayofTable[i]) {
					isCheckButton = true;
					dayAndDayofTable[i].setBackground(purple);
					dayAndDayofTable[i].setForeground(Color.WHITE);
					checkYear = dayAndDayofTable[i].getYear();
					checkMonth = dayAndDayofTable[i].getMonth();
					checkDay = dayAndDayofTable[i].getDay();
					checkDayofweek = dayAndDayofTable[i].getDayofweek();
				}
			}
			// 초기화
			for (int i = 0; i < 4; i++) {
				content[i].setEnabled(true);
				content[i].setVisible(false);
				content[i].setBackground(Color.WHITE);	
			}
			isReset = true;
		}

	}

	// 영화 시간표 버튼
	class MovieTime_Event implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (user.getUserID() != null) {
				for(int i=0; i<content.length; i++) {
					if(e.getSource() == content[i]) {
						System.out.println("버튼: "+content[i].getMovieArea().get_key());
						new MovieSitPage(user,content[i].getMovieArea());// 유저 정보와 영화 정보들 넘기기
						dispose();
					}
					
				}
				
			} else {
				JOptionPane message = new JOptionPane();// 메시지 박스 객체
				message.showMessageDialog(null, "로그인해주세요");
				new DOKPage(user);
				dispose();
			}

		}

	}

	// area 버튼
	class Reservation_country_Event implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			JKeyButton btn = (JKeyButton) e.getSource();
			countryKey = btn.getText();
			theaterKey = btn.getTheaterKey();

			for (int i = 0; i < 4; i++) {
				content[i].setVisible(false);
				content[i].setBackground(Color.WHITE);
			}
			

			// 서울 지역들
			for (int i = 0; i < seoulArea.length; i++) {
				seoulArea[i].setBackground(Color.WHITE);
				seoulArea[i].setForeground(Color.BLACK);
				isNoSchedule = false;
				if (e.getSource() == seoulArea[i]) {
					seoulArea[i].setBackground(new Color(82, 12, 139));
					seoulArea[i].setForeground(Color.WHITE);

				}
			}

			// 경기도 지역들
			for (int i = 0; i < gyeonggiArea.length; i++) {
				gyeonggiArea[i].setBackground(Color.WHITE);
				gyeonggiArea[i].setForeground(Color.BLACK);
				if (e.getSource() == gyeonggiArea[i]) {
					gyeonggiArea[i].setBackground(new Color(82, 12, 139));
					gyeonggiArea[i].setForeground(Color.WHITE);
					if (e.getSource() == gyeonggiArea[2]) {
						isNoSchedule = true;
					}
				}
				
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JKeyButton m = (JKeyButton) e.getSource();
		movieName = m.getText();
		movieKey = m.getMovieKey();// 영화 프라이머리_key가져오기

		for (int i = 0; i < movieNum; i++) {
			btn_movie[i].setBackground(Color.WHITE);
			btn_movie[i].setForeground(Color.BLACK);
			if (m == btn_movie[i]) {
				btn_movie[i].setBackground(new Color(82, 12, 139));
				btn_movie[i].setForeground(Color.WHITE);
			}
		}
	}

	class EventAdaptor extends MouseAdapter {
		@Override // 마우스가 들어가면
		public void mouseEntered(MouseEvent e) {
			for (int i = 0; i < dayAndDayofTable.length; i++) {
				if (e.getSource() == dayAndDayofTable[i]) {
					yearMonthTable[i].setVisible(true);
					int year = dayAndDayofTable[i].getYear();
					int month = dayAndDayofTable[i].getMonth();
					yearMonthTable[i].setText(year + "년  " + (month + 1) + "월");
				}
			}
		}

		@Override // 마우스가 나가지면
		public void mouseExited(MouseEvent e) {
			for (int i = 0; i < dayAndDayofTable.length; i++) {
				if (e.getSource() == dayAndDayofTable[i]) {
					yearMonthTable[i].setVisible(false);
					break;
				}
			}
		}
	}

	@Override
	public void run() {
		
		
		  int se = 48; int m = 29; int h = 22;
		 

		while (true) {// 무한반복	
			Date currentDate = new Date();
			
			// 동작확인 코드
			
			  currentDate.setMonth(10); currentDate.setDate(23); currentDate.setHours(h);
			  currentDate.setMinutes(m); currentDate.setSeconds(se);
			 
			 
			Calendar cal = Calendar.getInstance();//현재 날짜
			dayofweek = cal.get(Calendar.DAY_OF_WEEK);//오늘 요일
			try {	
				/*
				 * se++; System.out.println(se); System.out.println(currentDate);
				 * Thread.sleep(1000); if(se>60) { m+=1; }
				 */
				//System.out.println(currentDate);
				if(isCheckButton && isNoSchedule && movieAreas.size()<=0) {//데이터가 없다면
					for(int i=0; i<content.length; i++) {
						content[i].setVisible(false);
					}
					noSchedule.setVisible(true);
				}
				if(isReset) {
					movieAreas = moviearea_connect.getMovieArea(movieKey, theaterKey, checkDayofweek % 7);//영화 데이터 받기	
					for (int i = 0; i < movieAreas.size(); i++) {	
						if(isCheckButton && isNoSchedule==false) {
							noSchedule.setVisible(false);
							content[i].setText("<html>시작시간 : " + movieAreas.get(i).getStartTime() + "<br>제목 : "
									+ movieName + escape1 + movieAreas.get(i).getHall() + escape2 + "남은 자리 :"
									+ movieAreas.get(i).getVacantSeat() + "/" + "216</html>");
							content[i].setBackground(Color.WHITE);
							content[i].setHorizontalAlignment(JButton.LEFT);
							content[i].setBorder(new LineBorder(purple, 1));
							content[i].setMovieArea(movieAreas.get(i));// moviearea의 key
							content[i].setVisible(true);
							if(movieAreas.get(i).getVacantSeat()<=0) {//비어있는좌석 수가 없다면
								content[i].setEnabled(false);
							}
						}
						str = checkYear+"-"+(checkMonth+1)+"-"+checkDay+" "+movieAreas.get(i).getStartTime();//1월이 0부터 시작
						Date movieDate = sdf.parse(str);
						if(movieDate.compareTo(currentDate)<0) {//상영영화가 현재 시간보다 작으면	
							content[i].setEnabled(false);//샹엉 불가능
						}
					}
				}isReset = false;
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
	}

	public void IsnoScheduleVisible() {
		if(isCheckButton && isNoSchedule && movieAreas.size()<=0) {//데이터가 없다면
			for(int i=0; i<content.length; i++) {
				content[i].setVisible(false);
			}
			noSchedule.setVisible(true);
		}
	}
	
	public void movieAreaContent(Date currentDate) throws ParseException {
		if(isReset) {//초기화 되었을떄만 그리기
			movieAreas = moviearea_connect.getMovieArea(movieKey, theaterKey, checkDayofweek % 7);//영화 데이터 받기	
			for (int i = 0; i < movieAreas.size(); i++) {	
				if(isCheckButton && isNoSchedule==false) {
					noSchedule.setVisible(false);
					content[i].setText("<html>시작시간 : " + movieAreas.get(i).getStartTime() + "<br>제목 : "
							+ movieName + escape1 + movieAreas.get(i).getHall() + escape2 + "남은 자리 :"
							+ movieAreas.get(i).getVacantSeat() + "/" + "216</html>");
					content[i].setBackground(Color.WHITE);
					content[i].setHorizontalAlignment(JButton.LEFT);
					content[i].setBorder(new LineBorder(purple, 1));
					content[i].setMovieArea(movieAreas.get(i));// moviearea의 key
					content[i].setVisible(true);
					if(movieAreas.get(i).getVacantSeat()<=0) {//비어있는좌석 수가 없다면
						content[i].setEnabled(false);
					}
				}
				str = checkYear+"-"+(checkMonth+1)+"-"+checkDay+" "+movieAreas.get(i).getStartTime();//1월이 0부터 시작
				Date movieDate = sdf.parse(str);
				if(movieDate.compareTo(currentDate)<0) {//상영영화가 현재 시간보다 작으면	
					content[i].setEnabled(false);//샹엉 불가능
				}
			}
		}isReset= false;//다 그린후에는 다시 false
		
	}
	
	public String getDayOfweek(int dayofweek) {
		switch (dayofweek) {
		case 1:
			weeksName = "일";
			break;
		case 2:
			weeksName = "월";
			break;
		case 3:
			weeksName = "화";
			break;
		case 4:
			weeksName = "수";
			break;
		case 5:
			weeksName = "목";
			break;
		case 6:
			weeksName = "금";
			break;
		case 7:
			weeksName = "토";
			break;
		}
		return weeksName;
	}
}

class JKeyButton extends JButton {

	private int theaterKey;
	private int movieKey;

	private int year;
	private int month;
	private int day;
	private int dayofweek;
	
	private MovieArea movieArea;

	public MovieArea getMovieArea() {
		return movieArea;
	}

	public void setMovieArea(MovieArea movieArea) {
		this.movieArea = movieArea;
	}

	public JKeyButton() {

	}

	public JKeyButton(String str) {
		super(str);
	}

	public int getMovieKey() {
		return movieKey;
	}

	public void setMovieKey(int movieKey) {
		this.movieKey = movieKey;
	}

	public int getTheaterKey() {
		return theaterKey;
	}

	public void setTheaterKey(int theaterKey) {
		this.theaterKey = theaterKey;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getDayofweek() {
		return dayofweek;
	}

	public void setDayofweek(int dayofweek) {
		this.dayofweek = dayofweek;
	}

}