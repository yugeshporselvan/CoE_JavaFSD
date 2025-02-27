document.addEventListener("DOMContentLoaded", () => {
    fetch('speakers.json')
        .then(response => response.json())
        .then(data => {
            const container = document.getElementById('speakers-container');
            data.forEach(speaker => {
                const speakerDiv = document.createElement('div');
                speakerDiv.className = 'speaker';
                speakerDiv.innerHTML = `
                    <h2>${speaker.name}</h2>
                    <p>${speaker.bio}</p>
                `;
                container.appendChild(speakerDiv);
            });
        })
        .catch(error => console.error('Error loading speakers:', error));



        fetch('speakers.json')
    .then(response => {
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
    })
    .then(data => console.log("Loaded data:", data))
    .catch(error => console.error("Error loading speakers:", error));
});
