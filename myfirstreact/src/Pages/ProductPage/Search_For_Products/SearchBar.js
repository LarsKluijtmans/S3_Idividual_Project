import React, {useState} from "react";
import SearchResult from "./SearchResult";

function SearchBar() {

    const [SearchString, setSearchString] = useState("");

    const handleChangeSearch = (e) => {
        setSearchString(e.target.value);
    };

    return (
        <div>
            <div className="searchbar">
                <div className="search-container">
                    <input type="text" placeholder="Search.." name="search" value={SearchString} onChange={handleChangeSearch}/>
                </div>
            </div>
            <SearchResult SearchString={SearchString}/>
        </div>
    );
}

export default SearchBar;