import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import com.NoticeInfo;
//import static main.Login.*;

public class TicketCheck extends JFrame {
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
   Color color = new Color(250, 210, 200);
   
   String id = Login.user;
   
    
   Connection conn = null; // DB연결된 상태(세션)을 담은 객체
   PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
   ResultSet rs=null;
   Statement pstmt = null;
   
   JButton showbtn, cancelbtn;
   static String bookdeparture;
   static String bookdestination;
   static String ticket;
    
    
    TicketCheck() {
      setTitle("티켓 확인 및 취소");
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
            Image changedImg2 = img2.getScaledInstance(160, 120, Image.SCALE_SMOOTH);
            ImageIcon icon2 = new ImageIcon(changedImg2);
            
            menu2 = new JButton(icon2);
            menu2.setBackground(Color.WHITE);
            menu2.setBorderPainted(false);
            menu2.setPreferredSize(new Dimension(160, 120));
            barpanel.add(menu2);
            
            menu2.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  new Search();
                  dispose();
            }});
            
            // 예매확인
            Image img3 = icon3.getImage();
            Image changedImg3 = img3.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            ImageIcon icon3 = new ImageIcon(changedImg3);
            
            menu3 = new JButton(icon3);
            menu3.setBackground(Color.WHITE);
            menu3.setBorderPainted(false);
            menu3.setPreferredSize(new Dimension(120, 120));
            barpanel.add(menu3);
            
            menu3.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  new TicketCheck();
                  dispose();
            }});
            
            // 예매취소
            Image img4 = icon4.getImage();
            Image changedImg4 = img4.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            ImageIcon icon4 = new ImageIcon(changedImg4);
            
            menu4 = new JButton(icon4);
            menu4.setBackground(Color.WHITE);
            menu4.setBorderPainted(false);
            menu4.setPreferredSize(new Dimension(120, 120));
            barpanel.add(menu4);
            
            menu4.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
            	   new Terminal();
                  dispose();
            }});
            
            // 공지사항
            Image img5 = icon5.getImage();
            Image changedImg5 = img5.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            ImageIcon icon5 = new ImageIcon(changedImg5);
            
            menu5 = new JButton(icon5);
            menu5.setBackground(Color.WHITE);
            menu5.setBorderPainted(false);
            menu5.setPreferredSize(new Dimension(120, 120));
            barpanel.add(menu5);
            
            menu5.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                 new Notice();
                  dispose();
            }});

            c.add(barpanel, BorderLayout.NORTH);
      
      center = new CenterPanel();
      c.add(center, BorderLayout.CENTER);
      
      setSize(680, 700);
      setVisible(true);
     // setResizable(false);
      setLocationRelativeTo(null);
      
   }
   
   class CenterPanel extends JPanel {
      
      CenterPanel() {
         setLayout(new BorderLayout());
         
         
         panel2 = new JPanel();
         panel2.setBackground(color);
         
         String title[] = {"티켓번호", "출발지", "도착지"};
        // title.setFont(new Font("시월구일",Font.BOLD,20));
         model = new DefaultTableModel(title,0);
         JTable table = new JTable(model);
         JScrollPane scroll = new JScrollPane(table);
         
         
         Color tablecolor = new Color(220, 210, 200);
         table.setBackground(Color.PINK);
         table.setGridColor(Color.BLACK);
         String arr[] = {"티켓번호", "출발지", "도착지"};
         model.addRow(arr);
         String str = null;
         String a;

      
         
         try {
               NoticeInfo dao = new NoticeInfo(null,null,null);
                 String sql = "SELECT * FROM book where memberid='"+id+"'";
                 conn = DBConnection.getConnection();
                 pstmt = conn.prepareStatement(sql);
                 rs = pstmt.executeQuery(sql);
                  

                 DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
                 dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                 
                table.getColumnModel().getColumn(0).setCellRenderer(dtcr); 
                table.getColumnModel().getColumn(1).setCellRenderer(dtcr);
                table.getColumnModel().getColumn(2).setCellRenderer(dtcr); 
               
                 while(rs.next()) {                     
                     arr[0] = rs.getString(2);
                     arr[1] = rs.getString(3);
                     arr[2] = rs.getString(4);
                     model.addRow(arr);
                 }
                 rs.close();
           }
         catch(Exception e){
            e.printStackTrace();
         }
         
         table.getColumnModel().getColumn(0).setPreferredWidth(200);
         table.getColumnModel().getColumn(1).setPreferredWidth(225);
         table.getColumnModel().getColumn(2).setPreferredWidth(225);
         table.setFont(new Font("a시월구일1", Font.BOLD, 17));
         table.setRowHeight(35);

         setBounds(40,40,600,600);
         add(scroll);
         panel2.add(table);
         
         setVisible(true);
                  
         add(panel2,BorderLayout.CENTER);
         
         panel1 = new JPanel();
         panel1.setLayout(new FlowLayout());
         panel1.setBackground(color);
         
         
         showbtn = new JButton("티켓확인");
         showbtn.setFont(new Font("a시월구일2", Font.PLAIN, 20));
         showbtn.setSize(80, 25);
         showbtn.setBackground(Color.LIGHT_GRAY);
         
         showbtn.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
               int row = table.getSelectedRow();
               if(row==-1) {
                  return;
               }
               else {
                  try {
                     NoticeInfo dao = new NoticeInfo(null,null,null);
                       String quary = "SELECT * FROM book WHERE memberid='"+id+"' And ticketnumber = '"+table.getValueAt(row, 0)+ "' AND departure='"
                     +table.getValueAt(row, 1)+ "' AND destination='"+table.getValueAt(row, 2)+ "'";
			           conn = DBConnection.getConnection();
			           pstm = conn.prepareStatement(quary);
		               rs = pstm.executeQuery();
		               
		               ticket = (String)table.getValueAt(row, 0);
		               bookdeparture = (String) table.getValueAt(row, 1);
		               bookdestination = (String) table.getValueAt(row, 2);
		               
		               
		                if (rs.next() == true) {
		                	new TicketView();
		                	dispose();
		                }

                  }
                  catch(Exception ee){
                      ee.printStackTrace();
                              }
               }
             }
               
          });
         
         
         cancelbtn = new JButton("티켓취소");
         cancelbtn.setFont(new Font("a시월구일2", Font.PLAIN, 20));
         cancelbtn.setSize(80, 25);
         cancelbtn.setBackground(Color.LIGHT_GRAY);

       
    /*     cancelbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              int row = table.getSelectedRow();
              int j=0;
              if(row==-1) {
                 return;
              }
              else {
                 try {
                    NoticeInfo dao = new NoticeInfo(null,null,null);
                     
                     if()
                     sql = "UPDATE bus SET seat4 = 0 WHERE departure = '" + table.getValueAt(row, 1) + "' AND destination = '"
                     + table.getValueAt(row, 2) + "'";
                     conn = DBConnection.getConnection();
                     pstm = conn.prepareStatement(sql);
                     j=0;
                     j = pstm.executeUpdate();
                     
                     String sql = "DELETE FROM book WHERE date = '"+table.getValueAt(row, 0)+ "' AND departure='"
                           	+table.getValueAt(row, 1)+ "' AND destination='"+table.getValueAt(row, 2)+ "'";
                           conn = DBConnection.getConnection();
                           pstm = conn.prepareStatement(sql);
                          int rs1 = pstm.executeUpdate();
                          System.out.println(rs1+"행 삭제 완료!");
                          model.removeRow(row);
                 }
                 catch(Exception ee){
                     ee.printStackTrace();
                             }
              }
            }
              
         }); */
         panel1.add(showbtn);
         panel1.add(cancelbtn);
         
         add(panel1,BorderLayout.SOUTH);
      
      }
   }
   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TicketCheck();

	}
}