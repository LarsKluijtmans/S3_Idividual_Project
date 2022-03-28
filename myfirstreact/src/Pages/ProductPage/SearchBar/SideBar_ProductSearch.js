import React, {useState} from "react";
import SearchResult from "./SearchForProducts";
import MostPopularProducts from "../MostPopulerProducts";
import Search from "./Search";

function SideBar_ProductSearch() {

    /*Search by name*/
    const [search, setSearch] = useState('');
    const handleSearchChange = (e) => {
        setSearch(e.target.value);
    };

    return (
        <div>
            <div className="sidenav">
                <label>Search:</label>
                <input type="text" placeholder="Search" name={"SearchBar"} value={search} onChange={handleSearchChange}/>
                <button>Contact</button>
            </div>
            <Search Search_Name={search}/>
        </div>
    );
}

export default SideBar_ProductSearch;
