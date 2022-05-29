import {Link, Route, Routes} from "react-router-dom";
import Homepage from "../NotLogedin/HomePage";
import React from "react";
import Products from "../NotLogedin/ProductPage/Product";
import View_Product_Details from "../NotLogedin/ProductPage/View_Product_Details";
import MyAccount from "../NormalUser/MyAccount";
import MyProducts from "../NormalUser/MyProducts";
import MyProductDetails from "../NormalUser/MyProductDetails";
import UpdateAccount from "../NormalUser/UpdateAccount";

function NormalUserNav({logout, username}) {

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
                    <Link to={"/myAccount/" + username}>My Account</Link>
                </li>
                <li>
                    <Link to={"/MyProducts/" + username}>My Products</Link>
                </li>
                <div  className="loginbutton" >
                    <li>
                        <Link  className={"button"} onClick={logout} to="/"> logout </Link>
                    </li>
                </div>
            </ul>

            <Routes>
                <Route path='/' element={<Homepage/>}/>
                <Route path='/products' element={<Products/>}/>
                <Route path='/products/:productId' element={<View_Product_Details/>}/>

                <Route path='/myAccount/' element={<MyAccount/>}/>
                <Route path='/myAccount/:username' element={<MyAccount/>}/>
                <Route path='/myAccount/update/:username' element={<UpdateAccount/>}/>

                <Route path='/MyProducts/:productId' element={<MyProducts/>}/>
                <Route path='/MyProduct/:productId' element={<MyProductDetails/>}/>
            </Routes>
        </div>
    );
}

export default NormalUserNav;