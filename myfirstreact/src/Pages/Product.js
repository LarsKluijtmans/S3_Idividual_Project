import React from "react";
import MostPopularProducts from "./ProductPage/MostPopulerProducts";
import SideBar_ProductSearch from "./ProductPage/SearchBar/SideBar_ProductSearch";
import "./Css/Product.css";

function Products() {
        return (
            <div className={"main"}>
                <SideBar_ProductSearch/>
            </div>
        );

}

export default Products;