import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from "react-router-dom";
import axios from "axios";
import "../../NotLogedin/AccountPages/Login.css"

const UpdateAccount= () =>{

    useEffect(() => {
        getUser();
    },[]);

    const {username} = useParams();

    let navigate = useNavigate();

    const [user, setUser] = useState({});
    const [error, serError] = useState({});

    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [email, setEmail] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");


    let token = localStorage.getItem("token");

    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };
    const getUser =() => {
        axios.get(`http://localhost:8080/users/normal/`+ username, config
        ).then(res => {
            setUser(res.data);
            setFirstname(res.data.firstName)
            setLastname(res.data.lastName)
            setEmail(res.data.email)
            setPhoneNumber(res.data.phoneNumber)
        }).catch(err => {
            serError(err.message);
        });
    }

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

    const [uniquePhoneNumber, setUniquePhoneNumber] = useState(true);
    const [uniqueEmail, setUniqueEmail] = useState(true);

    const IsEmailUnique = ()=>{
        axios.get("http://localhost:8080/users/unique/email/" + email)
            .then(res => {setUniqueEmail(res.data);})
            .catch(err => {});
    }
    const IsPhoneNumberUnique = ()=>{
        axios.get("http://localhost:8080/users/unique/phoneNumber/" + phoneNumber)
            .then(res => {setUniquePhoneNumber(res.data);})
            .catch(err => {});
    }

    const updateUser = () => {

        let regex1 = /^([a-zA-Z0-9_-]){1,50}$/;
        let regexEmail = /^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/;
        let regexPhoneNumber = /^([a-zA-Z0-9_-]){7,20}$/;

        if (!uniqueEmail) {
            alert("firstname has to be between 1 and 50");
            return;
        }
        if (!uniquePhoneNumber) {
            alert("firstname has to be between 1 and 50");
            return;
        }


        if (regex1.test(firstname) === false) {
            alert("firstname has to be between 1 and 50");
            return;
        }
        if (regex1.test(lastname) === false) {
            alert("lastName has to be between 1 and 50");
            return;
        }
        if (regexEmail.test(email) === false) {
            alert("email not valid");
            return;
        }
        if (regexPhoneNumber.test(phoneNumber) === false) {
            alert("phoneNumber not valid");
            return;
        }

        const data = {
            username: user.username,
            firstName: firstname,
            lastName: lastname,
            phoneNumber: phoneNumber,
            email: email
        }

        axios.put(`http://localhost:8080/users`, data, config
        ).then(res => {
            alert("you information has been updated");
        }).catch(err => {
            serError(err.message);
        });
        let path = `/myAccount/` + username;
        navigate(path);
    }

    return (
        <div>
            <h1 id="heading1">Update</h1>
            <div action="" className="myForm" name="myForm">
                <input className="input-field" type="text" placeholder="FirstName" name="FirstName" value={firstname}
                       required minLength={1} maxLength={50}  onChange={handleFirstnameChange}/>

                <input className="input-field" type="text" placeholder="LastName" name="LastName" value={lastname}
                       required minLength={1} maxLength={50}  onChange={handleLastNameChange}/>

                <input className="input-field" type="text" placeholder="Email" name="Email" value={email}
                       required minLength={8} maxLength={50}  onChange={handleEmailChange}/>

                <input className="input-field" type="text" placeholder="PhoneNumber" name="PhoneNumber" value={phoneNumber}
                       required minLength={8} maxLength={50} onChange={handlePhoneNumberChange}/>

                <button className={"bttn"} onClick={updateUser}>Update account</button>
            </div>
        </div>
    );
}
export default UpdateAccount;