import React, {useState} from 'react';
import {Link} from "react-router-dom";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Login() {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleUsernameChange = (e) => {
        setUsername(e.target.value);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    return (
        <div className="LoginContainer">
            <label><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name={"username"} value={username}
                   onChange={handleUsernameChange} required/>

            <label><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name={"password"} value={password}
                   onChange={handlePasswordChange} required/>

            <TryToLogin username={username} password={password}/>

            <Link to="/make_account"> make account </Link>

            <Link to="/forgot_password"> forgot password </Link>
        </div>
    );
}

function TryToLogin({username, password})
{
    const [auth, setauth] = useState(localStorage.getItem("authorization") || null)

    const handleLogin = () => {
        axios.get("http://localhost:8080/users/login/"+ username +"/"+ password)
            .then(res => {
                if(res.data.authorization === "NORMAL") {
                    localStorage.setItem("authorization", "NORMAL");
                } else if(res.data.authorization === "ADMIN"){
                    localStorage.setItem("authorization", "ADMIN");
                }
                setauth(localStorage.getItem("authorization"));
            }).catch(err => {
            });
    };

    return (
        <div className="LoginContainer">
            <button className={"loginButton"} type="submit" onClick={handleLogin}>Login</button>
            <h3>authorization {auth} </h3>
        </div>
    );
}

export default Login;
