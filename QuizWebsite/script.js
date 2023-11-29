document.addEventListener('DOMContentLoaded', function () {
    const smoothScrollLinks = document.querySelectorAll('a[href^="#"]');

    for (let link of smoothScrollLinks) {
        link.addEventListener('click', function (e) {
            e.preventDefault();
            const targetId = this.getAttribute('href').substring(1);
            const targetElement = document.getElementById(targetId);

            if (targetElement) {
                scrollToElement(targetElement);
            }
        });
    }
});

function scrollToElement(element) {
    const scrollOffset = 100;
    const elementPosition = element.offsetTop;
    const offsetPosition = elementPosition - scrollOffset;

    window.scrollTo({
        top: offsetPosition,
        behavior: 'smooth'
    });
}

window.addEventListener('DOMContentLoaded', (event) => {
    const themes = [
        { id: 1, name: 'General Knowledge', code: 'GK' },
        { id: 2, name: 'Team Fortress 2', code: 'TF2' },
        { id: 3, name: 'Programming', code: 'Code' },
        { id: 4, name: 'CS:GO', code: 'CS:GO' },
        { id: 5, name: "Five Night's at Freddy's", code: 'FNaF' },
        { id: 6, name: 'Complete The Sentence', code: 'Cts' },
        { id: 7, name: 'And much more!!!', code: 'AmM'}
    ];

    const themesList = document.getElementById('themes-list');

    themes.forEach(theme => {
        const listItem = document.createElement('li');
        listItem.textContent = theme.name;
        themesList.appendChild(listItem);
    });
});

window.addEventListener('DOMContentLoaded', (event) => {
    const achievements = [
        { id: 1, name: 'First Login', description: '(You created an account)' },
        { id: 2, name: 'The Beginning', description: '(Complete any quiz)' },
        { id: 3, name: 'Ding Ding Ding!', description: '(Complete a quiz with every question correctly answered)' },
        { id: 4, name: 'A Great Loser', description: '(Complete a quiz with no correct answers)' },
        { id: 5, name: 'Pootis enjoyer', description: '(You got 7/7 in a TF2 themed quiz)' },
        { id: 6, name: 'Tough Mind', description: '(Get a winning-streak on 3 quizzes with no mistakes)' },
        { id: 7, name: 'A.I.', description: '(Get a winning-streak on 10 quizzes with no mistakes)' },
        { id: 8, name: 'Pootis Penser Here!', description: '(You clicked somewhere heavy wanted!)' },
        { id: 9, name: '101 Questions', description: '(You went through 101 questions. Simple)' },
        { id: 10, name: 'Medic!', description: '(You got assisted in a question)' },
        { id: 11, name: 'And much more!!!', description: ''}
    ];

    const achievementsList = document.getElementById('achievements-list');

    achievements.forEach(achievement => {
        const listItem = document.createElement('li');
        listItem.innerHTML = `<strong>${achievement.name}</strong><br>${achievement.description}`;
        achievementsList.appendChild(listItem);
    });
});

function scrollToTop() {
    const topSection = document.getElementById('top');
    topSection.scrollIntoView({ behavior: 'smooth' });
}