import React from "react";
import {Link} from 'react-router-dom';

function DisplayProducts({productsArray}) {

    return (
        <div className={"wrapper"}>
            {productsArray.map(product => (
                <div className="box">
                    <section className="product">
                        {product.productInfo.images != null &&
                            <img className="product_image" src={product.productInfo.images[0]} alt={"Product main Image"}/>
                        }
                        <div className="product__info">
                            <div className="title">
                                <h1>{product.productInfo.title}</h1>
                                <span>{product.productInfo.sub_title}</span>
                            </div>
                            <div className="price">
                                <span>{product.productInfo.price} </span> $
                            </div>
                            <div className="description">
                                <h4>{product.productInfo.description}</h4>
                            </div>

                            <Link className="ToProductPageButton" to={'/products/'+ product.id}>Details</Link>
                        </div>
                    </section>
                </div>)
            )}
        </div>
    );
}

export default DisplayProducts;
