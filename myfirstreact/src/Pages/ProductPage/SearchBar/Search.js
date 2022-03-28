import React from "react";
import SearchResult from "./SearchForProducts";

function Search({Search_Name = ""}) {

   if(Search_Name != "") {
       return (<SearchResult search={Search_Name}/>);
   }
   else {
       return (<h2>No results found</h2>);
    }

}

export default Search;