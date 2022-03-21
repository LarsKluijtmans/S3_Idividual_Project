import React, {useState} from "react";
import axios from "axios";

function SearchForProducts() {

    const [search, setSearch] = useState('');
    const [MostPopularArray, updateMostPopularArray] = useState([]);
    const [error, setError] = useState('');

    const handleSearchChange = (e) => {
        setSearch(e.target.value);
        updateMostPopularArray(null);
        setError(null);
    };

    const StartSearch = () => {
        console.log(search);
        axios.get('http://localhost:8080/products/search/' + search)
            .then(res => {
                updateMostPopularArray(res.data);
                console.log(res.data);
            })
            .catch(err => {
                setError(err.message);
                console.log(err.message);
            });
    };

    if(MostPopularArray != null) {
        return (
            <div className="container">
                <label>Search:</label>
                <input type="text" placeholder="Search" name={"SearchBar"} value={search} onChange={handleSearchChange} required/>

                <button type="submit" onClick={StartSearch}>start search</button>


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
            <div className="container">
                <label>Search:</label>
                <input type="text" placeholder="Search" name={"SearchBar"} value={search} onChange={handleSearchChange} required/>

                <button type="submit" onClick={StartSearch}>start search</button>

                <h3> Error: {error}</h3>
            </div>
        );
    }
    else{
        return (
            <div className="container">
                <label>Search:</label>
                <input type="text" placeholder="Search" name={"SearchBar"} value={search} onChange={handleSearchChange} required/>

                <button type="submit" onClick={StartSearch}>start search</button>
            </div>
        );
    }
}

export default SearchForProducts;