import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

public class Cannon implements Runnable {

	final int ANIMATION_DURATION = 100;
	
	float x;
	float y;

	private List<Bubble> bubbles;
	
	private boolean active;

	private Animation animation;
	private Sound sound;

	private Thrower thrower;
	Thread t;

	public Cannon(Thrower thrower, float x, float y) 
			throws SlickException {
		
		this.x = x;
		this.y = y;
		
		bubbles = new ArrayList<Bubble>();
		
		SpriteSheet cannonSheet = new SpriteSheet("res/cannon.png", 65, 160);
		animation = new Animation(cannonSheet, ANIMATION_DURATION);
		animation.stop();
		animation.setLooping(false);

		sound = new Sound("res/cannon.wav");
		
		this.active = false;
		
		this.thrower = thrower;
		t = new Thread(this);
		t.start();
	}
	
	public void restart() {
		this.active = true;
		t = new Thread(this);
		t.start();
	}

	public void update() {
		if (animation.isStopped()) {
			animation.setCurrentFrame(0);
		}
	}

	public void boom() {
		animation.start();
		sound.play();
		bubbles.add(new Bubble(x, y+80));
	}

	public List<Bubble> getBubbles() {
		return bubbles;
	}

	public Animation getAnimation() {
		return animation;
	}

	@Override
	public void run() {
		synchronized (thrower) {
			if (active)
				thrower.callThrow(this);
		}
	}
}
