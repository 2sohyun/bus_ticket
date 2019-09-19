import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class MemberJoin extends JFrame{
   Container c;
   MemberPanel centerPanel;
   KakaoPanel northPanel;
   JButton overBtn, okBtn, resetBtn, cancelBtn; //중복확인, 확인, 다시쓰기, 취소 버튼
   JLabel idLabel, pwdLabel, nameLabel, phoneLabel, sexLabel;
   //아이디, 비밀번호, 이름, 전화번호, 성별 라벨
   JTextField idText, nameText, phoneText;
   //아이디, 이름, 전화번호 입력 칸
   JPasswordField pwdText;//비밀번호 입력 칸
   JRadioButton manRbtn, womanRbtn;
   ButtonGroup  group = new ButtonGroup(); //2개의 라디오 버튼을 묶을 버튼 그룹 객체 생성
   Color color = new Color(250, 210, 200);
   
   
   Connection conn = null; // DB연결된 상태(세션)을 담은 객체
   PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
   ResultSet rs = null;
   Statement stmt = null;
   String sql = null;
   boolean ok = false;
   String memgender = "성별";
   int success;
   
   
   public MemberJoin() {
      setTitle("고속버스 예매 시스템");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      c = getContentPane();

      northPanel = new KakaoPanel();
      centerPanel = new MemberPanel();
      centerPanel.setBackground(color);
      c.add(northPanel, BorderLayout.NORTH);
      c.add(centerPanel, BorderLayout.CENTER);
      
      setSize(400,450);
      setVisible(true);
	  setResizable(false);
	  setLocationRelativeTo(null);
   }
   
   class MemberPanel extends JPanel {
	   
	   MemberPanel() {
		   setLayout(null);
		   
		   nameLabel = new JLabel("이름 :");
		   nameLabel.setFont(new Font("a시월구일2", Font.PLAIN, 20));
		   nameLabel.setSize(100,20);
		   nameLabel.setLocation(35,35);
		   //이름 라벨
		      
		   nameText = new JTextField(15);
		   nameText.setSize(100,25);
		   nameText.setLocation(100,33);
		   nameText.setFont(new Font("a찐빵M", Font.BOLD, 20));
		   //이름 입력할 창
		      
		   idLabel = new JLabel("아이디 :");
		   idLabel.setFont(new Font("a시월구일2", Font.PLAIN, 20));
		   idLabel.setSize(100,20);
		   idLabel.setLocation(35,78);
		   //ID 라벨
		      
		   idText = new JTextField(15);
		   idText.setSize(125,25);
		   idText.setLocation(120,76);
		   idText.setFont(new Font("a찐빵M", Font.BOLD, 20));
		   //아이디 입력할 창
		      
		   overBtn = new JButton("중복확인");
		   overBtn.setSize(90,25);
		   overBtn.setLocation(255,76);
		   overBtn.setBackground(Color.lightGray);
		   overBtn.addActionListener(new ActionListener() {

				@Override
				//id중복확인(db연동)
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(e.getSource()==overBtn) {
					       
					       try {
					           
					           String quary = "SELECT * FROM member WHERE memberid='"+ idText.getText()+"'";
					           conn = DBConnection.getConnection();
					           pstm = conn.prepareStatement(quary);
				               rs = pstm.executeQuery();
				 
				                if (rs.next() == true || (idText.getText().isEmpty()) == true) {
				                	// 이미 id가 존재하다면 다른 id 쓰게 함
						            JOptionPane.showMessageDialog(null, "해당 ID는 사용이 불가능합니다. 다시 작성해주세요.", "Message", 
						            		JOptionPane.WARNING_MESSAGE);
						            idText.setText("");
				                } else {
						            JOptionPane.showMessageDialog(null, "사용 가능한 ID 입니다.", "Message", 
											JOptionPane.PLAIN_MESSAGE);
				                }

					       } catch (SQLException sqle) {
					    	  sqle.printStackTrace();
					       }
					}
				}
			    	  
			   });
		   //ID 중복확인 버튼
		      
		   pwdLabel = new JLabel("비밀번호 :");
		   pwdLabel.setSize(150,25);
		   pwdLabel.setLocation(35,120);
		   pwdLabel.setFont(new Font("a시월구일2", Font.PLAIN, 20));
		   //비밀번호 라벨
		      
		   pwdText = new JPasswordField(20);
		   pwdText.setSize(160,25);
		   pwdText.setLocation(140,120);
		   pwdText.setFont(new Font("a찐빵M", Font.BOLD, 20));
		   //비밀번호 입력할 창
		      
		   phoneLabel = new JLabel("전화번호 :");
		   phoneLabel.setSize(150,25);
		   phoneLabel.setLocation(35,165);
		   phoneLabel.setFont(new Font("a시월구일2", Font.PLAIN, 20));
		   //전화번호 라벨
		      
		   phoneText = new JTextField(10);
		   phoneText.setSize(150,25);
		   phoneText.setLocation(140,165);
		   phoneText.setFont(new Font("a찐빵M", Font.BOLD, 20));
		   //전화번호 입력할 창
		      
		   JLabel mlabel = new JLabel("('-'없이 입력)");
		   mlabel.setSize(120,25);
		   mlabel.setLocation(290,165);
		   mlabel.setFont(new Font("a시월구일2", Font.PLAIN, 15));
		   add(mlabel);
		   //전화번호 입력시 주의!
		      
		   sexLabel = new JLabel("성별 :");
		   sexLabel.setSize(150,25);
		   sexLabel.setLocation(35,210);
		   sexLabel.setFont(new Font("a시월구일2", Font.PLAIN, 20));
		   //성별 라벨
		      
		   manRbtn = new JRadioButton("남자");
		   manRbtn.setFont(new Font("a찐빵M", Font.BOLD, 20));
		   manRbtn.setSize(70,25);
		   manRbtn.setLocation(100,210);
		   manRbtn.setBackground(color);
		   group.add(manRbtn);
		      
		   womanRbtn = new JRadioButton("여자");
		   womanRbtn.setFont(new Font("a찐빵M", Font.BOLD, 20));
		   womanRbtn.setSize(70,25);
		   womanRbtn.setLocation(180,210);
		   womanRbtn.setBackground(color);
		   group.add(womanRbtn);
		      
		   resetBtn = new JButton("다시쓰기");
		   resetBtn.setSize(90,25);
		   resetBtn.setLocation(50,260);
		   resetBtn.setBackground(Color.lightGray);
		   resetBtn.addActionListener(new ActionListener() {

			@Override
		    public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				idText.setText("");
				pwdText.setText("");
				nameText.setText("");
				phoneText.setText("");
				group.clearSelection();	
			 }
		   });
		      
		   okBtn = new JButton("확인");
		   okBtn.setSize(70,25);
		   okBtn.setLocation(150,260);
		   okBtn.setBackground(Color.lightGray);
		   okBtn.addActionListener(new ActionListener() {

			@Override
			//회원가입 db연동
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==okBtn) {
				       
				       try {
				           
				           String quary = "INSERT INTO member(memberid,password,name,cellphone,gender) VALUES(?,?,?,?,?)";
				           
				           conn = DBConnection.getConnection();
				           pstm = conn.prepareStatement(quary);


				           pstm.setString(1, idText.getText());
				           pstm.setString(2, pwdText.getText());
				           pstm.setString(3, nameText.getText());
				           pstm.setString(4, phoneText.getText());
					        if(manRbtn.isSelected()){
					            memgender="남자";
					            pstm.setString(5, memgender);
					        }else if(womanRbtn.isSelected()){
					        	memgender="여자";
					        	pstm.setString(5, memgender);
					        }
				           
			                if ((idText.getText().isEmpty()) == true||(pwdText.getText().isEmpty()) == true||
			                		(nameText.getText().isEmpty()) == true||(phoneText.getText().isEmpty()) == true||
			                				(memgender == "성별")) {
			                	// 이미 id가 존재하다면 다른 id 쓰게 함
					            JOptionPane.showMessageDialog(null, "모두 입력하지 않았습니다. 모두 입력해 주시기 바랍니다.", "Message", 
					            		JOptionPane.ERROR_MESSAGE);
					            success = 0;
			                } else {
					            success = pstm.executeUpdate();
			                }

				           
				           if(success > 0) {
				               System.out.println("데이터 입력 성공");
				               ok = true;
				           }
				           else
				               System.out.println("데이터 입력 실패");
				           
					       if(ok){
					            
					            JOptionPane.showMessageDialog(null, "가입이 완료되었습니다.", "Message", 
										JOptionPane.PLAIN_MESSAGE);
					            dispose();

					       }else{
					            
					            JOptionPane.showMessageDialog(null, "가입이 정상적으로 처리되지 않았습니다.", "Message", 
					            		JOptionPane.ERROR_MESSAGE);
					       }

				           
				       } catch (SQLException sqle) {
				           sqle.printStackTrace();
				       }
				}
			}
		    	  
		   });
		      
		   cancelBtn = new JButton("취소");
		   cancelBtn.setSize(70,25);
		   cancelBtn.setLocation(230,260);
		   cancelBtn.setBackground(Color.lightGray);
		   cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				idText.setText("");
				pwdText.setText("");
				nameText.setText("");
				phoneText.setText("");
				group.clearSelection();	
				dispose();
			}
		    	  
		   });
		      
		   add(nameLabel);
		   add(nameText);
		   add(idLabel);
		   add(idText);
		   add(overBtn);
		   add(pwdLabel);
		   add(pwdText);
		   add(phoneLabel);
		   add(phoneText);
		   add(sexLabel);
		   add(manRbtn);
		   add(womanRbtn);
		   add(okBtn);
		   add(resetBtn);
		   add(cancelBtn);
	   }
    }
   
   class KakaoPanel extends JPanel {
	   KakaoPanel() {
		   setLayout(new FlowLayout());
		   setBackground(color);
		   
		   ImageIcon non = new ImageIcon("images/join.png");
		   Image originImg1 = non.getImage();
		   Image changedImg1 = originImg1.getScaledInstance(370,85,Image.SCALE_SMOOTH);      
		   ImageIcon kakao = new ImageIcon(changedImg1);
			 
		   JLabel kakaoLabel = new JLabel(kakao);
		   add(kakaoLabel);  
	   }
   }
}

