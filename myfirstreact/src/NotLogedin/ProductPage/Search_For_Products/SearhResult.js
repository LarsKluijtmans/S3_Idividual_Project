import React, {useEffect, useState} from "react";
import axios from "axios";
import DisplayProducts from "../DisplayProducts";

function SearchResult({SearchString = ""}) {

    useEffect(() => {
        GetSearchResult();
    },[SearchString]);

    const [productsArray, setProductsArray] = useState([]);

    const GetSearchResult =() => {
        if(SearchString !== "") {
            axios.get(`http://localhost:8080/products/search/` + SearchString)
                .then(res => {
                    setProductsArray(res.data);
                })
                .catch(err => {});
        }
        else {
            axios.get(`http://localhost:8080/products`)
                .then(res => {
                    setProductsArray(res.data);
                })
                .catch(err => {});
        }
    }

    return (
            <DisplayProducts productsArray={productsArray} />
    );
}

export default SearchResult;