import React from 'react';
import { motion } from 'framer-motion';
import './LoadingSpinner.css';

const LoadingSpinner = ({ size = 'large', message = 'Loading...' }) => {
  const spinnerVariants = {
    start: {
      transition: {
        staggerChildren: 0.2
      }
    },
    end: {
      transition: {
        staggerChildren: 0.2
      }
    }
  };

  const circleVariants = {
    start: {
      opacity: 1,
      scale: 1,
      y: 0
    },
    end: {
      opacity: 0.3,
      scale: 0.8,
      y: -20
    }
  };

  const sizeClasses = {
    small: 'spinner-small',
    medium: 'spinner-medium',
    large: 'spinner-large'
  };

  return (
    <div className="loading-container">
      <div className="loading-content">
        {/* Animated Logo/Icon */}
        <motion.div
          className={`loading-spinner ${sizeClasses[size]}`}
          initial={{ scale: 0, rotate: 0 }}
          animate={{ scale: 1, rotate: 360 }}
          transition={{
            scale: { duration: 0.5, ease: "easeOut" },
            rotate: { duration: 2, ease: "linear", repeat: Infinity }
          }}
        >
          <div className="spinner-inner">
            <motion.div
              className="spinner-dots"
              variants={spinnerVariants}
              initial="start"
              animate="end"
              transition={{
                repeat: Infinity,
                repeatType: "reverse",
                duration: 1
              }}
            >
              {[...Array(8)].map((_, i) => (
                <motion.div
                  key={i}
                  className="spinner-dot"
                  variants={circleVariants}
                  style={{
                    transform: `rotate(${i * 45}deg) translateY(-20px)`
                  }}
                />
              ))}
            </motion.div>
          </div>
        </motion.div>

        {/* Loading Text */}
        <motion.div
          className="loading-text"
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.3, duration: 0.5 }}
        >
          <h3>{message}</h3>
          <motion.div
            className="loading-dots"
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            transition={{ delay: 0.5 }}
          >
            <motion.span
              animate={{ opacity: [0, 1, 0] }}
              transition={{ duration: 1.5, repeat: Infinity, delay: 0 }}
            >
              .
            </motion.span>
            <motion.span
              animate={{ opacity: [0, 1, 0] }}
              transition={{ duration: 1.5, repeat: Infinity, delay: 0.5 }}
            >
              .
            </motion.span>
            <motion.span
              animate={{ opacity: [0, 1, 0] }}
              transition={{ duration: 1.5, repeat: Infinity, delay: 1 }}
            >
              .
            </motion.span>
          </motion.div>
        </motion.div>

        {/* Floating Food Icons */}
        <div className="floating-icons">
          {['🍽️', '🥘', '🍛', '☕'].map((icon, index) => (
            <motion.div
              key={index}
              className="floating-icon"
              initial={{ 
                opacity: 0, 
                scale: 0,
                x: Math.random() * 200 - 100,
                y: Math.random() * 200 - 100
              }}
              animate={{ 
                opacity: [0, 1, 0],
                scale: [0, 1, 0],
                y: [0, -50, 0],
                rotate: [0, 360]
              }}
              transition={{
                duration: 3,
                repeat: Infinity,
                delay: index * 0.5,
                ease: "easeInOut"
              }}
            >
              {icon}
            </motion.div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default LoadingSpinner;