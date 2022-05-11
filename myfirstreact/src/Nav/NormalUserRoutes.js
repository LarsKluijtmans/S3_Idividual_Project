import React from "react";
import {Route, Routes} from "react-router-dom";
import Homepage from "../NotLogedin/HomePage";
import Products from "../NotLogedin/ProductPage/Product";
import View_Product_Details from "../NotLogedin/ProductPage/View_Product_Details";
import MyAccount from "../NormalUser/MyAccount";
import MyProducts from "../NormalUser/MyProducts";
import MyProductDetails from "../NormalUser/MyProductDetails";

function NormalUserRoutes() {

    return (
        <div>
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
}

export default NormalUserRoutes;