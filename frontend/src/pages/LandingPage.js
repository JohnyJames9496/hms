import React from 'react';
import { motion } from 'framer-motion';
import { useNavigate } from 'react-router-dom';
import { FiUsers, FiCalendar, FiCreditCard, FiShield, FiStar, FiArrowRight } from 'react-icons/fi';
import './LandingPage.css';

const LandingPage = () => {
  const navigate = useNavigate();

  const features = [
    {
      icon: <FiUsers />,
      title: 'Student Management',
      description: 'Effortlessly manage student profiles, meal preferences, and dining schedules'
    },
    {
      icon: <FiCalendar />,
      title: 'Meal Planning',
      description: 'Smart meal scheduling with breakfast & dinner options, skip functionality'
    },
    {
      icon: <FiCreditCard />,
      title: 'Digital Payments',
      description: 'Seamless UPI payments with QR codes, automated billing, and fine management'
    },
    {
      icon: <FiShield />,
      title: 'Secure & Reliable',
      description: 'Role-based access control with admin and student dashboards'
    }
  ];

  const stats = [
    { number: '100+', label: 'Happy Students' },
    { number: '2K+', label: 'Meals Served' },
    { number: '24/7', label: 'Available' },
    { number: '₹60', label: 'Per Meal' }
  ];

  return (
    <div className="landing-page">
      {/* Hero Section */}
      <section className="hero-section">
        <div className="hero-background">
          <div className="hero-shapes">
            <motion.div 
              className="shape shape-1"
              animate={{ 
                rotate: [0, 360],
                scale: [1, 1.1, 1]
              }}
              transition={{ 
                duration: 20,
                repeat: Infinity,
                ease: "linear"
              }}
            />
            <motion.div 
              className="shape shape-2"
              animate={{ 
                rotate: [360, 0],
                y: [0, -20, 0]
              }}
              transition={{ 
                duration: 15,
                repeat: Infinity,
                ease: "easeInOut"
              }}
            />
            <motion.div 
              className="shape shape-3"
              animate={{ 
                scale: [1, 1.2, 1],
                x: [0, 10, 0]
              }}
              transition={{ 
                duration: 12,
                repeat: Infinity,
                ease: "easeInOut"
              }}
            />
          </div>
        </div>

        <div className="container hero-content">
          <motion.div
            className="hero-text"
            initial={{ opacity: 0, y: 50 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.8, ease: "easeOut" }}
          >
            <motion.div
              className="hero-badge"
              initial={{ opacity: 0, scale: 0.8 }}
              animate={{ opacity: 1, scale: 1 }}
              transition={{ delay: 0.2, duration: 0.5 }}
            >
              <FiStar className="badge-icon" />
              <span>Modern Mess Management</span>
            </motion.div>

            <motion.h1
              className="hero-title"
              initial={{ opacity: 0, y: 30 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ delay: 0.3, duration: 0.8 }}
            >
              Hostel Mess
              <span className="title-highlight"> Management</span>
              <br />Made Simple
            </motion.h1>

            <motion.p
              className="hero-description"
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ delay: 0.5, duration: 0.6 }}
            >
              Experience the future of hostel dining with our intuitive platform. 
              Manage meals, payments, and student preferences with the elegance of modern design.
            </motion.p>

            <motion.div
              className="hero-actions"
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ delay: 0.7, duration: 0.6 }}
            >
              <button 
                className="btn btn-primary btn-lg hero-cta"
                onClick={() => navigate('/login')}
              >
                Get Started
                <FiArrowRight className="btn-icon" />
              </button>
              
              <div className="hero-stats-mini">
                <div className="stat-item">
                  <span className="stat-number">₹60</span>
                  <span className="stat-label">per meal</span>
                </div>
                <div className="stat-divider"></div>
                <div className="stat-item">
                  <span className="stat-number">2</span>
                  <span className="stat-label">meals/day</span>
                </div>
              </div>
            </motion.div>
          </motion.div>

          <motion.div
            className="hero-visual"
            initial={{ opacity: 0, x: 50 }}
            animate={{ opacity: 1, x: 0 }}
            transition={{ delay: 0.4, duration: 0.8 }}
          >
            <div className="hero-card">
              <div className="card-header">
                <div className="card-avatar">
                  <span>👨‍🍳</span>
                </div>
                <div className="card-info">
                  <h4>Today's Menu</h4>
                  <p>Breakfast & Dinner</p>
                </div>
                <div className="card-status">
                  <span className="status-dot"></span>
                  <span>Active</span>
                </div>
              </div>
              
              <div className="card-content">
                <div className="meal-item">
                  <span className="meal-icon">🌅</span>
                  <span className="meal-name">Breakfast</span>
                  <span className="meal-price">₹60</span>
                </div>
                <div className="meal-item">
                  <span className="meal-icon">🌙</span>
                  <span className="meal-name">Dinner</span>
                  <span className="meal-price">₹60</span>
                </div>
              </div>
              
              <div className="card-footer">
                <button className="card-btn">
                  <FiCreditCard />
                  Pay with UPI
                </button>
              </div>
            </div>
          </motion.div>
        </div>
      </section>

      {/* Stats Section */}
      <section className="stats-section">
        <div className="container">
          <div className="stats-grid">
            {stats.map((stat, index) => (
              <motion.div
                key={index}
                className="stat-card"
                initial={{ opacity: 0, y: 30 }}
                whileInView={{ opacity: 1, y: 0 }}
                transition={{ delay: index * 0.1, duration: 0.6 }}
                viewport={{ once: true }}
              >
                <h3 className="stat-number">{stat.number}</h3>
                <p className="stat-label">{stat.label}</p>
              </motion.div>
            ))}
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="features-section">
        <div className="container">
          <motion.div
            className="section-header"
            initial={{ opacity: 0, y: 30 }}
            whileInView={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6 }}
            viewport={{ once: true }}
          >
            <h2 className="section-title">
              Everything you need for
              <span className="title-highlight"> mess management</span>
            </h2>
            <p className="section-description">
              Powerful features designed to make hostel dining management effortless and enjoyable
            </p>
          </motion.div>

          <div className="features-grid">
            {features.map((feature, index) => (
              <motion.div
                key={index}
                className="feature-card"
                initial={{ opacity: 0, y: 30 }}
                whileInView={{ opacity: 1, y: 0 }}
                transition={{ delay: index * 0.1, duration: 0.6 }}
                viewport={{ once: true }}
                whileHover={{ y: -5 }}
              >
                <div className="feature-icon">
                  {feature.icon}
                </div>
                <h3 className="feature-title">{feature.title}</h3>
                <p className="feature-description">{feature.description}</p>
              </motion.div>
            ))}
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="cta-section">
        <div className="container">
          <motion.div
            className="cta-content"
            initial={{ opacity: 0, y: 30 }}
            whileInView={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.6 }}
            viewport={{ once: true }}
          >
            <h2 className="cta-title">
              Ready to transform your
              <span className="title-highlight"> mess management?</span>
            </h2>
            <p className="cta-description">
              Join hundreds of hostels already using HMS for seamless dining operations
            </p>
            <button 
              className="btn btn-primary btn-lg cta-button"
              onClick={() => navigate('/login')}
            >
              Start Your Journey
              <FiArrowRight className="btn-icon" />
            </button>
          </motion.div>
        </div>
      </section>
    </div>
  );
};

export default LandingPage;