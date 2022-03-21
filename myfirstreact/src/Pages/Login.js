import React, {useState} from 'react';

import "./Css/Login.css"
import axios from "axios";


function Login() {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [result, setResult] = useState('');
    const [error, setError] = useState('');

    const handleUsernameChange = (e) => {
        setUsername(e.target.value);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };



    const handleLogin = () => {
        const options = {
            method: 'get',
            url: "http://localhost:8080/accounts/login",
            data: {
                username: username.toString(),
                password: password.toString()
            }
        };

        axios(options)
            .then(res => {
                setResult(res.data);
                console.log("Result  username:" + username.toString() + " ,password: " +password.toString());
            }).catch(err => {
                setError(err.message);
                console.log("Error   username:" + username.toString() + " ,password: " +password.toString());
            });
    };

    const MakeAccount =()=>{
        console.log("Make account")
    };
    const ForgotPassword =()=>{
        console.log("Forgot password")
    };

    if(result != null) {
        return (
            <div className="container">
                <label><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name={"username"} value={username}
                       onChange={handleUsernameChange} required/>

                <label><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name={"password"} value={password}
                       onChange={handlePasswordChange} required/>

                <button className={"loginButton"} type="submit" onClick={handleLogin}>Login</button>
                <button  className={"makeAccountButton"} type="submit" onClick={MakeAccount}> make account </button>
                <button className={"forgotPassword"} type="submit" onClick={ForgotPassword}> forgot password </button>


                <p>{result.firstname}</p>
            </div>
        );
    }
    else if(error != null){
        return (
            <div className="container">
                <label><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name={"username"} value={username}
                       onChange={handleUsernameChange} required/>

                <label><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name={"password"} value={password}
                       onChange={handlePasswordChange} required/>

                <button  className={"loginButton"}  type="submit" onClick={handleLogin}>Login</button>
                <button  className={"makeAccountButton"} type="submit" onClick={MakeAccount}> make account </button>
                <button className={"forgotPassword"} type="submit" onClick={ForgotPassword}> forgot password </button>

                <h3> Login failed</h3>
            </div>
        );
    }
    else{
        return (
            <div className="container">
                <label><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name={"username"} value={username}
                       onChange={handleUsernameChange} required/>

                <label><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name={"password"} value={password}
                       onChange={handlePasswordChange} required/>

                <button   className={"loginButton"}  type="submit" onClick={handleLogin}>Login</button>
                <button  className={"makeAccountButton"} type="submit" onClick={MakeAccount}> make account </button>
                <button className={"forgotPassword"} type="submit" onClick={ForgotPassword}> forgot password </button>
            </div>
        );
    }
}

export default Login;
