import React from 'react';
import axios from 'axios';

function GetAccountById({userID}) {

    axios.get( 'http://localhost:8080/accounts/' + {userID})
        .then(res=> {
            console.log(res.data)
            return(
                <h2>{res.data}</h2>
            )
        }).catch(err => {
            console.log(err)
        })
}

export default GetAccountById;