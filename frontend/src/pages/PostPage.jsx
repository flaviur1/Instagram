import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "../axiosConfig";
import Header from "../components/Header.jsx";
import "../styles/PostPage.css";

function PostPage() {
  const { postid } = useParams();
  const [post, setPost] = useState({});
  const [comments, setComments] = useState([]);
  const [buttonPressed, setButtonPressed] = useState(false);
  const [title, setTitle] = useState("");
  const [text, setText] = useState("dasdas");
  const [imagePath, setImagePath] = useState("");

  const navigate = useNavigate();
  const username = localStorage.getItem("username");

  useEffect(() => {
    const getPost = async () => {
      try {
        const response = await axios.get("/api/posts/findById/" + postid);
        console.log(response.data);
        setPost(response.data);
        setComments(response.data.replies);
        const parent = post;
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

      const response = await axios.post("/api/posts/add/", {
        title,
        text,
        imagePath,
        userId,
        parent,
      });
      console.log(response.data);
    } catch (error) {
      console.log(error);
    }
  };

    let date = "";

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
        </div>

        <div>
          <input
            type="text"
            placeholder="Write a comment"
            className="input-comment"
            onChange={(val) => setTitle(val.target.value)}
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

        <div>
          <h3>Comments:</h3>
          {comments.map((reply) => (
            <div className="comment" key={reply.id}>
              <p>{reply.title}</p>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default PostPage;
