import React from "react";
import MostPopularProducts from "./MostPopulerProducts";
import SearchResult from "./SearchForProducts";

function Search({Search_Name = ""}) {

   if(Search_Name != "")
   {
       return (
           <SearchResult search={Search_Name}/>
       );
   }
   else
    {

        return (
            <MostPopularProducts/>
        );
    }

}

export default Search;