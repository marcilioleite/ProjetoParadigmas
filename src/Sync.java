public class Sync {
   public static void main(String args[]) {
      Chamado target = new Chamado();
      Chamador ob1 = new Chamador(target, "Hello");
      Chamador ob2 = new Chamador(target, "Synchronized");
      Chamador ob3 = new Chamador(target, "World");
   
      try {
         ob1.t.join();
         ob2.t.join();
         ob3.t.join();
      } catch(InterruptedException e) {
         System.out.println("Interrupted");
      }
   }
}