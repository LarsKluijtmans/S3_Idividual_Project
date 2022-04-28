import React from 'react';
import {Link } from "react-router-dom";
import "../Navbar.css"

const navbar= () =>{

    const ReturnToHomePage = () => {
        localStorage.setItem("authorization","");
        window.location.reload();
    }

    return (
        <div>
            <ul>
                <li>
                    <Link to="/">Home page</Link>
                </li>
                <li>
                    <Link to="/products">Products</Link>
                </li>
                <li>
                    <Link to="/users">Users</Link>
                </li>
                <li>
                    <Link to="/statistics">Statistics</Link>
                </li>
                <div  className="loginbutton" >
                    <li>
                        <button onClick={ReturnToHomePage()}> logout </button>
                    </li>
                </div>
            </ul>
        </div>
    );
}
export default navbar;