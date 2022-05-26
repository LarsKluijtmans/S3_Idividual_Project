import React, {useState} from 'react';
import axios from "axios";

import "./Navbar.css"

import {Link, Route, Routes} from "react-router-dom";
import Homepage from "./NotLogedin/HomePage";
import Products from "./NotLogedin/ProductPage/Product";
import View_Product_Details from "./NotLogedin/ProductPage/View_Product_Details";
import Login from "./NotLogedin/AccountPages/Login";
import MakeAccount from "./NotLogedin/AccountPages/MakeAccount";
import AllUsers from "./Admin/AllUsers";
import AllProducts from "./Admin/AllProducts";
import MyAccount from "./NormalUser/MyAccount";
import MyProducts from "./NormalUser/MyProducts";
import MyProductDetails from "./NormalUser/MyProductDetails";

function App() {

    const [authorization, setAuthorization] = useState("");

    const login = async (username, password) => {
        const login = {username: username, password: password};

         axios.post(`http://localhost:8080/login`, login)
            .then(result => {
                localStorage.setItem("token", result.data.accessToken);
                setAuthorization(result.data.authorizationLevel);
            });
    }
    const logout =()=>{
        localStorage.setItem("token", "");
        setAuthorization("");
    }

    return (
        <div>
            {/* (authorization === "NORMAL")?(
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
                                <Link  className={"button"} onClick={logout} to="/"> logout </Link>
                            </li>
                        </div>
                    </ul>

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
            ):(authorization === "ADMIN")?(*/
                <div>
                    <ul>
                        <li>
                            <Link className={"button"} to="/">Home page</Link>
                        </li>
                        <li>
                            <Link className={"button"} to="/products">Products</Link>
                        </li>
                        <li>
                            <Link className={"button"} to="/users">Users</Link>
                        </li>
                        <div className="loginbutton" >
                            <li>
                                <button className={"button"} onClick={logout}> logout </button>
                            </li>
                        </div>
                    </ul>

                    <Routes>
                        <Route path='/' element={<Homepage/>}/>
                        <Route path='/products' element={<AllProducts/>}/>
                        <Route path='/users' element={<AllUsers/>}/>
                    </Routes>
                </div>
            /*):(
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
            )*/}
        </div>
    );
}

export default App;