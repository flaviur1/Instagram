import { useState, useEffect } from "react";
import "../styles/HomePage.css";
import axios from "../axiosConfig";
import { useNavigate } from "react-router-dom";

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
    <div className="posts-container">
      {posts.map((post) => (
        <div className="post-card" key={post.id}>
          <h3>{post.title || "Untitled Post"}</h3>
          <h4>{post.userId.username || "null"}</h4>
          <p>{post.content || JSON.stringify(post)}</p>
          {/* Add more fields as needed */}
        </div>
      ))}
    </div>
  );
}

export default HomePage;
