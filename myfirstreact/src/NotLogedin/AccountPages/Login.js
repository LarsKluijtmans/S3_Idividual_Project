import React, {useState} from 'react';
import {Link} from "react-router-dom";


function Login({login}) {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleUsernameChange = (e) => {
        setUsername(e.target.value);
    };
    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    const tryLogin = () => {
      login(username, password);
    }

    return (
        <div className="LoginContainer">
            <label><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name={"username"} value={username}
                   onChange={handleUsernameChange} required/>

            <label><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name={"password"} value={password}
                   onChange={handlePasswordChange} required/>

            <button className={"loginButton"} type="submit" onClick={tryLogin}>Login</button>

            <Link to="/make_account"> make account </Link>

            <Link to="/forgot_password"> forgot password </Link>
        </div>
    );
}


export default Login;
