const checkbox = document.getElementById("checkbox")
checkbox.addEventListener("change", () => {
  document.body.classList.toggle("dark")
})
/*
const button1 = document.getElementById('button1');
const button2 = document.getElementById('button2');
const counterDisplay = document.getElementById('counter');

// Initialize counter
let counter = 0;

// Function to update the counter display
function updateCounter() {
  counterDisplay.textContent = counter;
}

// Event listeners for button clicks
button1.addEventListener('click', function() {
  counter++;
  updateCounter();
});

button2.addEventListener('click', function() {
  counter++;
  updateCounter();
});*/
// Retrieve the total count from localStorage or set to 0 if it doesn't exist
let totalCount = localStorage.getItem('totalCount');
if (!totalCount) {
  localStorage.setItem('totalCount', '0');
  totalCount = 0;
}

// Display the initial count
document.getElementById('totalCount').textContent = totalCount;

// Function to increment the total count and update the display
function updateCount() {
  totalCount++;
  localStorage.setItem('totalCount', totalCount);
  document.getElementById('totalCount').textContent = totalCount;
}

// Button 1 click event
document.getElementById('button1').addEventListener('click', updateCount);

// Button 2 click event
document.getElementById('button2').addEventListener('click', updateCount);
