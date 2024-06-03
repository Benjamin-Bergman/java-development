USE northwind;

-- 1:
INSERT INTO suppliers (CompanyName, ContactName, ContactTitle, Address, City, Region, PostalCode, Country, Phone, Fax, HomePage) 
	VALUES ("Steel Potatoes", "Raymond Maroun", "Supreme Leader", "123 Main Street", "Pittsburgh", NULL, 15222, "USA", "555-555-5555", NULL, NULL);

-- 2:
INSERT INTO `northwind`.`products`
(`ProductName`,
`SupplierID`,
`CategoryID`,
`QuantityPerUnit`,
`UnitPrice`,
`UnitsInStock`,
`UnitsOnOrder`,
`ReorderLevel`,
`Discontinued`)
VALUES
("Potato Soup",
(SELECT SupplierID FROM suppliers WHERE CompanyName = "Steel Potatoes"),
(SELECT CategoryID FROM categories WHERE CategoryName = "Produce"),
"3 qts",
12.2,
0,
100,
0,
0);

-- 3:
SELECT * FROM products p LEFT JOIN suppliers s ON p.SupplierID = s.SupplierID;

-- 4:
UPDATE products SET UnitPrice = UnitPrice * 1.15 WHERE ProductName = "Potato Soup";

-- 5:
SELECT p.ProductName, p.UnitPrice FROM products p WHERE p.SupplierID = (SELECT s.SupplierID FROM suppliers s WHERE s.CompanyName = "Steel Potatoes");

-- 6:
DELETE FROM products WHERE ProductName = "Potato Soup";

-- 7:
DELETE FROM suppliers WHERE CompanyName = "Steel Potatoes";

-- 8:
SELECT * FROM products;

-- 9:
SELECT * FROM suppliers;