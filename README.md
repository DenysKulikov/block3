Test case: Check for the brand name  in all product cards after searching:
1) Open website:https://eldorado.ua/uk/
	Expected result: the site has opened, search input is present, search placeholder has the correct placeholder, named: "Пошук товарів"
2) Type brand name  in the search bar 
Expected result: the word was written successfully;
3) Press enter button 
Expected result: wait for the Title Element to appear on the search page, product page was loaded and every product contains brand name in the title text.

Test case: Check Ideas for gifts: all products correspond to the specified price range :
1) Open website:https://eldorado.ua/uk/
	Expected result: the site has opened, search input is present, search placeholder has the correct placeholder, named: "Пошук товарів"
2) Click the button: "Ідеї для подарунків".
		Expected result:
2) Click the button: "Згенерувати".
		Expected result: All products correspond to the specified price range	

Test case: Check if the Total Amount on the Cart page matches the sum of the prices of the selected products:
1) Open website:https://eldorado.ua/uk/
	Expected result: the site has opened, search input is present, search placeholder has the correct placeholder, named: "Пошук товарів"
2) Type 'iPhone' in the search bar 
Expected result: the word was written successfully;
3) Press enter button 
		 Expected result: the word iPhone appears in the page title.
4) Click on the add to cart button on the first product.
Expected result: Wait for the pop-up window to appear, check if there is a button to go to the cart on the pop-up window.
5) Click on the button  “Перейти до кошику” on the popup window, in order to go to the cart page.
	Expected result: Cart page opens, check that the product price( on the search page) matches product amount on the cart page. The full name of the added product matches the name of the product card on the search page

Test case: Check if the game is present on the games page:
1) Open website:https://eldorado.ua/uk/
	Expected result:  the site has opened, search placeholder has the correct placeholder, named: "Пошук товарів", the button: Ігрова зона is present.
2) Click on the button: Ігрова зона
Expected result: game zone page has opened, wait until the Title Element appears on the page;
3) Click on the “Ігриі”  button:
 Expected result: the page with the games has appeared, wait until the title bar appears on the page.  Check if the game appears in any product card title on the games page.

Test case: Check if the item in the cart is being deleted correctly:
1) Open website:https://eldorado.ua/uk/
	Expected result: the site has opened, search input is present, search placeholder has the correct placeholder, named: "Пошук товарів"
2) Click on the add to cart button on the first product 
Expected result: the word was written successfully, ;
3)  Click on the button “Перейти до кошику” on the popup window, in order to go to the cart page
Expected result: 
4) Click on the button with a trash can to remove the selected product from the cart
	Expected result: You've been thrown to the home page, there is no product quantity icon above the cart icon
