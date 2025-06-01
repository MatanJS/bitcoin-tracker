package bitcointracker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class BitcoinService {

    @Value("${API_KEY:no api key}")
    private String apiKey;

    private final Queue<Double> prices = new LinkedList<>();
    private boolean started = false;

    private final WebClient webClient = WebClient.create();

    public void startFetching() {
        if (!started) {
            System.out.println("Starting...");
            started = true;
            fetchPrice();
        }
    }

    @Scheduled(fixedRate = 60000)
    public void fetchPrice() {
        if (!started) return;

        webClient.get()
            .uri("https://api.api-ninjas.com/v1/bitcoin")
            .header("X-Api-Key", apiKey)
            .retrieve()
            .bodyToMono(BitcoinResponse.class)
            .subscribe(response -> {
                double price = response.getPrice();
                LocalDateTime now = LocalDateTime.now();
                System.out.printf("[%s] Bitcoin Price: $%.2f%n", now, price);
                prices.add(price);
                if (prices.size() > 10) prices.poll();
                if (prices.size() == 10) {
                    double avg = prices.stream().mapToDouble(Double::doubleValue).average().orElse(0);
                    System.out.printf("[%s] 10-min Average: $%.2f%n", now, avg);
                }
            }, error -> {
                System.err.println("Error fetching Bitcoin price: " + error.getMessage());
                error.printStackTrace();
            });
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BitcoinResponse {
        private double price;

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
} 