import React, {useEffect, useState} from 'react';
import Stomp from 'stompjs';
import axios from "axios";
import ReactDOM from "react-dom";
import {useNavigate, useParams} from "react-router-dom";

import SockJS from 'sockjs-client';
const ENDPOINT = "http://localhost:8080/ws";

const AddProduct= () =>{

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

    // google api requests
    function SaveImage(e) {
        let file = e.target.files[0] //the file
        let reader = new FileReader() //this for convert to Base64
        reader.readAsDataURL(e.target.files[0]) //start conversion...
        reader.onload = function (e) { //.. once finished..
            let rawLog = reader.result.split(',')[1]; //extract only the file data part

            setLoadingImage("loading...");

            let dataSend = {
                dataReq: {data: rawLog, name: file.name, type: file.type},
                fname: "uploadFilesToGoogleDrive"
            }; //preapre info to send to API
            fetch('https://script.google.com/macros/s/AKfycby3Ey1lmmyX9CAsRlanTAU4FveEyfKqnjrYQPTaaBHUEN6Z3OrF/exec', //your AppsScript URL
                {method: "POST", body: JSON.stringify(dataSend)}) //send to Api
                .then(res => res.json()).then((a) => {
                AddImage(a.url);
                setLoadingImage("");
            }).catch((e) => {
                setLoadingImage("Something went wrong please try again later.\n" + e);
            })
        }
    }

    //Image related stuff
    function AddImage(url) {
        let includeID = url.replace("file/d/", "uc?export=view?&id=");
        let ChangeView = includeID.replace("/view?usp=drivesdk", "");

        if (images === null) {
            setImages(ChangeView);
        } else if (images.length < 10) {
            images.push(ChangeView);
            setImages(images);
        }
        ReactDOM.render( <LoadImages/>, document.getElementById('images'));
        setImages(images);
    }
    const RemoveImage = (e) => {
        for (let i = 0; i < images.length; i++) {
            if (images[i] === e.target.value) {
                images.splice(i, 1);
                ReactDOM.render( <LoadImages/>, document.getElementById('images'));
                return;
            }
        }
    }
    const [loadingImage, setLoadingImage] = useState("");

    //Update
    let token = localStorage.getItem("token");
    const config = {
        headers: { Authorization: `Bearer ${token}` }
    };
    const addProduct = () => {
        let regex1 = /^.{1,50}$/;
        let regex2 = /^.{1,5000}$/;

        if(regex1.test(title) === false) {
            alert("title has to be between 1 and 50");
            return;
        }
        if(regex1.test(subTitle) === false) {
            alert("subTitle has to be between 1 and 50");
            return;
        }
        if(regex1.test(series) === false) {
            alert("series has to be between 1 and 50");
            return;
        }
        if(regex2.test(description) === false) {
            alert("description has to be between 1 and 5000");
            return;
        }
        if(year <= 1900 || year >= 2023) {
            alert("year has to be between 1900 and 2023");
            return;
        }
        if(price <= 0.50) {
            alert("price has to be More then 0.50");
            return;
        }
        if(genreId <= 0) {
            alert("Please select a genre");
            return;
        }
        if(regex1.test(productType) === false) {
            alert("Please select a productType");
            return;
        }
        if(images == null || images.length == 0) {
            alert("Please add at least 1 image.");
            return;;
        }

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
            stompClient.send("/app/NewApp", {}, JSON.stringify({"title":title, "subTitle": subTitle, "price": price, "condition": condition}));
            let path = "/MyProduct/" + res.data.productId;
            navigate(path);
        })
        .catch(err => {
        });
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


    //Display images
    function LoadImages() {
        return (
            <div id="images">
                {images.map(image => (
                    <div className={"inlineBlock"} >
                        <img className={"list_image"} src={image}  alt={"Failed to save image"}/>
                        <button className={"deleteButton"} type="button" value={image} onClick={RemoveImage} > delete </button>
                    </div>
                ))}
            </div>
        );
    }

    return (
        <div className="select-Options">
            <div>
                <div>
                    <div className="select">
                        <h2>ProductType:</h2>
                        <select id='ProductType' className="input" onInput={changeProductType}>
                            <option value="GAME">GAME</option>
                            <option value="GAMES">GAMES</option>
                        </select>
                    </div>

                    <div className="select">
                        <h2>Genre:</h2>
                        <select id='Genre' className="input" onInput={changeGenre}>
                            {allGenre.map(genre => (
                                <option value={genre.genre}>{genre.genre}</option>
                            ))}
                        </select>
                    </div>

                    <div className="select">
                        <h2>Condition:</h2>
                        <select id='Condition' className="input" onInput={changeCondition}>
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
                <input className="input" type="text" value={title} placeholder="Title" onChange={changeTitle}/>
                <h2>Sub Title:</h2>
                <input className="input" type="text" value={subTitle} placeholder="SubTitle" onChange={changeSubTitle}/>
                <h2>Series:</h2>
                <input className="input" type="text" value={series} placeholder="Series" onChange={changeSeries}/>

                <div>
                    <div className={"inlineBlock"}>
                        <h2>Year:</h2>
                        <input className="inputNumber" type="number" step="1" value={year} placeholder="Year" onChange={changeYear}/>
                    </div>
                    <div className="inlineBlock">
                        <h2>Price:</h2>
                        <input className="inputNumber" type="number" step="0.01" value={price} placeholder="Price" onChange={changePrice}/>
                    </div>
                </div>
                <h2>Description:</h2>
                <textarea className="description" value={description} placeholder="description" onChange={changeDescription}/>

                <label htmlFor="fileInput" className="addImage">Upload image</label>
                <input id='fileInput' type="file" accept="image/jpeg, image/png" onChange={(e) => SaveImage(e)}/>
                <p className="loading-image">{loadingImage}</p>
                <br/>
                <LoadImages/>

            </div>

            <div>
                <button className="add_button" onClick={addProduct}>add</button>
            </div>
        </div>
    );
}
export default AddProduct;