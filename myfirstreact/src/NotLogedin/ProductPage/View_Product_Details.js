import React, {useEffect, useState} from "react";
import "./ProdutDetails.css";
import {useNavigate, useParams} from "react-router-dom";
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
    let navigate = useNavigate();

    const GetProductByID =()=> {
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

    let token = localStorage.getItem("token");
    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };
    const buyProduct =()=> {
        if(token === null){
            alert("Please login before trying to buy a product");
        }

        axios.put(`http://localhost:8080/products/buy/` + productId,null,config)
            .then(res => {
                let path = "/products";
                navigate(path);
            })
            .catch(err => {
               alert(err.message);
            });
    }

    const setMainImage =(url)=> {
      setmainImage(url);
    }

   if(product != null) {
       return (
           <div className="ProductContainer">

               <div className="left-column">
                   <img className={"mainImage"} src={mainImage} height={"500"} alt={"picture"}/>
                   <div className="list_Of_Sub_Images">
                       {productImages != null &&
                           productImages.map(ProductImage => (
                           <img className={"subImage"} src={ProductImage} height={"500"} alt={"picture"} onClick={() => setMainImage(ProductImage)}/>
                       ))}
                   </div>
               </div>

               <div className="right-column">

                   <div className="product-description">
                       <h1>{product.title}</h1>
                       <h2>{product.subTitle}</h2>
                       <span>{product.series}</span>
                       <h3>Condition: {product.condition}</h3>
                       <p>{product.description}</p>
                       <span>year:{product.year} /</span>
                       <span>genre: {product.genre} /</span>
                       <span>type: {product.productType}</span>
                   </div>

                   <div className="product-price">
                       <span>{product.price}</span>
                       <button className="cart-btn" onClick={buyProduct}>Buy</button>
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