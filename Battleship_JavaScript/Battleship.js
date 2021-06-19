// ==========================================
//              The View
//===========================================

// The Vew object is responsible for updating the view and it will implement it 
// with methods.
var view = {
    // This method takes a string message like “HIT!”, “You missed.” and
    // “You sank my battleship!” and displays it in the message display area.
    displayMessage: function (msg) {
        // We update the text of the messageArea element by setting its innerHTML to msg
        var messageArea = document.getElementById("messageArea");
        messageArea.innerHTML = msg;

    },

    // To display a ship or MISS graphic in the grid, we need to take a <td> element and
    // add either the “hit” or the “miss” class.
    // We need a string id that consists of two numbers for the location of the hit or miss.
    // We use the DOM to get the element with that id and set that element’s class attribute
    // to “hit” if we’re in displayHit, and “miss” if we’re in displayMiss. The location
    // argument should match the id of a cell. We’re using the id we created from the player's
    // guess to get the correct element to update

    displayHit: function (location) {    // This method displays a "MISS" on the grid
        // We get a reference to that element
        var cell = document.getElementById(location);
        // We add the class “hit”
        cell.setAttribute("class", "hit");
    },
    
    displayMiss: function (location) {   // This method displays a ship graphic on the grid
        var cell = document.getElementById(location);
        cell.setAttribute("class", "miss");
    }
};

//------------------------
// // testing the view
// view.displayMiss("00");
// view.displayHit("34");
// view.displayMiss("55");
// view.displayHit("12");
// view.displayMiss("25");
// view.displayHit("26");
// view.displayMessage("Tap tap, is this thing on?");
//-------------------------------------------------------


// ==========================================
//              The Model
//===========================================

