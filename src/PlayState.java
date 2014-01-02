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

	private Cannon[] cannons;
	Thrower thrower;
	
	private Image background;
	private Sound ambiance;
	private Input input;
	
	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {

		thrower = new Thrower();
		cannons = new Cannon[6];
		initCannons(false);
		
		background = new Image("res/background.jpg");
		ambiance = new Sound("res/ambiance.wav");
		input = gc.getInput();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics display)
			throws SlickException {
		
		background.draw(0, 0);
		
		for (int i = 0; i < cannons.length; i++) {
			cannons[i].getAnimation().draw((100*i)+100, -20);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {

		if (!ambiance.playing()) {
			ambiance.loop();
		}
		
		for (Cannon cannon : cannons)
			cannon.update();
	
		if (input.isKeyDown(Input.KEY_SPACE)) {
			if (!isCannonsAnimating())
				initCannons(true);
		}
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
		while (!s.isEmpty())
			cannons[s.pop()] = new Cannon(thrower, actives);
	}

}
