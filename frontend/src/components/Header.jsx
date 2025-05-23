import { useNavigate } from "react-router-dom";
import "../styles/Header.css";
import SearchBar from "./SearchBar";

function Header() {
  return (
    <div className="header">
      <p className="text">Instagram</p>
      <SearchBar className="search-bar"></SearchBar>
      <button className="log-out-button">Log out</button>
    </div>
  );
}

export default Header;
