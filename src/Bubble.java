public class Bubble {

	float x;
	float cx;
	float y;
	
	public Bubble(float x, float y) {
		this.x = x;
		this.cx = x;
		this.y = y;
	}
	
	public void update() {
		y += 0.3;
		x = cx + (float)(Math.toDegrees(Math.sin(y/10-Math.PI))*1/2);
	}
}
