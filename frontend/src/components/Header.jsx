import { useNavigate } from "react-router-dom";
import "../styles/Header.css";
import SearchBar from "./SearchBar";

function Header() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <div className="header">
      <button
        className="insta-button"
        onClick={() => {
          navigate("/home");
        }}
      >
        Instagram
      </button>
      <SearchBar className="search-bar"></SearchBar>
      <div>
        <button
          className="header-button"
          onClick={() => {
            handleLogout();
          }}
        >
          Log out
        </button>
        <button
          className="header-button"
          onClick={() => {
            navigate("/post/add");
          }}
        >
          Add Post
        </button>
      </div>
    </div>
  );
}

export default Header;
