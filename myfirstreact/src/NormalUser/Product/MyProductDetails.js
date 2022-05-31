import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from "react-router-dom";
import axios from "axios";

const MyProductDetails= () =>{

    useEffect(() => {
        GetProductByID();
    },[]);

    const {productId} = useParams();

    let navigate = useNavigate();

    const [product, setProduct] = useState({});
    const [productImages, setProductImages] = useState([]);
    const [mainImage, setmainImage] = useState("");


    const GetProductByID =() => {
        axios.get(`http://localhost:8080/products/` + productId)
            .then(res => {
                console.log(res.data)
                setProduct(res.data);
                setProductImages(res.data.images);
                setmainImage(res.data.images[0]);
            })
            .catch(err => {
            });
    }

    const setMainImage = (url) => {
        setmainImage(url);
    }

    const updateProduct = () => {
        let path = "/MyProduct/update/"+ productId;
        navigate(path);
    }

    let token = localStorage.getItem("token");
    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };
    const deleteProduct = () => {
        axios.delete(`http://localhost:8080/products/normal/` + productId, config)
            .then(res => {
                let path = "/MyProduct/" + localStorage.getItem("username");
                navigate(path);
            })
            .catch(err => {
            });
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
                    <button className="cart-btn" onClick={updateProduct}>update</button>
                    <button className="cart-btn" onClick={deleteProduct}>delete</button>
                </div>
            </div>
        </div>
    );
}
export default MyProductDetails;