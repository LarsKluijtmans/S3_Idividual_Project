import React from "react";
import {Route, Routes} from "react-router-dom";
import Homepage from "../NotLogedin/HomePage";
import Products from "../NotLogedin/ProductPage/Product";
import View_Product_Details from "../NotLogedin/ProductPage/View_Product_Details";
import Login from "../NotLogedin/AccountPages/Login";
import MakeAccount from "../NotLogedin/AccountPages/MakeAccount";
import ForgotPassword from "../NotLogedin/AccountPages/ForgotPassword";

function NotLogedinRoutes({login}) {

    return (
        <div>
            <Routes>
                <Route path='/' element={<Homepage/>}/>
                <Route path='/products' element={<Products/>}/>
                <Route path='/products/:productId' element={<View_Product_Details/>}/>
                <Route path='/login' element={<Login login={login}/>}/>
                <Route path='/make_account' element={<MakeAccount/>}/>
                <Route path='/forgot_password' element={<ForgotPassword/>}/>
            </Routes>
        </div>
    );
}

export default NotLogedinRoutes;