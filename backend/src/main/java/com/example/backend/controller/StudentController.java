package com.example.backend.controller;

import com.example.backend.model.Student;
import com.example.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentRepository repo;

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(repo.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        if (student.getFirstName() == null || student.getFirstName().trim().isEmpty())
            return ResponseEntity.badRequest().body("First name is required");
        if (student.getLastName() == null || student.getLastName().trim().isEmpty())
            return ResponseEntity.badRequest().body("Last name is required");
        if (student.getRollNumber() == null || student.getRollNumber().trim().isEmpty())
            return ResponseEntity.badRequest().body("Roll number is required");
        if (student.getGender() == null || student.getGender().trim().isEmpty())
            return ResponseEntity.badRequest().body("Gender is required");

        if (repo.existsByRollNumber(student.getRollNumber()))
            return ResponseEntity.badRequest().body("Roll number already exists");

        return new ResponseEntity<>(repo.save(student), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> existing = repo.findById(id);
        if (existing.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");

        Student s = existing.get();

       
        if (!s.getRollNumber().equals(student.getRollNumber()) &&
            repo.existsByRollNumber(student.getRollNumber())) {
            return ResponseEntity.badRequest().body("Roll number already exists");
        }

        s.setFirstName(student.getFirstName());
        s.setLastName(student.getLastName());
        s.setRollNumber(student.getRollNumber());
        s.setGender(student.getGender());

        return ResponseEntity.ok(repo.save(s));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (!repo.existsById(id))
            return ResponseEntity.notFound().build();

        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
