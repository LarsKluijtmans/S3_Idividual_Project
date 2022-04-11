import React, {useEffect, useState} from "react";
import "./ProdutDetails.css";
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
           <div className="ProductContainer">

               <div className="left-column">
                   <h2>Image of product</h2>

                   <img src="./images/download.png" />

               </div>

               <div className="right-column">

                   <div className="product-description">
                       <span>{product.serie}</span>
                       <h1>{product.name1}</h1>
                       <h2>{product.name2}</h2>
                       <h3>Condition: {product.condition}</h3>
                       <p>{product.description}</p>
                       <span>year:{product.year} /</span>
                       <span>genre: {product.genre} /</span>
                       <span>type: {product.productType}</span>
                   </div>

                   <div className="product-price">
                       <span>{product.price}</span>
                       <button className="cart-btn">Buy</button>
                   </div>
               </div>
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