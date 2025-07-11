package com.example.apicoding1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()));

            if (authentication.isAuthenticated()) {
                String token = jwtUtil.generateToken(request.getUsername());
                return new AuthResponse(token);
            } else {
                throw new RuntimeException("Invalid access");
            }
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid Username or Password");
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return """
            <html>
            <head><title>Login - Books API</title></head>
            <body style="font-family: Arial, sans-serif; margin: 40px;">
                <h2>🔐 Login to Books API</h2>
                <p>Use the following credentials to get a JWT token:</p>
                <div style="background: #f5f5f5; padding: 20px; border-radius: 5px; margin: 20px 0;">
                    <strong>Username:</strong> admin<br>
                    <strong>Password:</strong> password
                </div>
                
                <h3>📝 How to Login:</h3>
                <p>Send a POST request to <code>/login</code> with JSON body:</p>
                <pre style="background: #f0f0f0; padding: 15px; border-radius: 5px;">
{
  "username": "admin",
  "password": "password"
}
                </pre>
                
                <h3>🚀 Quick Test:</h3>
                <button onclick="testLogin()" style="background: #007bff; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;">
                    Test Login
                </button>
                <div id="result" style="margin-top: 20px;"></div>
                
                <script>
                async function testLogin() {
                    try {
                        const response = await fetch('/login', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json',
                            },
                            body: JSON.stringify({
                                username: 'admin',
                                password: 'password'
                            })
                        });
                        
                        if (response.ok) {
                            const data = await response.json();
                            document.getElementById('result').innerHTML = 
                                '<div style="color: green; background: #e8f5e8; padding: 15px; border-radius: 5px;">' +
                                '<strong>✅ Login Successful!</strong><br>' +
                                'JWT Token: <code>' + data.token + '</code>' +
                                '</div>';
                        } else {
                            throw new Error('Login failed');
                        }
                    } catch (error) {
                        document.getElementById('result').innerHTML = 
                            '<div style="color: red; background: #ffe8e8; padding: 15px; border-radius: 5px;">' +
                            '❌ Login Failed: ' + error.message +
                            '</div>';
                    }
                }
                </script>
                
                <p><a href="/">← Back to Home</a></p>
            </body>
            </html>
            """;
    }
}
