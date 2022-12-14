import React from "react";
import {Link} from 'react-router-dom';

function DisplayProducts({productsArray}) {

    return (
        <div className={"wrapper"}>
            {productsArray.map(product => (
                <Link className="ToProductPageButton" to={'/products/'+ product.id}>
                    <div className="box">
                        <section>
                            {product.images != null &&
                                <img className="product_image_card" src={product.images[0]} alt={"Product main Image"}/>
                            }
                            <div>
                                <div className="title">
                                    <h1>{product.title}</h1>
                                    <span>{product.sub_title}</span>
                                </div>
                                <div className="price">
                                    <span>{product.price} </span> $
                                </div>
                                <div className="descriptionCard">
                                    <h4>{product.description}</h4>
                                </div>
                            </div>
                        </section>
                    </div>
                </Link>

                )
            )}
        </div>
    );
}

export default DisplayProducts;
