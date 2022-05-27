import React, {useState, useEffect} from 'react';
import axios from "axios";

import "./UsersTable.css"
import {Link} from "react-router-dom";

const AllUsers= () =>{

    const [users, setUsers] = useState([]);
    const [error, serError] = useState([]);
    const [searchString, setSearchString] = useState("");

    useEffect(() => {
        GetAllUsers();
    },[searchString]);

    const handleChangeSearch = (e) => {
        setSearchString(e.target.value);
    };

    let token = localStorage.getItem("token");
    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };
    const GetAllUsers =() => {
        if(searchString === "") {
            axios.get(`http://localhost:8080/users`, config
            ).then(res => {
                setUsers(res.data);
            }).catch(err => {
                serError(err.message);
            });
        }
        else{
            axios.get(`http://localhost:8080/users/search/` + searchString, config
            ).then(res => {
                setUsers(res.data);
            }).catch(err => {
                serError(err.message);
            });
        }
    }

    return (
        <div>
            <div className={"searchbar"}>
                <input className={"SearchInput"} type="text" placeholder="Search.." name="search" value={searchString} onChange={handleChangeSearch}/>
            </div>
            {users !== [] ?(
                <table id={"Users"}>
                    <tr>
                        <th>position</th>
                        <th>username</th>
                        <th>Name</th>
                        <th>phoneNumber</th>
                        <th>email</th>
                        <th>View Details</th>
                    </tr>
                    {users.map((user) => (
                        <tr>
                            <td>{user.position}</td>
                            <td>{user.username}</td>
                            <td>{user.firstName + " "+ user.lastName}</td>
                            <td>{user.phoneNumber}</td>
                            <td>{user.email}</td>
                            <td><Link to={user.username}>Details</Link></td>
                        </tr>
                    ))}
                </table>
            ) :(
                <h2>{error}</h2>
            )}
        </div>
    );
}
export default AllUsers;