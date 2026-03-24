// sudo iptables -A PREROUTING -t nat -p tcp --dport 80 -j REDIRECT --to-ports 8000
// port forwards 8000 to 80
import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.OutputStream;
import java.nio.file.Files;
import java.io.File;

public class WebServer {
    public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0); // second parameter is queue of requests, 0 is default value
		HttpContext context = server.createContext("/");
		context.setHandler(new HttpHandler() {
			@Override
			public void handle(HttpExchange exchange) throws IOException { // called for every request
				System.out.println("incoming request");
				
				String response = "<p>Hello from web server</p>";
				
				exchange.getResponseHeaders().add("Content-type", "text/html");
				exchange.sendResponseHeaders(200, response.length());
				
				OutputStream responseStream = exchange.getResponseBody();
				responseStream.write(response.getBytes());
				responseStream.close();
			}
		});
		
		server.start();
		System.out.println("server started");
    }

}