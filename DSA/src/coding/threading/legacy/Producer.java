package coding.threading.legacy;

public class Producer implements Runnable{
	private SharedResource sr;
	private long num = 0;
	public Producer(SharedResource sr) {
		this.sr = sr;
	}

	@Override
	public void run() {
		while (num < 1000) {
			try {
				if (sr.isFull()) {
					
						synchronized (sr) {
							System.out.println("PPP SR is full. going to wait");
							sr.wait();
							System.out.println("PPP Came back from wait. Going to write 1");
							writeToSR();
							System.out.println("PPP notifying 1");
							sr.notify();
							sr.wait();
						}
					
				} else {
					synchronized (sr) {
						System.out.println("PPP Going to write 2");
						writeToSR();
						System.out.println("PPP notifying 2");
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
	
	private void writeToSR() {
		System.out.println("Producing "+"srikanth"+(++num));
		sr.add("srikanth"+num);
	}

}
