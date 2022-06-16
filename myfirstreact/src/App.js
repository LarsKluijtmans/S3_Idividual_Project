import React, {useEffect, useState} from 'react';
import {Link} from "react-router-dom";
import {useNavigate} from "react-router-dom";
import axios from "axios";

import "./Navbar.css"
import "./index.css"

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

    let navigate = useNavigate();

    useEffect(() => {
        let auth = localStorage.getItem("authorization");
        let username = localStorage.getItem("username");
        if( auth !== null && username !== null){
            setAuthorization(auth);
            setUsername(username);
        }
    },[]);

    async function login(username, password) {

        await axios.post(`http://localhost:8080/login`,
            {
                "username": username,
                "password": password
            })
            .then(res => {

                //Week 12 meeting with marcio
                //Priority implement web sockits
                //Business and controller layer is priority for testing, repository is possible for later
                //When user adds product popup about new product
                    //Service for every method to hide it from the UI code
                    //Service class for localstorage
                    //class that stores everything
                //Test javascript

                //Message box dosn't disapear on close, works on second click
                //Link works unless in /Products/{id} page

                localStorage.setItem("token", res.data.accessToken);
                localStorage.setItem("authorization", res.data.authorizationLevel);
                localStorage.setItem("username", username);

                setAuthorization(res.data.authorizationLevel);
                setUsername(username);

                navigate(`/`);
            })
            .catch(err => {
                alert("Wrong login information");
            });
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
                console.log('got mail')
                onMessageReceived(data);
            });
        });
        setStompClient(stompClient);
    },[]);

    const [newProducts, setNewProducts] = useState([]);

    const RemoveNewProductMessage = (e) => {
        for (let i = 0; i < newProducts.length; i++) {
            if (newProducts[i].id == e.target.value) {
                newProducts.splice(i, 1);
                setNewProducts(newProducts);
                break;
            }
        }
    }
    const onMessageReceived = (data) => {

        console.log(newProducts)

        const result=  JSON.parse(data.body);

        newProducts.push(result);
        setNewProducts(newProducts);
        
        return(<loadNewProductMessages/>);
    };

    return (
        <>
            <div>
                {(authorization === "NORMAL")?(
                    <NormalUserNav logout={logout} username={username}/>
                ):(authorization === "ADMIN")?(
                    <AdminNav logout={logout}/>
                ):(
                    <NotLoggedNav login={login}/>
                )}
            </div>
            <div className='block'>
                {newProducts.map(product => (
                    <div className='MessageSpacing'>
                        <Link style={{ textDecoration: 'none' }} to={"/products/" + product.id}>
                            <p className='MessageBoxText'> {product.title}</p>
                            <p className='MessageBoxText'> $ {product.price},-</p>
                        </Link>
                        <button className='removeMessage' type="button" value={product.id} onClick={RemoveNewProductMessage} >close</button>
                    </div>
                ))}
            </div>
         </>
    );
}

export default App;