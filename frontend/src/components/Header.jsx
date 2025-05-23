import { useNavigate } from "react-router-dom";
import "../styles/Header.css";
import SearchBar from "./SearchBar";

function Header() {
  const navigate = useNavigate();
  return (
    <div className="header">
      <p className="text">Instagram</p>
      <SearchBar className="search-bar"></SearchBar>
      <div>
        <button className="header-button">Log out</button>
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
