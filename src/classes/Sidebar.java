package classes;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class Sidebar extends JPanel {
	GamePanel gp;
	JProgressBar enemyWaveCountBar, playerHpBar, targetEnemyHpBar;
	int levelEnemyCount;
	public Sidebar(GamePanel gp) {
		this.gp = gp;
		setPreferredSize(new Dimension(460,940));
		setBackground(Color.black);
		
		levelEnemyCount = gp.enemies.size();
		
		enemyWaveCountBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, gp.enemies.size());
		enemyWaveCountBar.setValue(gp.enemies.size());
		enemyWaveCountBar.setStringPainted(true);
		enemyWaveCountBar.setForeground(new Color(0,0,255));
		enemyWaveCountBar.setPreferredSize(new Dimension(450, 50));
		enemyWaveCountBar.setFont(enemyWaveCountBar.getFont().deriveFont(36.0f));
		enemyWaveCountBar.setString(String.format("%d / %d", gp.enemies.size(), gp.enemies.size()));
		
		
		
		playerHpBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, (int)gp.player.getMaximumHp());
		playerHpBar.setStringPainted(true);
		playerHpBar.setValue((int)gp.player.getMaximumHp());
		playerHpBar.setFont(playerHpBar.getFont().deriveFont(36.0f));
		playerHpBar.setString(String.format("%.2f / %.2f", gp.player.getMaximumHp(), gp.player.getMaximumHp()));
		playerHpBar.setForeground(new Color(0,255,0));
		playerHpBar.setPreferredSize(new Dimension(450, 50));
		
		
		targetEnemyHpBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, (int)gp.targetedEnemy.getMaximumHp());
		targetEnemyHpBar.setStringPainted(true);
		targetEnemyHpBar.setValue((int)gp.targetedEnemy.getMaximumHp());
		targetEnemyHpBar.setFont(targetEnemyHpBar.getFont().deriveFont(36.0f));
		targetEnemyHpBar.setString(String.format("%.2f / %.2f", gp.targetedEnemy.getCurrentHp(), gp.targetedEnemy.getMaximumHp()));
		targetEnemyHpBar.setForeground(new Color(255,0,0));
		targetEnemyHpBar.setPreferredSize(new Dimension(450, 50));
		
		
		this.add(enemyWaveCountBar);
		this.add(playerHpBar);
		this.add(targetEnemyHpBar);
		
	}
	
	/**
	 * on reinit, set new max and set value to that max 
	 * @param max enemies amount
	 */
	public void setEnemyWaveMax(int max) {
		levelEnemyCount = max;
		enemyWaveCountBar.setMaximum(max);
		enemyWaveCountBar.setValue(max);
		enemyWaveCountBar.setString(String.format("%d / %d", max, max));
		
	}
	
	
	public void setPlayerHpMax(int max) {
		playerHpBar.setMaximum(max);
		playerHpBar.setString(String.format("%.2f / %.2f", gp.player.getCurrentHp(), max));
		
	}
	
	
	public void initTargetEnemyHpBar(Enemy targetEnemy) {
		targetEnemyHpBar.setMaximum((int)targetEnemy.getMaximumHp());
		targetEnemyHpBar.setValue((int)targetEnemy.getCurrentHp());
		targetEnemyHpBar.setString(String.format("%.2f / %.2f", targetEnemy.getCurrentHp() > 0 ? targetEnemy.getCurrentHp() : 0, targetEnemy.getMaximumHp()));
	}
	
	public void onEnemyDies() {
		enemyWaveCountBar.setValue(gp.enemies.size());
		enemyWaveCountBar.setString(String.format("%d / %d",gp.enemies.size(), levelEnemyCount));
		
	}
	
	public void onPlayerHit() {
		playerHpBar.setValue((int) gp.player.getCurrentHp());
		playerHpBar.setString(String.format("%.2f / %.2f", gp.player.getCurrentHp() > 0 ? gp.player.getCurrentHp() : 0, gp.player.getMaximumHp()));
	}

	public void onNewLevel(double max, double current) {
		playerHpBar.setMaximum((int)max);
		playerHpBar.setValue((int)current);
		playerHpBar.setString(String.format("%.2f / %.2f", current, max));
	}
	
}
