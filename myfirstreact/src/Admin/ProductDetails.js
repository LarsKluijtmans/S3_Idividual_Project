import React, {useState, useEffect} from 'react';
import axios from "axios";

import {Link, useNavigate, useParams} from "react-router-dom";

const ProductDetails= () =>{

    useEffect(() => {
        GetProduct();
    },[]);

    const {ProductId} = useParams();

    let navigate = useNavigate();

    const [product, setProduct] = useState({});
    const [error, setError] = useState("");
    const [productImages, setProductImages] = useState([]);
    const [mainImage, setmainImage] = useState("");
    const [seller, setSeller] = useState([]);

    let token = localStorage.getItem("token");
    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };
    const GetProduct =() => {
        axios.get(`http://localhost:8080/products/`+ ProductId,
            config
        ).then(res => {
            setProduct(res.data);
            setSeller(res.data.seller);
            setProductImages(res.data.images);
            setmainImage(res.data.images[0]);
        }).catch(err => {
            setError(err.message);
        });
    }
    const deleteProduct =()=>{
        axios.delete(`http://localhost:8080/products/admin/`+ ProductId, config
        ).then(res => {
        }).catch(err => {
            setError(err.message);
        });
        let path = `/products` ;
        navigate(path);
    }

    const ToSeller = () => {
        let path = `/users/` + seller.username ;
        navigate(path);
    }

    const setMainImage = (url) => {
        setmainImage(url);
    }

    return (
        <div className="ProductContainer">
            <div className="left-column">
                <h2>Image of product</h2>

                <img className={"mainImage"} src={mainImage} height={"500"} alt={"picture"}/>
                <div className="list_Of_Sub_Images">
                    {productImages != null &&
                        productImages.map(ProductImage => (
                            <img className={"subImage"} src={ProductImage} height={"500"} alt={"picture"} onClick={() => setMainImage(ProductImage)}/>
                        ))
                    }
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
                    <button className="cart-btn" onClick={deleteProduct}>Delete</button>
                </div>
                <div>
                    <button className="cart-btn" onClick={ToSeller}>To seller</button>
                </div>

            </div>
        </div>
    );
}
export default ProductDetails;