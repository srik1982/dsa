package coding.threading.legacy;

public class Consumer implements Runnable{
	private SharedResource sr;
	public Consumer(SharedResource sr) {
		this.sr = sr;
	}
	
	@Override
	public void run() {

		while (true) {
			try {
				if (sr.isEmpty()) {
					
						synchronized (sr) {
							System.out.println("CCC sr is empty. going to wait");
							sr.wait();
							System.out.println("CCC came out of wait. going to remove from sr 1");
							printFromSr();
							System.out.println("CCC Notifying 1");
							sr.notify();
							sr.wait();
						}
					
	
				} else {
					synchronized (sr) {
						System.out.println("CCC going to remove from sr 2");
						printFromSr();
						System.out.println("CCC Notifying 2");
						sr.notify();
						sr.wait();
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void printFromSr() {
		System.out.println("Consuming "+sr.remove());
	}
	
}
