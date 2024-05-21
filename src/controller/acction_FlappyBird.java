package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.ImageIcon;

import model.DB;
import model.Pipe;
import model.bird;
import view.FlappyBird;

public class acction_FlappyBird implements ActionListener{
	
	private FlappyBird Fl;
	private int velocityX = -4;
	private int velocityY = 0; 
	private int gravity = 1;
	public int broadWidth = 360;
	public int broadHeight = 640;
	public acction_FlappyBird(FlappyBird flappyBird) {
		// TODO Auto-generated constructor stub
		this.Fl = flappyBird;
	}

	public void move() {
		Fl.setVelocityY(Fl.getVelocityY()+gravity);
		Fl.setBY(Math.max(Fl.getB().getY() + Fl.getVelocityY(), 0));
		// pipes
		for (int i = 0; i < Fl.getPipes().size(); i++) {
			Pipe pipe = Fl.getPipes().get(i);
			pipe.setX(pipe.getX() + velocityX);
			if (!pipe.isPassed() && Fl.getB().getX() > pipe.getX() + pipe.getWidth()) {
				pipe.setPassed(true);
				Fl.setScore(Fl.getScore()+0.5);
			}
			if (collision(Fl.getB(), pipe)) {
				Fl.setGameOver(true);
			}
		}
		if (Fl.getB().getY() > broadHeight) {
			Fl.setGameOver(true);
		}
	}
	public void move2() {
		Fl.setVelocityY(Fl.getVelocityY()+gravity);
		Fl.setVelocityY1(Fl.getVelocityY1()+gravity);
		Fl.setBY(Math.max(Fl.getB().getY() + Fl.getVelocityY(), 0));
		Fl.setB1Y(Math.max(Fl.getB1().getY() + Fl.getVelocityY1(), 0));
		Fl.getB1().setX(Fl.getB().getX()-50);
		// pipes
		for (int i = 0; i < Fl.getPipes().size(); i++) {
			Pipe pipe = Fl.getPipes().get(i);
			pipe.setX(pipe.getX() + velocityX);
			if (!pipe.isPassed() && Fl.getB().getX() > pipe.getX() + pipe.getWidth()) {
				pipe.setPassed(true);
				if(Fl.isbIsContinue()) {
					Fl.setScore(Fl.getScore()+0.5);
				}
				if(Fl.isB1IsContinue()) {
					 Fl.setScore1(Fl.getScore1()+0.5);
				}
			}
			if (collision(Fl.getB(), pipe)&&collision(Fl.getB1(), pipe)) {
				Fl.setGameOver(true);
			}else if(collision(Fl.getB1(), pipe)) {
				Fl.setB1IsContinue(false);
			}else  if(collision(Fl.getB(), pipe)) {
				Fl.setbIsContinue(false);
			}
		}
		if (Fl.getB().getY() > broadHeight&& Fl.getB1().getY() > broadHeight) {
			Fl.setGameOver(true);
		}else if(Fl.getB().getY() > broadHeight) {
			Fl.setbIsContinue(false);
		}else if(Fl.getB1().getY() > broadHeight) {
			Fl.setB1IsContinue(false);
		}
	}
	public boolean collision(bird a, Pipe b) {
		return a.getX() < b.getX() + b.getWidth() && a.getX() + a.getBirdWeight() > b.getX()
				&& a.getY() < b.getY() + b.getHeight() && a.getY() + a.getBirdHeight() > b.getY();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(Fl.isPlayers()) {
			move2();
		}else {
			move();
		}
		Fl.repaint();
		if (Fl.isGameOver()) {
			Fl.addListPlayer();
			if (Fl.getListPlayer().checkScore()) {
				Fl.addListPlayerDB();
				Fl.setRecords(true);
			}
			Fl.PlacePipesTimerStop();
			Fl.getGameLoopStop();
		}
		
	}
	

}
