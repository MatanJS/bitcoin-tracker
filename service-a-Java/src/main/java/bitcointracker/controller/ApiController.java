package bitcointracker.controller;

import bitcointracker.service.BitcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    @Autowired
    private BitcoinService bitcoinService;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("I am Healthy");
    }

    @GetMapping("/get-btc")
    public ResponseEntity<String> getBitcoin() {
        bitcoinService.startFetching();
        return ResponseEntity.ok("fetching bitcoin values - check your server logs");
    }
}