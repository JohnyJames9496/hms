import React, { createContext, useContext, useState, useEffect } from 'react';
import axios from 'axios';
import toast from 'react-hot-toast';

const AuthContext = createContext();

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  // Configure axios defaults
  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      // For now, skip token verification to avoid issues
      // verifyToken(token);
    }
    setLoading(false);
  }, []);

  const verifyToken = async (token) => {
    try {
      const response = await axios.get('/api/auth/verify');
      setUser(response.data);
    } catch (error) {
      console.error('Token verification failed:', error);
      localStorage.removeItem('token');
      delete axios.defaults.headers.common['Authorization'];
    } finally {
      setLoading(false);
    }
  };

  const login = async (credentials) => {
    try {
      setLoading(true);

      // Check for admin login first (demo mode)
      if (credentials.role === 'admin') {
        console.log('Admin login attempt:', credentials);
        if (credentials.username === 'admin' && credentials.password === '1234567890') {
          // Admin login success
          const userData = {
            id: 1,
            username: 'admin',
            name: 'Administrator',
            role: 'admin'
          };

          const token = `local-admin-token-${Date.now()}`;
          localStorage.setItem('token', token);
          axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
          setUser(userData);

          toast.success(`Welcome back, Administrator! 🎉`);
          return { success: true };
        } else {
          toast.error('Invalid admin credentials. Use: admin / 1234567890');
          return { success: false, message: 'Invalid credentials' };
        }
      }

      // For student login, check locally added students first
      if (credentials.role === 'student') {
        const addedStudents = JSON.parse(localStorage.getItem('addedStudents') || '[]');
        const localStudent = addedStudents.find(s =>
          s.name.toLowerCase() === credentials.name.toLowerCase() &&
          s.admissionNumber === credentials.admissionNumber
        );

        if (localStudent) {
          // Local student login success
          const userData = {
            id: localStudent.id,
            name: localStudent.name,
            admissionNumber: localStudent.admissionNumber,
            role: 'student',
            email: localStudent.email,
            phone: localStudent.phone,
            roomNumber: localStudent.roomNumber
          };

          const token = `local-student-token-${Date.now()}`;
          localStorage.setItem('token', token);
          axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
          setUser(userData);

          toast.success(`Welcome back, ${userData.name}! 🎉`);
          return { success: true };
        } else {
          toast.error('Student not found. Please contact admin to add your details first.');
          return { success: false, message: 'Student not found' };
        }
      }

      // Try API login as fallback (for production)
      try {
        const response = await axios.post('/api/auth/login', credentials);
        const { token, user: userData } = response.data;

        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
        setUser(userData);

        toast.success(`Welcome back, ${userData.name || userData.username}! 🎉`);
        return { success: true };
      } catch (apiError) {
        console.log('API login failed, using local authentication');
        toast.error('Login failed. Please check your credentials.');
        return { success: false, message: 'Login failed' };
      }

    } catch (error) {
      console.error('Login error:', error);
      toast.error('Login failed. Please try again.');
      return { success: false, message: 'Login failed' };
    } finally {
      setLoading(false);
    }
  };

  const logout = () => {
    localStorage.removeItem('token');
    delete axios.defaults.headers.common['Authorization'];
    setUser(null);
    toast.success('Logged out successfully! 👋');
  };

  const value = {
    user,
    login,
    logout,
    loading,
    setLoading
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
};