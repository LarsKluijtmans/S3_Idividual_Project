import React, {useEffect, useState} from "react";


import {useParams} from "react-router-dom";
import axios from "axios";

function View_Product_Details() {

    const [product, setProduct] = useState({});
    const [error, setError] = useState("");

    useEffect(() => {
        GetProductByID();
    },[]);

    const {productId} = useParams();

    const GetProductByID =() => {
        setProduct(null);
        setError(null);
        axios.get(`http://localhost:8080/products/` + productId)
            .then(res => {
                setProduct(res.data);
            })
            .catch(err => {
                setError(err.message);
            });
    }

   if(product != null) {
       return (
           <div>
               <h3>Name: {product.name1}</h3>
               <h4>sub-Name: {product.name2}</h4>
               <p>serie: {product.serie}</p>
               <p>year: {product.year}</p>
               <h2>price: {product.price}</h2>

               <p>condition: {product.condition}</p>
               <p>genre: {product.genre}</p>
           </div>
       );
   }
   else if(error != null) {
       return (
           <div>
               <h1>{error}</h1>
           </div>
       );
   }
   else{
      return(
          <div>
              <h1>Something has gone wrong please try again later.</h1>
          </div>);
   }
}

export default View_Product_Details;