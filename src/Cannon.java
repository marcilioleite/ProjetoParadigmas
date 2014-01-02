import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

public class Cannon implements Runnable {

	final int ANIMATION_DURATION = 100;
	
	private boolean active;

	private Animation animation;
	private Sound sound;

	private Thrower thrower;
	Thread t;

	public Cannon(Thrower thrower, boolean active) throws SlickException {
		SpriteSheet cannonSheet = new SpriteSheet("res/cannon.png", 65, 160);
		animation = new Animation(cannonSheet, ANIMATION_DURATION);
		animation.stop();
		animation.setLooping(false);

		sound = new Sound("res/cannon.wav");
		
		this.active = active;
		
		this.thrower = thrower;
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
	}

	public Animation getAnimation() {
		return animation;
	}

	public boolean isActive() {
		return active;
	}
	
	@Override
	public void run() {
		synchronized (thrower) {
			if (active)
				thrower.callThrow(this);
		}
	}
}
