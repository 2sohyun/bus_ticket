import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Notice_content extends JFrame{
	
	Connection conn = null; // DB연결된 상태(세션)을 담은 객체
	PreparedStatement pstm = null;  // SQL 문을 나타내는 객체
	ResultSet rs = null;
	Statement stmt = null;
	String sql = null;
	boolean register = false;
	int success;
	
	Container c;
	CenterPanel centerpanel;
	Color color = new Color(250, 210, 200);
	
	Notice_content() {
		setTitle("공지사항 입력(관리자전용)");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		centerpanel = new CenterPanel();
		c.add(centerpanel, BorderLayout.CENTER);
		
		setSize(480,330);
		setVisible(true);
		setResizable(false);
		
	}
	
	class CenterPanel extends JPanel {
		
		CenterPanel() {
			
			setLayout(null);
			setBackground(color);
			
			//공지사항 제목 라벨
			JLabel titlelabel = new JLabel("제목");
			titlelabel.setSize(60,60);
			titlelabel.setLocation(40,20);
			titlelabel.setFont(new Font("a시월구일2", Font.PLAIN, 17));
			add(titlelabel);
				
			//공지사항 제목 입력 창
			JTextField text = new JTextField(10);
			text.setSize(200,25);
			text.setLocation(85,40);
			text.setFont(new Font("a시월구일2", Font.PLAIN, 15));
			add(text);
			
			//공지사항 내용 라벨
			JLabel contentlabel = new JLabel("내용");
			contentlabel.setSize(60,60);
			contentlabel.setLocation(40,55);
			contentlabel.setFont(new Font("a시월구일2", Font.PLAIN, 17));
			add(contentlabel);
			
			//공지사항 내용 입력 창
			JTextArea area = new JTextArea(30,30);
			area.setSize(400,150);
			area.setLocation(40,100);
			area.setFont(new Font("a시월구일2", Font.PLAIN, 15));
			JScrollPane scroll = new JScrollPane(area);
			scroll.setBounds(35,100,400,150); //JScrollPane에 포함된 컴포넌트 크기를 지정함!
			add(scroll);
			
			//등록 버튼
			JButton button = new JButton("등록");
			button.setSize(65,25);
			button.setLocation(300,40);
			button.setFont(new Font("a시월구일2", Font.PLAIN, 15));
			button.setBackground(Color.LIGHT_GRAY);
			add(button);
			button.addActionListener(new ActionListener() {

					@Override
					//공지사항 등록버튼(db연동)
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(e.getSource()==button) {
						       
						       try {
						           
						    	   String quary = "INSERT INTO notice(title, content) VALUES(?,?)";
						           conn = DBConnection.getConnection();
						           pstm = conn.prepareStatement(quary);


						           pstm.setString(1, text.getText());
						           pstm.setString(2, area.getText());
						           
						           if ((text.getText().isEmpty()) == true||(area.getText().isEmpty()) == true) {
					                	// 내용이 비워져 있다면 입력하도록 하기
							            JOptionPane.showMessageDialog(null, "내용을 모두 입력해 주십시오.", "Message", 
							            		JOptionPane.ERROR_MESSAGE);
							            success = 0;
					                } else {
							            success = pstm.executeUpdate();
					                }
						           
						           if(success > 0) {
						               System.out.println("데이터 입력 성공");
						               register = true;
						           }
						           else
						               System.out.println("데이터 입력 실패");
						           
							       if(register){
							    	   
							            JOptionPane.showMessageDialog(null, "등록되었습니다.", "Message", 
												JOptionPane.PLAIN_MESSAGE);
							            dispose();

							       }else {
							    	   
							            JOptionPane.showMessageDialog(null, "등록되지 않았습니다.", "Message", 
							            		JOptionPane.ERROR_MESSAGE);
							            
							       }
						           

						       } catch (SQLException sqle) {
						    	  sqle.printStackTrace();
						       }
						}
					}
				    	  
				   });
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Notice_content();
	}

}
