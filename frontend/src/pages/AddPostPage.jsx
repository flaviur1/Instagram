import { useState, useEffect } from "react";
import Header from "../components/Header";
import "../styles/AddPostPage.css";
import { useNavigate } from "react-router-dom";
import axios from "../axiosConfig";

function AddPostPage() {
  const [title, setTitle] = useState("");
  const [text, setText] = useState("");
  const [imagePath, setImagePath] = useState("");

  const username = localStorage.getItem("username");
  const navigate = useNavigate();

  //   let date = new Date().toLocaleString();
  //   console.log(date);

  //   console.log(localStorage.getItem("username"));

  const addPost = async () => {
    try {
      const response1 = await axios.get(
        "/api/users/findByUsername/" + username
      );
      console.log(response1.data);
      const userId = response1.data;

      const response = await axios.post("/api/posts/add", {
        title,
        text,
        imagePath,
        userId,
      });
      navigate("/home");
      console.log(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  //   useEffect(() => {
  //     if (!localStorage.getItem("username")) {
  //       navigate("/login");
  //     }
  //   }, []);

  return (
    <div>
      <div>
        <Header></Header>
      </div>

      <div className="addPost-body">
        <div className="input-body">
          <input
            type="text"
            className="title text-input"
            placeholder="Title"
            onChange={(val) => setTitle(val.target.value)}
          />
          <input
            type="text"
            className="image text-input"
            placeholder="Image Path"
            onChange={(val) => setImagePath(val.target.value)}
          />
          <input
            type="text"
            className="description text-input"
            placeholder="Text"
            onChange={(val) => setText(val.target.value)}
          />
        </div>

        <button
          className="create-button"
          onClick={() => {
            addPost();
          }}
        >
          Create Post
        </button>
      </div>
    </div>
  );
}

export default AddPostPage;
