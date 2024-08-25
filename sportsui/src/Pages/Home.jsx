import React, { useEffect, useState } from 'react';
import UserService from "../service/UserService";
import { Link } from 'react-router-dom';

export default function Home() {

  const [error, setError] = useState('');
  const isAuthenticated = UserService.isAuthenticated();


  useEffect(() => {
    window.history.pushState(null, document.title, window.location.href);
    window.addEventListener('popstate', function (event) {
      window.history.pushState(null, document.title, window.location.href);
    });
  }, []);

  let logout = async () => {
    const confirmDelete = window.confirm('Are you sure you want to logout this user?');
    if (confirmDelete) {
      UserService.logout();
    }
  }

  return (
    <div>
      <h1>Dashboard</h1>
      {isAuthenticated && <button><Link to="/" onClick={logout}>Logout</Link></button>}
    </div>
  );
}
