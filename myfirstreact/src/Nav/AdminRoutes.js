import React from "react";
import {Route, Routes} from "react-router-dom";
import Homepage from "../NotLogedin/HomePage";
import Products from "../NotLogedin/ProductPage/Product";
import View_Product_Details from "../NotLogedin/ProductPage/View_Product_Details";
import MyAccount from "../NormalUser/MyAccount";
import MyProducts from "../NormalUser/MyProducts";
import MyProductDetails from "../NormalUser/MyProductDetails";

function AdminRoutes() {

    return (
        <div>
            <Routes>
                <Route path='/' element={<Homepage/>}/>
                <Route path='/products' element={<Products/>}/>
            </Routes>
        </div>
    );
}

export default AdminRoutes;