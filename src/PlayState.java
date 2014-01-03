import java.util.Collections;
import java.util.Stack;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class PlayState extends BasicGameState {

	private Fish fish;
	private Cannon[] cannons;
	Thrower thrower;
	
	private Image bubbleImage;
	private Image background;
	private Sound ambiance;
	private Input input;
	
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {

		fish = new Fish();
		thrower = new Thrower();
		cannons = new Cannon[6];
		initCannons(false);
		
		bubbleImage = new Image("res/bubble.png");
		background = new Image("res/background.jpg");
		ambiance = new Sound("res/ambiance.wav");
		input = gc.getInput();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {

		if (!ambiance.playing()) {
			ambiance.loop();
		}
		
		fish.update();
		
		for (Cannon cannon : cannons) {
			
			cannon.update();
			
			for (int i = 0; i < cannon.getBubbles().size(); i++)
				if (cannon.getBubbles().get(i).y > 600)
					cannon.getBubbles().remove(cannon.getBubbles().get(i));
				else
					cannon.getBubbles().get(i).update();
		}
		if (input.isKeyDown(Input.KEY_SPACE)) {
			
			if (!isCannonsAnimating())
				initCannons(true);
			
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			fish.moveLeft();
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			fish.moveRight();
		}
		
		bubbleImage.rotate(1);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics display)
			throws SlickException {
		
		background.draw(0, 0);
		
		for (int i = 0; i < cannons.length; i++) {
			
			cannons[i].getAnimation().draw(cannons[i].x, cannons[i].y);
			
			for (Bubble bubble : cannons[i].getBubbles())
				bubbleImage.draw(bubble.x, bubble.y);
		}
		
		fish.getFrame().draw(fish.x, fish.y);
	}

	@Override
	public int getID() {
		return 0;
	}
	
	public boolean isCannonsAnimating() {
		boolean allCannonsAnimFinished = true;
		for (int i = 0; i < cannons.length; i++)
			if (cannons[i].t.getState() != Thread.State.TERMINATED)
				allCannonsAnimFinished = false;
		return !allCannonsAnimFinished;
	}
	
	public void initCannons(boolean actives) throws SlickException {
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < 6; i++)
			s.push(i);
		Collections.shuffle(s);
		while (!s.isEmpty()) {
			int i = s.pop();
			if (cannons[i] == null)
				cannons[i] = new Cannon(thrower, (100*i)+100, -20);
			else 
				cannons[i].restart();
		}
	}

}
