import React, {useEffect, useState} from "react";
import axios from "axios";

function MostPopularProducts() {

    const [productsArray, setProductsArray] = useState([]);

    useEffect(() => {
        GetMostPopulerProducts();
        console.log("loaded");
    },[]);

    const GetMostPopulerProducts =() => {
        axios.get(`http://localhost:8080/products`)
            .then(res => {
                setProductsArray(res.data);
            })
            .catch(err => {
                console.log(err.message);
            });
    }

    return (
        <div className="container">

            <h1> Most popular </h1>

            <div className={"wrapper"}>

                {productsArray.map(product => (
                    <div className="box">
                        <section className="product">
                            <div className="product__info">
                                <div className="title">
                                    <h1>{product.name1}</h1>
                                    <span>{product.name2}</span>
                                </div>
                                <div className="price">
                                    <span>{product.price} </span> $
                                </div>
                                <div className="description">
                                    <h3>Description</h3>
                                    <h4>{product.description}</h4>
                                </div>
                                <button className="buy--btn">Details</button>
                            </div>
                        </section>
                    </div>)
                )}
            </div>
        </div>
    );
}

export default MostPopularProducts;