import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "../axiosConfig";
import Header from "../components/Header.jsx";
import "../styles/PostPage.css";

function PostPage() {
  const { postid } = useParams();
  const [post, setPost] = useState({});
  const [comments, setComments] = useState([]);
  const [text, setText] = useState("");
  const [imagePath, setImagePath] = useState("");

  const navigate = useNavigate();
  const username = localStorage.getItem("username");

  useEffect(() => {
    const getPost = async () => {
      try {
        const response = await axios.get("/api/posts/findById/" + postid);
        console.log(response.data);
        setPost(response.data);
        let replies = response.data.replies;
        const sortedReplies = replies.sort((a, b) => {
          const scoreA = a.score !== null ? a.score : 0;
          const scoreB = b.score !== null ? b.score : 0;
          return scoreB - scoreA;
        });
        setComments(replies);
      } catch (error) {
        console.log(error);
      }
    };

    getPost();
  }, []);

  const addComment = async () => {
    try {
      const response1 = await axios.get(
        "/api/users/findByUsername/" + username
      );
      console.log(response1.data);
      const userId = response1.data;

      const response = await axios.post("/api/posts/addComment/" + postid, {
        text,
        imagePath,
        userId,
      });
      console.log(response.data);
      setText("");
      setImagePath("");
      setComments((previous) => [...previous, response.data]);
    } catch (error) {
      console.log(error);
    }
  };

  let date = "";
  let dateComment = "";

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
        setComments((prevComments) => {
          const updatedComments = prevComments.map((reply) =>
            reply.id === response.data.id ? response.data : reply
          );

          return updatedComments.slice().sort((a, b) => {
            const scoreA = a.score !== null ? a.score : 0;
            const scoreB = b.score !== null ? b.score : 0;
            return scoreB - scoreA;
          });
        });
      }
    } catch (error) {
      console.log(error);
    }
  };

  const handleDislike = async (id) => {
    try {
      const response = await axios.put("/api/posts/minusScore/" + id);
      if (response.data) {
        setComments((prevComments) => {
          const updatedComments = prevComments.map((reply) =>
            reply.id === response.data.id ? response.data : reply
          );

          return updatedComments.slice().sort((a, b) => {
            const scoreA = a.score !== null ? a.score : 0;
            const scoreB = b.score !== null ? b.score : 0;
            return scoreB - scoreA;
          });
        });
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

      <div className="post-body">
        <div>
          <h1>{post.title}</h1>

          <p>{(date = new Date(post.dateTime).toLocaleString())}</p>

          <p>{post.text}</p>

          {post.imagePath && (
            <img
              src={`${axios.defaults.baseURL}/images/${post.imagePath}`}
              alt="Image"
              className="post-image"
            />
          )}
          <div>
            {(function () {
              if (
                post.userId != undefined &&
                localStorage.getItem("username") == post.userId.username
              )
                return (
                  <button
                    className="edit-post button"
                    onClick={() => {
                      navigate("/post/edit/" + postid);
                    }}
                  >
                    Edit Post
                  </button>
                );
              else return undefined;
            })()}
          </div>
        </div>

        <div>
          <input
            type="text"
            placeholder="Write a comment"
            className="input-comment"
            onChange={(val) => setText(val.target.value)}
          />

          <input
            type="text"
            placeholder="ImagePath"
            className="input-comment"
            onChange={(val) => setImagePath(val.target.value)}
          />

          <button
            className="comment-button"
            onClick={() => {
              addComment();
            }}
          >
            Add comment
          </button>
        </div>

        <div className="comments-container">
          <h3>Comments:</h3>
          {comments.map((reply) => (
            <div className="comment-card" key={reply.id}>
              <div>
                <button
                  className="user button"
                  onClick={() => {
                    handleUserClick(reply.userId.username);
                  }}
                >
                  {reply.userId.username || "null"}
                </button>
              </div>

              <p>{(dateComment = new Date(reply.dateTime).toLocaleString())}</p>

              <p>{reply.text || ""}</p>
              <p>
                {reply.imagePath && (
                  <img
                    src={`${axios.defaults.baseURL}/images/${reply.imagePath}`}
                    alt="Image"
                    className="comment-image"
                  />
                )}

                {(function () {
                  if (localStorage.getItem("username") == reply.userId.username)
                    return (
                      <button
                        className="edit-comment button"
                        onClick={() => {
                          navigate("/post/edit/" + reply.id);
                        }}
                      >
                        edit
                      </button>
                    );
                  else return undefined;
                })()}
              </p>

              <div className="comment-score-div">
                <button
                  className="comment-like"
                  onClick={() => {
                    handleLike(reply.id);
                  }}
                >
                  l
                </button>
                <p className="comment-score">{reply.score || 0}</p>
                <button
                  className="comment-dislike"
                  onClick={() => {
                    handleDislike(reply.id);
                  }}
                >
                  d
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default PostPage;
