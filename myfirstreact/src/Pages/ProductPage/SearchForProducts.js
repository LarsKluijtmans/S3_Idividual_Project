import React, {useState, ReactDOM} from "react";
import axios from "axios";

export default SearchResult;

function SearchResult({search = ""}) {

    const [MostPopularArray, updateMostPopularArray] = useState([]);
    const [error, setError] = useState('');

    const StartSearch = () => {
        updateMostPopularArray(null);
        setError(null);
        axios.get('http://localhost:8080/products/search/' + search)
            .then(res => {
                updateMostPopularArray(res.data);
            })
            .catch(err => {
                setError(err.message);
            });
    };

    if(MostPopularArray != null) {
        return(
            <div className="container">
                <button type="submit" onClick={StartSearch}>start search</button>
                <div className={"wrapper"}>
                    {MostPopularArray.map(product => (
                        <div className="box">
                            <section className="product">
                                <div className="product__info">
                                    <div className="title">
                                        <h1>{product.name1}</h1>
                                        <span>{product.name2}</span>
                                    </div>
                                    <div className="price">
                                        <span>{product.price} </span> $
                                    </div>
                                    <div className="description">
                                        <h3>Description</h3>
                                        <h4>{product.description}</h4>
                                    </div>
                                    <button className="buy--btn">Details</button>
                                </div>
                            </section>
                        </div>)
                    )}
                </div>
            </div>
        );
    }
    else if(error != null){
        return (
            <div className="container">
                <button type="submit" onClick={StartSearch}>start search</button>
                <h3> Error: {error}</h3>
            </div>
        );
    }
    else {
        return (
            <div className="container">
                <button type="submit" onClick={StartSearch}>start search</button>
            </div>
        );
    }
}