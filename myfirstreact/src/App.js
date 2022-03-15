import React from 'react';
import {useState} from 'react';
import './App.css';
import GetAllAccounts from "./ApiAcount/GetAllAccounts";
import GetAccountById from "./ApiAcount/GetAccountById";

function App() {

    const [id, setId] = useState('')

  return (
      <div>
          <input  value={id} onChange={event => setId(event.target.value)} />
          <button onClick={GetAccountById UserID = id}>Get user by ID</button>
          <button onClick={GetAllAccounts}>Get all users</button>
          <p> {id} </p>
          <GetAllAccounts/>
          <GetAccountById userID = {id}/>
      </div>
  );
}

export default App;