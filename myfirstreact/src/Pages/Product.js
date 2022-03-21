import React from "react";
import SearchForProducts from "./ProductPage/SearchForProducts";
import MostPopularProducts from "./ProductPage/MostPopulerProducts";
import "./Css/Product.css";

function Products() {
        return (
            <div className="container">
                <SearchForProducts/>
                <MostPopularProducts/>
            </div>
        );

}

export default Products;