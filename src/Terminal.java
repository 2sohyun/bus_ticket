import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URI;
import java.util.*;
import java.sql.*;

public class Terminal extends JFrame {
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
   
   ImageIcon mapicon = new ImageIcon("images/mymap.png");
   
   ImageIcon mainicon1 = new ImageIcon("images/location.png");
   ImageIcon mainicon2 = new ImageIcon("images/call.png");
   ImageIcon mainicon3 = new ImageIcon("images/gohome.png");
   
   Terminal() {
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
    
     
      Image mapImg = mapicon.getImage();
      Image changedmapImg = mapImg.getScaledInstance(400, 500, Image.SCALE_SMOOTH);
      ImageIcon mapicon = new ImageIcon(changedmapImg);
      JLabel mapLabel = new JLabel(mapicon);
      mapLabel.setSize(500,500);
      mapLabel.setLocation(0,0);
      
      mapLabel.setPreferredSize(new Dimension(400,500));
      centerpanel.add(mapLabel);
      
      //지도 버튼 눌렀을 때 [오시는 길]▼
      Image mainImg1 = mainicon1.getImage();
      Image changedmainImg1 = mainImg1.getScaledInstance(160, 70, Image.SCALE_SMOOTH);
      ImageIcon mainicon1 = new ImageIcon(changedmainImg1);
      
      JLabel msgLabel = new JLabel(mainicon1);
      msgLabel.setSize(200,200);
      msgLabel.setLocation(460,20);
      msgLabel.setPreferredSize(new Dimension(200,200));
      msgLabel.setVisible(false);
      
      //지도 버튼 눌렀을 때 [전화안내]▼
      Image mainImg2 = mainicon2.getImage();
      Image changedmainImg2 = mainImg2.getScaledInstance(160, 70, Image.SCALE_SMOOTH);
      ImageIcon mainicon2 = new ImageIcon(changedmainImg2);
      
      JLabel msgLabel2 = new JLabel(mainicon2);
      msgLabel2.setSize(200,210);
      msgLabel2.setLocation(460,160);
      msgLabel2.setPreferredSize(new Dimension(200,200));
      msgLabel2.setVisible(false);
      
      //지도 버튼 눌렀을 때 [링크 클릭] ▼
      Image mainImg3 = mainicon3.getImage();
      Image changedmainImg3 = mainImg3.getScaledInstance(240,100, Image.SCALE_SMOOTH);
      ImageIcon mainicon3 = new ImageIcon(changedmainImg3);
      
      JLabel msgLabel3 = new JLabel(mainicon3);
      msgLabel3.setSize(220,200);
      msgLabel3.setLocation(460,280);
      msgLabel3.setPreferredSize(new Dimension(220,200));
      msgLabel3.setVisible(false);
      
      
      JLabel seoul1 = new JLabel("서울시 서초구");
      seoul1.setFont(new Font("a시월구일1", Font.BOLD, 20));
      seoul1.setSize(200,200);
      seoul1.setLocation(490,70);
      seoul1.setVisible(false);
      
      JLabel seoul2 = new JLabel("신반포로 194");
      seoul2.setFont(new Font("a시월구일1", Font.BOLD, 20));
      seoul2.setSize(200,200);
      seoul2.setLocation(490,90);
      seoul2.setVisible(false);
      
      JLabel seoul3 = new JLabel("1688-4700");
      seoul3.setFont(new Font("a시월구일1", Font.BOLD, 25));
      seoul3.setSize(200,210);
      seoul3.setLocation(510,190);
      seoul3.setVisible(false);
      
      //지도 위 지역별 버튼 ▼
      JButton mapBtn1 = new JButton("서울");
      mapBtn1.setFont(new Font("a시월구일1", Font.BOLD, 15));
      mapBtn1.setBorderPainted(false); 
      mapBtn1.setSize(70,30);
      mapBtn1.setLocation(110,80);
      mapBtn1.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
             msgLabel.setVisible(true);
             seoul1.setVisible(true);
             seoul2.setVisible(true);
             msgLabel2.setVisible(true);
             seoul3.setVisible(true);
             msgLabel3.setVisible(true);
            }
       });
      msgLabel3.addMouseListener(new MouseListener() {
          public void actionPerformed(MouseEvent e) {
             msgLabel.setVisible(true);
             seoul1.setVisible(true);
             seoul2.setVisible(true);
             msgLabel2.setVisible(true);
             seoul3.setVisible(true);
             msgLabel3.setVisible(true);
            }

      @Override
      public void mouseClicked(MouseEvent e) {
         String link = "http://exterminal.co.kr/";
         Desktop desktop = Desktop.getDesktop();
         try {
            desktop.browse(new URI(link));
         } catch (IOException | URISyntaxException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
      }

      @Override
      public void mouseEntered(MouseEvent arg0) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public void mouseExited(MouseEvent arg0) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public void mousePressed(MouseEvent arg0) {
         // TODO Auto-generated method stub
         
      }

      @Override
      public void mouseReleased(MouseEvent arg0) {
         // TODO Auto-generated method stub
         
      }
     
       });
      
      centerpanel.add(msgLabel);
      centerpanel.add(msgLabel2);
      centerpanel.add(seoul1);
      centerpanel.add(seoul2);
      centerpanel.add(seoul3);
      centerpanel.add(msgLabel3);
      mapLabel.add(mapBtn1);
      
      JButton mapBtn2 = new JButton("경기");
      mapBtn2.setFont(new Font("a시월구일1", Font.BOLD, 15));
      mapBtn2.setBorderPainted(false); 
      mapBtn2.setSize(70,30);
      mapBtn2.setLocation(140,120);
      mapLabel.add(mapBtn2);
      
      JButton mapBtn3 = new JButton("강원");
      mapBtn3.setFont(new Font("a시월구일1", Font.BOLD, 15));
      mapBtn3.setBorderPainted(false); 
      mapBtn3.setSize(70,30);
      mapBtn3.setLocation(270,90);
      mapLabel.add(mapBtn3);
      
      
      JButton mapBtn4 = new JButton("충북");
      mapBtn4.setFont(new Font("a시월구일1", Font.BOLD, 15));
      mapBtn4.setBorderPainted(false); 
      mapBtn4.setSize(70,30);
      mapBtn4.setLocation(200,200);
      mapLabel.add(mapBtn4);
      
      
      JButton mapBtn5 = new JButton("충남");
      mapBtn5.setFont(new Font("a시월구일1", Font.BOLD, 15));
      mapBtn5.setBorderPainted(false); 
      mapBtn5.setSize(70,30);
      mapBtn5.setLocation(115,220);
      mapLabel.add(mapBtn5);
      
      
      JButton mapBtn6 = new JButton("경북");
      mapBtn6.setFont(new Font("a시월구일1", Font.BOLD, 15));
      mapBtn6.setBorderPainted(false); 
      mapBtn6.setSize(70,30);
      mapBtn6.setLocation(305,250);
      mapLabel.add(mapBtn6);
      
      JButton mapBtn7 = new JButton("경남");
      mapBtn7.setFont(new Font("a시월구일1", Font.BOLD, 15));
      mapBtn7.setBorderPainted(false); 
      mapBtn7.setSize(70,30);
      mapBtn7.setLocation(280,350);
      mapLabel.add(mapBtn7);
      
      JButton mapBtn8 = new JButton("전북");
      mapBtn8.setFont(new Font("a시월구일1", Font.BOLD, 15));
      mapBtn8.setBorderPainted(false); 
      mapBtn8.setSize(70,30);
      mapBtn8.setLocation(145,305);
      mapLabel.add(mapBtn8);
      
      JButton mapBtn9 = new JButton("전남");
      mapBtn9.setFont(new Font("a시월구일1", Font.BOLD, 15));
      mapBtn9.setBorderPainted(false); 
      mapBtn9.setSize(70,30);
      mapBtn9.setLocation(130,380);
      mapLabel.add(mapBtn9);
      
      
      
      
      mainpanel.add(centerpanel, BorderLayout.CENTER);
      contentPane.add(mainpanel);
      
      setSize(700, 700);
      setVisible(true);
      setResizable(false);
	  setLocationRelativeTo(null);
   } 
   
   public static void main(String[] args) {
      new Terminal();
   }
}
