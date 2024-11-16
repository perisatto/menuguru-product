Feature: Product Management

	Scenario: Register product
		Given product has the following attributes
		 | name    | description                           | type		| price | image                                                                                        |
		 | X-Bacon | O x-bacon é um sanduíche irresistível | LANCHE | 10.00 | iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk+A8AAQUBAScY42YAAAAASUVORK5CYII= |
		 When register a new product
		 Then the product is successfully registered
		  And should be showed