import { useState, useEffect } from "react";
import Header from "../components/Header";
import "../styles/EditPostPage.css";
import { useNavigate, useParams } from "react-router-dom";
import axios from "../axiosConfig";

function EditPostPage() {
  const { postid } = useParams();
  const [post, setPost] = useState({});
  const [title, setTitle] = useState("");
  const [text, setText] = useState("");
  const [imagePath, setImagePath] = useState("");
  const [userId, setUserId] = useState({});

  const navigate = useNavigate();

  useEffect(() => {
    const getPost = async () => {
      try {
        const response = await axios.get("/api/posts/findById/" + postid);
        console.log(response.data);
        const data = response.data;
        setPost(data);
        setTitle(data.title);
        setText(data.text);
        setImagePath(data.imagePath);
        setUserId(data.userId);
      } catch (error) {
        console.log(error);
      }
    };

    getPost();
  }, []);

  const editPost = async () => {
    try {
      const response1 = await axios.get("/api/posts/getParent/" + postid);
      console.log(response1.data);
      const parent = response1.data;

      const response = await axios.put("/api/posts/put", {
        id: postid,
        title,
        text,
        imagePath,
        userId,
        parent,
      });
      if (parent) navigate("/post/" + parent.id);
      else navigate("/home");
      console.log(response.data);
      let date = response.data.dateTime;
    } catch (error) {
      console.log(error);
    }
  };

  const deletePost = async () => {
    try {
      const response = axios.delete("/api/posts/deleteById/" + postid);
      console.log(response.data);
      navigate("/home");
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div>
      <div>
        <Header></Header>
      </div>
      {post.id ? (
        <div className="editPost-body">
          <div className="input-body-edit">
            <input
              type="text"
              className="title text-input-edit"
              placeholder="Title"
              onChange={(val) => setTitle(val.target.value)}
              value={title}
            />
            <input
              type="text"
              className="image text-input-edit"
              placeholder="Image Path"
              onChange={(val) => setImagePath(val.target.value)}
              value={imagePath}
            />
            <input
              type="text"
              className="description text-input-edit"
              placeholder="Text"
              onChange={(val) => setText(val.target.value)}
              value={text}
            />
          </div>

          <div>
            <button
              className="edit-button"
              onClick={() => {
                editPost();
              }}
            >
              Save changes
            </button>

            <button
              className="delete-button"
              onClick={() => {
                deletePost();
              }}
            >
              Delete
            </button>
          </div>
        </div>
      ) : (
        <div></div>
      )}
    </div>
  );
}

export default EditPostPage;
