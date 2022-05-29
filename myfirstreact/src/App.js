import React, {useEffect, useState} from 'react';

import "./Navbar.css"

import AdminNav from "./Nav/Admin";
import NormalUserNav from "./Nav/NormalUser";
import NotLoggedNav from "./Nav/NotLogged";
import axios from "axios";

function App() {

    useEffect(() => {
        let auth = localStorage.getItem("authorization");
        let username = localStorage.getItem("username");
        if( auth !== null && username !== null){
            setAuthorization(auth);
            setUsername(username);
        }
    },[]);

    const [authorization, setAuthorization] = useState("");
    const [username, setUsername] = useState("");

    function login (username, password) {
        axios.post(`http://localhost:8080/login`,
            {
                "username":username,
                "password":password
            })
            .then(res => {
                localStorage.setItem("token", res.data.accessToken);
                localStorage.setItem("authorization", res.data.authorizationLevel);
                localStorage.setItem("username",username);

                setAuthorization(res.data.authorizationLevel);
                setUsername(username);
            })
            .catch(err => {});
    }
    const logout =()=>{
        localStorage.removeItem("token");
        localStorage.removeItem("authorization");
        localStorage.removeItem("username");
        setAuthorization("");
    }

    return (
        <div>
            {(authorization === "NORMAL")?(
                <NormalUserNav logout={logout} username={username}/>
            ):(authorization === "ADMIN")?(
                <AdminNav logout={logout}/>
            ):(
                <NotLoggedNav login={login}/>
            )}
        </div>
    );
}

export default App;