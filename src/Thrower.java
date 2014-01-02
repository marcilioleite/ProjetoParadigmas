class Thrower {

	void callThrow(Cannon cannon) {

		cannon.boom();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
