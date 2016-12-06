package example.client;

import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class NetworkClientCallable implements Callable<RequestResponse> {

	private final RequestResponse lookup;

	public NetworkClientCallable(RequestResponse lookup) {
		super();
		this.lookup = lookup;
	}

	@Override
	public RequestResponse call() throws Exception {
		Socket socket = new Socket(lookup.host, lookup.port);
		Scanner scanner = new Scanner(socket.getInputStream());
		lookup.response = scanner.next();

		return lookup;
	}

}
