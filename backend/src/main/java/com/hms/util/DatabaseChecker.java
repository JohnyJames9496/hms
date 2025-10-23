package com.hms.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DatabaseChecker implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseChecker(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {
        System.out.println("\n========================================");
        System.out.println("🔍 CHECKING DATABASE CONNECTION...");
        System.out.println("========================================\n");

        try {
            // Check connection
            jdbcTemplate.execute("SELECT 1");
            System.out.println("✅ Database connection successful!\n");

            // Check tables
            String query = "SELECT table_name FROM information_schema.tables " +
                          "WHERE table_schema = 'public' ORDER BY table_name";
            
            List<Map<String, Object>> tables = jdbcTemplate.queryForList(query);
            
            if (tables.isEmpty()) {
                System.out.println("❌ NO TABLES FOUND!");
                System.out.println("⚠️  Please run the supabase-init.sql script in Supabase SQL Editor\n");
            } else {
                System.out.println("✅ Found " + tables.size() + " tables:\n");
                for (Map<String, Object> table : tables) {
                    String tableName = (String) table.get("table_name");
                    
                    // Count rows in each table
                    try {
                        Integer count = jdbcTemplate.queryForObject(
                            "SELECT COUNT(*) FROM " + tableName, Integer.class);
                        System.out.println("   📊 " + tableName + " (" + count + " rows)");
                    } catch (Exception e) {
                        System.out.println("   📊 " + tableName + " (error counting rows)");
                    }
                }
                System.out.println();
            }

            // Check for required tables
            String[] requiredTables = {"admins", "students", "meals", "student_meals", "monthly_bills", "payments"};
            System.out.println("🔍 Checking required tables:");
            for (String tableName : requiredTables) {
                boolean exists = tables.stream()
                    .anyMatch(t -> tableName.equals(t.get("table_name")));
                if (exists) {
                    System.out.println("   ✅ " + tableName);
                } else {
                    System.out.println("   ❌ " + tableName + " (MISSING)");
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Database connection failed!");
            System.out.println("Error: " + e.getMessage());
            System.out.println("\n⚠️  Please check:");
            System.out.println("   1. Supabase project is active");
            System.out.println("   2. Database credentials in application.yml");
            System.out.println("   3. Network connection\n");
        }

        System.out.println("========================================\n");
    }
}
