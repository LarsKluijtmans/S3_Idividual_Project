import React from 'react';
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";


import Navbar  from "./Navbar/Navbar";
import Homepage from "./Pages/HomePage"
import Products from "./Pages/Product"
import Login from "./Pages/Login"
import MakeAccount from "./Pages/AccountPages/MakeAccount"
import ForgotPassword from "./Pages/AccountPages/ForgotPassword"

function App() {

    return (
        <div>
            <Router>
                <Navbar/>
                <Switch>
                    <Route path='/' exact component={Homepage}/>
                    <Route path='/products' exact component={Products}/>
                    <Route path='/login' exact component={Login}/>
                    <Route path='/make_account' exact component={MakeAccount}/>
                    <Route path='/forgot_password' exact component={ForgotPassword}/>
                </Switch>
            </Router>
        </div>
      );
}

export default App;