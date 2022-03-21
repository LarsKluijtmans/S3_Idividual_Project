import React from 'react';
import axios from 'axios';
import {useState} from 'react';

function GetAllAccounts() {

    const [myArray, updateMyArray] = useState([]);

    axios.get('http://localhost:8080/accounts/')
           .then(res=> {
                 updateMyArray(res.data);
           })

        return (
            <div>
                <table>
                    <tr>
                        <th>Firstname</th>
                        <th>LastName</th>
                        <th>Email</th>
                        <th>PhoneNumber</th>
                    </tr>
                    {myArray.map(user => (
                        <tr>
                            <td>{user.firstname}</td>
                            <td>{user.lastname} </td>
                            <td>{user.email}</td>
                            <td>{user.phoneNumber}</td>
                        </tr>)
                    )}
                </table>
            </div>
        );
}

export default GetAllAccounts;