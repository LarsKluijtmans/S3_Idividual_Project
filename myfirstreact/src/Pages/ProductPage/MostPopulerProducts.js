import React, {useState} from "react";
import axios from "axios";

function MostPopularProducts() {

    const [MostPopularArray, updateMostPopularArray] = useState([]);
    const [error, setError] = useState('');

    const MostPopular = () => {
        axios.get('http://localhost:8080/products')
            .then(res => {
                updateMostPopularArray(res.data);
            })
            .catch(err => {
                setError(err.message);
            });
    }
    if(MostPopularArray != null) {
        return (
            <div className="container">

                <h1> Most popular </h1>
                <button type="submit" onClick={MostPopular}>start search</button>

                {MostPopularArray.map(product => (
                    <div  className="card">
                        <h1>{product.name1}</h1>
                        <h3>{product.name2}</h3>
                        <p>{product.genre}</p>
                        <p>{product.description} </p>
                    </div>)
                )}
            </div>
        );
    }
    else if(error != null){
        return (
            <div>
                <h3> Most popular </h3>
                <button type="submit" onClick={MostPopular}>start search</button>

                <h3> Error: {error}</h3>
            </div>
        );
    }
    else {
        return (
            <div>
                <h3> Most popular </h3>
                <button type="submit" onClick={MostPopular}>start search</button>

                <h1>Something went wrong!!!</h1>
            </div>
        );
    }
}

export default MostPopularProducts;