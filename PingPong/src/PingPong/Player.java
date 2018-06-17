package PingPong;

import java.awt.event.KeyEvent;

public class Player extends AbstractPlayer{

	public Player(int player) {
		//super(player);
		up = false; down = false;
		y = 210; yVel = 0;
		
		if(player == 0)
			x = 20;
		else
			x=760;
	}

	public void keyPressed(KeyEvent e, int vkW, int vkS) {
		if(e.getKeyCode() == vkW){
			setUp(true);
		}
		else if(e.getKeyCode() == vkS){
			setDown(true);
		}
	}
	public void keyReleased(KeyEvent e, int vkW, int vkS) {
		if(e.getKeyCode() == vkW){
			setUp(false);
		}
		else if(e.getKeyCode() == vkS){
			setDown(false);
		}
		
	}

	
}
