package classes;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Sidebar extends JPanel {
	GamePanel gp;
	public Sidebar(GamePanel gp) {
		this.gp = gp;
		setPreferredSize(new Dimension(460,940));
		setBackground(Color.black);
		
		
	}
}
