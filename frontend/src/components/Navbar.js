import { useState } from 'react';
import { motion } from 'framer-motion';
import { useAuth } from '../context/AuthContext';
import { FiUser, FiLogOut, FiMenu, FiX } from 'react-icons/fi';
import './Navbar.css';

const Navbar = () => {
  const { user, logout } = useAuth();
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const handleLogout = () => {
    logout();
    setIsMenuOpen(false);
  };

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  return (
    <nav className="navbar">
      <div className="navbar-container">
        {/* Logo */}
        <div className="navbar-logo">
          <div className="logo-icon">
            <span>🍽️</span>
          </div>
          <div className="logo-text">
            <h3>HMS</h3>
            <span>Mess Management</span>
          </div>
        </div>

        {/* Desktop Navigation */}
        <div className="navbar-menu desktop-menu">
          {/* User Profile */}
          <div className="navbar-profile">
            <div className="profile-info">
              <div className="profile-avatar">
                <FiUser />
              </div>
              <div className="profile-details">
                <span className="profile-name">
                  {user?.name || user?.username}
                </span>
                <span className="profile-role">
                  {user?.role === 'admin' ? 'Administrator' : 'Student'}
                </span>
              </div>
            </div>
            
            <button
              className="logout-btn"
              onClick={handleLogout}
            >
              <FiLogOut />
              <span>Logout</span>
            </button>
          </div>
        </div>

        {/* Mobile Menu Toggle */}
        <button
          className="mobile-menu-toggle"
          onClick={toggleMenu}
        >
          {isMenuOpen ? <FiX /> : <FiMenu />}
        </button>
      </div>

      {/* Mobile Menu */}
      <div className={`mobile-menu ${isMenuOpen ? 'open' : ''}`}>
        <div className="mobile-menu-content">
          <div className="mobile-profile">
            <div className="profile-avatar">
              <FiUser />
            </div>
            <div className="profile-details">
              <span className="profile-name">
                {user?.name || user?.username}
              </span>
              <span className="profile-role">
                {user?.role === 'admin' ? 'Administrator' : 'Student'}
              </span>
            </div>
          </div>

          <div className="mobile-links">
            <button
              className="mobile-logout"
              onClick={handleLogout}
            >
              <FiLogOut />
              <span>Logout</span>
            </button>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;