package example.client;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class NetworkClientMain {

	public static void main(String[] args) {
		String host = "localhost";

		for (int port = 10000; port < 10010; port++) {
			RequestResponse lookup = new RequestResponse(host, port);
			try (Socket socket = new Socket(lookup.host, lookup.port);

				Scanner scanner = new Scanner(socket.getInputStream());) {

				lookup.response = scanner.next();
				
				System.out.println("host: " + lookup.host + " port "+ port + " response : "+ lookup.response);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		Map<RequestResponse, Future<RequestResponse>> callables  = new HashMap<>();
		
		for (int port = 10000; port < 10010; port++) {
			RequestResponse lookup = new RequestResponse(host, port);
			
			NetworkClientCallable callable = new NetworkClientCallable(lookup);
			
			Future<RequestResponse> future = executorService.submit(callable);
			
			callables.put(lookup, future);
		}
		
		executorService.shutdown();
		
		try{
			executorService.awaitTermination(5000, TimeUnit.MILLISECONDS);
			
		}catch (InterruptedException interruptedException){
			interruptedException.printStackTrace();
		}
		
		for( RequestResponse lookup : callables.keySet()){
			
			Future<RequestResponse> future = callables.get(lookup);
			
			try{
				future.get();
				System.out.println(lookup.host + " "+lookup.port+ " "+lookup.response);
			}catch(InterruptedException | ExecutionException e){
				e.printStackTrace();
			}
		}
	}

}
