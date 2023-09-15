const express = require("express");
const morgan = require('morgan');
const mysql = require("mysql");
// express app
const app = express();
// body-parser
const bodyParser = require("body-parser");
app.use(bodyParser.json());

// register view engine
// We declare here our view engine of choice is ejs
// it will look at views folder by default
app.set("view engine", "ejs");
// app.set('views', 'myviews');   // setting other folder for views

// create mysql connection
const mysqlConnection = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "12345",
  database: "supplier_tracker",
  multipleStatements: true
});
mysqlConnection.connect((err) => {
  if(!err)
    console.log("DB connection succed.");
  else {
    throw err;
    console.log("DB connection failed \n Error: " + JSON.stringify(err, undefined, 2));
  }
});

// listen for requests
app.listen(3000, () => {console.log("Server running at 3000")});


// middleware
// static middleware comes shipped with express and serves static files(like CSS)
app.use(express.static('public'));
app.use(express.urlencoded({ extended: true }));
// How is formated what we we log to the console
app.use(morgan("dev"));


// Get all suppliers
app.get("/", (req, res) => {  
  mysqlConnection.query(`SELECT supplier_id, company_name, contact_name, email, product_name, price, quantity,
                    (quantity * price) AS cost
                    FROM suppliers INNER JOIN products
                    ON suppliers.product_id = products.product_id
                    ORDER BY suppliers.supplier_id;`, (err, suppliers, fields) => {
    if(!err) {
      console.log(suppliers);
      res.render("index", { title: "Home", suppliers });
      // res.send(suppliers);
    }
    else
      console.log(err);
  })
});

// Get Single Member
app.get("/suppliers/details/:id", (req, res) => {
  mysqlConnection.query(`SELECT supplier_id, company_name, contact_name, email, product_name, price, quantity,
                    (quantity * price) AS cost FROM suppliers INNER JOIN products
                    ON suppliers.product_id = products.product_id
                    WHERE suppliers.supplier_id = ?;`, [req.params.id], (err, supplier, fields) => {
    if(!err) {
      console.log(supplier);
      res.render("details", { title: "Supplier's Details", supplier });
      // res.send(supplier);
    }
    else
      console.log(err);
  })
});

// Insert a Supplier
app.post("/insertData", (req, res) => {

  // This will come from the front
  const company_name = req.body.company_name;
  const contact_name = req.body.contact_name;
  const email = req.body.email;
  const product = req.body.product;
  const quantity = req.body.quantity;

  console.log(company_name);
  // const supplier = req.body;

  mysqlConnection.query(`INSERT INTO suppliers (company_name, contact_name, email, product_id, quantity)
                         VALUES (?, ?, ?, ?, ?);`,
                         [company_name, contact_name, email, product, quantity], (err, suppliers, fields) => {
    if(!err) {
      console.log("Record inserted succesfully");
      // res.send("Record inserted succesfully");
      res.redirect("/");
    }
    else
      console.log(err); 
  })
});

// Update a supplier
// Get Single Member for update
app.get("/suppliers/detailsUpdate/:id", (req, res) => {
  mysqlConnection.query(`SELECT supplier_id, company_name, contact_name, email, product_name, price, quantity,
                      (quantity * price) AS cost
                    FROM suppliers INNER JOIN products
                    ON suppliers.product_id = products.product_id
                    WHERE suppliers.supplier_id = ?;`, [req.params.id], (err, supplier, fields) => {
    if(!err) {
      console.log(supplier);
      res.render("update", { title: "Update Supplier", supplier });
      // res.send(supplier);
    }
    else
      console.log(err);
  })
});
// Update
app.post("/update/:id", (req, res) => {
  // This will come from the front
  const company_name = req.body.company_name;
  const contact_name = req.body.contact_name;
  const email = req.body.email;
  const product = req.body.product;
  const quantity = req.body.quantity;
  // Query DB
  mysqlConnection.query(`UPDATE suppliers
                        SET company_name = ?, contact_name= ?, email= ?, product_id = ?, quantity = ?
                        WHERE suppliers.supplier_id = ?;`, [
                          company_name, contact_name, email, product, quantity, req.params.id
                        ], (err, suppliers, fields) => {
    if(!err) {
      console.log("Record updated succesfully");
      // res.send("Record updated succesfully");
      res.redirect("/");
      // res.json({ redirect: '/' });
    }
    else
      console.log(err);
  })
});

// Delete a supplier
app.delete("/delete/:id", (req, res) => {
  console.log(req.params.id);
  mysqlConnection.query(`DELETE FROM suppliers
                         WHERE supplier_id = ?`, [req.params.id], (err, suppliers, fields) => {
    if(!err) {      
      console.log("deleted succesfully");
      res.json({ redirect: '/' });
    }
    else
      console.log(err);
  })
});

app.get("/about", (req, res) => {
  res.render("about", { title: "About" });
});

app.get("/suppliers/create", (req, res) => {
  res.render("create", { title: "Create new Supplier" });
});

// 404 page
// If nothing else matches, sends the 404.ejs page
app.use((req, res) => {
  res.status(404).render("404", { title: "404" });
});