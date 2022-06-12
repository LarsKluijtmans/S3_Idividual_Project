import React, {useState} from "react";
import SearchResult from "./SearhResult";

function SearchBar() {

    const [SearchString, setSearchString] = useState("");

    const handleChangeSearch = (e) => {
        setSearchString(e.target.value);
    };

    return (
        <div>
            <div className="searchbar">
                <input className={"SearchInput"} type="text" placeholder="Search.." name="search" value={SearchString} onChange={handleChangeSearch}/>
            </div>

            <SearchResult SearchString={SearchString}/>
        </div>
    );
}

export default SearchBar;