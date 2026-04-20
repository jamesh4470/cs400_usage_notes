// sudo iptables -A PREROUTING -t nat -p tcp --dport 80 -j REDIRECT --to-ports 8000
// port forwards 8000 to 80
// browser will automatically request the css file if it detects the html file needs one
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
                String requestedFilePath = exchange.getRequestURI().getPath(); // URI: unique resource identifier
				System.out.println("incoming request for: " + requestedFilePath);
				
                File readFile = new File(requestedFilePath.substring(1)); // substring(1) removes first character, the slash, from string
                if (requestedFilePath.equals("/shortestPath")) {
                    // url: ip:/shortestPath?start=startPoint&end=endPoint
					String queryString = exchange.getRequestURI().getQuery();
					String startLocation = null;
					String endLocation = null;
					for (String i : queryString.split("&")) {
						if (i.startsWith("start=")) {
                            startLocation = i.substring(6);
                        }
                        if (i.startsWith("end=")) {
                            endLocation = i.substring(4);
                        }
                        System.out.println("start location: " + startLocation + "---" + "end location: " + endLocation);

                        String placeholderResult = "<h3>Shortest path (placeholder server)</h3>" + 
                                                    "<ul>" +
                                                    "<li>" + startLocation + "</li>" +
                                                    "<li>" + "placeholder server" + "</li>" +
                                                    "<li>" + endLocation + "</li>" +
                                                    "</ul>";
                        exchange.getResponseHeaders().add("Content-type", "text/html");
                        exchange.sendResponseHeaders(200, placeholderResult.length());
                                                    
                        OutputStream responseStream = exchange.getResponseBody();
                        responseStream.close();
					}
				} else if (readFile.exists()) {
                    String contentType = "text/html";
                    if (requestedFilePath.endsWith(".css")) contentType = "text/css";
                    if (requestedFilePath.endsWith(".png")) contentType = "image/png";

                    exchange.getResponseHeaders().add("Content-type", contentType);
                    exchange.sendResponseHeaders(200, readFile.length()); // readFile.length gives size of file
                            
                    Files.copy(readFile.toPath(), exchange.getResponseBody());
                    exchange.getResponseBody().close();
                } else {
                    String response = "<p>Hello from web server</p>";
                    
                    exchange.getResponseHeaders().add("Content-type", "text/html");
                    exchange.sendResponseHeaders(200, response.length());
                    
                    OutputStream responseStream = exchange.getResponseBody();
                    responseStream.write(response.getBytes());
                    responseStream.close();
                }

			}
		});
		
		server.start();
		System.out.println("server started");
    }

}
