
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.LinkedList;

public class ClientThreadedServer implements Runnable{
	
	private int portNumber;
	private LinkedList<Job> jobs;
	
	public ClientThreadedServer(int portNumber, LinkedList<Job> jobs)
	{
		this.portNumber = portNumber;
		this.jobs = jobs;
	}
	
	@Override
	public void run() 
	{		
		
		final int THREADS = 3;		
		
		try (ServerSocket serverSocket = new ServerSocket(portNumber);) 
		{
			ArrayList<Thread> threads = new ArrayList<Thread>();
			for (int i = 0; i < THREADS; i++)
				threads.add(new Thread(new ServerThread(serverSocket, i, jobs)));
			for (Thread t : threads)
				t.start();
			
			/*for (Thread t: threads)
			{
				try {
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}*/
		} 
		
		catch (IOException e) {
			System.out.println(
					"Exception caught when trying to listen on port " + "30122"  + " or listening for a connection");
			System.out.println(e.getMessage());
		}
		
	}

}
			
			
		
