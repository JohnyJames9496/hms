-- Hostel Mess Management System Database Schema

CREATE DATABASE IF NOT EXISTS hostel_mess_db;
USE hostel_mess_db;

-- Admin table
CREATE TABLE admins (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Students table
CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    admission_number VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(15),
    room_number VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE
);

-- Meals table
CREATE TABLE meals (
    id INT PRIMARY KEY AUTO_INCREMENT,
    meal_type ENUM('breakfast', 'dinner') NOT NULL,
    meal_date DATE NOT NULL,
    cost DECIMAL(10,2) DEFAULT 60.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY unique_meal_date (meal_type, meal_date)
);

-- Student meal preferences
CREATE TABLE student_meals (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    meal_id INT NOT NULL,
    is_opted BOOLEAN DEFAULT TRUE,
    skipped_date TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (meal_id) REFERENCES meals(id) ON DELETE CASCADE,
    UNIQUE KEY unique_student_meal (student_id, meal_id)
);

-- Monthly bills
CREATE TABLE monthly_bills (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    month INT NOT NULL,
    year INT NOT NULL,
    total_meals INT DEFAULT 0,
    total_amount DECIMAL(10,2) DEFAULT 0.00,
    fine_amount DECIMAL(10,2) DEFAULT 0.00,
    is_paid BOOLEAN DEFAULT FALSE,
    payment_date TIMESTAMP NULL,
    due_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    UNIQUE KEY unique_student_month (student_id, month, year)
);

-- Payment transactions
CREATE TABLE payments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    bill_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    payment_method VARCHAR(50) DEFAULT 'UPI',
    transaction_id VARCHAR(100),
    payment_status ENUM('pending', 'completed', 'failed') DEFAULT 'pending',
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (bill_id) REFERENCES monthly_bills(id) ON DELETE CASCADE
);

-- Insert default admin
INSERT INTO admins (username, password) VALUES ('admin', '1234567890');

-- Create indexes for better performance
CREATE INDEX idx_student_meals_date ON student_meals(created_at);
CREATE INDEX idx_meals_date ON meals(meal_date);
CREATE INDEX idx_bills_due_date ON monthly_bills(due_date);
CREATE INDEX idx_payments_date ON payments(payment_date);