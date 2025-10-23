# 🚀 Complete Deployment Guide - Hostel Mess Management System

## 📋 Overview

This guide will help you deploy your application to production with:
- **Backend**: Railway (recommended) or Render
- **Frontend**: Vercel (recommended) or Netlify
- **Database**: Supabase (already configured ✅)

---

## 🎯 Quick Start (Recommended Path)

### Option 1: Railway + Vercel (Easiest & Free)

**Time**: ~15 minutes
**Cost**: Free tier available

1. Deploy Backend to Railway
2. Deploy Frontend to Vercel
3. Connect them together

---

## 📦 Part 1: Deploy Backend to Railway

### Step 1: Prepare Railway Account
1. Go to [railway.app](https://railway.app)
2. Sign up with GitHub
3. Click "New Project"

### Step 2: Deploy from GitHub

#### Option A: Deploy from GitHub (Recommended)
1. Push your code to GitHub:
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git branch -M main
   git remote add origin https://github.com/YOUR_USERNAME/hostel-mess-system.git
   git push -u origin main
   ```

2. In Railway:
   - Click "Deploy from GitHub repo"
   - Select your repository
   - Railway will auto-detect the Spring Boot app

#### Option B: Deploy from Local
1. Install Railway CLI:
   ```bash
   npm install -g @railway/cli
   ```

2. Login and deploy:
   ```bash
   railway login
   cd backend
   railway init
   railway up
   ```

### Step 3: Configure Environment Variables

In Railway dashboard, go to your service → Variables:

```env
SPRING_PROFILES_ACTIVE=prod
DATABASE_URL=jdbc:postgresql://db.tzxqnjuywtsbeovrkjej.supabase.co:5432/postgres
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=rintoCherian
JWT_SECRET=hmsSecretKey2024ForHostelMessManagementSystem
PORT=8080
```

### Step 4: Get Your Backend URL

After deployment, Railway will give you a URL like:
```
https://hostel-mess-backend-production.up.railway.app
```

**Save this URL** - you'll need it for the frontend!

---

## 🎨 Part 2: Deploy Frontend to Vercel

### Step 1: Prepare Vercel Account
1. Go to [vercel.com](https://vercel.com)
2. Sign up with GitHub
3. Click "Add New Project"

### Step 2: Configure Environment Variable

Before deploying, you need to set the backend URL:

1. In Vercel project settings → Environment Variables
2. Add:
   ```
   Name: REACT_APP_API_URL
   Value: https://your-railway-backend-url.up.railway.app/api
   ```

### Step 3: Deploy

#### Option A: Deploy from GitHub (Recommended)
1. In Vercel, click "Import Git Repository"
2. Select your repository
3. Set Root Directory to `frontend`
4. Click "Deploy"

#### Option B: Deploy from CLI
```bash
npm install -g vercel
cd frontend
vercel
```

### Step 4: Get Your Frontend URL

Vercel will give you a URL like:
```
https://hostel-mess-system.vercel.app
```

---

## 🔧 Part 3: Configure CORS (Important!)

Your backend needs to allow requests from your frontend domain.

### Update Backend CORS Configuration

Edit `backend/src/main/java/com/hms/config/SecurityConfig.java`:

```java
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList(
        "http://localhost:3000",
        "https://your-frontend-url.vercel.app"  // Add your Vercel URL
    ));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);
    
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}
```

After updating, redeploy the backend.

---

## 🌐 Alternative: Deploy to Render

### Backend on Render

1. Go to [render.com](https://render.com)
2. Click "New +" → "Web Service"
3. Connect your GitHub repository
4. Configure:
   ```
   Name: hms-backend
   Environment: Java
   Build Command: cd backend && mvn clean package -DskipTests
   Start Command: cd backend && java -jar target/hostel-mess-management-1.0.0.jar
   ```

5. Add Environment Variables (same as Railway)

6. Click "Create Web Service"

### Frontend on Render

1. Click "New +" → "Static Site"
2. Connect your repository
3. Configure:
   ```
   Name: hms-frontend
   Build Command: cd frontend && npm install && npm run build
   Publish Directory: frontend/build
   ```

4. Add Environment Variable:
   ```
   REACT_APP_API_URL=https://your-backend.onrender.com/api
   ```

5. Click "Create Static Site"

---

## 🌐 Alternative: Deploy Frontend to Netlify

### Step 1: Prepare Netlify Account
1. Go to [netlify.com](https://netlify.com)
2. Sign up with GitHub
3. Click "Add new site" → "Import an existing project"

### Step 2: Configure Build Settings

```
Base directory: frontend
Build command: npm run build
Publish directory: frontend/build
```

### Step 3: Environment Variables

In Netlify → Site settings → Environment variables:
```
REACT_APP_API_URL=https://your-backend-url.up.railway.app/api
```

### Step 4: Deploy

Click "Deploy site" and wait for build to complete.

---

## ✅ Verification Checklist

After deployment, verify everything works:

### Backend Health Check
```bash
curl https://your-backend-url.up.railway.app/actuator/health
```

Should return: `{"status":"UP"}`

### Frontend Check
1. Visit your frontend URL
2. Try logging in as admin: `admin` / `admin123`
3. Try adding a student
4. Verify data persists

### Database Check
1. Go to Supabase dashboard
2. Check Table Editor
3. Verify new data appears

---

## 🔐 Security Checklist (Production)

Before going live:

- [ ] Change default admin password
- [ ] Use strong JWT secret (generate new one)
- [ ] Enable HTTPS (automatic on Railway/Vercel)
- [ ] Set up proper CORS origins
- [ ] Enable Supabase Row Level Security
- [ ] Set up database backups
- [ ] Configure rate limiting
- [ ] Add monitoring/logging
- [ ] Set up custom domain (optional)

---

## 📊 Cost Breakdown

### Free Tier Limits

**Railway**:
- $5 free credit/month
- ~500 hours runtime
- Perfect for small apps

**Vercel**:
- 100GB bandwidth/month
- Unlimited deployments
- Free SSL

**Supabase**:
- 500MB database
- 2GB bandwidth
- Unlimited API requests

**Total**: FREE for small-medium usage! 🎉

---

## 🐛 Troubleshooting

### Backend Issues

**Error: "Connection refused"**
- Check DATABASE_URL is correct
- Verify Supabase is accessible
- Check Railway logs

**Error: "Port already in use"**
- Railway sets PORT automatically
- Make sure you're using `${PORT:8080}` in config

**Error: "Build failed"**
- Check Java version (needs 17+)
- Run `mvn clean install` locally first
- Check Railway build logs

### Frontend Issues

**Error: "API calls failing"**
- Verify REACT_APP_API_URL is set correctly
- Check CORS configuration in backend
- Open browser console for errors

**Error: "Environment variable not found"**
- Rebuild the frontend after adding env vars
- Check variable name starts with `REACT_APP_`
- Verify it's set in Vercel/Netlify dashboard

**Error: "404 on refresh"**
- Check vercel.json or netlify.toml is present
- Verify rewrites/redirects are configured

### Database Issues

**Error: "Table doesn't exist"**
- Run supabase-init.sql in Supabase SQL Editor
- Check Hibernate ddl-auto is set to `update`

---

## 🚀 Deployment Commands Quick Reference

### Railway CLI
```bash
# Install
npm install -g @railway/cli

