import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.MemberInfo;

public class Login extends JFrame{
      Container c;
      CPanel centerPanel;
      SPanel southPanel;
      
      
      Connection conn = null; // DB연결된 상태(세션)을 담은 객체
      PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
      ResultSet rs=null;
      Statement stmt = null;
      String sql = null;
      boolean ok = false;
      String memgender = "성별";
      
      static String user; //현재 사용자 ID를 저장하는 전역변수 user
      
      
      JButton joinbtn, okbtn; //회원가입 버튼, 확인 버튼
      JLabel idLabel, pwLabel; //아이디, 비밀번호 라벨
      JTextField idText; //아이디 입력 칸
      JPasswordField pwText;//비밀번호 입력 칸
      Color color = new Color(250, 210, 200);
      
      public Login() {
         setTitle("고속버스 예매 시스템");
         setDefaultCloseOperation(EXIT_ON_CLOSE);
         c = getContentPane();
         c.setLayout(null);
         
         centerPanel = new CPanel();
         centerPanel.setBackground(Color.WHITE);
         centerPanel.setLayout(null);
         centerPanel.setSize(525,500);
         centerPanel.setLocation(0,0);
         c.add(centerPanel);

         southPanel = new SPanel();
         southPanel.setLayout(null);
         southPanel.setSize(525,200);
         southPanel.setLocation(0,500);
         southPanel.setBackground(color);
         c.add(southPanel);
         
       setSize(505,700);
       setVisible(true);
       setResizable(false);
       setLocationRelativeTo(null);
   }
      
   class CPanel extends JPanel {
      ImageIcon nonmenu, bus1, bus2, bus3, bus4;
      Image originImg, changedImg;
      JLabel label1, label2, label3, label4;
      public CPanel() {
        
          
          nonmenu = new ImageIcon("images/gogo.png");
          originImg = nonmenu.getImage();
          changedImg = originImg.getScaledInstance(510,500,Image.SCALE_SMOOTH);      
          bus4 = new ImageIcon(changedImg);
         
          
          label4= new JLabel(bus4);
          label4.setSize(500,500);
          add(label4);
      }
   }
   
   class SPanel extends JPanel {
         
      public SPanel() {
          idLabel = new JLabel("ID :");
          idLabel.setFont(new Font("a찐빵M", Font.BOLD, 20));
          idLabel.setSize(50,30);
          idLabel.setLocation(160,20);
          //ID 라벨
          
          idText = new JTextField(20);
          idText.setSize(150,30);
          idText.setLocation(200,20);
          idText.setFont(new Font("a찐빵M", Font.BOLD, 20));
          //아이디 입력할 창
           
          pwLabel = new JLabel("PW :");
          pwLabel.setSize(50,30);
          pwLabel.setLocation(150,55);
          pwLabel.setFont(new Font("a찐빵M", Font.BOLD, 20));
          //비밀번호 라벨
           
          pwText = new JPasswordField(20);
          pwText.setSize(150,30);
          pwText.setLocation(200,55);
          pwText.setFont(new Font("a찐빵M", Font.BOLD, 20));
          //비밀번호 입력할 창
           
          add(idLabel);
          add(idText);
          add(pwLabel);
          add(pwText);
             
          joinbtn = new JButton("회원가입");
          joinbtn.setSize(120,30);
          joinbtn.setLocation(130,100);
          joinbtn.setFont(new Font("a아띠", Font.PLAIN, 20));
          joinbtn.setBackground(Color.lightGray);
          joinbtn.setToolTipText("회원이 아니시라면 클릭!");
          add(joinbtn);
          joinbtn.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
                 // TODO Auto-generated method stub
                 new MemberJoin();
              }
                 
          });
          //회원가입 버튼
          
          okbtn = new JButton("확인");
          okbtn.setSize(85,30);
          okbtn.setLocation(275,100);
          okbtn.setFont(new Font("a아띠", Font.PLAIN, 20));
          okbtn.setBackground(Color.lightGray);
          okbtn.setToolTipText("아이디와 비밀번호를 다 입렸했을 경우 클릭!");
          add(okbtn);
          okbtn.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                     // TODO Auto-generated method stub
                     if(e.getSource()==okbtn) {
                
                            try {
                                MemberInfo dao = new MemberInfo(null,null,null,0,null);
                                String quary = "SELECT * FROM member WHERE memberid='"+ idText.getText()+"'";
                                conn = DBConnection.getConnection();
                                pstm = conn.prepareStatement(quary);
                                  rs = pstm.executeQuery();
                   
                                  
                                  if(rs.next()==false||(idText.getText().isEmpty())==true) {
                                      JOptionPane.showMessageDialog(null, "없는 아이디입니다. 아이디를 확인해주세요.", "Message", 
                                    		  JOptionPane.ERROR_MESSAGE);
                                      idText.setText("");
                                      pwText.setText("");
                                  }
                                  else {
                                  quary = "SELECT name, password FROM member where id='"+idText.getText()+"'";                    
                                  rs = pstm.executeQuery();
                                  //
                                  if(rs.next()==true||rs.getString(1).equals(idText.getText())) {
                                	  
                                     if(rs.getString("password").equals(pwText.getText())) {
                                         if(rs.getString("memberid").equals("admin")){
                                             JOptionPane.showMessageDialog(null, "관리자입니다.", "Message", 
                                                     JOptionPane.PLAIN_MESSAGE);
                                             //현재 사용자 ID 저장(다른 클래스에서도 접근 가능하도록 전역변수로 저장함)
                                             user = rs.getString("memberid");
                                         }
                                         else{
                                             JOptionPane.showMessageDialog(null, rs.getString("name")+"님 환영합니다!", "Message", 
                                                     JOptionPane.PLAIN_MESSAGE);
                                           //현재 사용자 ID 저장(다른 클래스에서도 접근 가능하도록 전역변수로 저장함)
                                             user = rs.getString("memberid");
                                         }
                                         new Main();
                                         dispose();
                                     }
                                     else {
                                         JOptionPane.showMessageDialog(null, "아이디/비밀번호가 틀렸습니다.", "Message", 
                                        		 JOptionPane.ERROR_MESSAGE);
                                         idText.setText("");
                                         pwText.setText("");
                                     }
                                  }
                                }

                                rs.close();
                            } catch (SQLException sqle) {
                                sqle.printStackTrace();
                                System.out.println("DB 연결 오류");
                                System.out.println("Error code returned: " + sqle.getErrorCode());
                            }
                     }
                  }
            //확인 버튼
            
      });

    }
   }
}