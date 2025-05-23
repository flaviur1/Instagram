import { useState, useEffect } from "react";
import "../styles/UserPage.css";
import axios from "../axiosConfig";
import { useNavigate, useParams } from "react-router-dom";
import Header from "../components/Header";

function UserPage() {
  const [user, setUser] = useState({});
  const [posts, setPosts] = useState([]);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    const getUserData = async () => {
      try {
        const response = await axios.get("/api/users/findById/" + id);
        setUser(response.data);

        const response2 = await axios.get("/api/posts/getAllPostsByUser/" + id)
        setPosts(response2.data);
      } catch (error) {
        console.log(error);
      }
    };

    getUserData();
  }, []);

  return (
    <div>
      <div>
        <Header></Header>
      </div>

      <div className="user-page">
        <h3>{user.username}</h3>
        <h3>{user.email}</h3>
        <div className="poza">
          <img src="/assets/react.svg" alt="Poza" />
        </div>
      </div>

      <div className="posts-container">
        {posts.map((post) => (
          <div className="post-card" key={post.id}>
            <h3>{post.title || "Untitled Post"}</h3>

            <button className="user button">
              {post.userId.username || "null"}
            </button>

            <p>{post.text || ""}</p>

            <div className="score-div">
              <button className="like button">l</button>
              <p className="score">{post.score || 0}</p>
              <button className="dislike button">d</button>
            </div>
          </div>
        ))}
      </div>
      
    </div>
  );
}

export default UserPage;
