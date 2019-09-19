import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.sql.*;

public class Main extends JFrame{
	MenuPanel menupanel;
	LogoPanel logopanel;
	Container c;
	JLabel logolabel;
	String logoImages[] = {"image/배너1.png","image/배너2.png","image/배너3.png","image/배너4.png"};
	ImageIcon logo1 ,logo2, logo3, logo4, logo5, logo6, logo7, logo8,logo9, logo10, logo11, logo12;
	ImageIcon menuLogo1, menuLogo2, menuLogo3, menuLogo4, menuLogo5, nonmenu;
	ImageIcon kakao, friend, apeach;
	ImageIcon nontext, text1, text2, text3, text4, text5;
	JLabel label1, label2, label3, label4, label5, friendlabel, apeachlabel, label;
	JLabel textimg1,textimg2,textimg3,textimg4,textimg5;
	
	String userid = Login.user; // 현재 사용자의 ID를 받아옴
	
	public Main() {
		setTitle("고속버스 예매 시스템");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		c = getContentPane();
		c.setLayout(new BorderLayout());
		
		logopanel = new LogoPanel();
		c.add(logopanel, BorderLayout.NORTH);
		
		menupanel = new MenuPanel();
		c.add(menupanel, BorderLayout.CENTER);
		
		Thread thread = new Thread(new Runnable()
	       {
	            public void run()
	            {
	               int i=0;
	               while(true) {
	                  if(i==0) {
	                     logolabel.setIcon(logo1);
	                     i=1;
	                   }else if(logolabel.getIcon()==logo1) {
	                     logolabel.setIcon(logo2);
	                  }else if(logolabel.getIcon()==logo2){
	                     logolabel.setIcon(logo3);
	                  }else if(logolabel.getIcon()==logo3){
	                     logolabel.setIcon(logo4);
	                  }else if(logolabel.getIcon()==logo4){
	                     logolabel.setIcon(logo5);
	                  }else if(logolabel.getIcon()==logo5){
	                     logolabel.setIcon(logo6);
	                  }else if(logolabel.getIcon()==logo6){
	                     logolabel.setIcon(logo7);
	                  }else if(logolabel.getIcon()==logo7){
	                     if(i<=5) {
	                        logolabel.setIcon(logo7);
	                        i++;
	                     }else {
	                        logolabel.setIcon(logo8);
	                     }
	                  }else if(logolabel.getIcon()==logo8){
	                     logolabel.setIcon(logo9);
	                  }else if(logolabel.getIcon()==logo9){
	                     logolabel.setIcon(logo10);
	                  }else if(logolabel.getIcon()==logo10){
	                     logolabel.setIcon(logo11);
	                  }else if(logolabel.getIcon()==logo11){
	                     logolabel.setIcon(logo12);
	                  }else {
	                     logolabel.setIcon(logo1);
	                     i=1;
	                  }
	                  
	                  try
	                  {
	                     Thread.sleep(150);
	                     repaint();
	                  }
	                  catch(Exception e) {}
	               }
	            }
	        });
	        thread.start();

		
		setSize(600,650);
		setVisible(true);
		setResizable(false);
	    setLocationRelativeTo(null);
		
		
	}
	
	class MenuPanel extends JPanel {
		
