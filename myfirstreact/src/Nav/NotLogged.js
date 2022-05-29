import React from "react";
import {Link, Route, Routes} from "react-router-dom";
import Homepage from "../NotLogedin/HomePage";
import Products from "../NotLogedin/ProductPage/Product";
import View_Product_Details from "../NotLogedin/ProductPage/View_Product_Details";
import Login from "../NotLogedin/AccountPages/Login";
import MakeAccount from "../NotLogedin/AccountPages/MakeAccount";

function NotLoggedNav({login}) {

    return (
        <div>
            <ul>
                <li>
                    <Link className={"button"} to="/">Home page</Link>
                </li>
                <li>
                    <Link className={"button"} to="/products">Products</Link>
                </li>
                <div  className="loginbutton" >
                    <li>
                        <Link  className={"button"} to="/login">Login</Link>
                    </li>
                </div>
            </ul>
            <Routes>
                <Route path='/' element={<Homepage/>}/>
                <Route path='/products' element={<Products/>}/>
                <Route path='/products/:productId' element={<View_Product_Details/>}/>
                <Route path='/login' element={<Login login={login}/>}/>
                <Route path='/signUp' element={<MakeAccount/>}/>
            </Routes>
        </div>
    );
}

export default NotLoggedNav;