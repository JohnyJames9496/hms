# 🏠 Hostel Mess Management System (HMS)

A comprehensive full-stack web application for managing hostel mess operations, including meal tracking, payment management, and student administration.

## ✨ Features

### 👨‍💼 Admin Features
- Secure authentication with credentials
- Add and manage students
- Configure meal schedules (breakfast & dinner)
- Track all payments and generate reports
- Reset meal preferences for students
- Real-time dashboard with statistics

### 👨‍🎓 Student Features
- Login with admission number
- View daily meal schedule
- Skip meals (1 day in advance)
- View payment status and history
- Pay via UPI with QR code
- Track monthly bills and fines

### 💼 Business Rules
- Each meal costs ₹60
- Students must skip meals 1 day in advance
- Skipped meals persist until admin reset
- Payment due at month-end
- ₹30 daily fine after 7-day grace period
- UPI Payment ID: rintocherian2006@oksbi

## 🚀 Tech Stack

### Backend
- **Java Spring Boot 3.2.0** - REST API framework
- **Spring Data JPA** - ORM for database operations
- **Spring Security** - Authentication & authorization
- **JWT (jjwt 0.11.5)** - Token-based authentication
- **PostgreSQL** - Primary database (production)
- **H2 Database** - In-memory database (local development)
- **Maven 3.9+** - Dependency management

### Frontend
- **React 18.2.0** - UI library
- **React Router DOM 6.8.1** - Client-side routing
- **Axios 1.3.4** - HTTP client
- **Framer Motion 10.12.4** - Smooth animations
- **React Hot Toast 2.4.0** - Toast notifications
- **React Icons 4.8.0** - Icon library
- **Recharts 2.5.0** - Data visualization
- **QRCode.react 3.1.0** - QR code generation
- **date-fns 2.29.3** - Date utilities

### Database
- **Supabase PostgreSQL** - Cloud-hosted database (production)
- **H2 Database** - Local development database
- **Auto-migration** - JPA handles schema updates
- **Connection pooling** - HikariCP for performance

### Development Tools
- **Spring Boot DevTools** - Hot reload
- **React Scripts 5.0.1** - Build tooling
- **Java 21+** - Required for backend
- **Node.js 16+** - Required for frontend

## 📁 Project Structure

```
hms/
├── backend/                        # Spring Boot REST API
│   ├── src/main/
│   │   ├── java/com/hms/
│   │   │   ├── config/            # Security & CORS configuration
│   │   │   ├── controller/        # REST API endpoints
│   │   │   ├── dto/               # Data transfer objects
│   │   │   ├── entity/            # JPA entities
│   │   │   ├── repository/        # Data access layer
│   │   │   ├── service/           # Business logic
│   │   │   └── util/              # Helper utilities
│   │   └── resources/
│   │       ├── application.yml              # Main config
│   │       ├── application-local.yml        # Local dev config
│   │       └── application-prod.yml         # Production config
│   ├── pom.xml                    # Maven dependencies
│   └── .env.example               # Environment variables template
│
├── frontend/                       # React SPA
│   ├── src/
│   │   ├── components/            # Reusable UI components
│   │   ├── config/                # API configuration
│   │   ├── context/               # React Context (auth)
│   │   └── pages/                 # Page components
│   ├── public/                    # Static assets
│   ├── package.json               # NPM dependencies
│   ├── netlify.toml               # Netlify deployment config
│   └── .env.example               # Environment variables template
│
├── database/                       # Database setup scripts
│   └── supabase-init.sql          # PostgreSQL schema & initial data
│
├── .gitignore                      # Git ignore rules
└── README.md                       # Project documentation
```

## 🛠️ Installation & Setup

### Prerequisites

