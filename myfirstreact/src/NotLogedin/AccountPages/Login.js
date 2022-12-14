import React, {useState} from 'react';
import "./Login.css"
import {useNavigate} from "react-router-dom";
import MakeAccount from './MakeAccount';
import { render } from "@testing-library/react";

function Login({login}) {

    let navigate = useNavigate();

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleUsernameChange = (e) => {
        setUsername(e.target.value);
    };
    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    const tryLogin =()=> {
        login(username, password)
    }
    const SignUp =()=> {
        navigate(`/signUp`);
    }

    return (
        <>
            <h1 id="heading1">Login Here</h1>
            <img className={"img"} src="https://img.icons8.com/cute-clipart/344/login-rounded-right.png" alt="Login Logo"/>
                <div>
                    <div className="myForm" >
                        <div className="input-container">
                            <input type="text" placeholder="Username" name="username" className="input-field" value={username} required onChange={handleUsernameChange}/>
                        </div>

                        <div className="input-container">
                            <input type="password" placeholder="Password" name="password" className="input-field" value={password} required onChange={handlePasswordChange}/>
                        </div>
                            <button className={"LoginButton"}  onClick={tryLogin} disabled={!username}>Login</button>
                        </div>

                    <form action="" className="myForm" name="myForm">
                        <button className={"SignUp"} onClick={SignUp}>Sign up</button>
                    </form>
                </div>
        </>
    );
}


export default Login;
