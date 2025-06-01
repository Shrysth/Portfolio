document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('contactForm');
    const popup = document.getElementById('popup');
    const submitBtn = form.querySelector('button[type="submit"]');
    const sendText = submitBtn.querySelector('.send-text');
    const spinner = submitBtn.querySelector('.spinner-border');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        
        // Show loading state
        submitBtn.disabled = true;
        sendText.textContent = 'Sending...';
        spinner.classList.remove('d-none');

        const formData = new FormData(form);

        try {
            const response = await fetch('http://localhost:5000/contact', {
                method: 'POST',
                body: formData
            });

            if (response.ok) {
                showPopup('Thank you for reaching out! I\'ll get back to you soon.', 'success');
                form.reset();
            } else {
                const text = await response.text();
                showPopup(text || 'Something went wrong. Please try again.', 'error');
            }
        } catch (err) {
            console.error(err);
            showPopup('Network error. Please check your connection and try again.', 'error');
        } finally {
            // Reset button state
            submitBtn.disabled = false;
            sendText.textContent = 'Send Message';
            spinner.classList.add('d-none');
        }
    });

    function showPopup(message, type) {
        // Set popup content and style based on type
        popup.innerHTML = `
            <div class="popup-content">
                <p>${message}</p>
                <button onclick="closePopup()">Close</button>
            </div>
        `;
        
        if (type === 'error') {
            popup.style.backgroundColor = 'rgba(220, 53, 69, 0.9)';
        } else {
            popup.style.backgroundColor = 'rgba(138, 43, 226, 0.9)';
        }
        
        popup.classList.add('show');
        setTimeout(() => {
            popup.classList.remove('show');
        }, 5000);
    }

    window.closePopup = function() {
        popup.classList.remove('show');
        popup.style.backgroundColor = 'rgba(138, 43, 226, 0.9)';
    };
});