# Login
railway login

# Deploy
railway up

# View logs
railway logs

# Open dashboard
railway open
```

### Vercel CLI
```bash
# Install
npm install -g vercel

# Deploy
vercel

# Production deploy
vercel --prod

# View logs
vercel logs
```

### Git Deployment
```bash
# Initial setup
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin YOUR_REPO_URL
git push -u origin main

# Future updates
git add .
git commit -m "Update message"
git push
```

---

## 🎯 Post-Deployment Steps

1. **Test Everything**
   - Admin login
   - Student login
   - Add/delete students
   - Meal scheduling
   - Payment flow

2. **Set Up Monitoring**
   - Railway provides basic monitoring
   - Consider adding Sentry for error tracking
   - Set up uptime monitoring (UptimeRobot)

3. **Custom Domain (Optional)**
   - Buy domain from Namecheap/GoDaddy
   - Add to Vercel/Railway
   - Configure DNS

4. **Backup Strategy**
   - Supabase has automatic backups
   - Consider manual exports weekly
   - Document recovery process

---

## 📞 Support & Resources

### Documentation
- [Railway Docs](https://docs.railway.app)
- [Vercel Docs](https://vercel.com/docs)
- [Supabase Docs](https://supabase.com/docs)

### Community
- Railway Discord
- Vercel Discord
- Stack Overflow

---

## 🎉 Success!

Your Hostel Mess Management System is now live! 🚀

**Share your URLs:**
- Frontend: `https://your-app.vercel.app`
- Backend: `https://your-backend.up.railway.app`

**Next Steps:**
- Share with users
- Gather feedback
- Iterate and improve
- Scale as needed

---

**Need help?** Check the troubleshooting section or review the logs in your deployment platform.
