import React from "react";
import MostPopularProducts from "./ProductPage/Search_For_Products/MostPopulerProducts";
import GetAccountById from "./AccountPages/GetAccountByID";
import "./Css/HomePage.css";

function HomePage() {

        return (
            <div className="container">
                <MostPopularProducts/>
            </div>
        );

}
export default HomePage;