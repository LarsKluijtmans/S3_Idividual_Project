import React, {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import { render } from "@testing-library/react";


function MakeAccount(){

    let navigate = useNavigate();

    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [email, setEmail] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [passwordCheck, setPasswordCheck] = useState("");

    const handleFirstnameChange = (e) => {
        setFirstname(e.target.value);
    };
    const handleLastNameChange = (e) => {
        setLastname(e.target.value);
    };
    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    };
    const handlePhoneNumberChange = (e) => {
        setPhoneNumber(e.target.value);
    };
    const handleUsernameChange = (e) => {
        setUsername(e.target.value);
    };
    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };
    const handlePasswordCheckChange = (e) => {
        setPasswordCheck(e.target.value);
    };

    const [error, setError] = useState(null);
    const [uniquePhoneNumber, setUniquePhoneNumber] = useState(true);
    const [uniqueEmail, setUniqueEmail] = useState(true);
    const [uniqueUserName, setUniqueUserName] = useState(true);

    const IsEmailUnique = ()=>{
        axios.get("http://localhost:8080/users/unique/email/" + email)
            .then(res => {
                setUniqueEmail(res.data);
                console.log(res.data);
               })
            .catch(err => {});
    }
    const IsPhoneNumberUnique = ()=>{
        axios.get("http://localhost:8080/users/unique/phoneNumber/" + phoneNumber)
            .then(res => {
                setUniquePhoneNumber(res.data);
                console.log(res.data);

            })
            .catch(err => {});
    }
    const IsUsernameUnique = ()=>{
        axios.get("http://localhost:8080/users/unique/name/" + username)
            .then(res => {
                setUniqueUserName(res.data);
                console.log(res.data);

            })
            .catch(err => {});
    }

    const CheckInput = () => {
        let regex1 = /^([a-zA-Z0-9_-]){1,50}$/;
        let regex8 = /^([a-zA-Z0-9_-]){8,50}$/;

        if (regex1.test(firstname) === false
            || regex1.test(lastname) === false
            || regex8.test(email) === false
            || regex8.test(phoneNumber) === false
            || regex8.test(username) === false
            || regex8.test(password) === false) {
            return;
        }

        if (passwordCheck !== password) {
            setPassword("");
            setPasswordCheck("");
            alert("Please make sure passwords match");
            return;
        }

        IsEmailUnique();
        IsPhoneNumberUnique();
        IsUsernameUnique();

        if(!uniquePhoneNumber) {
            alert("There already exists a account with this PhoneNumber.");
        }
        else if(!uniqueUserName) {
            alert("username already taken.");
        }
        else if(!uniqueEmail) {
            alert("There already exists a account with this email.");
        }
        else{
            makeAccount();
            let path = `/login`;
            navigate(path);
        }


    }
    const makeAccount = () =>{
        axios.post("http://localhost:8080/users",
            {
                username: username,
                password: password,
                firstName: firstname,
                lastName: lastname,
                phoneNumber: phoneNumber,
                email: email,
            })
            .then(res =>{})
            .catch(err => {setError(err.message);});
    }

    return (
        <div>
            <h1 id="heading1">Sign up Here</h1>
            <form action="" className="myForm" name="myForm">
                <div className="input-container">
                    <input className="input-field" type="text" placeholder="FirstName" name={"FirstName"} value={firstname}
                           required minLength={1} maxLength={50}  onChange={handleFirstnameChange}/>
                </div>
                <div className="input-container">
                    <input className="input-field" type="text" placeholder="LastName" name={"LastName"} value={lastname}
                           required minLength={1} maxLength={50}  onChange={handleLastNameChange}/>
                </div>
                <div className="input-container">
                    <input className="input-field" type="text" placeholder="Email" name={"Email"} value={email}
                           required minLength={8} maxLength={50}  onChange={handleEmailChange}/>
                </div>
                <div className="input-container">
                    <input className="input-field" type="text" placeholder="PhoneNumber" name={"PhoneNumber"} value={phoneNumber}
                           required minLength={8} maxLength={50} onChange={handlePhoneNumberChange}/>
                </div>
                <div className="input-container">
                    <input className="input-field" type="text" placeholder="Username" name={"username"} value={username}
                           required minLength={8} maxLength={50}  onChange={handleUsernameChange}/>
                </div>
                <div className="input-container">
                    <input className="input-field" type="password" placeholder="Password" name={"password"} value={password}
                           required minLength={8} maxLength={50} onChange={handlePasswordChange}/>
                </div>

                <div className="input-container">
                    <input className="input-field" type="password" placeholder="Password check" name={"password"} value={passwordCheck}
                           required minLength={8} maxLength={50} onChange={handlePasswordCheckChange}/>
                </div>
                {(error != null)? (
                    <div>
                        <p> A error has occurred. please try again later.</p>
                        <h3> {error} </h3>
                    </div>
                ):(
                    <>
                    </>
                )}

                <button className={"bttn"} onClick={CheckInput}> create account</button>
            </form>
        </div>
    );
}
export default MakeAccount;

