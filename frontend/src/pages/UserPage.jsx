import { useState, useEffect } from "react";
import "../styles/UserPage.css";
import axios from "../axiosConfig";
import { useNavigate, useParams } from "react-router-dom";

function UserPage() {
  const [user, setUser] = useState([]);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    const getUserData = async () => {
      try {
        const response = await axios.get("/api/users/findById/" + id);
        setUser(response.data);
      } catch (error) {
        console.log(error);
      }
    };

    getUserData();
  }, []);

  return (
    <div className="user-page">
      <h3>{user.username}</h3>
      <h3>{user.email}</h3>
    </div>
  );
}

export default UserPage;
