import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Fish {

	final int ANIMATION_DURATION = 100; 
	
	float x;
	float y;
	
	private FishState state;
	private FishDirection direction;
	
	private Animation swimmingAnimation;
	private Animation turnAnimation;
	private Animation eatAnimation;
	private Animation deadAnimation;

	public Fish() throws SlickException {
		this.x = 500;
		this.y = 420;
		
		state = FishState.SWIMMING;
		direction = FishDirection.LEFT;
		
		SpriteSheet swimmigSheet = new SpriteSheet("res/fish_swim.png", 80, 80);
		swimmingAnimation = new Animation(swimmigSheet, ANIMATION_DURATION);
		
		SpriteSheet turnSheet = new SpriteSheet("res/fish_turn.png", 80, 80);
		turnAnimation = new Animation(turnSheet, ANIMATION_DURATION);
		turnAnimation.setLooping(false);
		
		SpriteSheet eatSheet = new SpriteSheet("res/fish_eat.png", 80, 80);
		eatAnimation = new Animation(eatSheet, ANIMATION_DURATION);
		eatAnimation.setLooping(false);
		
		SpriteSheet deadSheet = new SpriteSheet("res/fish_dead.png", 80, 80);
		deadAnimation = new Animation(deadSheet, ANIMATION_DURATION);
		deadAnimation.setLooping(false);
	}
	
	public void update() {
		
		if (state == FishState.TURNING) {
			
			if (turnAnimation.getFrame() == turnAnimation.getFrameCount() - 1) {
				
				state = FishState.SWIMMING;
				turnAnimation.restart();
			}
		}
	}
	
	public Image getFrame() {
		Animation a = null;
		if (state == FishState.SWIMMING)
			a = swimmingAnimation;
		else if (state == FishState.TURNING)
			a = turnAnimation;
		else if (state == FishState.EATING)
			a = eatAnimation;
		else
			a = deadAnimation;
		
		int currentFrame = a.getFrame();
		if (currentFrame >= a.getFrameCount() - 1)
			a.setCurrentFrame(0);
		else
			a.setCurrentFrame(currentFrame + 1);
		
		Image frame = null;
		
		if (state == FishState.TURNING) {
			if (direction == FishDirection.RIGHT)
				frame = a.getCurrentFrame();
			else
				frame = a.getCurrentFrame().getFlippedCopy(true, false);	
		} else {
			if (direction == FishDirection.RIGHT)
				frame = a.getCurrentFrame().getFlippedCopy(true, false);
			else
				frame = a.getCurrentFrame();
		}
		return frame;
	}
	
	public void moveLeft() {
		if (state != FishState.TURNING) {
			
			if (direction != FishDirection.LEFT)
				state = FishState.TURNING;
			
			x--;			
		}
		direction = FishDirection.LEFT;
	}
	
	public void moveRight() {
		if (state != FishState.TURNING) {

			if (direction != FishDirection.RIGHT)
				state = FishState.TURNING;
			
			x++;
		}
		direction = FishDirection.RIGHT;
	}

	enum FishDirection {
		RIGHT, LEFT
	}
	
	enum FishState {
		SWIMMING, EATING, TURNING, DEAD
	}
}