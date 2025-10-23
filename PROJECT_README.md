# 🏠 Hostel Mess Management System (HMS)

## 📖 Table of Contents
1. [Project Overview](#project-overview)
2. [System Architecture](#system-architecture)
3. [Frontend (React)](#frontend-react)
4. [Backend (Java Spring Boot)](#backend-java-spring-boot)
5. [Database (H2)](#database-h2)
6. [OOP Concepts in Java](#oop-concepts-in-java)
7. [Setup Instructions](#setup-instructions)
8. [How to Use](#how-to-use)

---

## 🎯 Project Overview

The **Hostel Mess Management System** is a web application that helps manage hostel mess operations, including:
- Student meal scheduling and tracking
- Payment management
- Admin dashboard for monitoring
- Meal skip functionality (students can skip meals 1 day in advance)

### Key Features
- ✅ Student login and dashboard
- ✅ Admin login and dashboard
- ✅ Meal scheduling (breakfast & dinner)
- ✅ Skip meals 1 day in advance
- ✅ Payment submission and verification
- ✅ Real-time updates between student and admin
- ✅ QR code payment integration

---

## 🏗️ System Architecture

The project follows a **3-tier architecture**:

```
┌─────────────────────────────────────────────────────────┐
│                    FRONTEND (React)                      │
│  - User Interface (UI)                                   │
│  - Student Dashboard & Admin Dashboard                   │
│  - Handles user interactions                             │
└─────────────────────────────────────────────────────────┘
                          ↕ HTTP/REST API
┌─────────────────────────────────────────────────────────┐
│                 BACKEND (Java Spring Boot)               │
│  - Business Logic                                        │
│  - REST API Controllers                                  │
│  - Services & Repositories                               │
└─────────────────────────────────────────────────────────┘
                          ↕ JPA/Hibernate
┌─────────────────────────────────────────────────────────┐
│                    DATABASE (H2)                         │
│  - Data Storage                                          │
│  - Tables: students, admins, meals, payments, etc.       │
└─────────────────────────────────────────────────────────┘
```

---

## 🎨 Frontend (React)

### Technology Stack
- **React 18.2.0** - JavaScript library for building user interfaces
- **React Router** - For navigation between pages
- **Axios** - For making HTTP requests to backend
- **Framer Motion** - For smooth animations
- **React Hot Toast** - For notifications
- **QRCode.react** - For generating payment QR codes

### Project Structure
```
frontend/
├── public/              # Static files
├── src/
│   ├── components/      # Reusable UI components
│   ├── context/         # React Context (AuthContext)
│   ├── pages/           # Main pages
│   │   ├── Login.js            # Login page
│   │   ├── StudentDashboard.js # Student interface
│   │   └── AdminDashboard.js   # Admin interface
│   ├── App.js           # Main app component
│   └── index.js         # Entry point
└── package.json         # Dependencies
```

### Key Components

#### 1. **Login Page** (`Login.js`)
- Handles both student and admin login
- Uses admission number (4 digits) for students
- Uses username for admin
- Stores authentication token in localStorage

#### 2. **Student Dashboard** (`StudentDashboard.js`)
- **My Meals Tab**: View and manage meal schedule
  - Shows meals from today to end of month
  - Skip meals 1 day in advance
  - Each meal costs ₹60
- **Payments Tab**: View bills and payment history
  - Current month bill
  - Payment submission with QR code
  - Payment status tracking

#### 3. **Admin Dashboard** (`AdminDashboard.js`)
- **Overview Tab**: Statistics and metrics
  - Total students
  - Today's meals
  - Pending payments
- **Students Tab**: Manage students
  - Add new students
  - View student details
  - Delete students
- **Payments Tab**: Verify payments
  - View pending payments
  - Verify/reject payments
  - Payment history

### How Frontend Works

1. **User logs in** → Frontend sends credentials to backend
2. **Backend validates** → Returns JWT token
3. **Token stored** → In localStorage for future requests
4. **Protected routes** → Only accessible with valid token
5. **API calls** → All requests include token in headers
6. **Real-time updates** → Polling every 2 seconds for changes

### Data Flow Example: Skipping a Meal
```
Student clicks "Skip Day"
    ↓
Frontend validates (must be future date)
    ↓
Updates local state
    ↓
Saves to localStorage (studentMealPreferences)
    ↓
Recalculates bill amount
    ↓
Saves to localStorage (studentBills)
    ↓
Admin dashboard reads updated amount
    ↓
Shows reduced pending payment
```

---

## ☕ Backend (Java Spring Boot)

### Technology Stack
- **Java 17** - Programming language
- **Spring Boot 3.1.5** - Framework for building Java applications
- **Spring Data JPA** - For database operations
- **Spring Security** - For authentication and authorization
- **H2 Database** - In-memory database
- **JWT (JSON Web Tokens)** - For secure authentication
- **Maven** - Build and dependency management tool

### Project Structure
```
backend/
├── src/main/java/com/hms/
│   ├── config/              # Configuration classes
│   │   └── SecurityConfig.java
│   ├── controller/          # REST API endpoints
│   │   ├── AuthController.java
│   │   ├── StudentController.java
│   │   └── AdminController.java
│   ├── dto/                 # Data Transfer Objects
│   │   ├── LoginRequest.java
│   │   ├── LoginResponse.java
│   │   └── StudentDto.java
│   ├── entity/              # Database entities
│   │   ├── Student.java
│   │   ├── Admin.java
│   │   ├── Meal.java
│   │   ├── Payment.java
│   │   └── MonthlyBill.java
│   ├── repository/          # Database access layer
│   │   ├── StudentRepository.java
│   │   └── AdminRepository.java
│   ├── service/             # Business logic layer
│   │   ├── AuthService.java
│   │   ├── StudentService.java
│   │   ├── MealService.java
│   │   └── PaymentService.java
│   └── HmsApplication.java  # Main application class
└── pom.xml                  # Maven dependencies
```

### Layered Architecture

The backend follows a **layered architecture** pattern:

```
┌─────────────────────────────────────────┐
│         CONTROLLER LAYER                 │
│  - Handles HTTP requests                 │
│  - Maps URLs to methods                  │
│  - Returns HTTP responses                │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│          SERVICE LAYER                   │
│  - Contains business logic               │
│  - Processes data                        │
│  - Coordinates between layers            │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│        REPOSITORY LAYER                  │
│  - Database operations (CRUD)            │
│  - Uses Spring Data JPA                  │
│  - Abstracts database queries            │
└─────────────────────────────────────────┘
                  ↓
┌─────────────────────────────────────────┐
│           DATABASE                       │
│  - Stores actual data                    │
└─────────────────────────────────────────┘
```

### Key Components

#### 1. **Controllers** (Handle HTTP Requests)

**AuthController.java** - Handles login
```java
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    // Validates credentials
    // Returns JWT token
}
```

**StudentController.java** - Student operations
```java
@GetMapping("/meals")
public List<MealScheduleDto> getMealSchedule() {
    // Returns student's meal schedule
}

@PostMapping("/skip-day")
public void skipDay(@RequestParam LocalDate date) {
    // Skips meals for a specific day
}
```

**AdminController.java** - Admin operations
```java
@GetMapping("/students")
public List<StudentDto> getAllStudents() {
    // Returns list of all students
}

@PostMapping("/verify-payment")
public void verifyPayment(@RequestParam Long paymentId) {
    // Verifies a pending payment
}
```

#### 2. **Services** (Business Logic)

**StudentService.java** - Student-related operations
- Get meal schedule
- Skip/enable meals
- Calculate payment summary
- Process payments

**MealService.java** - Meal management
- Track meal status
- Skip/enable meals
- Calculate monthly meal count

**PaymentService.java** - Payment processing
- Create payment records
- Verify payments
- Get payment history

#### 3. **Repositories** (Database Access)

**StudentRepository.java**
```java
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByAdmissionNumber(String admissionNumber);
    List<Student> findByIsActiveTrue();
}
```

**AdminRepository.java**
```java
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}
```

#### 4. **Entities** (Database Tables)

**Student.java** - Represents a student
```java
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String admissionNumber;
    private String email;
    private String phone;
    private String roomNumber;
    
    @OneToMany(mappedBy = "student")
    private List<StudentMeal> studentMeals;
    
    // Getters and setters
}
```

---

## 🗄️ Database (H2)

### What is H2 Database?
H2 is an **in-memory database** - it stores data in RAM (memory) instead of on disk. This makes it:
- ✅ Very fast
- ✅ Easy to set up (no installation needed)
- ✅ Perfect for development and testing
- ⚠️ Data is lost when application stops

### Database Schema

```sql
-- Students Table
CREATE TABLE students (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    admission_number VARCHAR(4) UNIQUE NOT NULL,
    email VARCHAR(255),
    phone VARCHAR(20),
    room_number VARCHAR(10),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Admins Table
CREATE TABLE admins (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Meals Table
CREATE TABLE meals (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    meal_type VARCHAR(20) NOT NULL,  -- 'breakfast' or 'dinner'
    meal_date DATE NOT NULL,
    cost DECIMAL(10,2) DEFAULT 60.00
);

-- Student Meals (Junction Table)
CREATE TABLE student_meals (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    meal_id BIGINT NOT NULL,
    is_opted BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (meal_id) REFERENCES meals(id)
);

-- Payments Table
CREATE TABLE payments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'pending',  -- 'pending', 'verified', 'rejected'
    verified_by BIGINT,
    verified_at TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (verified_by) REFERENCES admins(id)
);

-- Monthly Bills Table
CREATE TABLE monthly_bills (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    month VARCHAR(7) NOT NULL,  -- Format: 'YYYY-MM'
    total_meals INT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    due_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'pending',
    FOREIGN KEY (student_id) REFERENCES students(id)
);
```

### Sample Data

```sql
-- Sample Students
INSERT INTO students (name, admission_number, email, phone, room_number) VALUES
('John Doe', '1001', 'john@example.com', '9876543210', 'A101'),
('Jane Smith', '1002', 'jane@example.com', '9876543211', 'A102'),
('Bob Johnson', '1003', 'bob@example.com', '9876543212', 'B201');

-- Sample Admin
INSERT INTO admins (username, password, name, email) VALUES
('admin', 'admin123', 'Admin User', 'admin@hms.com');
```

---

## 🎓 OOP Concepts in Java

This project demonstrates key **Object-Oriented Programming (OOP)** concepts:

### 1. **Classes and Objects**

**What is it?**
- A **class** is a blueprint/template for creating objects
- An **object** is an instance of a class

**Example in our project:**
```java
// Student.java is a CLASS (blueprint)
public class Student {
    private String name;
    private String admissionNumber;
    
    // Constructor
    public Student(String name, String admissionNumber) {
        this.name = name;
        this.admissionNumber = admissionNumber;
    }
}

// Creating OBJECTS (instances)
Student student1 = new Student("John", "1001");
Student student2 = new Student("Jane", "1002");
```

### 2. **Encapsulation**

**What is it?**
- Hiding internal details and providing controlled access
- Using **private** fields and **public** getters/setters

**Example:**
```java
public class Student {
    // Private fields (hidden from outside)
    private String name;
    private String admissionNumber;
    
    // Public getters (controlled read access)
    public String getName() {
        return name;
    }
    
    // Public setters (controlled write access)
    public void setName(String name) {
        this.name = name;
    }
}

// Usage
Student student = new Student();
student.setName("John");  // ✅ Allowed
String name = student.getName();  // ✅ Allowed
// student.name = "John";  // ❌ Not allowed (private)
```

**Why?**
- Protects data from unauthorized access
- Allows validation before setting values
- Can change internal implementation without affecting other code

### 3. **Inheritance**

**What is it?**
- Creating new classes based on existing classes
- Child class inherits properties and methods from parent class

**Example:**
```java
// Parent class
public class User {
    protected String name;
    protected String email;
    
    public void login() {
        System.out.println(name + " logged in");
    }
}

// Child class (inherits from User)
public class Student extends User {
    private String admissionNumber;
    
    // Student has: name, email (from User) + admissionNumber
    public void attendMeal() {
        System.out.println(name + " attended meal");
    }
}

// Usage
Student student = new Student();
student.login();  // ✅ Inherited from User
student.attendMeal();  // ✅ Own method
```

### 4. **Polymorphism**

**What is it?**
- Same method name, different implementations
- "Many forms" of the same thing

**Example:**
```java
// Method Overloading (Compile-time polymorphism)
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
}

// Usage
Calculator calc = new Calculator();
calc.add(5, 10);        // Calls first method
calc.add(5.5, 10.5);    // Calls second method
calc.add(5, 10, 15);    // Calls third method
```

### 5. **Abstraction**

**What is it?**
- Hiding complex implementation details
- Showing only essential features

**Example in our project:**
```java
// Service layer abstracts business logic
@Service
public class StudentService {
    // Complex logic hidden inside
    public PaymentSummaryDto getPaymentSummary(Long studentId) {
        // 1. Get student from database
        // 2. Calculate meals
        // 3. Calculate amount
        // 4. Check payment status
        // 5. Return summary
        // All this complexity is hidden!
    }
}

// Controller just calls the service
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    
    @GetMapping("/payment-summary")
    public PaymentSummaryDto getSummary() {
        // Simple call - complexity is abstracted
        return studentService.getPaymentSummary(studentId);
    }
}
```

### 6. **Interfaces**

**What is it?**
- A contract that classes must follow
- Defines what methods a class must have

**Example:**
```java
// JpaRepository is an interface
public interface StudentRepository extends JpaRepository<Student, Long> {
    // We define what we want
    Optional<Student> findByAdmissionNumber(String admissionNumber);
    List<Student> findByIsActiveTrue();
}

// Spring automatically provides implementation!
// We don't write the actual database code
```

### 7. **Dependency Injection**

**What is it?**
- Providing dependencies from outside instead of creating them inside
- Spring Boot does this automatically with `@Autowired`

**Example:**
```java
@Service
public class StudentService {
    // Instead of: StudentRepository repo = new StudentRepository();
    // We let Spring inject it:
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private MealService mealService;
    
    // Spring automatically creates and injects these objects!
}
```

**Benefits:**
- Loose coupling (classes are independent)
- Easy to test (can inject mock objects)
- Easy to change implementations

### 8. **Annotations** (Java Feature)

**What are they?**
- Metadata that provides information to the compiler/framework
- Start with `@` symbol

**Common annotations in our project:**

```java
// Entity - Marks class as database table
@Entity
@Table(name = "students")
public class Student { }

// Controller - Marks class as REST API controller
@RestController
@RequestMapping("/api/student")
public class StudentController { }

// Service - Marks class as service layer
@Service
public class StudentService { }

// Autowired - Tells Spring to inject dependency
@Autowired
private StudentRepository repository;

// GetMapping - Maps HTTP GET request to method
@GetMapping("/meals")
public List<Meal> getMeals() { }

// PostMapping - Maps HTTP POST request to method
@PostMapping("/skip-day")
public void skipDay() { }
```

---

## 🚀 Setup Instructions

### Prerequisites
- **Java 17** or higher
- **Node.js 16** or higher
- **Maven** (usually comes with Java)
- **Git** (optional)

### Backend Setup

1. **Navigate to backend folder:**
```bash
cd backend
```

2. **Install dependencies:**
```bash
mvn clean install
```

3. **Run the application:**
```bash
mvn spring-boot:run
```

4. **Backend will start on:** `http://localhost:8080`

### Frontend Setup

1. **Navigate to frontend folder:**
```bash
cd frontend
```

2. **Install dependencies:**
```bash
npm install
```

3. **Run the application:**
```bash
npm start
```

4. **Frontend will start on:** `http://localhost:3000`

### Database Setup

No setup needed! H2 database is automatically created when backend starts.

**To view database:**
- Go to: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:hmsdb`
- Username: `sa`
- Password: (leave empty)

---

## 📱 How to Use

### For Students

1. **Login:**
   - Admission Number: `1001` (4 digits)
   - Password: `password123`

2. **View Meals:**
   - Go to "My Meals" tab
   - See all meals from today to end of month
   - Each meal costs ₹60

3. **Skip Meals:**
   - Click "Skip Day" for future dates
   - Cannot skip today's meals
   - Must skip 1 day in advance

4. **Make Payment:**
   - Go to "Payments" tab
   - Click "Pay Now"
   - Scan QR code or mark as paid
   - Wait for admin verification

### For Admin

1. **Login:**
   - Username: `admin`
   - Password: `admin123`

2. **View Dashboard:**
   - See total students
   - See today's meals
   - See pending payments

3. **Manage Students:**
   - Go to "Students" tab
   - Add new students
   - View student details
   - Delete students

4. **Verify Payments:**
   - Go to "Payments" tab
   - See pending payments
   - Click "Verify" to confirm
   - Click "Reject" to decline

---

## 🎯 Key Features Explained

### 1. Meal Skip Logic
- Students can only skip meals for **future dates**
- Must skip **1 day in advance**
- Today's meals cannot be modified
- Skipped meals reduce the bill amount

### 2. Payment Flow
```
Student submits payment
    ↓
Status: "Pending Verification"
    ↓
Admin reviews payment
    ↓
Admin clicks "Verify" or "Reject"
    ↓
Status: "Confirmed" or "Rejected"
    ↓
Student sees updated status
```

### 3. Real-time Updates
- Frontend polls backend every 2 seconds
- Admin sees new payments immediately
- Students see verified payments immediately
- Uses localStorage for temporary storage

### 4. Bill Calculation
```
Total Days in Month: 31 (October)
Meals per Day: 2 (breakfast + dinner)
Cost per Meal: ₹60

Full Month Bill:
31 days × 2 meals × ₹60 = ₹3,720

If student skips 3 days:
(31 - 3) days × 2 meals × ₹60 = ₹3,360
Savings: ₹360
```

---

## 🔐 Security Features

1. **JWT Authentication:**
   - Secure token-based authentication
   - Token expires after 24 hours
   - Token required for all protected routes

2. **Password Hashing:**
   - Passwords stored as hashed values
   - Uses BCrypt algorithm
   - Cannot be reversed

3. **Role-based Access:**
   - Students can only access student routes
   - Admins can only access admin routes
   - Enforced by Spring Security

---

## 📚 Technologies Summary

### Frontend
- **React** - UI library
- **React Router** - Navigation
- **Axios** - HTTP client
- **Framer Motion** - Animations
- **localStorage** - Client-side storage

### Backend
- **Spring Boot** - Java framework
- **Spring Data JPA** - Database operations
- **Spring Security** - Authentication
- **JWT** - Token-based auth
- **Maven** - Build tool

### Database
- **H2** - In-memory database
- **JPA/Hibernate** - ORM (Object-Relational Mapping)

---

## 🎓 Learning Resources

### For Java/Spring Boot:
- [Spring Boot Official Docs](https://spring.io/projects/spring-boot)
- [Java OOP Tutorial](https://www.w3schools.com/java/java_oop.asp)
- [Spring Boot Tutorial](https://www.baeldung.com/spring-boot)

### For React:
- [React Official Docs](https://react.dev/)
- [React Router Docs](https://reactrouter.com/)
- [JavaScript Tutorial](https://javascript.info/)

### For Database:
- [SQL Tutorial](https://www.w3schools.com/sql/)
- [JPA/Hibernate Guide](https://www.baeldung.com/learn-jpa-hibernate)

---

## 🐛 Troubleshooting

### Backend won't start
- Check if Java 17 is installed: `java -version`
- Check if port 8080 is free
- Run: `mvn clean install` again

### Frontend won't start
- Check if Node.js is installed: `node -version`
- Delete `node_modules` and run `npm install` again
- Check if port 3000 is free

### Database issues
- H2 database resets on restart (this is normal)
- Check H2 console at `http://localhost:8080/h2-console`
- Verify JDBC URL: `jdbc:h2:mem:hmsdb`

---

## 📞 Support

For questions or issues:
1. Check this README first
2. Review the code comments
3. Check console logs for errors
4. Verify all services are running

---

## 🎉 Conclusion

This project demonstrates:
- ✅ Full-stack web development
- ✅ RESTful API design
- ✅ Database management
- ✅ OOP principles in Java
- ✅ Modern React development
- ✅ Authentication and security
- ✅ Real-world application architecture

**Happy Learning! 🚀**
