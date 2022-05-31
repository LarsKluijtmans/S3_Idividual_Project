import React, {useEffect, useState} from 'react';
import {Link, useNavigate, useParams} from "react-router-dom";
import axios from "axios";

import "./MyAccountCSS.css"

const MyAccount= () =>{

    useEffect(() => {
        getUser();
    },[]);

    const {username} = useParams();

    let navigate = useNavigate();

    const [products, setProducts] = useState([]);
    const [user, setUser] = useState({});
    const [error, serError] = useState({});

    let token = localStorage.getItem("token");

    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };
    const getUser =() => {
        axios.get(`http://localhost:8080/users/normal/`+ username, config
        ).then(res => {
            setUser(res.data);
        }).catch(err => {
            serError(err.message);
        });
        getUsersProducts();
    }
    const getUsersProducts =() => {
        axios.get(`http://localhost:8080/products/normal/` + username, config
        ).then(res => {
            setProducts(res.data);
        }).catch(err => {
            serError(err.message);
        });
    }

    const updateUser = () => {
        let path = `/myAccount/update/` + username;
        navigate(path);
    }

    return (
        <div>
            {user !== {} ?(
                <div>
                    <div className={"Username"}>
                        <h2 className={"name"}>{ "Name: " + user.firstName + " " + user.lastName}</h2>
                        <hr/>
                    </div>
                    <div className={"content"}>
                        <h3 className={"username"}>{"Username: " + user.username}</h3>
                        <p>{"PhoneNumber: " + user.phoneNumber}</p>
                        <p>{"Email: " + user.email}</p>
                        <p>{"Position: " + user.position}</p>
                    </div>
                    <button className={"updateUser"} onClick={updateUser}> update </button>
                </div>
            ):(
                <h2>{error}</h2>
            )}

            {products !== [] ?(
                <table id={"Users"}>
                    <tr>
                        <th>title</th>
                        <th>subtitle</th>
                        <th>series</th>
                        <th>price</th>
                        <th className={"buttonRight"}> View Details</th>
                    </tr>

                    {products.map((product) => (
                        <tr>
                            <td>{product.title}</td>
                            <td>{product.subTitle}</td>
                            <td>{product.series}</td>
                            <td>{product.price}</td>
                            <td><Link to={"/MyProduct/" + product.id} >Details</Link></td>
                        </tr>
                    ))}
                </table>
            ) :(
                <h2>{error}</h2>
            )}
        </div>
    );
}
export default MyAccount;