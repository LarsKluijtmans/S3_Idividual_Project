import { render, screen } from "@testing-library/react"
import React from 'react'
import MostPopularProducts from "../NotLogedin/ProductPage/Search_For_Products/MostPopulerProducts";

//unit tests

test('Most popular products page, render', () => {
    render(<MostPopularProducts/>)
    screen.debug();
})