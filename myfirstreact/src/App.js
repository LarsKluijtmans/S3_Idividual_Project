import React, {useState} from 'react';
import axios from "axios";

import Navbar  from "./Nav/Navbar";
import NormalUserNav from "./Nav/NormalUserNav";
import AdminNav from "./Nav/AdminNav";

import AdminRoutes from "./Nav/AdminRoutes";
import NormalUserRoutes from "./Nav/NormalUserRoutes";
import NotLogedinRoutes from "./Nav/NotLogedinRoutes";

function App() {

    const [authorization, setAuthorization] = useState("");

    function login(username, password){
        axios.post(`http://localhost:8080/login`,
            {
                "username":username,
                "password":password
            })
            .then(res => {
                localStorage.setItem("token", res.data.accessToken);
                setAuthorization(res.data.authorizationLevel);
            })
            .catch(err => {});
    }
    function logout(){
        setAuthorization("");
    }

    return (
        <div>
            {(authorization === "NORMAL")?(
                <div>
                    <NormalUserNav  logout={logout}/>
                    <NormalUserRoutes/>
                </div>
            ):(authorization === "ADMIN")?(
                <div>
                    <AdminNav logout={logout}/>
                    <AdminRoutes/>
                </div>
            ):(
                <div>
                    <Navbar/>
                    <NotLogedinRoutes login={login}/>
                </div>
            )}
        </div>
    );
}

export default App;