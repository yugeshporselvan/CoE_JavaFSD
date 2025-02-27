document.addEventListener("DOMContentLoaded", () => {
    fetch('schedule.json')
        .then(response => response.json())
        .then(data => {
            const container = document.getElementById('schedule-container');
            const trackSelect = document.getElementById('track-select');
            const tracks = new Set();

            data.forEach(session => {
                tracks.add(session.track);
            });

            tracks.forEach(track => {
                const option = document.createElement('option');
                option.value = track;
                option.textContent = track;
                trackSelect.appendChild(option);
            });

            trackSelect.addEventListener('change', () => {
                const selectedTrack = trackSelect.value;
                const filteredData = selectedTrack === 'all' ? data : data.filter(session => session.track === selectedTrack);
                renderSchedule(filteredData, container);
            });

            renderSchedule(data, container);
        })
        .catch(error => console.error('Error loading schedule:', error));
});

function renderSchedule(sessions, container) {
    container.innerHTML = '';
    if (sessions.length === 0) {
        container.innerHTML = '<p>No sessions available for the selected track.</p>';
        return;
    }

    sessions.forEach(session => {
        const sessionDiv = document.createElement('div');
        sessionDiv.className = 'session';
        sessionDiv.innerHTML = `
            <h2>${session.title}</h2>
            <p><strong>Track:</strong> ${session.track}</p>
            <p>${session.description}</p>
        `;
        container.appendChild(sessionDiv);
    });
}
