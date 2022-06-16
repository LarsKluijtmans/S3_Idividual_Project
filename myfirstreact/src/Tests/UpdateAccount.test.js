import { render, screen } from "@testing-library/react"
import React from 'react'
import { BrowserRouter as Router } from "react-router-dom";
import UpdateAccount from "../NormalUser/Account/UpdateAccount";

test('Update account page, render', () => {
    render( <Router><UpdateAccount/>,</Router>,);
    screen.debug();
})

test('Update page, get buttons', () => {
    render( <Router><UpdateAccount/>,</Router>,);

    expect(screen.getByRole('button',  {name: /Update account/i}));
})