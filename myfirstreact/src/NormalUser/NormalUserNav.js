import React from 'react';
import {Link} from "react-router-dom";
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
                    <Link to="/myAccount/:id">My Account</Link>
                </li>
                <li>
                    <Link to="/MyProducts/:id">My Products</Link>
                </li>
                <div  className="loginbutton" >
                    <li>
                       <Link onClick={ReturnToHomePage()} to="/"> logout </Link>
                    </li>
                </div>
            </ul>
        </div>
    );
}
export default navbar;