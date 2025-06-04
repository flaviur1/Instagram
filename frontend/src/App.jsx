import { useState } from "react";
import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage.jsx";
import HomePage from "./pages/HomePage.jsx";
import UserPage from "./pages/UserPage.jsx";
import NotFound from "./pages/NotFound.jsx";
import AddPostPage from "./pages/AddPostPage.jsx";
import PostPage from "./pages/PostPage.jsx";
import EditPostPage from "./pages/EditPostPage.jsx";

function App() {
  return (
    <BrowserRouter>
      {/* <div className="body">
        <LoginPage></LoginPage>
      </div> */}
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        {/* punem aici register ca nu am facut pagina separata de register */}
        <Route path="/register" element={<LoginPage />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/user/:id" element={<UserPage />} />
        <Route path="/post/add" element={<AddPostPage />} />
        <Route path="/post/:postid" element={<PostPage />} />
        <Route path="/post/edit/:postid" element={<EditPostPage />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