Ensure you have the following installed:
- **Java 21 or higher** - [Download](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.9+** - [Download](https://maven.apache.org/download.cgi)
- **Node.js 16+** - [Download](https://nodejs.org/)
- **Git** - [Download](https://git-scm.com/)

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/hms.git
cd hms
```

### 2. Database Setup (Choose One)

#### Option A: Local Development (H2 Database - Recommended for Testing)

No additional setup required! The application uses an in-memory H2 database by default.

#### Option B: Production Database (Supabase PostgreSQL)

1. Create a free account at [supabase.com](https://supabase.com)
2. Create a new project
3. Go to **SQL Editor** in Supabase dashboard
4. Copy and run the entire script from `database/supabase-init.sql`
5. Go to **Settings → Database** and copy your connection string
6. Update backend configuration (see Backend Setup)

### 3. Backend Setup

```bash
cd backend
```

#### For Local Development (H2 Database):
```bash
# No additional configuration needed
mvn clean install
mvn spring-boot:run
```

#### For Production (Supabase):
1. Copy `.env.example` to `.env`:
   ```bash
   cp .env.example .env
   ```

2. Edit `.env` with your Supabase credentials:
   ```env
   SUPABASE_DB_URL=jdbc:postgresql://db.YOUR_PROJECT.supabase.co:5432/postgres
   SUPABASE_DB_USER=postgres
   SUPABASE_DB_PASSWORD=your_password
   JWT_SECRET=your_secret_key
   JWT_EXPIRATION=86400000
   UPI_ID=your_upi_id
   ```

3. Update `application.yml` to use `prod` profile:
   ```yaml
   spring:
     profiles:
       active: prod
   ```

4. Run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

**Backend runs on:** `http://localhost:8080`

### 4. Frontend Setup

```bash
cd frontend

# Install dependencies
npm install

# Create environment file
cp .env.example .env
```

Edit `.env`:
```env
REACT_APP_API_URL=http://localhost:8080/api
```

Start the development server:
```bash
npm start
```

**Frontend runs on:** `http://localhost:3000`

### 5. Access the Application

Open your browser and navigate to `http://localhost:3000`

#### Default Login Credentials

**Admin:**
- Username: `admin`
- Password: `admin123`

**Sample Students:**
- Admission Number: `1001` (John Doe)
- Admission Number: `1002` (Jane Smith)
- Admission Number: `1003` (Bob Johnson)

**⚠️ Important:** Change default credentials before deploying to production!

## 🏃 Running the Application

### Development Mode

**Terminal 1 - Backend:**
```bash
cd backend
mvn spring-boot:run
```

**Terminal 2 - Frontend:**
```bash
cd frontend
npm start
```

The application will open automatically at `http://localhost:3000`

### Production Build

**Backend:**
```bash
cd backend
mvn clean package
java -jar target/hostel-mess-management-1.0.0.jar
```

**Frontend:**
```bash
cd frontend
npm run build
# The build folder can be served with any static hosting service
```

## 🌐 Deployment

### Backend Deployment (Railway)

1. **Create GitHub Repository:**
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git branch -M main
   git remote add origin YOUR_REPO_URL
   git push -u origin main
   ```

2. **Deploy on Railway:**
   - Go to [railway.app](https://railway.app) and sign up
   - Click **"New Project"** → **"Deploy from GitHub repo"**
   - Select your repository
   - Railway will auto-detect the Spring Boot app
   
3. **Configure Environment Variables:**
   
   Go to your project → **Variables** tab and add:
   ```
   SPRING_PROFILES_ACTIVE=prod
   SUPABASE_DB_URL=jdbc:postgresql://db.YOUR_PROJECT.supabase.co:5432/postgres
   SUPABASE_DB_USER=postgres
   SUPABASE_DB_PASSWORD=your_password
   JWT_SECRET=your_secret_key
   JWT_EXPIRATION=86400000
   UPI_ID=your_upi_id
   ```

4. **Deploy:**
   - Railway will automatically build and deploy
   - Get your backend URL: `https://your-app.railway.app`

### Frontend Deployment (Netlify)

1. **Deploy on Netlify:**
   - Go to [netlify.com](https://netlify.com) and sign up
   - Click **"Add new site"** → **"Import an existing project"**
   - Connect to GitHub and select your repository
   
2. **Configure Build Settings:**
   ```
   Base directory: frontend
   Build command: npm run build
   Publish directory: frontend/build
   ```

3. **Add Environment Variable:**
   
   Go to **Site settings** → **Environment variables**:
   ```
   REACT_APP_API_URL=https://your-app.railway.app/api
   ```

4. **Deploy:**
   - Click **"Deploy site"**
   - Your app will be live at: `https://your-app.netlify.app`

### Post-Deployment

1. **Test the Application:**
   - Visit your Netlify URL
   - Login as admin (admin/admin123)
   - Verify all features work

2. **Update API URL if needed:**
   - If backend URL changes, update `REACT_APP_API_URL` in Netlify
   - Redeploy frontend

3. **Monitor Logs:**
   - Railway: Check deployment logs in dashboard
   - Netlify: Check function logs and deploy logs

## 🔧 Configuration

### Backend Configuration Files

- **`application.yml`** - Main configuration (shared settings)
- **`application-local.yml`** - Local development (H2 database)
- **`application-prod.yml`** - Production settings (Supabase)

### Frontend Configuration

- **`.env`** - Environment variables (API URL)
- **`netlify.toml`** - Netlify deployment settings

### Environment Variables

**Backend (.env):**
```env
SUPABASE_DB_URL=jdbc:postgresql://...
SUPABASE_DB_USER=postgres
SUPABASE_DB_PASSWORD=your_password
JWT_SECRET=your_jwt_secret_key
JWT_EXPIRATION=86400000
UPI_ID=your_upi_id@bank
```

**Frontend (.env):**
```env
REACT_APP_API_URL=http://localhost:8080/api
```

## � Database Schema

Key tables:
- **admins** - Admin user accounts
- **students** - Student information
- **meals** - Meal schedule and pricing
- **student_meals** - Student meal preferences
- **monthly_bills** - Monthly billing records
- **payments** - Payment transactions

See `database/supabase-init.sql` for the complete schema with sample data.

## 🔐 Security

- JWT token-based authentication
- Password encryption with BCrypt
- CORS configured for frontend origin
- SQL injection prevention via JPA
- XSS protection headers

## 📝 API Endpoints

**Authentication:**
- `POST /api/auth/login` - Login (admin/student)

**Admin:**
- `GET /api/admin/students` - Get all students
- `POST /api/admin/students` - Add student
- `GET /api/admin/payments` - Get payments

**Student:**
- `GET /api/student/meals` - Get meal schedule
- `POST /api/student/meals/skip` - Skip meal
- `GET /api/student/payments` - Get payment history

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is open source and available under the MIT License.

## 👨‍💻 Author

**Rinto Cherian**
- UPI: rintocherian2006@oksbi

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- React team for the UI library
- Supabase for the database platform

---

**Need Help?** Open an issue on GitHub or contact the maintainer.