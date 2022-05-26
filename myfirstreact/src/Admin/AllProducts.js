import React, {useEffect, useState} from 'react';
import axios from "axios";

const AllProducts= () =>{

    useEffect(() => {
        getAllProducts();
    },[]);

    const [products, setProduts] = useState([]);
    const [error, serError] = useState([]);

    localStorage.setItem("token","eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBRE1JTiIsImlhdCI6MTY1MzU4OTIxMCwiZXhwIjoxNjUzNTk2NDEwLCJzdHVkZW50SWQiOjEsInJvbGVzIjpbIkFETUlOIl19.BuYC3IWVazaeOIppsWJz9XqNWJPKciuPW9lBczSqCD4")

    let token = localStorage.getItem("token");

    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };

    const getAllProducts =() => {
        axios.get(`http://localhost:8080/products`, config
        ).then(res => {
            setProduts(res.data);
        }).catch(err => {
            serError(err.message);
        });
    }
    const deleteProduct =(e)=>  {
        axios.delete(`http://localhost:8080/products/` + e.target.value,
            config
        ).then(res => {

        }).catch(err => {
            serError(err.message);
        });
        getAllProducts();
    }

    return (
        <div>
            {products !== [] ?(
                <>
                    <table id={"Users"}>
                        <tr>
                            <th>title</th>
                            <th>subtitle</th>
                            <th>series</th>
                            <th>year</th>
                            <th>price</th>
                            <th>condition</th>
                            <th>description</th>
                            <th>productType</th>
                        </tr>

                        {products.map((product) => (
                            <tr>
                                <td>{product.productInfo.title}</td>
                                <td>{product.productInfo.subTitle}</td>
                                <td>{product.productInfo.series}</td>
                                <td>{product.productInfo.year}</td>
                                <td>{product.productInfo.price}</td>
                                <td>{product.productInfo.condition}</td>
                                <td>{product.productInfo.description}</td>
                                <td>{product.productInfo.productType}</td>
                                <td><button className={"deleteButton"} value={product.id} onClick={deleteProduct}>Delete</button></td>
                            </tr>
                        ))}

                    </table>
                </>

            ) :(
                <>
                    <h2>{error}</h2>
                </>
            )}
        </div>
    );
}
export default AllProducts;