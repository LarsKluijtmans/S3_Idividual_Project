import React, {useEffect, useState} from "react";
import "./ProdutDetails.css";
import {useParams} from "react-router-dom";
import axios from "axios";

function View_Product_Details() {

    const [product, setProduct] = useState({});
    const [error, setError] = useState("");
    const [productImages, setProductImages] = useState([]);
    const [mainImage, setmainImage] = useState("");

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
                setProductImages(res.data.images);
                setmainImage(res.data.images[0]);
            })
            .catch(err => {
                setError(err.message);
            });
    }

    const setMainImage = (url) => {
      setmainImage(url);
    }

   if(product != null) {
       return (
           <div className="ProductContainer">

               <div className="left-column">
                   <h2>Image of product</h2>

                   <img className={"mainImage"} src={mainImage} height={"500"} alt={"picture"}/>
                   <div className="list_Of_Sub_Images">
                       {productImages.map(ProductImage => (
                           <img className={"subImage"} src={ProductImage} height={"500"} alt={"picture"} onClick={() => setMainImage(ProductImage)}/>
                       ))}
                   </div>
               </div>

               <div className="right-column">

                   <div className="product-description">
                       <span>{product.serie}</span>
                       <h1>{product.title}</h1>
                       <h2>{product.sub_title}</h2>
                       <h3>Condition: {product.condition_}</h3>
                       <p>{product.description}</p>
                       <span>year:{product.year} /</span>
                       <span>genre: {product.genre} /</span>
                       <span>type: {product.product_type}</span>
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