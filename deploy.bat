@echo off
REM Hostel Mess Management System - Deployment Script for Windows
REM This script helps you deploy your application step by step

echo.
echo ========================================
echo Hostel Mess Management System
echo Deployment Helper for Windows
echo ========================================
echo.

REM Check if git is initialized
if not exist .git (
    echo Initializing Git repository...
    git init
    git add .
    git commit -m "Initial commit - Ready for deployment"
    echo Git repository initialized
) else (
    echo Git repository already exists
)

echo.
echo Deployment Options:
echo 1. Deploy Backend to Railway
echo 2. Deploy Frontend to Vercel
echo 3. Deploy Both (Recommended)
echo 4. View Deployment Guide
echo 5. Exit
echo.

set /p choice="Choose an option (1-5): "

if "%choice%"=="1" goto backend
if "%choice%"=="2" goto frontend
if "%choice%"=="3" goto both
if "%choice%"=="4" goto guide
if "%choice%"=="5" goto end
goto invalid

:backend
echo.
echo Deploying Backend to Railway...
echo.
echo Steps:
echo 1. Install Railway CLI: npm install -g @railway/cli
echo 2. Login: railway login
echo 3. Initialize: railway init
echo 4. Deploy: railway up
echo.
echo Opening Railway documentation...
start https://docs.railway.app/develop/cli
goto end

:frontend
echo.
echo Deploying Frontend to Vercel...
echo.
echo Steps:
echo 1. Install Vercel CLI: npm install -g vercel
echo 2. Navigate to frontend: cd frontend
echo 3. Deploy: vercel
echo.
echo Opening Vercel documentation...
start https://vercel.com/docs/cli
goto end

:both
echo.
echo Full Deployment Process
echo.
echo Step 1: Push to GitHub
echo ----------------------
set /p repo_url="Enter your GitHub repository URL: "

if not "%repo_url%"=="" (
    git remote add origin %repo_url% 2>nul || git remote set-url origin %repo_url%
    git branch -M main
    git push -u origin main
    echo Code pushed to GitHub
)

echo.
echo Step 2: Deploy Backend
echo ---------------------
echo 1. Go to https://railway.app
echo 2. Click 'New Project' - 'Deploy from GitHub repo'
echo 3. Select your repository
echo 4. Add environment variables:
echo    - SPRING_PROFILES_ACTIVE=prod
echo    - DATABASE_URL=jdbc:postgresql://db.tzxqnjuywtsbeovrkjej.supabase.co:5432/postgres
echo    - DATABASE_USERNAME=postgres
echo    - DATABASE_PASSWORD=rintoCherian
echo.
pause

echo.
set /p backend_url="Enter your Railway backend URL: "

echo.
echo Step 3: Deploy Frontend
echo ----------------------
echo 1. Go to https://vercel.com
echo 2. Click 'Add New Project' - 'Import Git Repository'
echo 3. Select your repository
echo 4. Set Root Directory to 'frontend'
echo 5. Add environment variable:
echo    - REACT_APP_API_URL=%backend_url%/api
echo.
echo Opening Vercel...
start https://vercel.com/new

echo.
echo Deployment process initiated!
echo.
echo Next Steps:
echo 1. Wait for deployments to complete
echo 2. Test your application
echo 3. Update CORS in backend with your Vercel URL
echo.
goto end

:guide
echo.
echo Opening Deployment Guide...
start DEPLOYMENT_GUIDE.md
goto end

:invalid
echo.
echo Invalid option
goto end

:end
echo.
echo For detailed instructions, see DEPLOYMENT_GUIDE.md
echo.
pause
