USE northwind;

-- 1:
SELECT ProductID, ProductName, UnitPrice, CategoryName FROM products LEFT JOIN categories ON products.CategoryID = categories.CategoryID ORDER BY CategoryName, ProductName;

-- 2:
SELECT p.ProductID, p.ProductName, p.UnitPrice, s.CompanyName FROM products p LEFT JOIN suppliers s ON p.SupplierID = s.SupplierID WHERE p.UnitPrice > 75 ORDER BY p.ProductName;

-- 3:
SELECT ProductID, ProductName, UnitPrice, CategoryName, CompanyName as SupplierName FROM products LEFT JOIN categories ON products.CategoryID = categories.CategoryID LEFT JOIN suppliers ON products.SupplierID = suppliers.SupplierID ORDER BY ProductName;

-- 4:
SELECT ProductName, CategoryName FROM products p LEFT JOIN categories c ON p.CategoryID = c.CategoryID WHERE p.UnitPrice = (SELECT MAX(p.UnitPrice) FROM products p);

-- 5:
SELECT o.OrderID, o.ShipName, o.ShipAddress, s.CompanyName FROM orders o LEFT JOIN shippers s ON o.ShipVia = s.ShipperID WHERE o.ShipCountry = "Germany";

-- 6:
SELECT o.OrderID, o.OrderDate, o.ShipName, o.ShipAddress FROM orders o LEFT JOIN `order details` d ON o.OrderID = d.OrderID WHERE d.ProductID = (SELECT p.ProductID FROM products p WHERE p.ProductName = "Sasquatch Ale" LIMIT 1);