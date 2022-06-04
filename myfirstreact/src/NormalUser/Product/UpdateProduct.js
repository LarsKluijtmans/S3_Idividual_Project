import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from "react-router-dom";
import axios from "axios";
import "./updateProductCSS.css"

const UpdateProduct= () =>{

    //deleting and adding images/ tests before sending to api

    //Initialize
    useEffect(() => {
        GetProductByID();
        getGenres();
    },[]);

    const {productId} = useParams();
    let navigate = useNavigate();

    const GetProductByID =async () => {
        let genre = "";

        await axios.get(`http://localhost:8080/products/` + productId)
            .then(res => {
                setTitle(res.data.title)
                setSubTitle(res.data.subTitle)
                setSeries(res.data.series)
                setYear(res.data.year)
                setPrice(res.data.price)
                setCondition(res.data.condition)
                setDescription(res.data.description)
                setOldGenre(res.data.genre)
                setProductType(res.data.productType)
                setImages(res.data.images)

                genre = res.data.genre;

                setOriginalProductType(res.data.productType)
                setOriginalCondition(res.data.condition)
                setOriginalOldGenre(res.data.genre)
            })
            .catch(err => {
            });

       await axios.get(`http://localhost:8080/genre/` + genre)
            .then(res => {
                console.log(genre)
                setGenreId(res.data.id);
            })
            .catch(err => {
            });
    }
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
    const updateProduct =  () => {
      /*  let regex1 = /^([a-zA-Z0-9_-]){1,50}$/;
        let regexSeries = /^([a-zA-Z0-9_-]){1,30}$/;

        if(regex1.test(title) === false) {
            alert("Title has to be between 1 and 50");
            return;
        } if(regex1.test(subTitle) === false) {
            alert("SubTitle has to be between 1 and 50");
            return;
        } if(regexSeries.test(series) === false) {
            alert("Series has to be between 1 and 30 chars");
            return;
        } if(description === "") {
            alert("description can not be empty");
            return;
        }*/

        const data = {
            "productId": productId,
            "title": title,
            "subTitle": subTitle,
            "series": series,
            "year": year,
            "price": price,
            "condition": condition,
            "description": description,
            "genreId": genreId,
            "productType": productType,
            "images": images
        }

        axios.put(`http://localhost:8080/products`, data, config)
            .then(res => {
                let path = "/MyProduct/" + productId;
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

    const [OriginalProductType, setOriginalProductType] = useState("");
    const [OriginalCondition, setOriginalCondition] = useState("");
    const [OriginalGenre, setOriginalOldGenre] = useState("");

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
                            <option value={OriginalProductType}>{"Original: " + OriginalProductType}</option>
                            <option value="GAME">GAME</option>
                            <option value="GAMES">GAMES</option>
                        </select>
                    </div>

                    <div className={"select"}>
                        <h2>Genre:</h2>
                        <select  className={"input"} name="genre" id="genreID" onInput={changeGenre}>
                            <option value={OriginalGenre}>{"Original: " + OriginalGenre}</option>
                            {allGenre.map(genre => (
                                <option value={genre.genre}>{genre.genre}</option>
                            ))}
                        </select>
                    </div>

                    <div className={"select"}>
                        <h2>Condition:</h2>
                        <select className={"input"}  name="condition" id="condition" onInput={changeCondition}>
                            <option value={OriginalCondition}>{"Original: " + OriginalCondition}</option>

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
                <button className="cart-btn" onClick={updateProduct}>update</button>
            </div>
        </div>
    );
}
export default UpdateProduct;