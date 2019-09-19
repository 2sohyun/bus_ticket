import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SearchResult extends JFrame {
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
   
   JLabel title, explain, view1, view2, view3, view4, view5, view6, view7;
   JLabel field1, field2;
   JRadioButton result1, result2, result3;
   JButton page, select;
   
   Connection conn = null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   
   String departure = Search.departureresult;
   String destination = Search.destinationresult;
   String date = Search.dateresult;
   String ampm = Search.timeresult;
   String grade = Search.grade;
   int adultchlid = Search.adultchild;
   
   String str1, str2, str3;
   int cnt1, cnt2, cnt3;
   static String option;
   static int book;
   static String adultfare, childfare;
   
   SearchResult() {
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
            new TicketCheck();
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
        	new Terminal();
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
      
      // 조회 결과
      title = new JLabel("조회 결과");
      title.setFont(new Font("a시월구일1", Font.BOLD, 20));
      title.setSize(95, 40);
      title.setLocation(302, 10);
      centerpanel.add(title);
      
      // 원하시는 버스를 선택해주세요.
      explain = new JLabel("원하시는 버스를 선택해 주세요.");
      explain.setFont(new Font("a시월구일1", Font.BOLD, 15));
      explain.setSize(235, 40);
      explain.setLocation(237, 40);
      centerpanel.add(explain);
      
      // 출발지 → 도착지
      view1 = new JLabel(departure + " → " + destination);
      view1.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      view1.setSize(120, 40);
      view1.setLocation(205, 80);
      view1.setVerticalAlignment(SwingConstants.CENTER);
      view1.setHorizontalAlignment(SwingConstants.CENTER);
      centerpanel.add(view1);
      
      // 소요 시간
      view2 = new JLabel();
      view2.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      view2.setSize(120, 40);
      view2.setLocation(205, 120);
      view2.setVerticalAlignment(SwingConstants.CENTER);
      view2.setHorizontalAlignment(SwingConstants.CENTER);
      centerpanel.add(view2);

      // 성인 일반/우등 요금
      view3 = new JLabel("성인 " + grade);
      view3.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      view3.setSize(90, 40);
      view3.setLocation(340, 80);
      view3.setVerticalAlignment(SwingConstants.CENTER);
      view3.setHorizontalAlignment(SwingConstants.CENTER);
      centerpanel.add(view3);
      
      view4 = new JLabel();
      view4.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      view4.setSize(65, 40);
      view4.setLocation(430, 80);
      view4.setVerticalAlignment(SwingConstants.CENTER);
      view4.setHorizontalAlignment(SwingConstants.CENTER);
      centerpanel.add(view4);

      // 아동 일반/우등 요금
      view5 = new JLabel("아동 " + grade);
      view5.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      view5.setSize(90, 40);
      view5.setLocation(340, 120);
      view5.setVerticalAlignment(SwingConstants.CENTER);
      view5.setHorizontalAlignment(SwingConstants.CENTER);
      centerpanel.add(view5);
      
      view6 = new JLabel();
      view6.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      view6.setSize(65, 40);
      view6.setLocation(430, 120);
      view6.setVerticalAlignment(SwingConstants.CENTER);
      view6.setHorizontalAlignment(SwingConstants.CENTER);
      centerpanel.add(view6);
      
      // 2018 - 1 - 1
      view7 = new JLabel(date);
      view7.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      view7.setSize(150, 40);
      view7.setLocation(275, 165);
      view7.setVerticalAlignment(SwingConstants.CENTER);
      view7.setHorizontalAlignment(SwingConstants.CENTER);
      centerpanel.add(view7);
      
      // 출발 시각
      field1 = new JLabel("출발 시각");
      field1.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      Color fieldcolor = new Color(250, 130, 105);
      field1.setBackground(fieldcolor);
      field1.setOpaque(true);
      field1.setSize(120, 40);
      field1.setLocation(225, 220);
      field1.setVerticalAlignment(SwingConstants.CENTER);
      field1.setHorizontalAlignment(SwingConstants.CENTER);
      centerpanel.add(field1);
      
      // 잔여 좌석
      field2 = new JLabel("잔여 좌석");
      field2.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      field2.setBackground(fieldcolor);
      field2.setOpaque(true);
      field2.setSize(120, 40);
      field2.setLocation(355, 220);
      field2.setVerticalAlignment(SwingConstants.CENTER);
      field2.setHorizontalAlignment(SwingConstants.CENTER);
      centerpanel.add(field2);
      
      // ○ 출발 시각 잔여 좌석
      ButtonGroup resultgroup = new ButtonGroup();
      
      result1 = new JRadioButton();
      result1.setFont(new Font("a시월구일1", Font.PLAIN, 22));
      result1.setBackground(color);
      result1.setSize(270, 50);
      result1.setLocation(205, 270);
      
      result2 = new JRadioButton();
      result2.setFont(new Font("a시월구일1", Font.PLAIN, 22));
      result2.setBackground(color);
      result2.setSize(270, 50);
      result2.setLocation(205, 330);
      
      result3 = new JRadioButton();
      result3.setFont(new Font("a시월구일1", Font.PLAIN, 22));
      result3.setBackground(color);
      result3.setSize(270, 50);
      result3.setLocation(205, 390);

      try {
         String sql = "SELECT * FROM bus WHERE departure = '" + departure + "' AND destination = '" + destination
                                 + "' AND date = '" + date + "' AND ampm = '" + ampm + "' AND grade = '" + grade + "'";
         conn = DBConnection.getConnection();
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         
         int i = 1;
         while(rs.next()) {
            view2.setText(rs.getString("hour"));
            view4.setText(rs.getString("adultfare"));
            view6.setText(rs.getString("childfare"));
            adultfare = rs.getString("adultfare");
            childfare = rs.getString("childfare");
            
            int cnt = 0;
            System.out.print(rs.getString("time") + " ");
            if(rs.getInt("seat1") == 1) cnt++;
            if(rs.getInt("seat2") == 1) cnt++;
            if(rs.getInt("seat3") == 1) cnt++;
            if(rs.getInt("seat4") == 1) cnt++;
            if(rs.getInt("seat5") == 1) cnt++;
            if(rs.getInt("seat6") == 1) cnt++;
            if(rs.getInt("seat7") == 1) cnt++;
            if(rs.getInt("seat8") == 1) cnt++;
            System.out.println(cnt);
            
            if(i == 1) {
               str1 = rs.getString("time");
               cnt1 = cnt;
               result1.setText("   " + rs.getString("time") + "             " + cnt);
            }
            if(i == 2) {
               str2 = rs.getString("time");
               cnt2 = cnt;
               result2.setText("   " + rs.getString("time") + "             " + cnt);
            }
            if(i == 3) {
               str3 = rs.getString("time");
               cnt3 = cnt;
               result3.setText("   " + rs.getString("time") + "             " + cnt);
            }
            i++;
         }
         System.out.println("----------------");
         rs.close();
      }
      catch (SQLException se) {
         se.printStackTrace();
      }
      
      if(str1 != null) {
         resultgroup.add(result1);
         centerpanel.add(result1);
      }
      if(str2 != null) {
         resultgroup.add(result2);
         centerpanel.add(result2);
      }
      if(str3 != null) {
         resultgroup.add(result3);
         centerpanel.add(result3);
      }
      
      ResultActionListener resultlistener = new ResultActionListener();
      result1.addActionListener(resultlistener);
      result2.addActionListener(resultlistener);
      result3.addActionListener(resultlistener);
      
      // [뒤로]
      page = new JButton("뒤로");
      page.setFont(new Font("a시월구일1", Font.BOLD, 20));
      page.setBackground(Color.LIGHT_GRAY);
      page.setSize(100, 40);
      page.setLocation(240, 465);
      centerpanel.add(page);
      
      PageMouseListener pagelistener = new PageMouseListener();
      page.addMouseListener(pagelistener);
      
      // [선택]
      select = new JButton("선택");
      select.setFont(new Font("a시월구일1", Font.BOLD, 20));
      select.setBackground(Color.LIGHT_GRAY);
      select.setSize(100, 40);
      select.setLocation(360, 465);
      centerpanel.add(select);
      
      SelectMouseListener selectlistener = new SelectMouseListener();
      select.addMouseListener(selectlistener);
      
      mainpanel.add(centerpanel, BorderLayout.CENTER);
      contentPane.add(mainpanel);
      
      setSize(700, 700);
      setVisible(true);
      setResizable(false);
      setLocationRelativeTo(null);
   }
   
   class ResultActionListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         if(result1.isSelected() == true) {
            option = str1;
            book = cnt1;
         }
         else if(result2.isSelected() == true) {
            option = str2;
            book = cnt2;
         }
         else if(result3.isSelected() == true) {
            option = str3;
            book = cnt3;
         }
      }
   }
   
   class PageMouseListener extends MouseAdapter {
      public void mousePressed(MouseEvent e) {
         new Search();
         dispose();
      }
   }
   
   class SelectMouseListener extends MouseAdapter {
      public void mousePressed(MouseEvent e) {
         if(adultchlid > book) {
            JOptionPane.showMessageDialog(null, "다른 버스를 이용하시길 바랍니다.");
         }
         else {
            int click = JOptionPane.showConfirmDialog(null, "출발 시각 " + option, "선택", JOptionPane.YES_NO_OPTION);
            if (click == JOptionPane.YES_OPTION) {
               JOptionPane.showMessageDialog(null, "다음 페이지에서 " + adultchlid + "개의 좌석을 선택해 주세요.");
               
               new Seat();
               dispose();
            }
         }
      }
   }
   
   public static void main(String[] args) {
      new SearchResult();
   }
}
