import {Link, Route, Routes} from "react-router-dom";
import Homepage from "../NotLogedin/HomePage";
import React from "react";
import AllProducts from "../Admin/AllProducts";
import ProductDetails from "../Admin/ProductDetails";
import AllUsers from "../Admin/AllUsers";
import UserDetails from "../Admin/UserDetails";

function AdminNav({logout}) {

    return (
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
                <Route path='/products/:ProductId' element={<ProductDetails/>}/>
                <Route path='/users' element={<AllUsers/>}/>
                <Route path='/users/:Username' element={<UserDetails/>}/>
            </Routes>
        </div>
    );
}

export default AdminNav;