import {Link, Route, Routes} from "react-router-dom";
import Homepage from "../NotLogedin/HomePage";
import React from "react";
import Products from "../NotLogedin/ProductPage/Product";
import View_Product_Details from "../NotLogedin/ProductPage/View_Product_Details";
import MyAccount from "../NormalUser/Account/MyAccount";
import MyProducts from "../NormalUser/Product/MyProducts";
import MyProductDetails from "../NormalUser/Product/MyProductDetails";
import UpdateAccount from "../NormalUser/Account/UpdateAccount";
import AddProduct from "../NormalUser/Product/AddProduct";
import UpdateProduct from "../NormalUser/Product/UpdateProduct";


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

                <Route path='/MyProducts/:username' element={<MyProducts/>}/>
                <Route path='/MyProduct/addProduct/:username' element={<AddProduct/>}/>

                <Route path='/MyProduct/:productId' element={<MyProductDetails/>}/>
                <Route path='/MyProduct/update/:productId' element={<UpdateProduct/>}/>

            </Routes>
        </div>
    );
}

export default NormalUserNav;