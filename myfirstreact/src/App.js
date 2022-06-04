import React, {useEffect, useState} from 'react';
import axios from "axios";

import "./Navbar.css"

import AdminNav from "./Nav/Admin";
import NormalUserNav from "./Nav/NormalUser";
import NotLoggedNav from "./Nav/NotLogged";

import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

const ENDPOINT = "http://localhost:8080/ws";

function App() {

    //Login
    const [authorization, setAuthorization] = useState("");
    const [username, setUsername] = useState("");

    useEffect(() => {
        let auth = localStorage.getItem("authorization");
        let username = localStorage.getItem("username");
        if( auth !== null && username !== null){
            setAuthorization(auth);
            setUsername(username);
        }
    },[]);

    function login (username, password) {
        axios.post(`http://localhost:8080/login`,
            {
                "username":username,
                "password":password
            })
            .then(res => {

                //Priority implement web sockits

                //Business and controller layer is priority for testing, repository is possible for later

                //When user adds product popup about new product

                //Service for every method to hide it from the UI code
                //Service class for localstorage

                localStorage.setItem("token", res.data.accessToken);
                localStorage.setItem("authorization", res.data.authorizationLevel);
                localStorage.setItem("username",username);

                //class that stores everything
                //Test javascript

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

    //WebSockets
    const [stompClient, setStompClient] = useState(null);
    useEffect(() => {
        const socket = SockJS(ENDPOINT);
        const stompClient = Stomp.over(socket);
        stompClient.connect({}, () => {
            stompClient.subscribe('/topic/newApps', (data) => {
                onMessageReceived(data);
            });
        });
        setStompClient(stompClient);
    },[]);
    function onMessageReceived(data) {
        const result=  JSON.parse(data.body);
        console.log(result);
        alert( "New Product has been added " +
            "\nTitle: " + result.title +
            "\nSubTitle: " + result.subtitle +
            "\nPrice: " + result.price +
            "\nCondition: " + result.condition)
    };


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