package PingPong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import GUI.GUIMainMenu;

/**Author @Even Berge Sandvik
 * 
 * PingPong main class
 * 
 */
public class PingPong extends Applet implements Runnable, KeyListener{
	//private GUIMainMenu mainmenu;
	final int WIDTH = 800, HEIGHT = 550; 
	Thread thread;
	Player player1, player2;
	AI playerAI;
	Ball ball = new Ball();
	starWild star;
	Graphics gfx;
	Image img;
	int score1 = 0, score2 = 0;
	boolean gameStarted = false;
	boolean logo = true;
	private static final long serialVersionUID = -4170574729049260633L;

	public void init(){
		star = new starWild(ball);
		this.addKeyListener(this);
		this.resize(WIDTH, HEIGHT);
		player1 = new Player("player1");
		player2 = new Player("player2");
		//playerAI = (AI) new AI(1,ball);
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g){
		if(logo){
			gfx.setColor(Color.white);
			gfx.fillRect(0, 0, WIDTH, HEIGHT);
			Font font = new Font("helvetica", Font.BOLD, 160);
			gfx.setFont(font);
			//star.rainbow(gfx);
			gfx.setColor(Color.black);
			gfx.drawString("PONG", (WIDTH/2)-230, (HEIGHT/2)+50);
			gfx.setColor(Color.black);
			font = new Font("helvetica", Font.BOLD, 25);
			gfx.setFont(font);
			gfx.drawString("{ Even Berge Sandvik }", (WIDTH/2)-140, (HEIGHT/2)+130);
			g.drawImage(img, 0, 0, this);
			try { TimeUnit.MILLISECONDS.sleep(2200);} 
			catch (InterruptedException e) {e.printStackTrace();}
			logo = false;
		}
		gfx.setColor(Color.white);//Senere sett til bakgrunnbilde?
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		gfx.setColor(Color.black);
		Font font = new Font("helvetica", Font.BOLD, 22);
		gfx.setFont(font);
		gfx.drawString("Controls: W/S and I/K", (WIDTH/2)-100, 30);
		gfx.drawString(Integer.toString(score1), 40, 30);
		gfx.drawString(Integer.toString(score2), 750, 30);
		if(!gameStarted){
			gfx.drawString("Press Enter to start", (WIDTH/2)-95, 340);	
		}
		//balls goes outside
		if(ball.getX()<-10 || ball.getX()>810){
			if(ball.getX()<-10)
			score2++;
			else if(ball.getX()>810)
				score1++;
			g.setFont(font);
			if(score1 > 20)
			{
				g.drawString("Player1 WINS", (WIDTH/2)-60, (HEIGHT/2));
				try { TimeUnit.MILLISECONDS.sleep(4000);} 
				catch (InterruptedException e) {e.printStackTrace();}
				restartGame();
			}
			else if(score2 > 20)
			{
				g.drawString("Player2 WINS", (WIDTH/2)-60, (HEIGHT/2));
				try { TimeUnit.MILLISECONDS.sleep(4000);} 
				catch (InterruptedException e) {e.printStackTrace();}
				restartGame();
			}
			else{
			//small pause
			g.drawString("Pause", (WIDTH/2)-30, (HEIGHT/2));
			try { TimeUnit.MILLISECONDS.sleep(300);} 
			catch (InterruptedException e) {e.printStackTrace();}
			}
			ball = new Ball();
			//playerAI.setBall(ball);
		}
		else{
			player1.draw(gfx);
			player2.draw(gfx);
			//playerAI.draw(gfx);
			ball.draw(gfx);
			star.draw(gfx);
		}
		g.drawImage(img, 0, 0, this);
	}

	private void restartGame() {
		score1 = 0;
		score2 = 0;
		gameStarted = false;
		player1.y = 210;
		player2.y = 210;
		star = new starWild(ball);
	}

	
	
	public void update(Graphics gfx){
		paint(gfx);
	}

	public void run() {
		while(true){
			repaint();
			if(gameStarted){
				ball.move();
				player1.move();
				//playerAI.moveAI();
				player2.move();
				
				ball.checkPlayerBallCollision(player1, player2);
				//ball.checkPlayerBallCollision(player1, playerAI);
				if(star.equals(null)) star = new starWild(ball);
			}
			try {
				Thread.sleep(7);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	//Controls
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			gameStarted = !gameStarted;
		}
		player1.keyPressed(e, KeyEvent.VK_W, KeyEvent.VK_S);
		player2.keyPressed(e, KeyEvent.VK_I, KeyEvent.VK_K);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player1.keyReleased(e, KeyEvent.VK_W, KeyEvent.VK_S);
		player2.keyReleased(e, KeyEvent.VK_I, KeyEvent.VK_K);
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
