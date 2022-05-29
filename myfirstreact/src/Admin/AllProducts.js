import React, {useEffect, useState} from 'react';
import axios from "axios";

import "./UsersTable.css"
import {Link} from "react-router-dom";

const AllProducts= () =>{

    const [products, setProducts] = useState([]);
    const [error, serError] = useState([]);
    const [searchString, setSearchString] = useState("");

    useEffect(() => {
        getAllProducts();
    },[searchString]);

    const handleChangeSearch = (e) => {
        setSearchString(e.target.value);
    };

    let token = localStorage.getItem("token");
    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };
    const getAllProducts =() => {
        if(searchString === "") {
            axios.get(`http://localhost:8080/products`, config
            ).then(res => {
                setProducts(res.data);
            }).catch(err => {
                serError(err.message);
            });
        }
        else{
            axios.get(`http://localhost:8080/products/search/` + searchString, config
            ).then(res => {
                setProducts(res.data);
            }).catch(err => {
                serError(err.message);
            });
        }
    }

    return (
        <div>
            <div className={"searchbar"}>
                <input className={"SearchInput"} type="text" placeholder="Search.." name="search" value={searchString} onChange={handleChangeSearch}/>
            </div>

            {products !== [] ?(
                <table id={"Users"}>
                    <tr>
                        <th>title</th>
                        <th>subtitle</th>
                        <th>series</th>
                        <th>price</th>
                        <th className={"buttonRight"}> View Details</th>
                    </tr>
                    {products.map((product) => (
                        <tr>
                            <td>{product.title}</td>
                            <td>{product.subTitle}</td>
                            <td>{product.series}</td>
                            <td>{product.price}</td>
                            <td><Link to={"" +product.id} >Details</Link></td>
                        </tr>
                    ))}
                </table>
            ) :(
                <h2>{error}</h2>
            )}
        </div>
    );
}
export default AllProducts;