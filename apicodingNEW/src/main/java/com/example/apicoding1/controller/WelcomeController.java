package com.example.apicoding1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String home() {
        return """
            <html>
            <head><title>Books API</title></head>
            <body style="font-family: Arial, sans-serif; margin: 40px;">
                <h1>📚 Welcome to Books API</h1>
                <p>Your Spring Boot application is running successfully!</p>
                <h3>🚀 Available Endpoints:</h3>
                <ul>
                    <li><strong>Authentication:</strong> POST <a href="/login">/login</a></li>
                    <li><strong>Books API:</strong> /books (requires authentication)</li>
                    <li><strong>Swagger UI:</strong> <a href="/swagger-ui.html">/swagger-ui.html</a></li>
                    <li><strong>API Documentation:</strong> <a href="/v3/api-docs">/v3/api-docs</a></li>
                </ul>
                <h3>🔑 Test Credentials:</h3>
                <p><strong>Username:</strong> admin</p>
                <p><strong>Password:</strong> password</p>
                <h3>📊 Database:</h3>
                <p>MySQL connected on localhost:3306/book</p>
                <p>Status: <span style="color: green;">✅ Connected</span></p>
            </body>
            </html>
            """;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Books API! Your application is running successfully.";
    }
}
