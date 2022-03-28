import React from 'react';
import { Route, Routes } from "react-router-dom";


import Navbar  from "./Navbar/Navbar";
import Homepage from "./Pages/HomePage"
import Products from "./Pages/Product"
import Login from "./Pages/Login"
import MakeAccount from "./Pages/AccountPages/MakeAccount"
import ForgotPassword from "./Pages/AccountPages/ForgotPassword"
import View_Product_Details from    "./Pages/ProductPage/View_Product_Details"

function App() {

    return (
        <div>
            <Navbar/>
            <Routes>
                <Route path='/' element={<Homepage/>}/>

                <Route path='/products/*' element={<Products/>}/>
                <Route path='/products/:productId' element={<View_Product_Details />}/>

                <Route path='/login' element={<Login/>}/>
                <Route path='/make_account' element={<MakeAccount/>}/>
                <Route path='/forgot_password' element={<ForgotPassword/>}/>
            </Routes>
        </div>
      );
}

export default App;