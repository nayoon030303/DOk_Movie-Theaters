package page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Movie.DB_MovieArea;
import Movie.MovieArea;
import User.User;
import ticket.DB_ticket;
import ticket.Ticket;

class Timer2 extends Thread{
   private JFrame frame;
   private JLabel timerLabel;
   private int n;
   private User user;
   private MovieArea movieArea;
   private Ticket ticket;
   private int num_adult,num_teen, num_kids;
   
   public Timer2(JFrame frame,JLabel timerLabel, User user, MovieArea movieArea,Ticket ticket, int num_adult, int num_teen, int num_kids) {
      this.frame = frame;
      this.timerLabel = timerLabel;
      this.user = user;
      this.movieArea = movieArea;
      this.ticket = ticket;
      this.num_adult = num_adult;
      this.num_teen = num_teen;
      this.num_kids = num_kids;
   }
   
   @Override
   public void run() {
      // TODO Auto-generated method stub
      n=5; 
      while(!this.isInterrupted()) {
         n--;
         try {
            if(n<0) {
               System.out.println("완료");
               frame.dispose();
               
               new ReservationCheckPage(user,movieArea,ticket, num_adult, num_teen, num_kids);
               interrupt();
               
            }
            sleep(1000); // 1/1000초 단위
         }catch (InterruptedException e) {
            // TODO: handle exception
            return;
         }
      }
   }
   
}

public class PayTimer extends JFrame {
   private JPanel panel = new JPanel();
   private JLabel label1 = new JLabel("결제가 ");
   private JLabel label2 = new JLabel("진행 중입니다.");
   private JLabel label3 = new JLabel("잠시만 기다려주세요.");
   
   private ImageIcon imgLoading = new ImageIcon("src/img/loading250.gif");
   private JLabel iconLoading = new JLabel(imgLoading);
   //private ImageIcon imgLoadingimg;
   
   private int WIDTH = 500;
   private int HEIGHT = 400;
   
   //DB
   private DB_ticket connect_ticket = new DB_ticket();
   private DB_MovieArea connect_movieArea = new DB_MovieArea();
   
   //Design
   private Font font1 = new Font("나눔바른고딕", Font.BOLD, 35); 
   private Font font2 = new Font("나눔바른고딕", Font.PLAIN, 20);
   private Font font3 = new Font("휴먼둥근헤드라인", Font.PLAIN, 35);
   
   public PayTimer(User user, MovieArea movieArea,Ticket ticket, int num_adult, int num_teen, int num_kids) {
      setTitle("결제중입니다");
      setSize(WIDTH,HEIGHT);
      setResizable(false);
      setVisible(true);
      //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //X를 누르면 창을 닫느다.
      Container c = getContentPane();   //패널 생성
      //c.setLayout(null);   //플로우 레이아웃
      panel.setBackground(Color.WHITE);
      
      
      iconLoading.setOpaque(true);
      iconLoading.setBackground(Color.WHITE);
      iconLoading.setBounds(0,20,WIDTH,150);
      iconLoading.setHorizontalAlignment(JLabel.CENTER);
      panel.add(iconLoading);   
   
      
      label1.setFont(font1);
      label1.setBounds(0,170,WIDTH,40);
      label1.setHorizontalAlignment(JLabel.CENTER);
      label1.setOpaque(true); 
      label1.setBackground(Color.WHITE);
      panel.add(label1);
         
      label2.setFont(font1);
      label2.setBounds(0,210,WIDTH,40);
      label2.setHorizontalAlignment(JLabel.CENTER);
      label2.setOpaque(true); 
      label2.setBackground(Color.WHITE);
      panel.add(label2);
         
      label3.setFont(font2);
      label3.setBounds(0,250,WIDTH,50);
      label3.setHorizontalAlignment(JLabel.CENTER);
      label3.setOpaque(true); 
      label3.setBackground(Color.WHITE);
      panel.add(label3);
      
      add(panel);
      panel.setLayout(null);   //플로우 레이아웃
      panel.setBackground(Color.WHITE);
      
      
      System.out.println(ticket.getUserID());
      System.out.println(ticket.getMovieareaKey());
      System.out.println(ticket.getPrice());
      System.out.println(ticket.getSeatCount());
      System.out.println(ticket.getSeatWhere());
      System.out.println(ticket.getYymmdd());
      System.out.println(ticket.getPayHow());
      
      //connect_ticket.addTicket(ticket.getUserID(), ticket.getMovieareaKey(), ticket.getPrice(), ticket.getSeatCount(), ticket.getSeatWhere(),ticket.getYymmdd(), ticket.getPayHow());
      //connect_movieArea.updateMovieArea(movieArea.get_key(),movieArea.getVacantSeat()-ticket.getSeatCount(), movieArea.getSeatState());
      
      Timer2 t2 = new Timer2(this,iconLoading,user,movieArea,ticket,num_adult,num_teen,num_kids);
      t2.start();
      
   }
   public static void main(String[] args) {
      //new PayTimer();
      
   }
}