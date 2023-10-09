document.addEventListener("DOMContentLoaded", function() {
    fetch('http://localhost:8080/reviews/pendingReviews')
    .then(response => response.json())
    .then(data => {
        const reviewsContainer = document.getElementById('pendingReviews');
        data.forEach(review => {
            const reviewElement = document.createElement('div');
            reviewElement.textContent = review.commentary;
            reviewsContainer.appendChild(reviewElement);
        });
    })
    .catch(error => console.error('Error fetching the reviews:', error));
});
