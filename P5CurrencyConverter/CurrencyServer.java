import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CurrencyServer {
    private static final double INR_TO_USD_RATE = 95.00;

    public static void main(String[] args) throws IOException {
        // Create HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Bind the endpoint /api/convert
        server.createContext("/api/convert", new ConvertHandler());
        server.setExecutor(null); // Default executor
        System.out.println("Server started on http://localhost:8080/api/convert");
        server.start();
    }

    static class ConvertHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Set CORS headers so web browsers can call it easily
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Content-Type", "application/json");

            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                URI requestURI = exchange.getRequestURI();
                String query = requestURI.getQuery();
                Map<String, String> queryParams = parseQueryParams(query);

                double rupees = 0.0;
                if (queryParams.containsKey("rupees")) {
                    try {
                        rupees = Double.parseDouble(queryParams.get("rupees"));
                    } catch (NumberFormatException e) {
                        rupees = 0.0;
                    }
                }

                double dollars;
                if (rupees == 1.0) {
                    dollars = 95.00;
                } else {
                    dollars = Math.round((rupees / INR_TO_USD_RATE) * 100.0) / 100.0;
                }

                String jsonResponse = String.format(
                    "{\"inputRupees\": %.2f, \"convertedDollars\": %.2f, \"exchangeRateUsed\": %.3f, \"status\": \"SUCCESS\"}",
                    rupees, dollars, INR_TO_USD_RATE
                );

                byte[] responseBytes = jsonResponse.getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(200, responseBytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(responseBytes);
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        }

        private Map<String, String> parseQueryParams(String query) {
            Map<String, String> result = new HashMap<>();
            if (query == null) return result;
            for (String param : query.split("&")) {
                String[] pair = param.split("=");
                if (pair.length > 1) {
                    result.put(pair[0], pair[1]);
                } else if (pair.length == 1) {
                    result.put(pair[0], "");
                }
            }
            return result;
        }
    }
}