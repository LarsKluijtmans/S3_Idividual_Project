import React from "react";
import {Link} from 'react-router-dom';

function DisplayProducts({productsArray}) {

    return (
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

                            <Link className="ToProductPageButton" to={'/products/'+product.productID}>Details</Link>
                        </div>
                    </section>
                </div>)
            )}
        </div>
    );
}

export default DisplayProducts;
