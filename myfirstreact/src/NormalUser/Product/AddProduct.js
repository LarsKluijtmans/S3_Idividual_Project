import React, {useEffect, useState} from 'react';
import Stomp from 'stompjs';
import axios from "axios";

import {useNavigate, useParams} from "react-router-dom";

import SockJS from 'sockjs-client';
const ENDPOINT = "http://localhost:8080/ws";

const AddProduct= () =>{
    //deleting and adding images/ tests before sending to api

    const [stompClient, setStompClient] = useState(null);

    //Initialize
    useEffect(() => {
        const socket = SockJS(ENDPOINT);
        const stompClient = Stomp.over(socket);
        setStompClient(stompClient);

        setYear(2000);
        setPrice(10.00);
        setGenreId(1);
        setProductType("GAME");
        setCondition("EXCELLENT")
        getGenres();
    },[]);

    const {username} = useParams();
    let navigate = useNavigate();

    const getGenres = () => {
        axios.get(`http://localhost:8080/genre`)
            .then(res => {
                setAllGenre(res.data);
            })
            .catch(err => {
            });
    }


    //Update
    let token = localStorage.getItem("token");
    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };
    const addProduct =  () => {
        const data = {
            "title": title,
            "subTitle": subTitle,
            "series": series,
            "year": year,
            "price": price,
            "condition": condition,
            "description": description,
            "genreId": genreId,
            "productType": productType,
            "images": images,
            "seller": username
        }

        axios.post(`http://localhost:8080/products`, data, config)
            .then(res => {
                let path = "/MyProduct/" + res.data.productId;
                navigate(path);
            })
            .catch(err => {
            });
            stompClient.send("/app/NewApp", {}, JSON.stringify({"title":title, "subTitle": subTitle, "price": price, "condition": condition}));
    }

    const [title, setTitle] = useState("");
    const [subTitle, setSubTitle] = useState("");
    const [series, setSeries] = useState("");
    const [year, setYear] = useState("");
    const [price, setPrice] = useState("");
    const [condition, setCondition] = useState("");
    const [description, setDescription] = useState("");
    const [genreId, setGenreId] = useState("");
    const [productType, setProductType] = useState("");
    const [images, setImages] = useState([]);

    const changeTitle = (e) => {
        setTitle(e.target.value);
    }
    const changeSubTitle = (e) => {
        setSubTitle(e.target.value);
    }
    const changeSeries = (e) => {
        setSeries(e.target.value);
    }
    const changeYear = (e) => {
        setYear(e.target.value);
    }
    const changePrice = (e) => {
        setPrice(e.target.value);
    }
    const changeDescription = (e) => {
        setDescription(e.target.value);
    }
    const changeGenre = (e) => {
        setOldGenre(e.target.value);
        axios.get(`http://localhost:8080/genre/` + e.target.value)
            .then(res => {
                setGenreId(res.data.id);
            })
            .catch(err => {
            });
    }
    const changeCondition = (e) => {
        setCondition(e.target.value);
    }
    const changeProductType = (e) => {
        setProductType(e.target.value);
    }



    //genre
    const [oldGenre, setOldGenre] = useState("");
    const [allGenre, setAllGenre] = useState([]);


    return (
        <div className={"select-Options"}>
            <div>
                <div>
                    <div className={"select"}>
                        <h2>ProductType:</h2>
                        <select className={"input"}  name="productType" id="productType" onInput={changeProductType}>
                            <option value="GAME">GAME</option>
                            <option value="GAMES">GAMES</option>
                        </select>
                    </div>

                    <div className={"select"}>
                        <h2>Genre:</h2>
                        <select  className={"input"} name="genre" id="genreID" onInput={changeGenre}>
                            {allGenre.map(genre => (
                                <option value={genre.genre}>{genre.genre}</option>
                            ))}
                        </select>
                    </div>

                    <div className={"select"}>
                        <h2>Condition:</h2>
                        <select className={"input"}  name="condition" id="condition" onInput={changeCondition}>
                            <option value="EXCELLENT">EXCELLENT</option>
                            <option value="GREAT">GREAT</option>
                            <option value="GOOD">GOOD</option>
                            <option value="OK">OK</option>
                            <option value="NOT_BAD">NOT_BAD</option>
                            <option value="BAD">BAD</option>
                            <option value="VERY_BAD">VERY_BAD</option>
                            <option value="TRASH">TRASH</option>
                        </select>
                    </div>
                </div>

                <h2>Title:</h2>
                <input className={"input"} type="text" value={title} placeholder={"Type here..."} onChange={changeTitle}/>
                <h2>Sub Title:</h2>
                <input className={"input"} type="text" value={subTitle} placeholder={"Type here..."} onChange={changeSubTitle}/>
                <h2>Series:</h2>
                <input className={"input"} type="text" value={series} placeholder={"Type here..."} onChange={changeSeries}/>

                <div>
                    <div className={"inlineBlock"}>
                        <h2>Year:</h2>
                        <input className={"inputNumber"} type="number" step="1" value={year} placeholder={"Type here..."} onChange={changeYear}/>
                    </div>
                    <div className={"inlineBlock"}>
                        <h2>Price:</h2>
                        <input className={"inputNumber"} type="number" step="0.01" value={price} placeholder={"Type here..."} onChange={changePrice}/>
                    </div>
                </div>
                <h2>Description:</h2>
                <textarea className={"description"} value={description} placeholder={"Type here..."} onChange={changeDescription}/>

                <div>
                    {images.map(image => (
                        <div className={"inlineBlock"} >
                            <img className={"image"} src={image}  alt={"picture"}/>
                            <button className={"deleteButton"} type="button" value={image} > delete </button>
                        </div>
                    ))}
                </div>
            </div>

            <div>
                <button className="cart-btn" onClick={addProduct}>add</button>
            </div>
        </div>
    );
}
export default AddProduct;