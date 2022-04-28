import React, {useState} from "react";
import {Link} from "react-router-dom";
import axios from "axios";


function MakeAccount(){
    const [firstname, setfirstname] = useState("");
    const [lastname, setlastname] = useState("");
    const [email, setemail] = useState("");
    const [phoneNumber, setphoneNumber] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

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



    return (
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

           <CreateUser username={username} password={password} firstname={firstname} lastname={lastname} phoneNumber={phoneNumber} email={email}/>

            <Link to="/login"> back </Link>
        </div>
    );
}
export default MakeAccount;


function CreateUser({username, password, firstname, lastname, phoneNumber, email}){

    const [error, setError] = useState(null);
    const [uniquePhoneNumber, setUniquePhoneNumber] = useState(true);
    const [uniqueEmail, setUniqueEmail] = useState(true);
    const [uniqueUserName, setUniqueUserName] = useState(true);

    let result = "";

    //Check if unique
    const IsEmailUnique = ()=>{
        axios.get("http://localhost:8080/users/unique/email/" + email)
        .then(res => {
            setUniqueEmail(res.data);
        })
        .catch(err => {
            setError(err.message);
        });
    }
    const IsPhoneNumberUnique = ()=>{
        axios.get("http://localhost:8080/users/unique/phoneNumber/" + phoneNumber)
            .then(res => {
                setUniquePhoneNumber(res.data);
            })
            .catch(err => {
                setError(err.message);
            });
    }
    const IsUsernameUnique = ()=>{
        axios.get("http://localhost:8080/users/unique/name/" + username)
            .then(res => {
                setUniqueUserName(res.data);
            })
            .catch(err => {
                setError(err.message);
            });
    }

    const CheckInput = () => {
        setError(null);

        result = "";

        IsEmailUnique();
        IsPhoneNumberUnique();
        IsUsernameUnique();

        if (username == "") {
            result += "username has to have 3 to 50 characters.\n";
        }
        if (password === "") {
            result += "username has to have 3 to 50 characters.\n";
        }
        if (firstname === "") {
            result += "username has to have 5 to 50 characters.\n";
        }
        if (lastname === "") {
            result += "username has to have 5 to 50 characters.\n";
        }
        if (phoneNumber === "") {
            result += "username has to have 9 to 50 characters.\n";
        }
        if (email === "") {
            result += "username has to have 8 to 50 characters.\n";
        }
        if (uniqueUserName === false) {
            result += "Username is taken.\n";
        }
        if (uniqueEmail === false) {
            result += "There already exists a account with this email.\n";
        }
        if (uniquePhoneNumber === false) {
            result += "There already exists a account with this phoneNumber.\n";
        }
        setUniqueUserName(true);
        setUniqueEmail(true);
        setUniquePhoneNumber(true);
        if(result === ""){
            makeAccount();}
        else{
            alert(result);
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
            .then()
            .catch(err => {
                setError(err.message);
            });
    }


    if(error != null) {
        return(
            <div>
                <p> A error has occurred. please try again </p>
                <h3> {error} </h3>
                <br/>
                <button onClick={CheckInput}> create account</button>
            </div>
        );
    }
    else {
        return (
            <div>
                <button onClick={CheckInput}> create account</button>
            </div>
        );
    }

}