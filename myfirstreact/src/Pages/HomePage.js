import React, {useState} from "react";
import axios from "axios";

function HomePage() {

    const [ID, setID] = useState('');
    const [result, setResult] = useState('');
    const [error, setError] = useState('');

    const handleIDChange = (e) => {
        setID(e.target.value);
        setResult(null);
        setError(null);
    };

    const getData = () => {
        axios.get('http://localhost:8080/accounts/' + ID)
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
                <label><b>User ID</b></label>
                <input type="text" placeholder="Enter ID" name={"ID"} value={ID} onChange={handleIDChange} required/>
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
                <label><b>User ID</b></label>
                <input type="text" placeholder="Enter ID" name={"ID"} value={ID} onChange={handleIDChange} required/>
                <button type="submit" onClick={getData}>Find account</button>
                <h3> Error: {error} </h3>
            </div>
        );
    }
    else {
        return (
            <div>
                <label><b>User ID</b></label>
                <input type="text" placeholder="Enter ID" name={"ID"} value={ID} onChange={handleIDChange} required/>
                <button type="submit" onClick={getData}>Find account</button>
            </div>
        );
    }
}
export default HomePage;