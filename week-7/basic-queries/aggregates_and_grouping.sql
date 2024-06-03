USE northwind;

-- 1:
SELECT COUNT(*) FROM suppliers;

-- 2:
SELECT SUM(Salary) FROM employees;

-- 3:
SELECT MIN(UnitPrice) FROM products;

-- 4:
SELECT AVG(UnitPrice) FROM products;

-- 5:
SELECT MAX(UnitPrice) FROM products;

-- 6:
SELECT SupplierID, COUNT(*) AS Count from products GROUP BY SupplierID;

-- 7:
SELECT CategoryID, AVG(UnitPrice) as AveragePrice from products GROUP BY CategoryID;

-- 8:
SELECT SupplierID, COUNT(*) AS Count from products GROUP BY SupplierID HAVING Count >= 5;

-- 9:
SELECT ProductID, ProductName, UnitPrice * UnitsInStock as InventoryValue FROM products ORDER BY InventoryValue DESC, ProductName;