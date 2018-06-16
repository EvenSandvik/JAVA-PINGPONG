package PingPong;

import java.util.Random;

/**
 * 
 * @author Even Berge Sandvik
 * Very dumb Bot
 *
 */
public class AI extends AbstractPlayer{
	Ball ball;
	double ballY;
	
	public AI(int player, Ball ball) {
		up = false; down = false;
		y = 210; yVel = 0;
		
		if(player == 0)
			x = 20;
		else
			x=760;
		this.ball = ball;
		ballY = ball.getY();
	}

	public void moveAI(){
		//legg til en listener elrns?
		Random rand = new Random();
		if(rand.nextInt(2)==1){
		if(ballY < this.getY()){ 
			moveUp();
		}
		else if(ballY > this.getY()){ 
			moveDown();
		}
		this.move();
		}
	}
	
	public void setBall(Ball ball){
		this.ball = ball;
	}
}
