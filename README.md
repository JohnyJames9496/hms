# 🏠 Hostel Mess Management System (HMS)

A comprehensive full-stack web application for managing hostel mess operations with Java Spring Boot backend, Supabase PostgreSQL database, and React frontend.

## ✨ Features

### 👨‍💼 Admin Features
- Login with credentials (admin/admin123)
- Add and manage students
- Manage meals and payments
- View all payment statuses
- Reset meal preferences
- Real-time dashboard with statistics

### 👨‍🎓 Student Features
- Login with admission number
- View meal schedule (breakfast & dinner)
- Skip meals (1 day in advance)
- View payment status and history
- Pay via UPI QR code
- Track monthly bills

### 💼 Business Rules
- Each meal costs ₹60
- Students can skip meals only 1 day before
- Skipped meals remain skipped until admin resets
- Payment due at month-end
- ₹30 daily fine after 1 week of non-payment
- UPI Payment: rintocherian2006@oksbi

## 🚀 Tech Stack

### Backend
- **Java Spring Boot 3.1.5** - REST API framework
- **Spring Data JPA** - Database ORM
- **Spring Security** - Authentication & authorization
- **JWT** - Token-based authentication
- **PostgreSQL** - Production database
- **Maven** - Build tool

### Frontend
- **React 18.2.0** - UI library
- **React Router** - Navigation
- **Axios** - HTTP client
- **Framer Motion** - Animations
- **React Hot Toast** - Notifications

### Database
- **Supabase PostgreSQL** - Cloud database
- **Persistent storage** - Data survives restarts
- **Automatic backups** - Built-in by Supabase

### Deployment Ready
- **Railway** - Backend hosting
- **Vercel** - Frontend hosting
- **Docker** - Containerization
- **CI/CD** - Auto-deploy on push

## 📁 Project Structure
```
hms/
├── backend/                    # Spring Boot application
│   ├── src/main/java/com/hms/
│   │   ├── config/            # Security & CORS config
│   │   ├── controller/        # REST API endpoints
│   │   ├── dto/               # Data transfer objects
│   │   ├── entity/            # Database entities
│   │   ├── repository/        # Database access
│   │   ├── service/           # Business logic
│   │   └── util/              # Utilities
│   ├── Dockerfile             # Docker configuration
│   └── pom.xml                # Maven dependencies
│
├── frontend/                   # React application
│   ├── src/
│   │   ├── components/        # Reusable components
│   │   ├── config/            # API configuration
│   │   ├── context/           # React context
│   │   └── pages/             # Main pages
│   ├── vercel.json            # Vercel config
│   └── package.json           # NPM dependencies
│
├── database/                   # Database scripts
│   ├── schema.sql             # MySQL schema (legacy)
│   └── supabase-init.sql      # Supabase setup
│
├── DEPLOYMENT_GUIDE.md        # Complete deployment guide
├── DEPLOYMENT_SUMMARY.md      # Quick deployment reference
├── SUPABASE_SETUP.md          # Database setup guide
├── deploy.sh                  # Deployment script (Linux/Mac)
└── deploy.bat                 # Deployment script (Windows)
```

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Node.js 16+
- Maven 3.6+
- Supabase account (free)

### 1. Database Setup
1. Create a Supabase project at [supabase.com](https://supabase.com)
2. Go to SQL Editor
3. Run the script from `database/supabase-init.sql`

### 2. Backend Setup
```bash
cd backend
mvn clean install
mvn spring-boot:run
```
Backend runs on `http://localhost:8080`

### 3. Frontend Setup
```bash
cd frontend
npm install
npm start
```
Frontend runs on `http://localhost:3000`

### 4. Login
- **Admin**: username: `admin`, password: `admin123`
- **Student**: admission number: `1001`, `1002`, or `1003`

## 🌐 Deployment

### Quick Deploy (15 minutes)

1. **Push to GitHub**
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git push
   ```

2. **Deploy Backend to Railway**
   - Go to [railway.app](https://railway.app)
   - Deploy from GitHub
   - Add environment variables

3. **Deploy Frontend to Vercel**
   - Go to [vercel.com](https://vercel.com)
   - Import repository
   - Set root directory to `frontend`
   - Add `REACT_APP_API_URL` environment variable

**Detailed Instructions**: See [DEPLOYMENT_GUIDE.md](./DEPLOYMENT_GUIDE.md)

**Quick Reference**: See [DEPLOYMENT_SUMMARY.md](./DEPLOYMENT_SUMMARY.md)

## 📚 Documentation

- [Complete Deployment Guide](./DEPLOYMENT_GUIDE.md) - Step-by-step deployment
- [Deployment Summary](./DEPLOYMENT_SUMMARY.md) - Quick reference
- [Supabase Setup](./SUPABASE_SETUP.md) - Database configuration
- [Project Documentation](./PROJECT_README.md) - Detailed architecture

## 🔐 Default Credentials

### Admin
- Username: `admin`
- Password: `1234567890`

### Sample Students
- Admission Numbers: `1001`, `1002`, `1003`
- Check database for details

**⚠️ Change these in production!**