// In model object we keep the state of the game and some logic relating to how the state
// changes. Here state includes the location of the ships, the locations of those that have
// been hit, and how many have been sunk. The logic is determining when a player’s guess
// has hit a ship and then marking that ship with a hit.
var model = {
    // These three properties keep us from hardcoding values.
    boardSize: 7,
    numShips: 3,
    shipLength: 3,  // the number of locations in each ship
    shipsSunk: 0,
    
    // We’ll create a single array variable to hold all the ships. It has three elements (the ships)
    // which hold the locations and its hits.The locations property is an array that holds each location
    // on the board. The hits property is also an array that holds whether or not a ship is hit at each location.
    // Hardcoded ship locations
    // ships: [    
    //     { locations: ["06", "16", "26"], hits: ["", "", ""] },
    //     { locations: ["24", "34", "44"], hits: ["", "", ""] },
    //     { locations: ["10", "11", "12"], hits: ["", "", ""] }
    // ],
    // We'll Remove the hardcoded ship locations and replace them with arrays initialized with 0's.

    ships: [
        { locations: [0, 0, 0], hits: ["", "", ""] },
        { locations: [0, 0, 0], hits: ["", "", ""] },
        { locations: [0, 0, 0], hits: ["", "", ""] }
    ],

    // A method to fire on a ship and figure out if the shot is a hit or miss.
    // It will take a guess and iterate over each ship to determine if that ship was hit.
    fire: function (guess) {
        for (var i = 0; i < this.numShips; i++) {
            // Here we have our hands on a ship. We need to see if the guess matches any of its locations.
            var ship = this.ships[i];
            // And we access the ship's set of locations. (this is a property
            // of the ship that contains an array).
            var locations = ship.locations;    
            // We use indexOf() to search the array for a matching value. It'll return its index,
            // or -1 if it can't find it.
            // For each ship... If the guess is in the locations array, we have a hit.
            var index = locations.indexOf(guess);
            // We can combine the above two statements by chaining together the expressions to:
            // var index = ship.locations.indexOf(guess);   //

            // if we get an index > = 0, the user's guess is in the location's array, and we have a hit.
            if (index >= 0) {
                // We mark the hits array at the same index.
                ship.hits[index] = "hit";
                // We have to notify the view when we get a hit or a miss in the model
                // We got a hit at the location in guess.
                view.displayHit(guess);
                // And ask the view to display the message “HIT!”.
                view.displayMessage("HIT!");
                // We use the isSunk() method to find out if a ship is sunk.
                // We'll add the check here, after we know for sure we have a hit. If the ship is sunk,
                // then we increase the number of ships that are sunk in model's shipsSunk property.
                if (this.isSunk(ship)) {
                    // We let the player know that this hit sank the battleship!
                    view.displayMessage("You sank my battleship!");
                    this.shipsSunk++;
                }

                return true;    // and we need to return true because we had a hit.
            }
        }
        // Notify the view that we got a miss at the location in guess
        view.displayMiss(guess);
        // And ask the view to display the message “You missed.”.
        view.displayMessage("You missed.");
        // Otherwise, if we make it through all the ships and don't have a hit,
        // it's a miss, so we return false
        return false;
    },
    // The code to determine if a ship is sunk.
    // The method isSunk takes a ship and return true if it's sunk and false if it is still floating.
    isSunk: function (ship) {
        for (var i = 0; i < this.shipLength; i++) {
            // It checks every possible location for a hit. If there's a location that doesn't
            // have a hit, then the ship is still floating, so return false.
            if (ship.hits[i] !== "hit") {
                return false;
            }
        }
        // Otherwise this ship is sunk! Return true.
        return true;
    },

    // How to place ships.
    // When placing ships on the game board, we need to consider that they can be oriented
    // either vertically or horizontally and they don’t overlap.

    // This method creates a ships array with the number of ships in the model’s numShips property
    generateShipLocations: function () {
        var locations;
        for (var i = 0; i < this.numShips; i++) {
            do {
                // We generate a new set of locations with the generateShip()
                locations = this.generateShip();
                // Each time we use the collision method passing the locations array (for a new ship we’d
                // like to place on the board) to make sure there are no overlaps. 
                // If there is, try again. Keep generating new locations until there's no collision.
            } while (this.collision(locations));
            // Once we have locations that work, we assign the locations to the ship’s locations
            // property in the model.ships array
            this.ships[i].locations = locations;
        }
    },

    // This method creates an array with random locations for one ship without worrying about overlapping
    generateShip: function () {
        // First we randomly pick a direction for the ship, horizontal or vertical (1 or 0)
        var direction = Math.floor(Math.random() * 2);
        var row, col;
        // Then we’ll create a starting location, like row = 0 or column = 3, for the new ship
        // depending on the direction. The rest of the locations will be the next two columns
        // if the ship is horizontal, or the next two rows if it’s vertical.
        // We need to generate two random numbers, a row and a column for the starting location (0 to 6).
        if (direction === 1) {      // Horizontal
            // A horizontal ship can be located in any row 
            row = Math.floor(Math.random() * this.boardSize);
            // But the starting column must be between 0 and 4, so that to be room for the rest of the ship
            // We use this.shipLength to generalize the code, so we can use it for any ship length
            col = Math.floor(Math.random() * (this.boardSize - this.shipLength));
        } else {        //Vertical
            // The starting row must be between 0 and 4
            row = Math.floor(Math.random() * (this.boardSize - this.shipLength));
            // But can be located in any column
            col = Math.floor(Math.random() * this.boardSize);
        }
        // For the new ship locations, we’ll start with an empty array, and add the locations one by one
        var newShipLocations = [];
        // We’ll loop for the number of locations in a ship
        for (var i = 0; i < this.shipLength; i++) {
            // And add a new location to the newShipLocations array each time through the loop.
            // depending again on the direction
            if (direction === 1) {  // Horizontal ship               
                // The new location is a string made up of the starting row and the column + i. 
                // The first time, i is 0, just the starting column. The second, the next column,
                // the third, the next over again. So we’ll get something like “01”, “02”, “03” in the array.
                newShipLocations.push(row + "" + (col + i));
            } else {    //Vertical ship
                // Now, we’re increasing the row instead of the column, adding i each time.
                // Here we’ll get something like “31”, “41”, “51”
                newShipLocations.push((row + i) + "" + col);
            }
        }
        // Once we’ve generated all the locations, we return the array to the calling method generateShipLocations.       
        return newShipLocations;
    },

    // Avoiding a collision method 
    // We implement this using two nested for loops. The outer loop iterates over all
    // the ships in the model (model.ships property). The inner loop iterates over
    // all the new ship’s locations in the locations array, and checks to see if any of those
    // locations is already taken by an existing ship on the board
    collision: function (locations) {
        // For each ship already on the board, check to see if any of the locations
        // in the new ship’s locations array are in an existing ship’s locations array.
        for (var i = 0; i < this.numShips; i++) {
            var ship = model.ships[i];
            for (var j = 0; j < locations.length; j++) {
                // We’re using indexOf to check if the location already exists in a ship, so if the
                // index is greater than or equal to 0, we know it matched an existing location, so we
                // return true (meaning, we found a collision)
                if (ship.locations.indexOf(locations[j]) >= 0) {
                    // Returning from a loop that’s inside another loop,
                    // stops the iteration of both loops immediately
                    return true;
                }
            }
        }
        // If we get here and haven’t returned, then we never found a match for any
        // of the locations we were checking, so we return false (there was no collision)
        return false;
    }
};


//------------------------------
// testing the model
// model.fire("53"); // miss

// model.fire("06"); // hit
// model.fire("16"); // hit
// model.fire("26"); // hit

// model.fire("34"); // hit
// model.fire("24"); // hit
// model.fire("44"); // hit

// model.fire("12"); // hit
// model.fire("11"); // hit
// model.fire("10"); // hit
//-------------------------------



// ==========================================
//              The Controller
//===========================================


