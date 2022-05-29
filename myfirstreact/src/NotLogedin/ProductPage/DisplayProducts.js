import React from "react";
import {Link} from 'react-router-dom';

function DisplayProducts({productsArray}) {

    return (
        <div className={"wrapper"}>
            {productsArray.map(product => (
                <div className="box">
                    <section className="product">
                        {product.images != null &&
                            <img className="product_image" src={product.images[0]} alt={"Product main Image"}/>
                        }
                        <div>
                            <div className="title">
                                <h1>{product.title}</h1>
                                <span>{product.sub_title}</span>
                            </div>
                            <div className="price">
                                <span>{product.price} </span> $
                            </div>
                            <div className="description">
                                <h4>{product.description}</h4>
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
