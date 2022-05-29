import React, {useState, useEffect} from 'react';
import axios from "axios";

import "./UserDetailsCss.css"

import {Link, useNavigate, useParams} from "react-router-dom";

const UserDetails= () =>{

    useEffect(() => {
        getUser();
    },[]);

    const {Username} = useParams();

    let navigate = useNavigate();

    const [products, setProducts] = useState([]);
    const [user, setUser] = useState({});
    const [error, serError] = useState({});

    let token = localStorage.getItem("token");

    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };
    const getUser =() => {
        axios.get(`http://localhost:8080/users/admin/`+ Username, config
        ).then(res => {
            setUser(res.data);
        }).catch(err => {
            serError(err.message);
        });
        getUsersProducts();
    }
    const getUsersProducts =() => {
        axios.get(`http://localhost:8080/products/admin/` + Username, config
        ).then(res => {
            setProducts(res.data);
        }).catch(err => {
            serError(err.message);
        });
    }
    const deleteUser =() => {
        axios.delete(`http://localhost:8080/users/`+ Username , config
        ).then(res => {
        }).catch(err => {
            serError(err.message);
        });
        let path = `/users` ;
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
                    <button className={"deleteUser"} onClick={deleteUser}> delete user </button>
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
                            <td><Link to={"/products/" + product.id} >Details</Link></td>
                        </tr>
                    ))}
                </table>
            ) :(
                <h2>{error}</h2>
            )}
        </div>
    );
}
export default UserDetails;