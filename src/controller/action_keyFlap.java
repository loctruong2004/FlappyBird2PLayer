package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.FlappyBird;

public class action_keyFlap implements KeyListener{
    
	private FlappyBird Fl;
	private int velocityX = -4;
	private int velocityY = 0;
	private int gravity = 1;
	public int broadWidth = 360;
	public int broadHeight = 640;
	public action_keyFlap(FlappyBird fl) {
		super();
		Fl = fl;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Fl.setVelocityY(-9); 
			if(!Fl.isStart()) {
				Fl.PlacePipesTimerStart();
				Fl.getGameLoopStart();
				Fl.setStart(true); 
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			Fl.setVelocityY1(-9); 
			if(!Fl.isStart()) {
				Fl.PlacePipesTimerStart();
				Fl.getGameLoopStart();
				Fl.setStart(true); 
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Fl.PlacePipesTimerStop();
			Fl.getGameLoopStop();
			Fl.setStart(false); 
		}
		// restart game
		if (Fl.isGameOver() && e.getKeyCode() == KeyEvent.VK_ENTER) {
			Fl.setBY(360/2);
			Fl.setB1Y(360/2);
			Fl.setVelocityY(0);
			Fl.setVelocityY1(0);
			Fl.getPipes().clear();
//			this.currPerson.setDiem((int)score);
            Fl.setB1IsContinue(true);
            Fl.setbIsContinue(true);
			Fl.setScore(0);
			Fl.setScore1(0);
			Fl.setGameOver(false);
			Fl.PlacePipesTimerStart();
			Fl.getGameLoopStart();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
