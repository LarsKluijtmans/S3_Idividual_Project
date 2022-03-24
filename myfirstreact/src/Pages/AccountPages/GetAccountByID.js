import React, {useState} from "react";
import axios from "axios";


function GetAccountById(){

    const [ID, setID] = useState('');

    const handleIDChange = (e) => {
        setID(e.target.value);
    };

    return (
        <div>
            <label><b>User ID</b></label>
            <input type="text" placeholder="Enter ID" name={"ID"} value={ID} onChange={handleIDChange} required/>
            <GetUser UserID={ID} />
        </div>
    );
}


function GetUser({UserID}) {
    const [result, setResult] = useState('');
    const [error, setError] = useState('');

    const getData = () => {

        setResult(null);
        setError(null);

        axios.get('http://localhost:8080/accounts/' + UserID)
            .then(res => {
                setResult(res.data);
            })
            .catch(err => {
                setError(err.message);
            });
    };

    if (result != null) {
    return (
        <div>
            <button type="submit" onClick={getData}>Find account</button>
            <table>
                <tr>
                    <td>{result.firstname}</td>
                    <td>{result.lastname} </td>
                    <td>{result.email}</td>
                    <td>{result.phoneNumber}</td>
                </tr>
            </table>
        </div>
    );
}
    else if (error != null) {
        return (
            <div>
                <button type="submit" onClick={getData}>Find account</button>
                <h3> Error: {error} </h3>
            </div>
        );
    }
    else {
        return (
            <div>
                <button type="submit" onClick={getData}>Find account</button>
                <h3> Something went wrong please try again later. </h3>
            </div>
        );
    }
}
export default GetAccountById;
