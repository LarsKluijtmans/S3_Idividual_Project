import React from 'react';
import {Link } from "react-router-dom";
import "../../Navbar.css"

const navbar= () =>{
    return (
        <div>
            <ul>
                <li>
                    <Link to="/">Home page</Link>
                </li>
                <li>
                    <Link to="/products">Products</Link>
                </li>
                <div  className="loginbutton" >
                    <li>
                        <Link to="/login">Login</Link>
                    </li>
                </div>
            </ul>
        </div>
    );
}
export default navbar;