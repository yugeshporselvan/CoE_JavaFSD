// TypeScript: Static typing for function
function countdownToConference(targetDate: string): void {
    const now = new Date();
    const conferenceDate = new Date(targetDate);
    const difference = conferenceDate.getTime() - now.getTime();
    
    const daysLeft = Math.floor(difference / (1000 * 3600 * 24));
    document.getElementById('countdown').innerText = `${daysLeft} days left until InnovateX!`;
  }
  
  countdownToConference("2025-06-01T00:00:00");
  