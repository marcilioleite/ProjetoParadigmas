import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class PlayState extends BasicGameState {

	SpriteSheet cannonSheet;
	Animation animation;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {

		cannonSheet = new SpriteSheet("res/cannon.png", 65, 160);
		animation = new Animation(cannonSheet, 100);	
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2)
			throws SlickException {
		
		animation.draw(100, 100);
		cannonSheet.getSubImage(0, 0).draw(165, 100);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {

	}

	@Override
	public int getID() {
		return 0;
	}

}
