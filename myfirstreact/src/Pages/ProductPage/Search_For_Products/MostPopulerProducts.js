import React, {useEffect, useState} from "react";
import axios from "axios";
import DisplayProducts from "../DisplayProducts"

function MostPopularProducts() {

    useEffect(() => {
        GetMostPopularProducts();
    },[]);

    const [productsArray, setProductsArray] = useState([]);

    const GetMostPopularProducts =() => {
        axios.get(`http://localhost:8080/products`)
            .then(res => {
                setProductsArray(res.data);
            })
            .catch(err => {
                console.log(err.message);
            });
    }

    return (
        <div>
            <h1> Most popular </h1>
            <br/>
           <DisplayProducts productsArray={productsArray} />
        </div>
    );
}

export default MostPopularProducts;