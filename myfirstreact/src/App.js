import React, {useState} from 'react';
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";


import Navbar  from "./Navbar/Navbar";
import Homepage from "./Pages/HomePage"
import Products from "./Pages/Product"
import Account from "./Pages/Account"
import Login from "./Pages/Login"

function App() {

    return (
        <div>
            <Router>
                <Navbar/>
                <Switch>
                    <Route path='/' exact component={Homepage}/>
                    <Route path='/products' exact component={Products}/>
                    <Route path='/account' exact component={Account}/>
                    <Route path='/login' exact component={Login}/>
                </Switch>
            </Router>
        </div>
      );
}

export default App;