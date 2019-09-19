import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Seat extends JFrame {
   Container contentPane;
   JPanel barpanel, mainpanel;
   
   JButton menu1, menu2, menu3, menu4, menu5;
   ImageIcon icon1 = new ImageIcon("images/home.png");
   ImageIcon icon2 = new ImageIcon("images/book.png");
   ImageIcon icon3 = new ImageIcon("images/ticket.png");
   ImageIcon icon4 = new ImageIcon("images/terminal.png");
   ImageIcon icon5 = new ImageIcon("images/notice.png");
   JLabel line;
   ImageIcon icon6 = new ImageIcon("images/line.png");
   
   JLabel title, explain, front, back;
   int select1, select2, select3, select4, select5, select6, select7, select8, cnt = 0;
   JRadioButton seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8;
   JLabel seatnum1, seatnum2, seatnum3, seatnum4, seatnum5, seatnum6, seatnum7, seatnum8;
   ImageIcon num1 = new ImageIcon("images/예매가능좌석.png");
   ImageIcon num0 = new ImageIcon("images/예매불가좌석.png");
   JButton page, finish;
   
   Connection conn = null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   
   String ticketnumber;
   String id = Login.user;
   String departure = Search.departureresult;
   String destination = Search.destinationresult;
   String date = Search.dateresult;
   String ampm = Search.timeresult;
   String time = SearchResult.option;
   String grade = Search.grade;
   String adultfare = SearchResult.adultfare;
   String childfare = SearchResult.childfare;
   int adult = Search.people1;
   int child = Search.people2;
   int adultchlid = Search.adultchild;
   
   Seat() {      
      setTitle("고속버스 예매 시스템");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      contentPane = getContentPane();
      contentPane.setLayout(new BorderLayout());

      // 여기부터 메뉴 바
      barpanel = new JPanel();
      barpanel.setBackground(Color.WHITE);
      
      // 홈
      Image img1 = icon1.getImage();
      Image changedImg1 = img1.getScaledInstance(100, 120, Image.SCALE_SMOOTH);
      ImageIcon icon1 = new ImageIcon(changedImg1);
      
      menu1 = new JButton(icon1);
      menu1.setBackground(Color.WHITE);
      menu1.setBorderPainted(false);
      menu1.setPreferredSize(new Dimension(100, 120));
      barpanel.add(menu1);
      
      menu1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new Main();
            dispose();
      }});
      
      // 조회예매
      Image img2 = icon2.getImage();
      Image changedImg2 = img2.getScaledInstance(130, 120, Image.SCALE_SMOOTH);
      ImageIcon icon2 = new ImageIcon(changedImg2);
      
      menu2 = new JButton(icon2);
      menu2.setBackground(Color.WHITE);
      menu2.setBorderPainted(false);
      menu2.setPreferredSize(new Dimension(130, 120));
      barpanel.add(menu2);
      
      menu2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new Search();
            dispose();
      }});
      
      // 확인취소
      Image img3 = icon3.getImage();
      Image changedImg3 = img3.getScaledInstance(130, 120, Image.SCALE_SMOOTH);
      ImageIcon icon3 = new ImageIcon(changedImg3);
      
      menu3 = new JButton(icon3);
      menu3.setBackground(Color.WHITE);
      menu3.setBorderPainted(false);
      menu3.setPreferredSize(new Dimension(130, 120));
      barpanel.add(menu3);
      
      menu3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new TicketView();
            dispose();
      }});
      
      // 터미널정보
      Image img4 = icon4.getImage();
      Image changedImg4 = img4.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
      ImageIcon icon4 = new ImageIcon(changedImg4);
      
      menu4 = new JButton(icon4);
      menu4.setBackground(Color.WHITE);
      menu4.setBorderPainted(false);
      menu4.setPreferredSize(new Dimension(130, 130));
      barpanel.add(menu4);
      
      menu4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            dispose();
      }});
      
      // 공지사항
      Image img5 = icon5.getImage();
      Image changedImg5 = img5.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
      ImageIcon icon5 = new ImageIcon(changedImg5);
      
      menu5 = new JButton(icon5);
      menu5.setBackground(Color.WHITE);
      menu5.setBorderPainted(false);
      menu5.setPreferredSize(new Dimension(130, 130));
      barpanel.add(menu5);
      
      menu5.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new Notice();
            dispose();
      }});
      
      contentPane.add(barpanel, BorderLayout.NORTH);
      
      
      // 여기부터 메인 기능
      mainpanel = new JPanel();
      mainpanel.setLayout(new BorderLayout());
      
      // -----
      line = new JLabel(icon6);
      mainpanel.add(line, BorderLayout.NORTH);
      
      
      JPanel centerpanel = new JPanel();
      centerpanel.setLayout(null);
      Color color = new Color(250, 210, 200);
      centerpanel.setBackground(color);
      
      // 좌석 선택
      title = new JLabel("좌석 선택");
      title.setFont(new Font("a시월구일1", Font.BOLD, 20));
      title.setSize(95, 40);
      title.setLocation(302, 10);
      centerpanel.add(title);

      // 원하시는 좌석을 선택해주세요.
      explain = new JLabel("원하시는 좌석을 선택해 주세요.");
      explain.setFont(new Font("a시월구일1", Font.BOLD, 15));
      explain.setSize(235, 40);
      explain.setLocation(237, 40);
      centerpanel.add(explain);
      
      // 앞
      front = new JLabel("                          앞");
      front.setFont(new Font("a시월구일1", Font.PLAIN, 15));
      front.setBackground(Color.WHITE);
      front.setOpaque(true);
      front.setSize(280, 20);
      front.setLocation(210, 80);
      centerpanel.add(front);
      
      int i = 1;
      try {
         String sql = "SELECT * FROM bus WHERE departure = '" + departure + "' AND destination = '" + destination +
                                 "' AND date = '" + date + "' AND ampm = '" + ampm + "' AND time = '" + time + "' AND grade = '" + grade + "'";
         
         conn = DBConnection.getConnection();
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();

         while(rs.next()) {
            System.out.print(rs.getInt("seat1") + " " + rs.getInt("seat2") + " " + rs.getInt("seat3") + " " + rs.getInt("seat4") + " ");
            System.out.println(rs.getInt("seat5") + " " + rs.getInt("seat6") + " " + rs.getInt("seat7") + " " + rs.getInt("seat8"));
            
            if(rs.getInt("seat1") == 1) select1 = 1; // 예매가능좌석
            else if(rs.getInt("seat1") == 0) select1 = 0; // 예매불가좌석
            if(rs.getInt("seat2") == 1) select2 = 1;
            else if(rs.getInt("seat2") == 0) select2 = 0;
            if(rs.getInt("seat3") == 1) select3 = 1;
            else if(rs.getInt("seat3") == 0) select3 = 0;
            if(rs.getInt("seat4") == 1) select4 = 1;
            else if(rs.getInt("seat4") == 0) select4 = 0;
            if(rs.getInt("seat5") == 1) select5 = 1;
            else if(rs.getInt("seat5") == 0) select5 = 0;
            if(rs.getInt("seat6") == 1) select6 = 1;
            else if(rs.getInt("seat6") == 0) select6 = 0;
            if(rs.getInt("seat7") == 1) select7 = 1;
            else if(rs.getInt("seat7") == 0) select7 = 0;
            if(rs.getInt("seat8") == 1) select8 = 1;
            else if(rs.getInt("seat8") == 0) select8 = 0;
            
            ticketnumber = rs.getString("ticketnumber");
         }
         System.out.println("----------------");
      }
      catch (SQLException se) {
         se.printStackTrace();
      }
      
      // □ □
      if(select1 == 1) {
         seat1 = new JRadioButton(num1);
         seat1.setBorderPainted(true);
      }
      if(select1 == 0) {
         seat1 = new JRadioButton(num0);
         seat1.setBorderPainted(false);
      }
      seat1.setBackground(color);
      seat1.setSize(70, 70);
      seat1.setLocation(240, 105);

      if(select2 == 1) {
         seat2 = new JRadioButton(num1);
         seat2.setBorderPainted(true);
      }
      if(select2 == 0) {
         seat2 = new JRadioButton(num0);
         seat2.setBorderPainted(false);
      }
      seat2.setBackground(color);
      seat2.setSize(70, 70);
      seat2.setLocation(390, 105);
      
      if(select3 == 1) {
         seat3 = new JRadioButton(num1);
         seat3.setBorderPainted(true);
      }
      if(select3 == 0) {
         seat3 = new JRadioButton(num0);
         seat3.setBorderPainted(false);
      }
      seat3.setBackground(color);
      seat3.setSize(70, 70);
      seat3.setLocation(240, 185);

      if(select4 == 1) {
         seat4 = new JRadioButton(num1);
         seat4.setBorderPainted(true);
      }
      if(select4 == 0) {
         seat4 = new JRadioButton(num0);
         seat4.setBorderPainted(false);
      }
      seat4.setBackground(color);
      seat4.setSize(70, 70);
      seat4.setLocation(390, 185);

      if(select5 == 1) {
         seat5 = new JRadioButton(num1);
         seat5.setBorderPainted(true);
      }
      if(select5 == 0) {
         seat5 = new JRadioButton(num0);
         seat5.setBorderPainted(false);
      }
      seat5.setBackground(color);
      seat5.setSize(70, 70);
      seat5.setLocation(240, 265);

      if(select6 == 1) {
         seat6 = new JRadioButton(num1);
         seat6.setBorderPainted(true);
      }
      if(select6 == 0) {
         seat6 = new JRadioButton(num0);
         seat6.setBorderPainted(false);
      }
      seat6.setBackground(color);
      seat6.setSize(70, 70);
      seat6.setLocation(390, 265);

      if(select7 == 1) {
         seat7 = new JRadioButton(num1);
         seat7.setBorderPainted(true);
      }
      if(select7 == 0) {
         seat7 = new JRadioButton(num0);
         seat7.setBorderPainted(false);
      }
      seat7.setBackground(color);
      seat7.setSize(70, 70);
      seat7.setLocation(240, 345);
      
      if(select8 == 1) {
         seat8 = new JRadioButton(num1);
         seat8.setBorderPainted(true);
      }
      if(select8 == 0) {
         seat8 = new JRadioButton(num0);
         seat8.setBorderPainted(false);
      }
      seat8.setBackground(color);
      seat8.setSize(70, 70);
      seat8.setLocation(390, 345);

      centerpanel.add(seat1);
      centerpanel.add(seat2);
      centerpanel.add(seat3);
      centerpanel.add(seat4);
      centerpanel.add(seat5);
      centerpanel.add(seat6);
      centerpanel.add(seat7);
      centerpanel.add(seat8);

      SeatActionListener seatlistener = new SeatActionListener();
      seat1.addActionListener(seatlistener);
      seat2.addActionListener(seatlistener);
      seat3.addActionListener(seatlistener);
      seat4.addActionListener(seatlistener);
      seat5.addActionListener(seatlistener);
      seat6.addActionListener(seatlistener);
      seat7.addActionListener(seatlistener);
      seat8.addActionListener(seatlistener);

      // N1 N2 N3 N4 N5 N6 N7 N8
      seatnum1 = new JLabel("N1");
      seatnum1.setFont(new Font("a시월구일1", Font.PLAIN, 15));
      seatnum1.setBackground(color);
      seatnum1.setSize(20, 20);
      seatnum1.setLocation(210, 125);
      
      seatnum2 = new JLabel("N2");
      seatnum2.setFont(new Font("a시월구일1", Font.PLAIN, 15));
      seatnum2.setBackground(color);
      seatnum2.setSize(20, 20);
      seatnum2.setLocation(470, 125);
      
      seatnum3 = new JLabel("N3");
      seatnum3.setFont(new Font("a시월구일1", Font.PLAIN, 15));
      seatnum3.setBackground(color);
      seatnum3.setSize(20, 20);
      seatnum3.setLocation(210, 205);
      
      seatnum4 = new JLabel("N4");
      seatnum4.setFont(new Font("a시월구일1", Font.PLAIN, 15));
      seatnum4.setBackground(color);
      seatnum4.setSize(20, 20);
      seatnum4.setLocation(470, 205);
      
      seatnum5 = new JLabel("N5");
      seatnum5.setFont(new Font("a시월구일1", Font.PLAIN, 15));
      seatnum5.setBackground(color);
      seatnum5.setSize(20, 20);
      seatnum5.setLocation(210, 285);
      
      seatnum6 = new JLabel("N6");
      seatnum6.setFont(new Font("a시월구일1", Font.PLAIN, 15));
      seatnum6.setBackground(color);
      seatnum6.setSize(20, 20);
      seatnum6.setLocation(470, 285);
      
      seatnum7 = new JLabel("N7");
      seatnum7.setFont(new Font("a시월구일1", Font.PLAIN, 15));
      seatnum7.setBackground(color);
      seatnum7.setSize(20, 20);
      seatnum7.setLocation(210, 365);
      
      seatnum8 = new JLabel("N8");
      seatnum8.setFont(new Font("a시월구일1", Font.PLAIN, 15));
      seatnum8.setBackground(color);
      seatnum8.setSize(20, 20);
      seatnum8.setLocation(470, 365);
      
      centerpanel.add(seatnum1);
      centerpanel.add(seatnum2);
      centerpanel.add(seatnum3);
      centerpanel.add(seatnum4);
      centerpanel.add(seatnum5);
      centerpanel.add(seatnum6);
      centerpanel.add(seatnum7);
      centerpanel.add(seatnum8);
      
      // 뒤   
      back = new JLabel("                          뒤");
      back.setFont(new Font("a시월구일1", Font.PLAIN, 15));
      back.setBackground(Color.WHITE);
      back.setOpaque(true);
      back.setSize(280, 20);
      back.setLocation(210, 420);
      centerpanel.add(back);
      
      // [뒤로]
      page = new JButton("뒤로");
      page.setFont(new Font("a시월구일1", Font.BOLD, 20));
      page.setBackground(Color.LIGHT_GRAY);
      page.setSize(100, 40);
      page.setLocation(240, 465);
      centerpanel.add(page);
      
      PageMouseListener pagelistener = new PageMouseListener();
      page.addMouseListener(pagelistener);
      
      // [예매]
      finish = new JButton("예매");
      finish.setFont(new Font("a시월구일1", Font.BOLD, 20));
      finish.setBackground(Color.LIGHT_GRAY);
      finish.setSize(100, 40);
      finish.setLocation(360, 465);
      centerpanel.add(finish);
      
      FinishMouseListener finishlistener = new FinishMouseListener();
      finish.addMouseListener(finishlistener);
      
      mainpanel.add(centerpanel, BorderLayout.CENTER);
      contentPane.add(mainpanel);

      setSize(700, 700);
      setVisible(true);
      setResizable(false);
      setLocationRelativeTo(null);
   }
   
   class SeatActionListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         cnt++;
         
         if(seat1.isSelected() == true) { seat1.setBorderPainted(false); }
         if(seat2.isSelected() == true) { seat2.setBorderPainted(false); }
         if(seat3.isSelected() == true) { seat3.setBorderPainted(false); }
         if(seat4.isSelected() == true) { seat4.setBorderPainted(false); }
         if(seat5.isSelected() == true) { seat5.setBorderPainted(false); }
         if(seat6.isSelected() == true) { seat6.setBorderPainted(false); }
         if(seat7.isSelected() == true) { seat7.setBorderPainted(false); }
         if(seat8.isSelected() == true) { seat8.setBorderPainted(false); }
         
         if(adultchlid == cnt) {
            if(seat1.isSelected() == false) { seat1.setEnabled(false); }
            if(seat2.isSelected() == false) { seat2.setEnabled(false); }
            if(seat3.isSelected() == false) { seat3.setEnabled(false); }
            if(seat4.isSelected() == false) { seat4.setEnabled(false); }
            if(seat5.isSelected() == false) { seat5.setEnabled(false); }
            if(seat6.isSelected() == false) { seat6.setEnabled(false); }
            if(seat7.isSelected() == false) { seat7.setEnabled(false); }
            if(seat8.isSelected() == false) { seat8.setEnabled(false); }
         }
      }
   }
   
   class PageMouseListener extends MouseAdapter {
      public void mousePressed(MouseEvent e) {
         new Search();
         dispose();
      }
   }
   
   class FinishMouseListener extends MouseAdapter {
      public void mousePressed(MouseEvent e) {
         if(adultchlid > cnt) {
            JOptionPane.showMessageDialog(null, "선택하신 좌석 수가 인원보다 적습니다.");
         }
         else {
            int fare = (Integer.parseInt(adultfare) * adult) + (Integer.parseInt(childfare) * child);      
            JOptionPane.showMessageDialog(null, "총 결제 금액은 " + fare + "원입니다.");
            JOptionPane.showMessageDialog(null, "예매가 완료되었습니다.");
            
            int j = 0;
            try {
               if(seat1.isSelected() == true) {
                  String sql = "UPDATE bus SET seat1 = 0 WHERE departure = '" + departure + "' AND destination = '" + destination +
                                                   "' AND date = '" + date + "' AND time = '" + time + "' AND grade = '" + grade + "'";
                  conn = DBConnection.getConnection();
                  pstmt = conn.prepareStatement(sql);
                  j = pstmt.executeUpdate();
                  System.out.println("N1");
               }
               if(seat2.isSelected() == true) {
                  String sql = "UPDATE bus SET seat2 = 0 WHERE departure = '" + departure + "' AND destination = '" + destination +
                                                   "' AND date = '" + date + "' AND time = '" + time + "' AND grade = '" + grade + "'";
                  conn = DBConnection.getConnection();
                  pstmt = conn.prepareStatement(sql);
                  j = pstmt.executeUpdate();
                  System.out.println("N2");
               }
               if(seat3.isSelected() == true) {
                  String sql = "UPDATE bus SET seat3 = 0 WHERE departure = '" + departure + "' AND destination = '" + destination +
                                                   "' AND date = '" + date + "' AND time = '" + time + "' AND grade = '" + grade + "'";
                  conn = DBConnection.getConnection();
                  pstmt = conn.prepareStatement(sql);
                  j = pstmt.executeUpdate();
                  System.out.println("N3");
               }
               if(seat4.isSelected() == true) {
                  String sql = "UPDATE bus SET seat4 = 0 WHERE departure = '" + departure + "' AND destination = '" + destination +
                                                   "' AND date = '" + date + "' AND time = '" + time + "' AND grade = '" + grade + "'";
                  conn = DBConnection.getConnection();
                  pstmt = conn.prepareStatement(sql);
                  j = pstmt.executeUpdate();
                  System.out.println("N4");
               }
               if(seat5.isSelected() == true) {
                  String sql = "UPDATE bus SET seat5 = 0 WHERE departure = '" + departure + "' AND destination = '" + destination +
                                                   "' AND date = '" + date + "' AND time = '" + time + "' AND grade = '" + grade + "'";
                  conn = DBConnection.getConnection();
                  pstmt = conn.prepareStatement(sql);
                  j = pstmt.executeUpdate();
                  System.out.println("N5");
               }
               if(seat6.isSelected() == true) {
                  String sql = "UPDATE bus SET seat6 = 0 WHERE departure = '" + departure + "' AND destination = '" + destination +
                                                   "' AND date = '" + date + "' AND time = '" + time + "' AND grade = '" + grade + "'";
                  conn = DBConnection.getConnection();
                  pstmt = conn.prepareStatement(sql);
                  j = pstmt.executeUpdate();
                  System.out.println("N6");
               }
               if(seat7.isSelected() == true) {
                  String sql = "UPDATE bus SET seat7 = 0 WHERE departure = '" + departure + "' AND destination = '" + destination +
                                                   "' AND date = '" + date + "' AND time = '" + time + "' AND grade = '" + grade + "'";
                  conn = DBConnection.getConnection();
                  pstmt = conn.prepareStatement(sql);
                  j = pstmt.executeUpdate();
                  System.out.println("N7");
               }
               if(seat8.isSelected() == true) {
                  String sql = "UPDATE bus SET seat8 = 0 WHERE departure = '" + departure + "' AND destination = '" + destination +
                                                   "' AND date = '" + date + "' AND time = '" + time + "' AND grade = '" + grade + "'";
                  conn = DBConnection.getConnection();
                  pstmt = conn.prepareStatement(sql);
                  j = pstmt.executeUpdate();
                  System.out.println("N8");
               }
               System.out.println("----------------");
               
               String sql = "INSERT INTO book(memberid, ticketnumber, departure, destination, date, time, grade, seat1, seat2, seat3, seat4, seat5, seat6, seat7, seat8) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
               conn = DBConnection.getConnection();
               pstmt = conn.prepareStatement(sql);
               
               pstmt.setString(1, id);
                    pstmt.setString(2, ticketnumber);
                    pstmt.setString(3, departure);
                    pstmt.setString(4, destination);
                    pstmt.setString(5, date);
                    pstmt.setString(6, time);
                    pstmt.setString(7, grade);
                    if(seat1.isSelected() == true) {
                       int book = 0;
                       pstmt.setInt(8, book);
                    }
                    else if(seat1.isSelected() == false) {
                       int book = 1;
                       pstmt.setInt(8, book);
                    }
                    if(seat2.isSelected() == true) {
                       int book = 0;
                       pstmt.setInt(9, book);
                    }
                    else if(seat2.isSelected() == false) {
                       int book = 1;
                       pstmt.setInt(9, book);
                    }
                    if(seat3.isSelected() == true) {
                       int book = 0;
                       pstmt.setInt(10, book);
                    }
                    else if(seat3.isSelected() == false) {
                       int book = 1;
                       pstmt.setInt(10, book);
                    }
                    if(seat4.isSelected() == true) {
                       int book = 0;
                       pstmt.setInt(11, book);
                    }
                    else if(seat4.isSelected() == false) {
                       int book = 1;
                       pstmt.setInt(11, book);
                    }
                    if(seat5.isSelected() == true) {
                       int book = 0;
                       pstmt.setInt(12, book);
                    }
                    else if(seat5.isSelected() == false) {
                       int book = 1;
                       pstmt.setInt(12, book);
                    }
                    if(seat6.isSelected() == true) {
                       int book = 0;
                       pstmt.setInt(13, book);
                    }
                    else if(seat6.isSelected() == false) {
                       int book = 1;
                       pstmt.setInt(13, book);
                    }
                    if(seat7.isSelected() == true) {
                       int book = 0;
                       pstmt.setInt(14, book);
                    }
                    else if(seat7.isSelected() == false) {
                       int book = 1;
                       pstmt.setInt(14, book);
                    }
                    if(seat8.isSelected() == true) {
                       int book = 0;
                       pstmt.setInt(15, book);
                    }
                    else if(seat8.isSelected() == false) {
                       int book = 1;
                       pstmt.setInt(15, book);
                    }
               j = pstmt.executeUpdate();
            }
            catch (SQLException se) {
               se.printStackTrace();
            }
            
            new Main();
            dispose();
         }
      }
   }
   
   public static void main(String[] args) {
      new Seat();
   }
}