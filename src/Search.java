import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class Search extends JFrame {
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
   
   JLabel title, explain, fromlabel, tolabel, datelabel, timelabel, adultlabel, childlabel;
   JComboBox from, to, date, time, adult, child;
   String [] fromlist = {"서울", "세종", "부산", "광주"};
   String [] tolist = {"서울", "세종", "부산", "광주"};
   int [] datecount = {31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
   JRadioButton general, premium;
   ImageIcon bus1 = new ImageIcon("images/general.png");
   ImageIcon bus2 = new ImageIcon("images/premium.png");
   static String grade;
   JButton search;
   
   Connection conn = null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   
   static String departureresult, destinationresult, dateresult, timeresult;
   static int people1, people2, adultchild;
   
   Search() {
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
      
      // 버스 조회
      title = new JLabel("버스 조회");
      title.setFont(new Font("a시월구일1", Font.BOLD, 20));
      title.setSize(95, 40);
      title.setLocation(302, 10);
      centerpanel.add(title);
      
      // 원하시는 조건을 선택해주세요.
      explain = new JLabel("원하시는 조건을 선택해 주세요.");
      explain.setFont(new Font("a시월구일1", Font.BOLD, 15));
      explain.setSize(235, 40);
      explain.setLocation(237, 40);
      centerpanel.add(explain);
      
      // 출발지 : 서울 ▼
      fromlabel = new JLabel("출발지 : ");
      fromlabel.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      fromlabel.setSize(90, 40);
      fromlabel.setLocation(150, 100);
      centerpanel.add(fromlabel);
      
      from = new JComboBox();
      for (int i = 0; i < fromlist.length; i++) {
         from.addItem(fromlist[i]);
      }
      from.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      from.setSize(80, 40);
      from.setLocation(230, 100);
      centerpanel.add(from);
      
      // 도착지 : 서울 ▼
      tolabel = new JLabel("도착지 : ");
      tolabel.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      tolabel.setSize(90, 40);
      tolabel.setLocation(380, 100);
      centerpanel.add(tolabel);
      
      to = new JComboBox();
      for (int i = 0; i < tolist.length; i++) {
         to.addItem(tolist[i]);
      }
      to.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      to.setSize(80, 40);
      to.setLocation(460, 100);
      centerpanel.add(to);
      
      // 날   짜 : 2018 - 1 - 1 ▼
      datelabel = new JLabel("날   짜 : ");
      datelabel.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      datelabel.setSize(90, 40);
      datelabel.setLocation(215, 170);
      centerpanel.add(datelabel);
      
      date = new JComboBox();
      Calendar now = Calendar.getInstance();
      int year = now.get(Calendar.YEAR);
      int month = now.get(Calendar.MONTH) + 1;
      int day = now.get(Calendar.DATE);
      for (int i = 0; i <= 11; i++) {
         date.addItem(year + " - " + month + " - " + day);
         if (month == 12 && day == 31) {
            year++;
            month = 1;
            day = 0;
         }
         if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) datecount[1] = 29;
         else datecount[1] = 28;
         if (day == datecount[month-1]) {
            month++;
            day = 0;
         }
         day++;
      }
      date.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      date.setSize(180, 40);
      date.setLocation(295, 170);
      centerpanel.add(date);
      
      // 시간대 : 오전 ▼
      timelabel = new JLabel("시간대 : ");
      timelabel.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      timelabel.setSize(90, 40);
      timelabel.setLocation(215, 240);
      centerpanel.add(timelabel);
      
      time = new JComboBox();
      time.addItem("오전");
      time.addItem("오후");
      time.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      time.setSize(80, 40);
      time.setLocation(295, 240);
      centerpanel.add(time);
      
      // 성   인 : 0 ▼
      adultlabel = new JLabel("성   인 : ");
      adultlabel.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      adultlabel.setSize(90, 40);
      adultlabel.setLocation(150, 310);
      centerpanel.add(adultlabel);
      
      adult = new JComboBox();
      for (int i = 0; i <= 8; i++) {
         adult.addItem(i);
      }
      adult.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      adult.setSize(80, 40);
      adult.setLocation(230, 310);
      centerpanel.add(adult);
      
      // 아   동 : 0 ▼
      childlabel = new JLabel("아   동 : ");
      childlabel.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      childlabel.setSize(90, 40);
      childlabel.setLocation(380, 310);
      centerpanel.add(childlabel);
      
      child = new JComboBox();
      for (int i = 0; i <= 8; i++) {
         child.addItem(i);
      }
      child.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      child.setSize(80, 40);
      child.setLocation(460, 310);
      centerpanel.add(child);
      
      // (왕관)일반  (왕관)프리미엄
      ButtonGroup busgroup = new ButtonGroup();
      
      general = new JRadioButton("일  반", bus1);
      general.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      general.setBorderPainted(true);
      general.setBackground(color);
      general.setSize(100, 45);
      general.setLocation(235, 390);
      
      premium = new JRadioButton("우  등", bus2);
      premium.setFont(new Font("a시월구일1", Font.PLAIN, 20));
      premium.setBorderPainted(true);
      premium.setBackground(color);
      premium.setSize(100, 45);
      premium.setLocation(365, 390);
      
      busgroup.add(general);
      busgroup.add(premium);
      
      centerpanel.add(general);
      centerpanel.add(premium);
      
      GradeActionListener gradelistener = new GradeActionListener();
      general.addActionListener(gradelistener);
      premium.addActionListener(gradelistener);
      
      // [조회]
      search = new JButton("조회");
      search.setFont(new Font("a시월구일1", Font.BOLD, 20));
      search.setBackground(Color.LIGHT_GRAY);
      search.setSize(100, 40);
      search.setLocation(300, 465);
      centerpanel.add(search);
      
      SearchMouseListener searchlistener = new SearchMouseListener();
      search.addMouseListener(searchlistener);
      
      mainpanel.add(centerpanel, BorderLayout.CENTER);
      contentPane.add(mainpanel);
      
      setSize(700, 700);
      setVisible(true);
      setResizable(false);
      setLocationRelativeTo(null);
   }
   
   class GradeActionListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         if(general.isSelected() == true)
            grade = "일반";
         else if(premium.isSelected() == true)
            grade = "우등";
      }
   }
   
   class SearchMouseListener extends MouseAdapter {
      public void mousePressed(MouseEvent e) {
         int city1 = from.getSelectedIndex();
         int city2 = to.getSelectedIndex();
         people1 = adult.getSelectedIndex();
         people2 = child.getSelectedIndex();
         adultchild = people1 + people2;
         
         if((city1 == city2) || (people1 + people2 == 0) || (people1 + people2 > 8) || (general.isSelected() == false && premium.isSelected() == false)) {
            if(city1 == city2)
               JOptionPane.showMessageDialog(null, "출발지와 도착지가 같습니다.");
            if(people1 + people2 == 0)
               JOptionPane.showMessageDialog(null, "인원을 입력하지 않았습니다.");
            if(people1 + people2 > 8)
               JOptionPane.showMessageDialog(null, "인원이 초과되었습니다.");
            if(general.isSelected() == false && premium.isSelected() == false)
               JOptionPane.showMessageDialog(null, "등급이 선택되지 않았습니다.");
         }
         
         else {
            int click = JOptionPane.showConfirmDialog(null, from.getSelectedItem() + " → " + to.getSelectedItem() + "\n"
                                                + date.getSelectedItem() + "  (" + time.getSelectedItem() + ")\n"
                                                + "성인 " + adult.getSelectedItem() + "명, 아동 " + child.getSelectedItem() + "명\n"
                                                + grade + " 등급", "조회", JOptionPane.YES_NO_OPTION);
            
            if (click == JOptionPane.YES_OPTION) {
               JOptionPane.showMessageDialog(null, "다음 페이지에서 출발 시간을 선택해 주세요.");
               
               departureresult = (String) from.getSelectedItem();
               destinationresult = (String) to.getSelectedItem();
               dateresult = (String) date.getSelectedItem();
               timeresult = (String) time.getSelectedItem();
               
               try {
                  String sql = "SELECT * FROM bus WHERE departure = '" + from.getSelectedItem() + "' AND destination = '" + to.getSelectedItem()
                                          + "' AND date = '" + date.getSelectedItem() + "' AND ampm = '" + time.getSelectedItem()
                                          + "' AND grade = '" + grade + "'";
                  conn = DBConnection.getConnection();
                  pstmt = conn.prepareStatement(sql);
                  rs = pstmt.executeQuery();
                  
                  while(rs.next()) {
                     System.out.println(rs.getString("ticketnumber"));
                  }
                  System.out.println("----------------");
                  rs.close();
               }
               catch (SQLException se) {
                  se.printStackTrace();
               }
               
               new SearchResult();
               dispose();
            }
         }
      }
   }
   
   public static void main(String[] args) {
      new Search();
   }
}
