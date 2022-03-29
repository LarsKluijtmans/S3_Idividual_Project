import React, {useEffect, useState} from "react";
import DisplayProducts from "../DisplayProducts";
import axios from "axios";

function SearchResult({SearchString = ""}) {

    useEffect(() => {
        GetSearchResult();
    },[]);

    const [productsArray, setProductsArray] = useState([]);

    const GetSearchResult =() => {
        if(SearchString != "") {
            axios.get(`http://localhost:8080/products/search/` + SearchString)
                .then(res => {
                    setProductsArray(res.data);
                    console.log(res.data);
                })
                .catch(err => {
                        console.log(err.message);
                    }
                );
        }
        else {
            axios.get(`http://localhost:8080/products`)
                .then(res => {
                    setProductsArray(res.data);
                    console.log(res.data);
                })
                .catch(err => {
                        console.log(err.message);
                    }
                );
        }
    }

    return (
        <div className="container">
            <DisplayProducts productsArray={productsArray} />
        </div>
    );
}

export default SearchResult;