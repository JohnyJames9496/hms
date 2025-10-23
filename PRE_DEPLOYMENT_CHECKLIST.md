# ✅ Pre-Deployment Checklist

Complete this checklist before deploying to production.

## 📋 Code Preparation

### Backend
- [x] PostgreSQL driver added to pom.xml
- [x] Supabase connection configured
- [x] Production profile created (application-prod.yml)
- [x] Dockerfile created
- [x] Environment variables configured
- [ ] CORS configured with production URLs
- [ ] JWT secret changed from default
- [ ] Admin password changed from default
- [ ] Error handling implemented
- [ ] Logging configured

### Frontend
- [x] API client with environment variables created
- [x] Production environment file created
- [x] Vercel/Netlify config files created
- [x] Build tested locally (`npm run build`)
- [ ] API URL updated for production
- [ ] Error boundaries added (optional)
- [ ] Analytics configured (optional)

### Database
- [x] Supabase project created
- [x] Tables created and verified
- [x] Sample data loaded
- [x] Connection tested
- [ ] Backups configured
- [ ] Row Level Security enabled (optional)

## 🔐 Security

- [ ] Changed default admin password
- [ ] Generated new JWT secret
- [ ] Configured CORS with actual frontend URL
- [ ] Removed debug/test code
- [ ] Removed console.log statements
- [ ] Environment variables not committed to git
- [ ] .env files in .gitignore
- [ ] Database credentials secured

## 🧪 Testing

- [x] Backend starts without errors
- [x] Database connection works
- [x] All tables created
- [ ] Admin login works
- [ ] Student login works
- [ ] Add student works
- [ ] Meal scheduling works
- [ ] Payment flow works
- [ ] Data persists after restart
- [ ] Mobile responsive tested

## 📦 Git & GitHub

- [ ] Repository created on GitHub
- [ ] .gitignore configured
- [ ] README.md updated
- [ ] All files committed
- [ ] Pushed to GitHub
- [ ] Repository is public (or private if preferred)

## 🌐 Deployment Accounts

- [ ] GitHub account created
- [ ] Railway account created (for backend)
- [ ] Vercel account created (for frontend)
- [ ] Supabase account created (already done ✅)

## 📝 Configuration Files

### Backend Files
- [x] backend/Dockerfile
- [x] backend/src/main/resources/application-prod.yml
- [x] backend/.dockerignore

### Frontend Files
- [x] frontend/src/config/api.js
- [x] frontend/.env.development
- [x] frontend/.env.production
- [x] frontend/vercel.json
- [x] frontend/netlify.toml

### Platform Files
- [x] railway.json
- [x] render.yaml

## 🚀 Deployment Steps

### Step 1: Push to GitHub
- [ ] Code committed
- [ ] Pushed to main branch
- [ ] Repository accessible

### Step 2: Deploy Backend
- [ ] Railway project created
- [ ] Repository connected
- [ ] Environment variables added:
  - [ ] SPRING_PROFILES_ACTIVE=prod
  - [ ] DATABASE_URL
  - [ ] DATABASE_USERNAME
  - [ ] DATABASE_PASSWORD
  - [ ] JWT_SECRET
- [ ] Build successful
- [ ] Backend URL obtained
- [ ] Health check passes

### Step 3: Deploy Frontend
- [ ] Vercel project created
- [ ] Repository connected
- [ ] Root directory set to 'frontend'
- [ ] Environment variable added:
  - [ ] REACT_APP_API_URL
- [ ] Build successful
- [ ] Frontend URL obtained
- [ ] Site accessible

### Step 4: Connect Frontend & Backend
- [ ] Frontend API URL updated with backend URL
- [ ] Backend CORS updated with frontend URL
- [ ] Both redeployed
- [ ] Connection tested

## ✅ Post-Deployment

### Verification
- [ ] Frontend loads without errors
- [ ] Backend API responds
- [ ] Admin can login
- [ ] Student can login
- [ ] Data operations work
- [ ] Payment flow works
- [ ] Mobile view works

### Monitoring
- [ ] Railway metrics checked
- [ ] Vercel analytics enabled
- [ ] Error tracking set up (optional)
- [ ] Uptime monitoring configured (optional)

### Documentation
- [ ] Deployment URLs documented
- [ ] Admin credentials documented (securely)
- [ ] Known issues documented
- [ ] User guide created (optional)

## 🎯 Final Checks

- [ ] All features tested in production
- [ ] Performance acceptable
- [ ] No console errors
- [ ] No broken links
- [ ] SSL/HTTPS working
- [ ] Custom domain configured (optional)

## 📞 Support Preparation

- [ ] Error logs accessible
- [ ] Database backup tested
- [ ] Rollback plan documented
- [ ] Support contact configured

## 🎉 Launch

- [ ] All checklist items completed
- [ ] Stakeholders notified
- [ ] Users can access the application
- [ ] Monitoring active

---

## 📊 Checklist Summary

**Total Items**: 70+
**Completed**: Check your progress above
**Remaining**: Complete before deployment

---

## 🚨 Critical Items (Must Complete)

These items are absolutely required:

1. ✅ Database connected to Supabase
2. ✅ Backend builds successfully
3. ✅ Frontend builds successfully
4. ⚠️ CORS configured correctly
5. ⚠️ Environment variables set
6. ⚠️ Default passwords changed
7. ⚠️ Code pushed to GitHub

---

## 💡 Tips

- **Test locally first**: Make sure everything works on localhost
- **One step at a time**: Deploy backend first, then frontend
- **Check logs**: Always review deployment logs for errors
- **Use free tiers**: Start with free tiers, upgrade if needed
- **Document everything**: Keep track of URLs and credentials

---

## 🆘 If Something Goes Wrong

1. **Check logs** in Railway/Vercel dashboard
2. **Verify environment variables** are set correctly
3. **Test locally** to isolate the issue
4. **Review CORS** configuration
5. **Check database** connection
6. **Consult** [DEPLOYMENT_GUIDE.md](./DEPLOYMENT_GUIDE.md)

---

**Ready to deploy?** Complete this checklist and follow [DEPLOYMENT_GUIDE.md](./DEPLOYMENT_GUIDE.md)!
