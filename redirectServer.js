const express = require('express');
const app = express();
const port = 3000;

app.get('/', (req, res) => { // Callback handler for the root path
  const code = req.query.code;
  if (code) {
    console.log(`Authorization Code: ${code}`);
    res.send('Authorization code received! Check your console.');
  } else {
    res.status(400).send('Error: Authorization code not found.');
  }
});

app.listen(port, () => {
  console.log(`Callback server listening at http://localhost:${port}/`);
});