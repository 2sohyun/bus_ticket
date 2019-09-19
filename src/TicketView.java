import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class TicketView extends JFrame {
   Container contentPane;
   JPanel barPanel, mainPanel;
   JLabel lineLabel, mainImgLabel, textLabel, textLabel2, clockLabel, myLabel;
   JLabel departtextLabel, timeLabel, hour_dayLabel, minuteLabel, nowLabel, lateLabel;
   JLabel text1, text2, text3, text4;
   JLabel terminals, booktime, seat, bookgrade;
   
   String id = Login.user; // 현재 사용자의 ID를 받아옴
   
   Connection conn = null;
   PreparedStatement pstm = null;
   ResultSet rs = null;
   Statement stmt = null;

   
   String bookdeparture = TicketCheck.bookdeparture;
   String bookdestination = TicketCheck.bookdestination;
   String bookticket = TicketCheck.ticket;
   
   String beforehour, beforeminute, beforeday;
  

   JTextField numText;
   JButton bookOk;
   
   
   JButton menuBtn1, menuBtn2, menuBtn3, menuBtn4, menuBtn5;
   
   //메뉴바 5개 이미지▼
   ImageIcon icon1 = new ImageIcon("images/home.png");
   ImageIcon icon2 = new ImageIcon("images/book.png");
   ImageIcon icon3 = new ImageIcon("images/ticket.png");
   ImageIcon icon4 = new ImageIcon("images/terminal.png");
   ImageIcon icon5 = new ImageIcon("images/notice.png");
   ImageIcon icon6 = new ImageIcon("images/line.png");
   

   ImageIcon mainIcon = new ImageIcon("images/gogo.png");
   ImageIcon myIcon = new ImageIcon("images/myIcon.png");
  // ImageIcon clockIcon = new ImageIcon("images/clock.png");
   
   TicketView() {
      setTitle("고속버스 예매 시스템");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      contentPane = getContentPane();
      contentPane.setLayout(new BorderLayout());      
   
      barPanel = new JPanel();
      barPanel.setBackground(Color.WHITE);
      
      
      /* 메뉴바 5개 구성 ▼ */
      Image img1 = icon1.getImage();
      Image changedImg1 = img1.getScaledInstance(100,120,Image.SCALE_SMOOTH);   
      ImageIcon icon1 = new ImageIcon(changedImg1);

      menuBtn1 = new JButton(icon1);
      menuBtn1.setBackground(Color.white);
      menuBtn1.setBorderPainted(false); //버튼 테두리 해제
      menuBtn1.setPreferredSize(new Dimension(100, 120));
      barPanel.add(menuBtn1);
      menuBtn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new Main();
				dispose(); // 해당 프레임만 사라짐
			}
      });
      
      Image img2 = icon2.getImage();
      Image changedImg2 = img2.getScaledInstance(160,120,Image.SCALE_SMOOTH);   
      // 롤오버 했을 때 아이콘 menuBtn1.setRolloverIcon(이미지);
      ImageIcon icon2 = new ImageIcon(changedImg2);
      menuBtn2 = new JButton(icon2);
      menuBtn2.setBackground(Color.white);
      menuBtn2.setBorderPainted(false); //버튼 테두리 해제
      menuBtn2.setPreferredSize(new Dimension(160, 120));
      barPanel.add(menuBtn2);
      menuBtn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new Search();
				dispose(); // 해당 프레임만 사라짐
			}
      });
      
      Image img3 = icon3.getImage();
      Image changedImg3 = img3.getScaledInstance(120,120,Image.SCALE_SMOOTH);   
      // 롤오버 했을 때 아이콘 menuBtn1.setRolloverIcon(이미지);
      ImageIcon icon3 = new ImageIcon(changedImg3);
      menuBtn3 = new JButton(icon3);
      menuBtn3.setBackground(Color.white);
      menuBtn3.setBorderPainted(false); //버튼 테두리 해제
      menuBtn3.setPreferredSize(new Dimension(120, 120));
      barPanel.add(menuBtn3);
      menuBtn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new TicketCheck();
				dispose(); // 해당 프레임만 사라짐
			}
      });
      
      Image img4 = icon4.getImage();
      Image changedImg4 = img4.getScaledInstance(120,120,Image.SCALE_SMOOTH);   
      // 롤오버 했을 때 아이콘 menuBtn1.setRolloverIcon(이미지);
      ImageIcon icon4 = new ImageIcon(changedImg4);
      menuBtn4 = new JButton(icon4);
      menuBtn4.setBackground(Color.white);
      menuBtn4.setBorderPainted(false); //버튼 테두리 해제
      menuBtn4.setPreferredSize(new Dimension(120, 120));
      barPanel.add(menuBtn4);
      menuBtn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new Terminal();
				dispose(); // 해당 프레임만 사라짐
			}
      });
      
      Image img5 = icon5.getImage();
      Image changedImg5 = img5.getScaledInstance(120,120,Image.SCALE_SMOOTH);   
      // 롤오버 했을 때 아이콘 menuBtn1.setRolloverIcon(이미지);
      ImageIcon icon5 = new ImageIcon(changedImg5);
      menuBtn5 = new JButton(icon5);
      menuBtn5.setBackground(Color.white);
      menuBtn5.setBorderPainted(false); //버튼 테두리 해제
      menuBtn5.setPreferredSize(new Dimension(120, 120));
      barPanel.add(menuBtn5);
      menuBtn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new Notice();
				dispose(); // 해당 프레임만 사라짐
			}
      });
      
      contentPane.add(barPanel,BorderLayout.NORTH);


      /* 메인(예매확인) 패널 */
      mainPanel = new JPanel();
      mainPanel.setLayout(new BorderLayout());
      Color color = new Color(250, 210, 200);
      mainPanel.setBackground(color);     
      
      
      lineLabel = new JLabel(icon6); // 메뉴바랑 메인 사이에 줄
      mainPanel.add(lineLabel, BorderLayout.NORTH);
    
      Image mainImg = mainIcon.getImage();
      Image changedMainImg = mainImg.getScaledInstance(680,250,Image.SCALE_SMOOTH);   
      // 롤오버 했을 때 아이콘 menuBtn1.setRolloverIcon(이미지);
      ImageIcon mainIcon = new ImageIcon(changedMainImg);
      
      
      JPanel centerPanel = new JPanel();
      centerPanel.setLayout(null);
      centerPanel.setBackground(color); 
      
      //출발 글자 라벨
      departtextLabel = new JLabel("출발");
      departtextLabel.setFont(new Font("a시월구일3", Font.PLAIN, 40));
      departtextLabel.setSize(80,80);
      departtextLabel.setLocation(180,40);
      departtextLabel.setVerticalAlignment(SwingConstants.CENTER);
      departtextLabel.setHorizontalAlignment(SwingConstants.CENTER);
      
      // 시간 표시!!
      timeLabel = new JLabel();
      timeLabel.setFont(new Font("a시월구일3", Font.PLAIN, 40));
      timeLabel.setSize(80,80);
      timeLabel.setLocation(290,40);
      timeLabel.setVerticalAlignment(SwingConstants.CENTER);
      timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
      
      // '시'(& '일') 표시!
      hour_dayLabel = new JLabel(" 시간 전!");
      hour_dayLabel.setFont(new Font("a시월구일3", Font.PLAIN, 40));
      hour_dayLabel.setSize(200,80);
      hour_dayLabel.setLocation(310,40);
      hour_dayLabel.setVerticalAlignment(SwingConstants.CENTER);
      hour_dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
      
      // '분' 표시!
      minuteLabel = new JLabel(" 분 전!");
      minuteLabel.setFont(new Font("a시월구일3", Font.PLAIN, 40));
      minuteLabel.setSize(200,80);
      minuteLabel.setLocation(310,40);
      minuteLabel.setVerticalAlignment(SwingConstants.CENTER);
      minuteLabel.setHorizontalAlignment(SwingConstants.CENTER);
      minuteLabel.setVisible(false);
      
      //출발 시간일 경우!!
      nowLabel = new JLabel("출발시간입니다!!");
      nowLabel.setSize(450,80);
      nowLabel.setLocation(110,40);
      nowLabel.setFont(new Font("a시월구일3", Font.PLAIN, 40));
      nowLabel.setVerticalAlignment(SwingConstants.CENTER);
      nowLabel.setHorizontalAlignment(SwingConstants.CENTER);
      nowLabel.setVisible(false);
      
      //출발 시간이 지났을 경우!!
      lateLabel = new JLabel("출발시간이 지났습니다.");
      lateLabel.setSize(450,80);
      lateLabel.setLocation(120,40);
      lateLabel.setFont(new Font("a시월구일3", Font.PLAIN, 40));
      lateLabel.setVerticalAlignment(SwingConstants.CENTER);
      lateLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lateLabel.setVisible(false);
      
      //발권 가능할 때
      textLabel = new JLabel("현장 발권은 5분 전에 완료해 주세요.");
      textLabel.setFont(new Font("a시월구일2", Font.PLAIN, 20));
      textLabel.setSize(600,80);
      textLabel.setLocation(170,90);
      
      //발권 불가능할 때
      textLabel2 = new JLabel("발권이 불가합니다.");
      textLabel2.setFont(new Font("a시월구일2", Font.PLAIN, 20));
      textLabel2.setSize(400,80);
      textLabel2.setLocation(245,90);
      textLabel2.setVisible(false);
      
      //출발&도착 터미널
      terminals = new JLabel("출발&도착");
      terminals.setFont(new Font("a시월구일2", Font.PLAIN, 20));
      terminals.setSize(100,80);
      terminals.setLocation(270,180);
      terminals.setVisible(true);
      
      //출발 날짜 및 시간
      booktime = new JLabel("출발 날짜 &시간");
      booktime.setFont(new Font("a시월구일2", Font.PLAIN, 20));
      booktime.setSize(200,80);
      booktime.setLocation(270,265);
      booktime.setVisible(true);
      
      //등급
      bookgrade = new JLabel("등급");
      bookgrade.setFont(new Font("a시월구일2", Font.PLAIN, 20));
      bookgrade.setSize(100,80);
      bookgrade.setLocation(270,355);
      bookgrade.setVisible(true);
      
      //인원 수
      seat = new JLabel("좌석 번호");
      seat.setFont(new Font("a시월구일2", Font.PLAIN, 17));
      seat.setSize(500,80);
      seat.setLocation(270,445);
      seat.setVisible(true);

      
      Calendar c = Calendar.getInstance();
      int hour = c.get(Calendar.HOUR_OF_DAY);
      int minute = c.get(Calendar.MINUTE);
      int day = c.get(Calendar.DAY_OF_MONTH);
      
      /*
      int year = c.get(Calendar.YEAR);
      int month = c.get(Calendar.MONTH)+1;
      int date1 = c.get(Calendar.DAY_OF_MONTH);
      String nowtime = year+"년 "+month+"월 "+date1+"일 ";
      System.out.println(nowtime); */
            
      try {
          //후에 조회가 되면 고칠 부분!!!
       /*   String quary = "SELECT * FROM book WHERE memberid='"+id+"' AND departure='"+bookdeparture+"' AND destination='"+bookdestination
        		  +"' AND date='"+bookdate+"' AND time= '"+book_time+"' AND grade='"+grade+"'"; */
          String quary = "SELECT * FROM book WHERE memberid='"+id+"' AND departure='"+bookdeparture+"' AND destination='"
        		  +bookdestination+"' AND ticketnumber='"+bookticket+"'"; 
          conn = DBConnection.getConnection();
          pstm = conn.prepareStatement(quary);
          rs = pstm.executeQuery();
          
          if(rs.next()) {
        	  
        	  String book_bus = rs.getString("departure")+" / "+rs.getString("destination"); //출발 / 도착 터미널
        	  terminals.setText(book_bus);
        	  
        	  String start_time = rs.getString("date")+" "+rs.getString("time"); // 출발 날짜 & 시간
        	  booktime.setText(start_time);
        	  
        	  String book_grade = rs.getString("grade"); //등급
        	  bookgrade.setText(book_grade);
        	  
        	  String book_seat=" "; // 예약 좌석
        	  
              if(rs.getInt("seat1")==0) {
            	  book_seat = book_seat.concat("N1번");
              }
              if(rs.getInt("seat2")==0) {
                 if(book_seat!=" ") {
                	 book_seat = book_seat.concat(", ");
                 }
                 book_seat = book_seat.concat("N2번");
              }
              if(rs.getInt("seat3")==0) {
                 if(book_seat!=" ") {
                	 book_seat = book_seat.concat(", ");
                 }
                 book_seat = book_seat.concat("N3번");
              }
              if(rs.getInt("seat4")==0) {
                  if(book_seat!=" ") {
                	  book_seat = book_seat.concat(", ");
                    }
                  book_seat =  book_seat.concat("N4번");
              }
              if(rs.getInt("seat5")==0) {
            	  if(book_seat!=" ") {
            		  book_seat = book_seat.concat(", ");
                    }
            	  book_seat = book_seat.concat("N5번");
              }
              if(rs.getInt("seat6")==0) {
            	  if(book_seat!=" ") {
            		  book_seat = book_seat.concat(", ");
                    }
            	  book_seat = book_seat.concat("N6번");
              }
              if(rs.getInt("seat7")==0) {
                  if(book_seat!=" ") {
                	  book_seat = book_seat.concat(", ");
                    }
                  book_seat = book_seat.concat("N7번");
              }
              if(rs.getInt("seat8")==0) {
                  if(book_seat!=" ") {
                	  book_seat = book_seat.concat(", ");
                    }
                  book_seat = book_seat.concat("N8번");
              }
              
        	  seat.setText(book_seat); 
        	  
              String date = rs.getString("date"); //날짜
        	  String dd = date.substring(11,13); // 출발 날짜 중 '일'부분
        	  int date_day = Integer.parseInt(dd)-day;
        	  beforeday = Integer.toString(date_day);
        	  
        	  
        	  String bt = rs.getString("time");
        	  String book_hour = bt.substring(0, 2); //출발 시간 '시'부분
        	  String book_minute = bt.substring(5,7); //출발 시간 '분'부분
        	  
        	  int bh = Integer.parseInt(book_hour)-hour;
        	  int bm = Integer.parseInt(book_minute)-minute;
        	  
        	  
        	  if((bh>1)||(bh==1&&bm>0)||(bh==1&&bm==0)) {
        		  beforehour = Integer.toString(bh); // 이 if문은 출발 1시간 전 이상
        		  
        	  }else if(bh==1&&bm<0) { // 출발시간까지 1시간도 남지 않은 상황
        		  int bmm = (Integer.parseInt(book_minute)+60)-minute;
        		  beforeminute = Integer.toString(bmm); // 현재 분>출발 시각 분
        		  
        	  }else if(bh==0&&bm>0) {
        		  beforeminute = Integer.toString(bm); //현재 시간 정각인 경우 (현재 분<출발 시각 분)
        	  }
        	  
        	  
        	  if(date_day>0) {
        		  timeLabel.setText(beforeday);
        		  hour_dayLabel.setText("일 전!");
        		  hour_dayLabel.setVisible(true);
        		  minuteLabel.setVisible(false);
        		  
        	  }else if(date_day==0) {
            	  if((bh>1)||(bh==1&&bm>0)||(bh==1&&bm==0)) {
            		  // 출발 몇 시간 전(1시간 이상 전)
            		  timeLabel.setText(beforehour);
            		  hour_dayLabel.setVisible(true);
            		  minuteLabel.setVisible(false);
            		  
            	  }else if((bh==1&&bm<0)||(bh==0&&bm>0)) {
            		  // 출발 몇 분 전(60분 미만으로 남았을 경우)
            		  timeLabel.setText(beforeminute);
            		  hour_dayLabel.setVisible(false);
            		  minuteLabel.setVisible(true);
            		  
            		  if((bh==0)&&(bm>5)&&(bm<=15)) {
            			  textLabel.setForeground(Color.RED);
            			  
            		  }else if((bh==0)&&(bm>0)&&(bm<=5)) {
            			  textLabel.setText("버스가 곧 출발합니다.");
            			  textLabel.setLocation(220,90);
            			  textLabel.setForeground(Color.RED);
            		  }
            		  
            	  }else if(bh==0&&bm==0) {
            		  //출발시간일 경우
            		  departtextLabel.setVisible(false);
            		  timeLabel.setVisible(false);
            		  hour_dayLabel.setVisible(false);
            		  minuteLabel.setVisible(false);
            		  textLabel.setVisible(false);
            		  nowLabel.setVisible(true);
            		  textLabel2.setVisible(true);
            		  textLabel2.setText("탑승하십시요!");
            		  textLabel2.setLocation(255,90);
            		          		  
            	  }else {
            		  //출발시간이 지났을 경우
            		  departtextLabel.setVisible(false);
            		  timeLabel.setVisible(false);
            		  hour_dayLabel.setVisible(false);
            		  minuteLabel.setVisible(false);
            		  lateLabel.setVisible(true);
            		  textLabel.setVisible(false);
            		  textLabel2.setVisible(true);
            	  }
        	  } else {
        		//출발시간이 지났을 경우
        		  departtextLabel.setVisible(false);
        		  timeLabel.setVisible(false);
        		  hour_dayLabel.setVisible(false);
        		  minuteLabel.setVisible(false);
        		  lateLabel.setVisible(true);
        		  textLabel.setVisible(false);
        		  textLabel2.setVisible(true);
        	   }
        	
        	}

      } catch (SQLException sqle) {
   	  sqle.printStackTrace();
      }

      
      Image myIconImg = myIcon.getImage();
      Image changedmyImg = myIconImg.getScaledInstance(90,350,Image.SCALE_SMOOTH);   
      ImageIcon myIcon = new ImageIcon(changedmyImg);
      myLabel = new JLabel(myIcon);
      myLabel.setSize(220,500);
      myLabel.setLocation(120,80); 
  
      text1 = new JLabel("출발/도착 터미널");
      text1.setFont(new Font("a시월구일2", Font.PLAIN, 17));
      text1.setForeground(Color.GRAY);
      text1.setSize(150,50);
      text1.setLocation(270,165);
     
      text2 = new JLabel("출발 날짜 및 시간");
      text2.setFont(new Font("a시월구일2", Font.PLAIN, 17));
      text2.setForeground(Color.GRAY);
      text2.setSize(150,50);
      text2.setLocation(270,250);
      
      text3 = new JLabel("버스 등급");
      text3.setFont(new Font("a시월구일2", Font.PLAIN, 17));
      text3.setForeground(Color.GRAY);
      text3.setSize(150,50);
      text3.setLocation(270,335);
      
      text4 = new JLabel("좌석 번호");
      text4.setFont(new Font("a시월구일2", Font.PLAIN, 17));
      text4.setForeground(Color.GRAY);
      text4.setSize(150,50);
      text4.setLocation(270,425);
      
     // centerPanel.add(clockLabel);
      centerPanel.add(departtextLabel);
      centerPanel.add(timeLabel);
      centerPanel.add(hour_dayLabel);
      centerPanel.add(minuteLabel);
      centerPanel.add(nowLabel);
      centerPanel.add(lateLabel);
      centerPanel.add(textLabel);
      centerPanel.add(textLabel2);
      centerPanel.add(myLabel);
      centerPanel.add(text1);
      centerPanel.add(text2);
      centerPanel.add(text3);
      centerPanel.add(text4); 
      centerPanel.add(terminals);
      centerPanel.add(booktime); 
      centerPanel.add(bookgrade);
      centerPanel.add(seat); 
      
      
      mainPanel.add(centerPanel, BorderLayout.CENTER);
      contentPane.add(mainPanel);
      
   setSize(680, 700);
   setVisible(true);
   setResizable(false);
   setLocationRelativeTo(null);

   }
   
   
   public static void main(String[] args) {
      new TicketView();
   }
}
