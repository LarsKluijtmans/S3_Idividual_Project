import React, {useState} from 'react';
import {Link} from "react-router-dom";
import "./Css/Login.css"
import axios from "axios";


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
        <div className="container">
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
    const [result, setResult] = useState('');
    const [error, setError] = useState('');

    const handleLogin = () => {
        axios.get("http://localhost:8080/accounts/login/"+ username +"/"+ password)
            .then(res => {
                setResult(res.data);
            }).catch(err => {
                setError(err.message);
            });
    };

    if(result != null) {
        return (
            <div className="container">
                <button className={"loginButton"} type="submit" onClick={handleLogin}>Login</button>
                <p>{result.firstname}</p>
            </div>
        );
    }
    else if(error != null){
        return (
            <div className="container">
                <button className={"loginButton"} type="submit" onClick={handleLogin}>Login</button>
                <h3>{error}</h3>
            </div>
        );
    }
    else{
        return (
            <div className="container">
                <button className={"loginButton"} type="submit" onClick={handleLogin}>Login</button>
                <h3>Login failed</h3>
            </div>
        );
    }
}

export default Login;
