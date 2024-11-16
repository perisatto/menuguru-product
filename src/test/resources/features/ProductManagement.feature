Feature: Product Management

	Scenario: Register product
		Given product has the following attributes
		 | name    | productType | description                           | price | image                                                                                        |
		 | X-Bacon | LANCHE      | O x-bacon é um sanduíche irresistível | 10.00 | iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk+A8AAQUBAScY42YAAAAASUVORK5CYII= |
		 When register a new product
		 Then the product is successfully registered
		  And should be showed

	Scenario: Retrieve product information		  
		Given the product is already registered with following attributes
		 | name        | productType | description                                | price | image                                                                                        |
		 | Suco de Uva | BEBIDA      | Suco integral de uva sem açucar adicionado | 9.00  | iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk+A8AAQUBAScY42YAAAAASUVORK5CYII= |
		 When ask for product information
		 Then the product information is retrieved
		 
  Scenario: Update product information		 
		Given the product is already registered with following attributes
		 | name        | productType | description                                | price | image                                                                                        |
		 | Suco de Uva | BEBIDA      | Suco integral de uva sem açucar adicionado | 9.00  | iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk+A8AAQUBAScY42YAAAAASUVORK5CYII= |
		 When gives a new price
		 Then updates the product information with new price 