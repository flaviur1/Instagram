import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "../axiosConfig";

function PostPage() {
  const { postid } = useParams();
  const [post, setPost] = useState({});
  const [comments, setComments] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    const getPost = async () => {
      try {
        const response = await axios.get("/api/posts/findById/" + postid);
        console.log(response.data);
        setPost(response.data);
        setComments(response.data.replies);
      } catch (error) {
        console.log(error);
      }
    };

    getPost();
  }, []);

  return (
    <div>
      <div>
        <p>Postare:</p>
        {post.title}
      </div>
      {post.text}

      <div>
        <p>comments:</p>
        {comments.map((reply) => (
          <div>
            <h3>{reply.title}</h3>
          </div>
        ))}
      </div>
    </div>
  );
}

export default PostPage;
