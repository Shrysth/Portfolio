const form = document.getElementById('contactForm');
const popup = document.getElementById('popup');

form.addEventListener('submit', async (e) => {
  e.preventDefault();

  const formData = new FormData(form); // works with @ModelAttribute

  try {
    const response = await fetch('https://portfolio-production-d593.up.railway.app/contact', {
      method: 'POST',
      body: formData
    });

    if (response.ok) {
      showPopup(); // 🎉 Thank-you popup!
      form.reset();
    } else {
      const text = await response.text();
      alert(text);
    }
  } catch (err) {
    console.error(err);
    alert(err);
  }
});

function showPopup() {
  popup.classList.add('show'); // Show the popup
  setTimeout(() => popup.classList.remove('show'), 5000); // Hide after 5 seconds
}

function closePopup() {
  popup.classList.remove('show'); // Ensure the popup is closed when clicked
}
