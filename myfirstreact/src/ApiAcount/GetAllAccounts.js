import React from 'react';
import axios from 'axios';

function GetAllAccounts() {
    axios.get('http://localhost:8080/accounts/')
        .then(res=> {
            console.log(res.data)
            return(
                <h2>{res.data}</h2>
            )
        }).catch(err => {
            console.log(err)
    })
}

export default GetAllAccounts;