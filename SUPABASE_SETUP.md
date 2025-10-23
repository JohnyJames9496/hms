# Supabase Database Setup Guide

## 🎯 Overview
This guide will help you set up your Hostel Mess Management System with Supabase PostgreSQL database.

## 📋 Prerequisites
- Supabase account (free tier works fine)
- Your Supabase project created

## 🚀 Setup Steps

### 1. Access Supabase SQL Editor
1. Go to your Supabase project dashboard
2. Click on **SQL Editor** in the left sidebar
3. Click **New Query**

### 2. Run the Initialization Script
1. Copy the entire content from `database/supabase-init.sql`
2. Paste it into the SQL Editor
3. Click **Run** or press `Ctrl+Enter`
4. Wait for the script to complete (should take a few seconds)

### 3. Verify Tables Created
1. Go to **Table Editor** in the left sidebar
2. You should see these tables:
   - admins
   - students
   - meals
   - student_meals
   - monthly_bills
   - payments

### 4. Backend Configuration
The backend is already configured with your Supabase credentials:
- **Host**: db.tzxqnjuywtsbeovrkjej.supabase.co
- **Port**: 5432
- **Database**: postgres
- **Username**: postgres
- **Password**: rintoCherian

### 5. Start the Backend
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

The application will:
- Connect to Supabase
- Create/update tables automatically (if needed)
- Insert sample data on first run

## 🔐 Default Credentials

### Admin Login
- Username: `admin`
- Password: `admin123`

### Sample Students
- Admission Number: `1001`, `1002`, `1003`
- Password: `password123` (you'll need to set this in your auth logic)

## 🔧 Configuration Details

### Database Connection
The connection is configured in `backend/src/main/resources/application.yml`:
```yaml
datasource:
  url: jdbc:postgresql://db.tzxqnjuywtsbeovrkjej.supabase.co:5432/postgres
  username: postgres
  password: rintoCherian
```

### Hibernate Settings
- **ddl-auto**: `update` - Automatically updates schema without dropping data
- **dialect**: PostgreSQL
- **show-sql**: `true` - Shows SQL queries in console (useful for debugging)

## 📊 Database Schema

### Tables Overview
1. **admins** - Admin user accounts
2. **students** - Student information
3. **meals** - Meal schedule (breakfast/dinner)
4. **student_meals** - Student meal preferences (opted/skipped)
5. **monthly_bills** - Monthly billing information
6. **payments** - Payment transactions

### Key Features
- Automatic timestamps (created_at, updated_at)
- Foreign key constraints for data integrity
- Indexes for better query performance
- Triggers for automatic timestamp updates

## 🔍 Monitoring & Debugging

### View Database in Supabase
1. Go to **Table Editor** to view/edit data
2. Go to **SQL Editor** to run custom queries
3. Go to **Database** → **Roles** to manage permissions

### Check Backend Logs
The backend logs all SQL queries when running. Look for:
```
Hibernate: SELECT ... FROM students WHERE ...
```

### Common Issues

#### Connection Refused
- Check if Supabase project is active
- Verify the database URL is correct
- Ensure your IP is not blocked (Supabase allows all IPs by default)

#### Authentication Failed
- Double-check username and password
- Ensure password doesn't have special characters that need escaping

#### Tables Not Created
- Run the `supabase-init.sql` script manually
- Check Supabase logs for errors
- Verify you have proper permissions

## 🌐 Production Deployment

### Environment Variables
For production, use environment variables instead of hardcoded credentials:

```bash
export SUPABASE_DB_URL=jdbc:postgresql://db.tzxqnjuywtsbeovrkjej.supabase.co:5432/postgres
export SUPABASE_DB_USER=postgres
export SUPABASE_DB_PASSWORD=rintoCherian
```

Update `application.yml`:
```yaml
datasource:
  url: ${SUPABASE_DB_URL}
  username: ${SUPABASE_DB_USER}
  password: ${SUPABASE_DB_PASSWORD}
```

### Security Best Practices
1. **Change default passwords** in production
2. **Use connection pooling** (already configured with HikariCP)
3. **Enable SSL** for database connections
4. **Rotate credentials** regularly
5. **Use Supabase Row Level Security (RLS)** for additional protection

## 📈 Next Steps

1. ✅ Database connected to Supabase
2. ✅ Tables created and initialized
3. ✅ Sample data inserted
4. 🔄 Test the application
5. 🚀 Deploy to production

## 🆘 Support

If you encounter issues:
1. Check Supabase project status
2. Review backend console logs
3. Verify database credentials
4. Check Supabase SQL Editor for errors

## 🎉 Success!

Your Hostel Mess Management System is now connected to Supabase PostgreSQL database. The data will persist across application restarts, and you can scale as needed with Supabase's infrastructure.
