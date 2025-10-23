#!/bin/bash

# Hostel Mess Management System - Deployment Script
# This script helps you deploy your application step by step

echo "🚀 Hostel Mess Management System - Deployment Helper"
echo "===================================================="
echo ""

# Check if git is initialized
if [ ! -d .git ]; then
    echo "📦 Initializing Git repository..."
    git init
    git add .
    git commit -m "Initial commit - Ready for deployment"
    echo "✅ Git repository initialized"
else
    echo "✅ Git repository already exists"
fi

echo ""
echo "📋 Deployment Options:"
echo "1. Deploy Backend to Railway"
echo "2. Deploy Frontend to Vercel"
echo "3. Deploy Both (Recommended)"
echo "4. Exit"
echo ""

read -p "Choose an option (1-4): " choice

case $choice in
    1)
        echo ""
        echo "🔧 Deploying Backend to Railway..."
        echo ""
        echo "Steps:"
        echo "1. Install Railway CLI: npm install -g @railway/cli"
        echo "2. Login: railway login"
        echo "3. Initialize: railway init"
        echo "4. Deploy: railway up"
        echo ""
        read -p "Press Enter to open Railway CLI installation guide..."
        xdg-open "https://docs.railway.app/develop/cli" 2>/dev/null || open "https://docs.railway.app/develop/cli" 2>/dev/null || start "https://docs.railway.app/develop/cli"
        ;;
    2)
        echo ""
        echo "🎨 Deploying Frontend to Vercel..."
        echo ""
        echo "Steps:"
        echo "1. Install Vercel CLI: npm install -g vercel"
        echo "2. Navigate to frontend: cd frontend"
        echo "3. Deploy: vercel"
        echo ""
        read -p "Press Enter to open Vercel CLI installation guide..."
        xdg-open "https://vercel.com/docs/cli" 2>/dev/null || open "https://vercel.com/docs/cli" 2>/dev/null || start "https://vercel.com/docs/cli"
        ;;
    3)
        echo ""
        echo "🚀 Full Deployment Process"
        echo ""
        echo "Step 1: Push to GitHub"
        echo "----------------------"
        read -p "Enter your GitHub repository URL: " repo_url
        
        if [ ! -z "$repo_url" ]; then
            git remote add origin $repo_url 2>/dev/null || git remote set-url origin $repo_url
            git branch -M main
            git push -u origin main
            echo "✅ Code pushed to GitHub"
        fi
        
        echo ""
        echo "Step 2: Deploy Backend"
        echo "---------------------"
        echo "1. Go to https://railway.app"
        echo "2. Click 'New Project' → 'Deploy from GitHub repo'"
        echo "3. Select your repository"
        echo "4. Add environment variables:"
        echo "   - SPRING_PROFILES_ACTIVE=prod"
        echo "   - DATABASE_URL=jdbc:postgresql://db.tzxqnjuywtsbeovrkjej.supabase.co:5432/postgres"
        echo "   - DATABASE_USERNAME=postgres"
        echo "   - DATABASE_PASSWORD=rintoCherian"
        echo ""
        read -p "Press Enter when backend is deployed..."
        
        echo ""
        read -p "Enter your Railway backend URL: " backend_url
        
        echo ""
        echo "Step 3: Deploy Frontend"
        echo "----------------------"
        echo "1. Go to https://vercel.com"
        echo "2. Click 'Add New Project' → 'Import Git Repository'"
        echo "3. Select your repository"
        echo "4. Set Root Directory to 'frontend'"
        echo "5. Add environment variable:"
        echo "   - REACT_APP_API_URL=$backend_url/api"
        echo ""
        read -p "Press Enter to open Vercel..."
        xdg-open "https://vercel.com/new" 2>/dev/null || open "https://vercel.com/new" 2>/dev/null || start "https://vercel.com/new"
        
        echo ""
        echo "✅ Deployment process initiated!"
        echo ""
        echo "📝 Next Steps:"
        echo "1. Wait for deployments to complete"
        echo "2. Test your application"
        echo "3. Update CORS in backend with your Vercel URL"
        echo ""
        ;;
    4)
        echo "👋 Goodbye!"
        exit 0
        ;;
    *)
        echo "❌ Invalid option"
        exit 1
        ;;
esac

echo ""
echo "📚 For detailed instructions, see DEPLOYMENT_GUIDE.md"
echo ""
