package com.example.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String rollNumber;
    private String gender;



   
    public Student() {}

 
    public Student(String firstName, String lastName, String rollNumber, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rollNumber = rollNumber;
        this.gender = gender;
    }

  
    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }
    
    public String getFirstName() { 
        return firstName; 
    }
    
    public void setFirstName(String firstName) { 
        this.firstName = firstName; 
    }
    
    public String getLastName() { 
        return lastName; 
    }
    
    public void setLastName(String lastName) { 
        this.lastName = lastName; 
    }
    
    public String getRollNumber() { 
        return rollNumber; 
    }
    
    public void setRollNumber(String rollNumber) { 
        this.rollNumber = rollNumber; 
    }
    
    public String getGender() { 
        return gender; 
    }
    
    public void setGender(String gender) { 
        this.gender = gender; 
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", rollNumber='" + rollNumber + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
