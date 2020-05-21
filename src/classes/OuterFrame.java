//import java.applet.Applet;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.FileNotFoundException;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.net.URL;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//public class OuterFrame extends JFrame {
//	
//	JButton start;
//	JButton info;
//	
//	public OuterFrame(){
//		super("Bubble Trouble (c)");
//		this.setSize(1024, 768);
//		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setVisible(true);
//		this.setFocusable(false);
//		this.setBackground(Color.red);
//		OuterFrame outerFrame = this;
//		
//		Font f = new Font("TimesRoman", Font.BOLD, 30);
//		start = new JButton("Start");
//		start.setBounds(300, 250, 350, 100);
//		start.setFont(f);		
//		start.setBackground(Color.green);
//		
//		info = new JButton("Info");
//		info.setBounds(300 , 400, 350, 100);
//		info.setFont(f);
//		info.setBackground(Color.blue);
//		info.setForeground(Color.white);
//
//		this.add(start);
//		this.add(info);
//
//		start.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent evt) {
//				JFrame frame = new JFrame();
//				GamePanel gp = new GamePanel(frame);
//				frame.setSize(gp.width, gp.height);
//				frame.setUndecorated(true);
//				frame.setLocationRelativeTo(null);
//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				frame.setResizable(false);
//				frame.setVisible(true);
//				frame.setFocusable(false);
//				frame.add(gp);
//				gp.hideMouseCursor();
//			}
//		});
//		
//		info.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent evt) {
//				JOptionPane.showMessageDialog(outerFrame,"LEVEL SETTINGS\n*Novice = 2 Balls And Start Low Speed\n*Intermediate = 3 Balls And Start Low Speed\n*Advanced = 4 Balls And Start Low Speed\nNOTE: All Level Starts Low Speed, If You Want To Increase Speed, Please Click Options Button In The Game.\nKEYBOARD CONTROL\n*Arrow Key = Move Man.\n*Space = Fire Bow.\n*Z = Standart Bow.\n*X = Second Bow." );
//			}
//		});
//	}
//	
//	public static void main(String[] args) {
//		
//		new OuterFrame();
//
//	}
//}
