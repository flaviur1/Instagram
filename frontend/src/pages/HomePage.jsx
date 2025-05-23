import { useState, useEffect } from "react";
import "../styles/HomePage.css";
import axios from "../axiosConfig";
import { useNavigate } from "react-router-dom";
import Header from "../components/Header";

function HomePage() {
  const [posts, setPosts] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const getPosts = async () => {
      try {
        const response = await axios.get("/api/posts/getAllPosts");
        setPosts(response.data);
      } catch (error) {
        console.log(error);
      }
    };

    getPosts();
  }, []);

  return (
    <div>
      <div>
        <Header></Header>
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

export default HomePage;
