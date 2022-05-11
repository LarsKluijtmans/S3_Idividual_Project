import React from 'react';
import {Link } from "react-router-dom";
import "../Navbar.css"

const navbar= ({logout}) =>{

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
                        <button onClick={logout}> logout </button>
                    </li>
                </div>
            </ul>
        </div>
    );
}
export default navbar;