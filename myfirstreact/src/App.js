import React, {useEffect, useState} from 'react';
import { Route, Routes } from "react-router-dom";

//All
import View_Product_Details from "./NotLogedin/ProductPage/View_Product_Details"
import Homepage from "./NotLogedin/HomePage"
import Products from "./NotLogedin/ProductPage/Product"

//NotLogenIn
import Navbar  from "./NotLogedin/Navbar/Navbar";
import Login from "./NotLogedin/AccountPages/Login"
import MakeAccount from "./NotLogedin/AccountPages/MakeAccount"
import ForgotPassword from "./NotLogedin/AccountPages/ForgotPassword"

//NormalUser
import MyAccount from "./NormalUser/MyAccount";
import MyProducts from "./NormalUser/MyProducts";
import MyProductDetails from "./NormalUser/MyProductDetails";
import NormalUserNav from "./NormalUser/NormalUserNav";

//Admin
import AdminNav from "./Admin/AdminNav";


function App() {

    if(localStorage.getItem('authorization') === "NORMAL") {
        return (
            <div>
                <NormalUserNav/>
                <Routes>
                    <Route path='/' element={<Homepage/>}/>

                    <Route path='/products' element={<Products/>}/>
                    <Route path='/products/:productId' element={<View_Product_Details/>}/>

                    <Route path='/myAccount/' element={<MyAccount/>}/>
                    <Route path='/myAccount/:id' element={<MyAccount/>}/>
                    <Route path='/MyProducts/:id' element={<MyProducts/>}/>
                    <Route path='/MyProduct/:productId' element={<MyProductDetails/>}/>
                </Routes>
            </div>
        );
    } else if(localStorage.getItem('authorization') === "ADMIN") {
        return (
            <div>
                <AdminNav/>
                <Routes>

                </Routes>
            </div>
        );
    } else {
        return (
            <div>
                <Navbar/>
                <Routes>
                    <Route path='/' element={<Homepage/>}/>

                    <Route path='/products' element={<Products/>}/>
                    <Route path='/products/:productId' element={<View_Product_Details/>}/>

                    <Route path='/login' element={<Login/>}/>
                    <Route path='/make_account' element={<MakeAccount/>}/>
                    <Route path='/forgot_password' element={<ForgotPassword/>}/>
                </Routes>
            </div>
        );
    }
}

export default App;