// The controller glues everything together. It gets and processes the player’s
// guess (like “A0” or “B1”). It keeps track of the number of guesses.
// Determines when the game is over (that is, when all ships have been sunk).
var controller = {
    guesses: 0,     // Keeps number of guesses.
    // The processGuess method takes an alphanumeric guess, processes it and passes it to the model.
    // After we receive a guess in this alpha-numeric form, like “A3”, we need to transform the guess
    // into a form the model understands, a string of two numeric characters, like “03”.
    processGuess: function (guess) {
        // We’ll use parseGuess to validate the player’s guess.
        var location = parseGuess(guess);
        // And as long as we don’t get null back, we know we’ve got a valid location object
        if (location) {
            // We keep track of the number of guesses by incrementing the guesses property
            this.guesses++;
            // And then we pass the row and column as a string to the model’s fire method.
            // The fire method returns true if a ship is hit.
            var hit = model.fire(location);
            // All we have left is to determine when the game is over.
            // If the guess was a hit, and the number of ships that are sunk is
            // equal to the number of ships in the game, then show the player a message
            // The guesses property is of “this” object, the controller.
            if (hit && model.shipsSunk === model.numShips) {
                view.displayMessage("You sank all my battleships, in " + this.guesses + " guesses");
            }
        }
    }
};

//------------------------------
// testing controller
// We’re calling the controller’s processGuess method and passing in
// guesses in Battleship format.
// We should see three ships on the board, one miss, and the message
// "You sank all my battleships in 10 guesses"

// controller.processGuess("A0"); // miss

// controller.processGuess("A6"); // hit
// controller.processGuess("B6"); // hit
// controller.processGuess("C6"); // hit

// controller.processGuess("C4"); // hit
// controller.processGuess("D4"); // hit
// controller.processGuess("E4"); // hit

// controller.processGuess("B0"); // hit
// controller.processGuess("B1"); // hit
// controller.processGuess("B2"); // hit
//------------------------------


// Helper function to parse a guess from the user. Used by controller
function parseGuess(guess) {
    // We use a helper array that contains the letters A-F. 
    var alphabet = ["A", "B", "C", "D", "E", "F", "G"];
    // Checking the validity of the guess 
    if (guess === null || guess.length !== 2) {
        alert("Oops, please enter a letter and a number on the board.");
    } else {
        // We Grab the first character of the guess. And to get the number,
        // we use the indexOf method to get the index of the letter in the array.
        firstChar = guess.charAt(0);
        // We get back a number between zero and six that corresponds to the letter
        var row = alphabet.indexOf(firstChar);
        // We grab the second character in the string, which represents the column.
        var column = guess.charAt(1);
        // We check both characters to make sure they are valid positions on the board
        // If they are numbers between zero and six. We are using type conversions here
        if (isNaN(row) || isNaN(column)) {
            alert("Oops, that isn't on the board.");
        } else if (row < 0 || row >= model.boardSize ||
            column < 0 || column >= model.boardSize) {
            alert("Oops, that's off the board!");
        } else {
            // At this point, everything looks good, so we can return a row and column.
            return row + column;
        }
    }
    // If any check for valid input fails, we’ll return null.
    return null;
}

//------------------------------
// testing parseGuess
// console.log("Testing parseGuess");
// console.log(parseGuess("A0"));
// console.log(parseGuess("B6"));
// console.log(parseGuess("G3"));
// console.log(parseGuess("H0")); // invalid
// console.log(parseGuess("A7")); // invalid
//------------------------------


// ==========================================
//              Event Handlers
//===========================================

// To get this all rolling the first thing we need to do is add an event handler to the Fire button
// We need somewhere this code to go, so we'll create an init function.

function init() {
    // First, we get a reference to the Fire button using the button’s id:
    var fireButton = document.getElementById("fireButton");
    // Then we add a click handler function
    fireButton.onclick = handleFireButton;
    // Adding a new handler that handles key press events
    var guessInput = document.getElementById("guessInput");
    guessInput.onkeypress = handleKeyPress;

    // We call the generate the ship locations from the init function so it happens right
    // when we load the game, before we start playing. That way all the ships will have
    // locations ready to go when we start playing.
    model.generateShipLocations();
}

// We want the browser to run init when the page is fully loaded
window.onload = init;

// This function will be called whenever you click the Fire! button.
function handleFireButton() {
    // The player’s guess is contained in the “guessInput” form input element.
    // We can get the value from it by accessing the element’s value property
    var guessInput = document.getElementById("guessInput");
    var guess = guessInput.value;
    // Passing the input to the controller
    controller.processGuess(guess);

    // This line resets the input element to be empty before entering a next guess 
    guessInput.value = "";
}

// We can add a new handler to handle key press events so the player doesn't only
// have to click the button but can just press enter.
// The browser passes an event object (e) to the handler. This object has info
// about which key was pressed.
function handleKeyPress(e) {
    var fireButton = document.getElementById("fireButton");
    // If you press the ENTER key, the event's keyCode property will be set to
    // 13. If so we want the Fire! button to act like it was clicked. 
    // We do that by calling the fireButton's click method
    if (e.keyCode === 13) {
        fireButton.click();
        // And we return false so the form doesn't do anything else
        // (like try to submit itself).
        return false;
    }
}


