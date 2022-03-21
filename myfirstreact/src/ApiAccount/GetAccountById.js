import React, {useState} from 'react';
import axios from 'axios';

function GetAccountById(props) {

    const [data, setdata] = useState({});

        axios.get('http://localhost:8080/accounts/' + props.ID)
            .then(res => {
                setdata(res.data);
            })

        return (
            <div>
                return (props.ID);
            </div>
        );

}

export default GetAccountById;