package classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OuterFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	JButton startGame;
	JButton controles;
	JButton quitGame;
	JPanel container;
	
	public OuterFrame(){
		super("Super Pacman Hero ver 0.21 2019 (c)");
		this.setSize(500, 500);
		container = new JPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setFocusable(false);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setBackground(Color.GRAY);
		
		OuterFrame outerFrame = this;
		
		Font f = new Font("Ariel", Font.BOLD, 30);
		startGame = new JButton("Start New Game");
		startGame.setLayout(null);
		startGame.setPreferredSize(new Dimension(400,100));
		startGame.setLocation(0, 200);
		startGame.setFont(f);		
		startGame.setBackground(Color.green);
		
		controles = new JButton("Controls");
		controles.setPreferredSize(new Dimension(400,100));
		controles.setFont(f);
		controles.setLocation(0, 400);
		controles.setBackground(Color.blue);
		controles.setForeground(Color.white);
		controles.setLayout(null);
		
		quitGame = new JButton("Quit Game");
		quitGame.setLayout(null);
		quitGame.setPreferredSize(new Dimension(400,100));
		quitGame.setLocation(0, 600);
		quitGame.setFont(f);		
		quitGame.setBackground(Color.red);

		container.add(startGame);
		container.add(controles);
		container.add(quitGame);
		this.add(container, BorderLayout.CENTER);

		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				JFrame f = new JFrame("Super Pacman Hero ver 0.21 2019 (c)");
				JPanel container = new JPanel();
				JPanel sidebarContainer = new JPanel();
				JPanel gamePanelContainer = new JPanel();
				GamePanel gp = new GamePanel();

				sidebarContainer.setPreferredSize(new Dimension(500, 1050));
				sidebarContainer.setBackground(Color.darkGray);
				sidebarContainer.add(gp.getSidebar(), BorderLayout.CENTER);

				gamePanelContainer.setPreferredSize(new Dimension(1050, 1050));
				gamePanelContainer.setBackground(new Color(51, 51, 51));
				gamePanelContainer.add(gp, BorderLayout.NORTH);

				container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
				container.add(gamePanelContainer);
				container.add(sidebarContainer);

				f.add(container);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(1550, 1050);
				f.setResizable(false);
				f.setVisible(true);
				f.setFocusable(false);
				gp.hideMouseCursor();

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		controles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(outerFrame,"CONTROLES:\n   W - move up\n   S - move down\n   D - move right\n   A - move left.\n * NOTE: Hero fires ONLY when not moved *" );
			}
		});
		
		quitGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				outerFrame.dispose();
				
			}
		});
	}
	
	public static void main(String[] args) {
		
		new OuterFrame();

	}
}
