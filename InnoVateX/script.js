// Dropdown menu (for mobile devices)
document.querySelector('nav').addEventListener('click', function(e) {
    if (e.target.tagName === 'A') {
      const sectionId = e.target.getAttribute('href').substring(1);
      document.getElementById(sectionId).scrollIntoView({ behavior: 'smooth' });
    }
  });
  
  // Modal (For example, a simple alert)
  function openModal(message) {
    alert(message);
  }
  
  // Form validation
  document.getElementById('contact-form').addEventListener('submit', function(e) {
    e.preventDefault();
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    if (!name || !email) {
      alert("Please fill in all fields.");
    } else {
      alert("Thank you for your message!");
    }
  });

  // Fetch speakers dynamically
fetch('speakers.json')
.then(response => response.json())
.then(data => {
  const speakersList = document.getElementById('speakers-list');
  data.forEach(speaker => {
    const speakerDiv = document.createElement('div');
    speakerDiv.innerHTML = `<h3>${speaker.name}</h3><p>Topic: ${speaker.topic}</p><p>${speaker.bio}</p>`;
    speakersList.appendChild(speakerDiv);
  });
});

// Dynamic schedule (filter by track)
const schedule = [
{ session: 'AI Workshop', track: 'AI' },
{ session: 'Blockchain Panel', track: 'Blockchain' }
];

function displaySchedule(filter = '') {
const scheduleList = document.getElementById('schedule-list');
scheduleList.innerHTML = '';
schedule.filter(session => filter ? session.track === filter : true)
  .forEach(session => {
    const sessionDiv = document.createElement('div');
    sessionDiv.innerHTML = `<h3>${session.session}</h3><p>Track: ${session.track}</p>`;
    scheduleList.appendChild(sessionDiv);
  });
}

displaySchedule(); // Load all initially
