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
        const sortedPosts = response.data.sort((a, b) => {
          const dateA = new Date(a.dateTime);
          const dateB = new Date(b.dateTime);
          return dateB - dateA;
        });
        setPosts(sortedPosts);
      } catch (error) {
        console.log(error);
      }
    };

    getPosts();
  }, []);

  const handleUserClick = async (username) => {
    try {
      const response = await axios.get("/api/users/findByUsername/" + username);
      const id = response.data.id;
      navigate("/user/" + id);
    } catch (error) {
      console.log(error);
    }
  };

  const handleLike = async (id) => {
    try {
      const response = await axios.put("/api/posts/addScore/" + id);
      if (response.data) {
        setPosts((prevPosts) =>
          prevPosts.map((post) =>
            post.id === response.data.id ? response.data : post
          )
        );
      }
    } catch (error) {
      console.log(error);
    }
  };

  const handleDislike = async (id) => {
    try {
      const response = await axios.put("/api/posts/minusScore/" + id);
      if (response.data) {
        setPosts((prevPosts) =>
          prevPosts.map((post) =>
            post.id === response.data.id ? response.data : post
          )
        );
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div>
      <div>
        <Header></Header>
      </div>

      <div className="posts-container">
        {posts.map((post) => (
          <div className="post-card" key={post.id}>
            <button
              className="post-button button"
              onClick={() => {
                navigate("/post/" + post.id);
              }}
            >
              <h3>{post.title || "Untitled Post"}</h3>
            </button>

            <div>
              <button
                className="user button"
                onClick={() => {
                  handleUserClick(post.userId.username);
                }}
              >
                {post.userId.username || "null"}
              </button>

              {/* {(function () {
                if (localStorage.getItem("username") == post.userId.username)
                  return (
                    <button className="edit button" onClick={() => {}}>
                      edit
                    </button>
                  );
                else return undefined;
              })()} */}
            </div>

            <p>{post.text || ""}</p>

            <div className="score-div">
              <button
                className="like button"
                onClick={() => {
                  handleLike(post.id);
                }}
              >
                l
              </button>
              <p className="score">{post.score || 0}</p>
              <button
                className="dislike button"
                onClick={() => {
                  handleDislike(post.id);
                }}
              >
                d
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default HomePage;
