import { wait, within, row } from "@testing-library/user-event/dist/utils"

describe("AddProduct", () =>{
  it('user adds a product', () =>{
    //go to login page
    cy.visit('localhost:3000/')
    cy.findByRole('link', {name: /login/i}).click()

    //Login
    cy.findByPlaceholderText(/username/i).type('user2')
    cy.findByPlaceholderText(/password/i).type('lars2030')
    cy.findByRole('button', {name: /login/i}).click()

    //go to my products page
    cy.findByRole('link', {name: /my products/i}).click()

    // go to add product page
    cy.findByRole('button', {name: /add \+/i}).click()
 
    //fill in fields
    cy.findByText(/upload image/i).selectFile("C:/Users/lars/Desktop/Screenshot 2022-06-14 164846.png");
    cy.wait(10000)
    cy.findByPlaceholderText('Title').type('Game that is old')
    cy.findByPlaceholderText('SubTitle').type('Old')
    cy.findByPlaceholderText('Series').type('Old game')
    cy.findByPlaceholderText('Year').clear()
    cy.findByPlaceholderText('Year').type("1958")
    cy.findByPlaceholderText('Price').clear()
    cy.findByPlaceholderText('Price').type('13.50')
    cy.findByPlaceholderText('description').type('just a game')


    cy.on('window:alert', (text) => {
      expect(text).to.contains('New Product has been added \nTitle: Game that is old\nSubTitle: Old\nPrice: 13.50\nCondition: EXCELLENT');
    })

    //press add button
    // get send to product details page
    cy.findByRole('button', {name: /add/i}).click()      
  })
})