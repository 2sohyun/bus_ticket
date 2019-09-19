import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import com.NoticeInfo;
//import static main.Login.*;

public class Notice extends JFrame {
   JButton menu1, menu2, menu3, menu4, menu5;
   //메뉴바 5개 이미지▼
   ImageIcon icon1 = new ImageIcon("images/home.png");
   ImageIcon icon2 = new ImageIcon("images/book.png");
   ImageIcon icon3 = new ImageIcon("images/ticket.png");
   ImageIcon icon4 = new ImageIcon("images/terminal.png");
   ImageIcon icon5 = new ImageIcon("images/notice.png");
   JLabel line;
   ImageIcon icon6 = new ImageIcon("images/line.png");
   JPanel barpanel;
   DefaultTableModel model;
   
   CenterPanel center;
   JPanel panel1, panel2, panel3;
   JLabel noticelabel;
   JLabel cntlabel, textlabel;
   int i = 1;
   Container c;
   JButton btn,btn2;
    Color color = new Color(250, 210, 200);
    String name = "admin";
    String userid = Login.user;
   
    
    Connection conn = null; // DB연결된 상태(세션)을 담은 객체
    PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
    ResultSet rs=null;
    Statement pstmt = null;
    //String sql = null;
    
    
   Notice() {
      setTitle("공지사항");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      c = getContentPane();
      c.setLayout(new BorderLayout());
      
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
      
      // 조회/예매
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
      
      // 예매확인
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
      
      // 예매취소
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
      menu5.setPreferredSize(new Dimension(130, 120));
      barpanel.add(menu5);
      
      menu5.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            new Notice();
            dispose();
      }});
      
      c.add(barpanel, BorderLayout.NORTH);
      
      center = new CenterPanel();
      c.add(center, BorderLayout.CENTER);
      
      setSize(700, 700);
      setVisible(true);
      setResizable(false);
      setLocationRelativeTo(null);
      
   }
   
   class CenterPanel extends JPanel {
      
      CenterPanel() {
         setLayout(new BorderLayout());
         
         
         panel2 = new JPanel();
         panel2.setBackground(color);
         
         String title[] = {"제목", "상세내용"};
        // title.setFont(new Font("시월구일",Font.BOLD,20));
         model = new DefaultTableModel(title,0);
         JTable table = new JTable(model);
         JScrollPane scroll = new JScrollPane(table);
         
         
         Color tablecolor = new Color(220, 210, 200);
         table.setBackground(Color.PINK);
         table.setGridColor(Color.BLACK);
         table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); // 단일선택
         String arr[] = {"제목", "상세내용"};
         model.addRow(arr);
         String str = null;
         String a;

      
         
         try {
               NoticeInfo dao = new NoticeInfo(null,null,null);
                 String sql = "SELECT * FROM notice";
                 conn = DBConnection.getConnection();
                 pstmt = conn.prepareStatement(sql);
                 rs = pstmt.executeQuery(sql);
                  
                 
                // table.getColumnModel().getColumn(0).setPreferredWidth(30);
                // table.getColumnModel().getColumn(1).setPreferredWidth(80);

                 DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
                 dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                 
                table.getColumnModel().getColumn(0).setCellRenderer(dtcr); 
                table.getColumnModel().getColumn(1).setCellRenderer(dtcr); 
               
                 while(rs.next()) {                     
                     str = rs.getString(1);
                     arr[0] = str;
                     for(int i=1; i<3;i++) {
                        a = rs.getString(i+1);
                         arr[i-1]=a;
                     }
                     model.addRow(arr);
                 }
                 rs.close();
           }
         catch(Exception e){
            e.printStackTrace();
         }
         
         table.getColumnModel().getColumn(0).setPreferredWidth(200);
         table.getColumnModel().getColumn(1).setPreferredWidth(490);
         table.setFont(new Font("a시월구일1", Font.BOLD, 17));
         table.setRowHeight(35);

         setBounds(40,40,600,600);
         add(scroll);
         panel2.add(table);
         
         setVisible(true);
        /* panel3 = new JPanel();
         panel3.setBackground(Color.white);
         panel3.setSize(300,300);
         panel2.add(panel3);*/
                  
         add(panel2,BorderLayout.CENTER);
         
         panel1 = new JPanel();
         panel1.setLayout(new FlowLayout());
         panel1.setBackground(color);
         
         btn = new JButton("글쓰기");
         btn.setFont(new Font("a시월구일2", Font.PLAIN, 20));
         btn.setSize(80, 25);
         btn.setBackground(Color.LIGHT_GRAY);
         
       
         
         if(name.equals(userid) == true) {
            btn.setVisible(true);
         }
         else {
            btn.setVisible(false);
         }
         panel1.add(btn);
           btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new Notice_content();
              }
         });
         panel1.add(btn);
         
         btn2 = new JButton("삭제");
         btn2.setFont(new Font("a시월구일2", Font.PLAIN, 20));
         btn2.setSize(80, 25);
         btn2.setBackground(Color.LIGHT_GRAY);
         
         if(name.equals(userid) == true) {
            btn2.setVisible(true);
         }
         else {
            btn2.setVisible(false);
         }
         panel1.add(btn2);
       
           btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              int row = table.getSelectedRow();
              if(row==-1) {
                 return;
              }
              else {
                 try {
                    NoticeInfo dao = new NoticeInfo(null,null,null);
                      String sql = "DELETE FROM notice WHERE title = '"+table.getValueAt(row, 0)+ "'";
                      conn = DBConnection.getConnection();
                      pstm = conn.prepareStatement(sql);
                     int rs1 = pstm.executeUpdate();
                     System.out.println(rs1+"행 삭제 완료!");
                     //int row = table.getSelectedRow();
                     model.removeRow(row);
                 }
                 catch(Exception ee){
                     ee.printStackTrace();
                             }
              }
            }
              
         });
         panel1.add(btn2);
         
         table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				JLabel d = new JLabel();
				d.setSize(200,200);
				d.setBackground(color);
				int row = table.getSelectedRow();
				String value = (String)table.getValueAt(row, 0);
				
				d.setText(value);
				new JLabel();
			}
        	 
         });
         
         add(panel1,BorderLayout.SOUTH);
      
      }
   }

   public static void main(String[] args) {
      new Notice();
   }
}
