USE northwind;

-- 1:
-- products

-- 2:
SELECT ProductID, ProductName, UnitPrice FROM products;

-- 3:
SELECT ProductID, ProductName, UnitPrice FROM products ORDER BY UnitPrice ASC;

-- 4:
SELECT * FROM products WHERE UnitPrice <= 7.5;

-- 5:
SELECT * FROM products WHERE UnitsInStock >= 100 ORDER BY UnitPrice DESC;

-- 6:
SELECT * FROM products WHERE UnitsInStock >= 100 ORDER BY UnitPrice DESC, ProductName;

-- 7:
SELECT * FROM products WHERE UnitsInStock = 0 AND UnitsOnOrder <> 0 ORDER BY ProductName;

-- 8:
-- categories

-- 9:
SELECT * FROM categories;
SELECT CategoryID FROM categories WHERE CategoryName = "Seafood";

-- 10:
SELECT * FROM products WHERE CategoryID = (SELECT CategoryID FROM categories WHERE CategoryName = "Seafood" LIMIT 1);

-- 11:
SELECT FirstName, LastName FROM employees;

-- 12:
SELECT * FROM employees WHERE Title LIKE "%Manager%";

-- 13:
SELECT DISTINCT Title FROM employees;

-- 14:
SELECT * FROM employees WHERE Salary >= 2000 AND Salary <= 2500;

-- 15:
SELECT * FROM suppliers;

-- 16:
SELECT * FROM products WHERE SupplierID = (SELECT SupplierID FROM suppliers WHERE CompanyName = "Tokyo Traders" LIMIT 1);