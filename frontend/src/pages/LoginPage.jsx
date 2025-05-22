import { useState } from "react";
import "../styles/LoginPage.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function LoginPage() {
  const [register, setRegister] = useState(false);
  const [email, setEmail] = useState("");
  const [username, setUsername] = useState("");
  const [telephone, setTelephone] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  console.log(register);

  const sendToRegister = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/users/register",
        { email, username, telephone, password }
      );
      console.log(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  const sendToLogin = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/users/login",
        { username, password }
      );
      console.log(response.data);
      if (response.data != "Authentication Failure") {
        const token = response.data; // adjust based on your backend response
        localStorage.setItem("token", token);
        axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
        navigate("/home");
      }
    } catch (error) {
      console.log(error);
    }
  };

  return register ? (
    <div className="login-body">
      <div className="register-box">
        <p className="insert-text">Insert Data</p>

        <input
          type="text"
          placeholder="Email"
          className="register-input"
          value={email}
          onChange={(val) => setEmail(val.target.value)}
        />

        <input
          type="text"
          placeholder="Username"
          className="register-input"
          value={username}
          onChange={(val) => setUsername(val.target.value)}
        />

        <input
          type="text"
          placeholder="Telephone"
          className="register-input"
          value={telephone}
          onChange={(val) => setTelephone(val.target.value)}
        />

        <input
          type="text"
          placeholder="Password"
          className="register-input"
          value={password}
          onChange={(val) => setPassword(val.target.value)}
        />

        <div>
          <button
            className="button"
            onClick={() => {
              sendToRegister();
            }}
          >
            Continue
          </button>
          <button className="button" onClick={() => setRegister(false)}>
            Go Back
          </button>
        </div>
      </div>
    </div>
  ) : (
    <div className="login-body">
      <div className="welcome-box">
        <p className="welcome-text">Welcome Back</p>

        <input
          type="text"
          placeholder="Username or email"
          className="user-input"
          value={username}
          onChange={(val) => setUsername(val.target.value)}
        />

        <input
          type="text"
          placeholder="Password"
          className="password-input"
          value={password}
          onChange={(val) => setPassword(val.target.value)}
        />

        <div>
          <button
            className="button"
            onClick={() => {
              sendToLogin();
            }}
          >
            Login
          </button>
          <button className="button" onClick={() => setRegister(true)}>
            Register
          </button>
        </div>
      </div>
    </div>
  );
}

export default LoginPage;
