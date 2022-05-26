import React, {useState, useEffect} from 'react';
import axios from "axios";

import "./UsersTable.css"

const AllUsers= () =>{

    useEffect(() => {
        GetAllUsers();
    },[]);

    const [users, setUsers] = useState([]);
    const [error, serError] = useState([]);

    let token = localStorage.getItem("token");

    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };

    const GetAllUsers =() => {
        axios.get(`http://localhost:8080/users`,
            config
        ).then(res => {
                setUsers(res.data);
        }).catch(err => {
            serError(err.message);
        });
    }
    const deleteUser =(e)=>  {
        axios.delete(`http://localhost:8080/users/` + e.target.value,
            config
        ).then(res => {

        }).catch(err => {
            serError(err.message);
        });
        GetAllUsers();
    }

    return (
        <div>
            {users !== [] ?(
                <>
                    <table id={"Users"}>
                       <tr>
                           <th>position</th>
                           <th>username</th>
                           <th>Name</th>
                           <th>phoneNumber</th>
                           <th>email</th>
                       </tr>

                    {users.map((user) => (
                        <tr>
                                <td>{user.position}</td>
                                <td>{user.username}</td>
                                <td>{user.firstName + " "+ user.lastName}</td>
                                <td>{user.phoneNumber}</td>
                                <td>{user.email}</td>
                                <td><button className={"deleteButton"} value={user.username} onClick={deleteUser}>Delete</button></td>
                        </tr>
                    ))}

                    </table>
                </>

            ) :(
                <>
                    <h2>{error}</h2>
                </>
            )}
        </div>
    );
}
export default AllUsers;