import userEvent from "@testing-library/user-event"
import React from 'react'
import { render, screen } from "@testing-library/react"
import { BrowserRouter as Router } from "react-router-dom"; 

import Login from "../NotLogedin/AccountPages/Login";

//Unit tests
test('Login page, render', () => {
    render( <Router><Login/>,</Router>,)
    screen.debug()
})

test('Login page, button is diabled ', () => {
    render( <Router><Login/>,</Router>,)

    expect(screen.getByRole('button',  {name: /Login/i})).toBeDisabled()
    expect(screen.getByRole('button',  {name: /Sign up/i}))
})

test('Login page, get inputs', () => {
    render( <Router><Login/>,</Router>,)

    expect(screen.getByPlaceholderText(/Username/i))
    expect(screen.getByPlaceholderText(/Password/i))
})

test('Login page, enter vaule in inputs', () => {
    render( <Router><Login/>,</Router>,)

    userEvent.type(screen.getByPlaceholderText(/Username/i), "username")
    expect(screen.getByRole('button',  {name: /Login/i})).toBeEnabled()
})

//Integration test

test('Login page,Login function', () => {

    const login =() =>{
    }

    render( <Router><Login login={login}/>,</Router>,)

    expect(screen.getByRole('button',  {name: /Login/i})).toBeDisabled()

    userEvent.type(screen.getByPlaceholderText(/Username/i), "username")
    userEvent.type(screen.getByPlaceholderText(/Password/i), "password")
    
    expect(screen.getByRole('button',  {name: /Login/i})).toBeEnabled()

    userEvent.click(screen.getByRole('button',  {name: /Login/i}))
})