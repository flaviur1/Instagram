import { useState } from "react";
import "../styles/LoginPage.css";

function LoginPage() {
  const [register, setRegister] = useState(false);

  console.log(register);

  return register ? (
    <div className="login-body">
      <div className="register-box">
        <p className="insert-text">Insert Data</p>

        <input
          type="text"
          placeholder="Email"
          className="register-input"
        />

        <input type="text" placeholder="Username" className="register-input" />

        <input type="text" placeholder="Telephone" className="register-input" />

        <input type="text" placeholder="Password" className="register-input" />

        <div>
          <button className="button">Continue</button>
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
        />

        <input type="text" placeholder="Password" className="password-input" />

        <div>
          <button className="button">Login</button>
          <button className="button" onClick={() => setRegister(true)}>
            Register
          </button>
        </div>
      </div>
    </div>
  );
}

export default LoginPage;
