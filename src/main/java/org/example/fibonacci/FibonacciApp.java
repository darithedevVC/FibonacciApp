package org.example.fibonacci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class FibonacciApp {

    private static final Logger logger = LoggerFactory.getLogger(FibonacciApp.class);

    public static int fibonacci(int n) {
        int ans;
        if (n <= 1){
            return n;
        } else {
            ans = fibonacci(n - 1) + fibonacci(n - 2);
        }
        return ans;
    }

    @GetMapping("/fibonacci")
    public int getSequence(@RequestParam int n) {

        int result = fibonacci(n);

        Map<String, Object> logContext = new HashMap<>();
        logContext.put("event", "fibonacci_request");
        logContext.put("input", n);
        logContext.put("result", result);
        logContext.put("timestamp", System.currentTimeMillis());

        logger.info(logContext.toString());

        return fibonacci(n);
    }

    // The main method
    public static void main(String[] args) {
        SpringApplication.run(FibonacciApp.class, args);
    }
}
