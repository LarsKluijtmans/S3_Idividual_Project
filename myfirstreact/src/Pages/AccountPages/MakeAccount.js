import React, {useState} from "react";
import {Link} from "react-router-dom";
import axios from "axios";


function MakeAccount(){
    const [firstname, setfirstname] = useState('');
    const [lastname, setlastname] = useState('');
    const [email, setemail] = useState('');
    const [phoneNumber, setphoneNumber] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleFirstnameChange = (e) => {
        setfirstname(e.target.value);
    };
    const handleLastNameChange = (e) => {
        setlastname(e.target.value);
    };
    const handleEmailChange = (e) => {
        setemail(e.target.value);
    };
    const handlePhoneNumberChange = (e) => {
        setphoneNumber(e.target.value);
    };
    const handleUsernameChange = (e) => {
        setUsername(e.target.value);
    };
    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    return(
        <div>
            <br/>
            <label><b>Firstname</b></label>
            <input type="text" placeholder="Enter FirstName" name={"FirstName"} value={firstname} onChange={handleFirstnameChange}/>

            <label><b>LastName</b></label>
            <input type="text" placeholder="Enter LastName" name={"LastName"} value={lastname} onChange={handleLastNameChange}/>

            <label><b>Email</b></label>
            <input type="text" placeholder="Enter Email" name={"Email"} value={email} onChange={handleEmailChange}/>

            <label><b>PhoneNumber</b></label>
            <input type="text" placeholder="Enter PhoneNumber" name={"PhoneNumber"} value={phoneNumber} onChange={handlePhoneNumberChange}/>

            <br/>

            <label><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name={"username"} value={username} onChange={handleUsernameChange}/>

            <label><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name={"password"} value={password} onChange={handlePasswordChange}/>

            <CreateAccount firstname={firstname} lastname={lastname} email={email} phoneNumber={phoneNumber} username={username} password={password}/>

            <Link to="/login"> back </Link>
        </div>
    );
}
export default MakeAccount;


function CreateAccount({firstname, lastname, email, phoneNumber, username, password}) {

    const [result, setResult] = useState(null);
    const [error, setError] = useState(null);

    const makeAccount = () =>{

        if(firstname != "") {
            axios.post("http://localhost:8080/accounts",
                {
                    userID: 12,
                    firstname: firstname.toString(),
                    lastname: lastname.toString(),
                    email: email.toString(),
                    phoneNumber: phoneNumber.toString(),
                    account: {
                        username: username.toString(),
                        password: password.toString()
                    }
                })
                .then(res => {
                    setResult(res.data);
                })
                .catch(err => {
                    setError(err.message);
                });
        }
    }

    if(result != null) {
        return (
            <div className="container">
                <button onClick={makeAccount}> create account</button>
                <p> Account has been made </p>
                <h3> {result} </h3>
            </div>
        );
    }
    else if(error != null) {
        return (
            <div className="container">
                <button onClick={makeAccount}> create account</button>
                <h3> {error} </h3>
            </div>
        );
    }
    else {
        return (
                <button onClick={makeAccount}> create account</button>
        );
    }
}