		public MenuPanel() {
			setBackground(Color.WHITE);
			setLayout(null);
			
			kakaofriend();		    
			MenuImage();
			speaking();
			
		    
		    label1= new JLabel(menuLogo1);
		    label1.setSize(200,80);
		    label1.setLocation(200,10);
		    add(label1);
		    label1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					new Notice();
					dispose(); // 해당 프레임만 사라짐
					//System.exit(0)을 사용 시 모든 프레임 사라짐
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					textimg1.setVisible(true);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					textimg1.setVisible(false);
				}
		    });
		    
		    label2= new JLabel(menuLogo2);
		    label2.setSize(200,80);
		    label2.setLocation(200,100);
		    add(label2);
		    label2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					new Search();
					dispose();
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					textimg2.setVisible(true);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					textimg2.setVisible(false);
				}
		    });
		    
		    label3= new JLabel(menuLogo3);
		    label3.setSize(200,80);
		    label3.setLocation(200,190);
		    add(label3);
		    label3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					new TicketCheck();
					dispose();
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					textimg3.setVisible(true);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					textimg3.setVisible(false);
				}
		    });
		    
		    label4= new JLabel(menuLogo4);
		    label4.setSize(200,80);
		    label4.setLocation(200,280);
		    add(label4);
		    label4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					new Terminal();
					dispose();
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					textimg4.setVisible(true);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					textimg4.setVisible(false);
				}
		    });
		    
		    label5= new JLabel(menuLogo5);
		    label5.setSize(200,80);
		    label5.setLocation(200,370);
		    add(label5);
		    label5.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub					
					new Login();
					dispose();
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					textimg5.setVisible(true);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					textimg5.setVisible(false);
				}
		    });
		    
		}
	}
	
	void MenuImage() { //메뉴이미지 5개
		 nonmenu = new ImageIcon("images/메뉴로고1.png");
		 Image originImg1 = nonmenu.getImage();
		 Image changedImg1 = originImg1.getScaledInstance(200,80,Image.SCALE_SMOOTH);      
		 menuLogo1 = new ImageIcon(changedImg1);
		    
		 nonmenu = new ImageIcon("images/메뉴로고2.png");
		 Image originImg2 = nonmenu.getImage();
		 Image changedImg2 = originImg2.getScaledInstance(200,80,Image.SCALE_SMOOTH);      
		 menuLogo2 = new ImageIcon(changedImg2);
		 
		 nonmenu = new ImageIcon("images/메뉴로고3.png");
		 Image originImg3 = nonmenu.getImage();
		 Image changedImg3 = originImg3.getScaledInstance(200,80,Image.SCALE_SMOOTH);      
		 menuLogo3 = new ImageIcon(changedImg3);
		    
		 nonmenu = new ImageIcon("images/메뉴로고4.png");
		 Image originImg4 = nonmenu.getImage();
		 Image changedImg4 = originImg4.getScaledInstance(200,80,Image.SCALE_SMOOTH);      
		 menuLogo4 = new ImageIcon(changedImg4);
		    
		 nonmenu = new ImageIcon("images/메뉴로고5.png");
		 Image originImg5 = nonmenu.getImage();
		 Image changedImg5 = originImg5.getScaledInstance(200,80,Image.SCALE_SMOOTH);
		 menuLogo5 = new ImageIcon(changedImg5);
	}
	
	void kakaofriend() {
	    
		kakao = new ImageIcon("images/friend.png");
	    Image originfriend = kakao.getImage();
	    Image changedfriend = originfriend.getScaledInstance(180,160,Image.SCALE_SMOOTH);      
	    friend = new ImageIcon(changedfriend);
	    
	    friendlabel= new JLabel(friend);
	    friendlabel.setSize(180,160);
	    friendlabel.setLocation(15,410);
	    add(friendlabel);
	    
	    kakao = new ImageIcon("images/apeach.png");
	    Image originapeach = kakao.getImage();
	    Image changedapeach = originapeach.getScaledInstance(185,150,Image.SCALE_SMOOTH);      
	    apeach = new ImageIcon(changedapeach);
	    
	    apeachlabel= new JLabel(apeach);
	    apeachlabel.setSize(185,150);
	    apeachlabel.setLocation(410,420);
	    add(apeachlabel);
	}
	
	void speaking() {
		
		nontext = new ImageIcon("images/말풍선1.png");
		Image origintext1 = nontext.getImage();
		Image changedtext1 = origintext1.getScaledInstance(150,100,Image.SCALE_SMOOTH);      
		text1 = new ImageIcon(changedtext1);
		
		textimg1= new JLabel(text1);
		textimg1.setSize(150,100);
		textimg1.setLocation(25,312);
		textimg1.setVisible(false);
	    add(textimg1);
	    
		nontext = new ImageIcon("images/말풍선2.png");
		Image origintext2 = nontext.getImage();
		Image changedtext2 = origintext2.getScaledInstance(150,100,Image.SCALE_SMOOTH);      
		text2 = new ImageIcon(changedtext2);
		
		textimg2= new JLabel(text2);
		textimg2.setSize(150,100);
		textimg2.setLocation(420,320);
		textimg2.setVisible(false);
	    add(textimg2);
	    
		nontext = new ImageIcon("images/말풍선3.png");
		Image origintext3 = nontext.getImage();
		Image changedtext3 = origintext3.getScaledInstance(150,100,Image.SCALE_SMOOTH);      
		text3 = new ImageIcon(changedtext3);
		
		textimg3= new JLabel(text3);
		textimg3.setSize(150,100);
		textimg3.setLocation(25,312);
		textimg3.setVisible(false);
	    add(textimg3);
	    
		nontext = new ImageIcon("images/말풍선4.png");
		Image origintext4 = nontext.getImage();
		Image changedtext4 = origintext4.getScaledInstance(150,100,Image.SCALE_SMOOTH);      
		text4 = new ImageIcon(changedtext4);
		
		textimg4= new JLabel(text4);
		textimg4.setSize(150,100);
		textimg4.setLocation(420,320);
		textimg4.setVisible(false);
	    add(textimg4);
	    
		nontext = new ImageIcon("images/말풍선5.png");
		Image origintext5 = nontext.getImage();
		Image changedtext5 = origintext5.getScaledInstance(150,100,Image.SCALE_SMOOTH);      
		text5 = new ImageIcon(changedtext5);
		
		textimg5= new JLabel(text5);
		textimg5.setSize(150,100);
		textimg5.setLocation(25,312);
		textimg5.setVisible(false);
	    add(textimg5);
	}	

	class LogoPanel extends JPanel {
		ImageIcon nonmenu;
		Image originImg, changedImg;
		
		public LogoPanel() {
			setBackground(Color.WHITE);
			nonmenu = new ImageIcon("images/배너1.png");
			originImg = nonmenu.getImage();
			changedImg = originImg.getScaledInstance(600,120,Image.SCALE_SMOOTH);      
			logo1 = new ImageIcon(changedImg);
			
			nonmenu = new ImageIcon("images/배너2.png");
			originImg = nonmenu.getImage();
			changedImg = originImg.getScaledInstance(600,120,Image.SCALE_SMOOTH);      
			logo2 = new ImageIcon(changedImg);
			
			nonmenu = new ImageIcon("images/배너3.png");
			originImg = nonmenu.getImage();
			changedImg = originImg.getScaledInstance(600,120,Image.SCALE_SMOOTH);      
			logo3 = new ImageIcon(changedImg);
			
			nonmenu = new ImageIcon("images/배너4.png");
			originImg = nonmenu.getImage();
			changedImg = originImg.getScaledInstance(600,120,Image.SCALE_SMOOTH);      
			logo4 = new ImageIcon(changedImg);
			
			nonmenu = new ImageIcon("images/배너5.png");
			originImg = nonmenu.getImage();
			changedImg = originImg.getScaledInstance(600,120,Image.SCALE_SMOOTH);      
			logo5 = new ImageIcon(changedImg);
			
			nonmenu = new ImageIcon("images/배너6.png");
			originImg = nonmenu.getImage();
			changedImg = originImg.getScaledInstance(600,120,Image.SCALE_SMOOTH);      
			logo6 = new ImageIcon(changedImg);
			
			nonmenu = new ImageIcon("images/배너7.png");
			originImg = nonmenu.getImage();
			changedImg = originImg.getScaledInstance(600,120,Image.SCALE_SMOOTH);      
			logo7 = new ImageIcon(changedImg);
			
			nonmenu = new ImageIcon("images/배너8.png");
			originImg = nonmenu.getImage();
			changedImg = originImg.getScaledInstance(600,120,Image.SCALE_SMOOTH);      
			logo8 = new ImageIcon(changedImg);
			
			nonmenu = new ImageIcon("images/배너9.png");
			originImg = nonmenu.getImage();
	    	changedImg = originImg.getScaledInstance(600,120,Image.SCALE_SMOOTH);      
			logo9 = new ImageIcon(changedImg);
			
			nonmenu = new ImageIcon("images/배너10.png");
			originImg = nonmenu.getImage();
	    	changedImg = originImg.getScaledInstance(600,120,Image.SCALE_SMOOTH);      
			logo10 = new ImageIcon(changedImg);
			
			nonmenu = new ImageIcon("images/배너11.png");
			originImg = nonmenu.getImage();
			changedImg = originImg.getScaledInstance(600,120,Image.SCALE_SMOOTH);      
			logo11 = new ImageIcon(changedImg);
			
			nonmenu = new ImageIcon("images/배너12.png");
			originImg = nonmenu.getImage();
			changedImg = originImg.getScaledInstance(600,120,Image.SCALE_SMOOTH);      
			logo12 = new ImageIcon(changedImg);
		    
     	    logolabel= new JLabel(logo1);
		    add(logolabel);
		}
	}
}
