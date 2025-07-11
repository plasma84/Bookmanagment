package com.example.apicoding1.config;

import com.example.apicoding1.entity.books;
import com.example.apicoding1.repo.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if books already exist to avoid duplicates
        if (booksRepository.count() == 0) {
            System.out.println("Initializing sample books data...");
            
            // Add sample books
            booksRepository.save(new books(1001, "The Great Gatsby", "F. Scott Fitzgerald", 1925));
            booksRepository.save(new books(1002, "To Kill a Mockingbird", "Harper Lee", 1960));
            booksRepository.save(new books(1003, "1984", "George Orwell", 1949));
            booksRepository.save(new books(1004, "Pride and Prejudice", "Jane Austen", 1813));
            booksRepository.save(new books(1005, "The Catcher in the Rye", "J.D. Salinger", 1951));
            booksRepository.save(new books(1006, "Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997));
            booksRepository.save(new books(1007, "The Lord of the Rings", "J.R.R. Tolkien", 1954));
            booksRepository.save(new books(1008, "The Chronicles of Narnia", "C.S. Lewis", 1950));
            
            System.out.println("Sample books data initialized successfully!");
            System.out.println("Total books in database: " + booksRepository.count());
        } else {
            System.out.println("Books data already exists. Total books: " + booksRepository.count());
        }
    }
}
