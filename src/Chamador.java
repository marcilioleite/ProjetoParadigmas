class Chamador implements Runnable {
	
   String msg;
   Chamado target;
   Thread t;
   
   public Chamador(Chamado targ, String s) {
      target = targ;
      msg = s;
      t = new Thread(this);
      t.start();
   }
   
   public void run() {
      synchronized(target) { 
         target.chamar(msg);
      }
   }
}