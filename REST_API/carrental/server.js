const express = require('express')
const app = express()
const path = require('path')
const fs = require('fs')
const port = 8080

app.use(express.json())

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, './index.html'));
})

app.listen(port, () => {
  console.log(`PTI HTTP Server listening at http://localhost:${port}`)
})

app.post('/newrental', (req, res, next) => {

  /* Check if file exists and create it if not */
  if(!fs.existsSync('rentals.json')){
    rentalsJSON = {"rentals": []};
    fs.writeFileSync("rentals.json", JSON.stringify(rentalsJSON));
  }

  /* Read file */
  rentalsFileRawData = fs.readFileSync('rentals.json');
  rentalsJSON = JSON.parse(rentalsFileRawData);

  /* Create new rental JSON and add it into the parsed array */
  new_rental = {
    "maker":req.body.maker,
    "model":req.body.model,
    "days":req.body.days,
    "units":req.body.units
  }
  rentalsJSON['rentals'].push(new_rental)

  /* Write array into the file */
  fs.writeFileSync("rentals.json", JSON.stringify(rentalsJSON));

  console.log(new_rental)
  res.end();
})

app.get('list', (req, res, next) => {
  /* Check if file exists and create it if not */
  if(!fs.existsSync('rentals.json')){
    console.log("File not found");
  }
  else{
    /* Read file */
    rentalsFileRawData = fs.readFileSync('rentals.json');
    rentalsJSON = JSON.parse(rentalsFileRawData);
    for(var i in rentalsJSON['rentals']){
      console.log(rentalsJSON['rentals'][i]+'\n');
    }
  }
})