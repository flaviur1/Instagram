import { useState } from "react";
import "../styles/HomePage.css";
import axios from "../axiosConfig";
import { useNavigate } from "react-router-dom";

function HomePage() {
  const navigate = useNavigate();

  console.log(localStorage.getItem("token"));

  const getData = async () => {
    try {
      const response = await axios.get("/api/posts/getAll", {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      });
      console.log(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  getData();

  return <div></div>;
}

export default HomePage;
