import React, {useEffect, useState} from 'react';
import axios from "axios";
import {Link, useNavigate, useParams} from "react-router-dom";
import "./MyProductCSS.css"

const MyProducts= () =>{

    useEffect(() => {
        getUsersProducts();
    },[]);

    const {username} = useParams();

    let navigate = useNavigate();

    const [products, setProducts] = useState([]);
    const [error, serError] = useState({});

    let token = localStorage.getItem("token");
    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };
    const getUsersProducts =() => {
        axios.get(`http://localhost:8080/products/normal/` + username, config
        ).then(res => {
            setProducts(res.data);
        }).catch(err => {
            serError(err.message);
        });
    }

    const addProduct = () => {
       let path = "/MyProduct/addProduct/"+ username;
        navigate(path);
    }

    return (
        <div>
             <button className={"AddProduct"} onClick={addProduct}> add + </button>

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
                            <td><Link to={"/MyProduct/" + product.id} >Details</Link></td>
                        </tr>
                    ))}
                </table>
            ) :(
                <h2>{error}</h2>
            )}
        </div>
    );
}
export default MyProducts;