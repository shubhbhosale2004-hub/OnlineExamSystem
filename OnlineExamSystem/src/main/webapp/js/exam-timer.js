/**
 * ExamPortal - Exam Timer and Question Navigation
 * Handles countdown timer, auto-submit, and question navigation during exams.
 */

var currentQuestion = 0;
var examEndTime = null;
var timerInterval = null;

/**
 * Initializes the exam countdown timer.
 * Uses Date.now() delta approach for accuracy even if tab is backgrounded.
 */
function initExamTimer(durationMinutes, formId) {
    // Check sessionStorage for an existing timer (survives page reloads)
    var storedEndTime = sessionStorage.getItem('examEndTime_' + formId);
    if (storedEndTime) {
        examEndTime = parseInt(storedEndTime, 10);
    } else {
        examEndTime = Date.now() + (durationMinutes * 60 * 1000);
        sessionStorage.setItem('examEndTime_' + formId, examEndTime.toString());
    }

    updateTimerDisplay(formId);
    timerInterval = setInterval(function () {
        updateTimerDisplay(formId);
    }, 1000);
}

/** Updates the timer display element and handles expiry. */
function updateTimerDisplay(formId) {
    var now = Date.now();
    var remaining = examEndTime - now;
    var timerEl = document.getElementById('examTimer');

    if (remaining <= 0) {
        // Time's up — auto submit
        clearInterval(timerInterval);
        timerEl.textContent = '00:00';
        sessionStorage.removeItem('examEndTime_' + formId);
        document.getElementById('autoSubmitField').value = 'true';
        alert('Time is up! Your exam will be submitted automatically.');
        document.getElementById(formId).submit();
        return;
    }

    var minutes = Math.floor(remaining / 60000);
    var seconds = Math.floor((remaining % 60000) / 1000);
    timerEl.textContent = pad(minutes) + ':' + pad(seconds);

    // Update page title with remaining time
    document.title = pad(minutes) + ':' + pad(seconds) + ' - Exam In Progress';

    // Visual warnings
    if (remaining <= 60000) {
        timerEl.className = 'timer-display danger';
    } else if (remaining <= 300000) {
        timerEl.className = 'timer-display warning';
    }
}

/** Pads a number with a leading zero if needed. */
function pad(num) {
    return num < 10 ? '0' + num : num.toString();
}

/* ---- Question Navigation ---- */

/** Shows the question at the given index and hides all others. */
function jumpToQuestion(index) {
    // Hide all question panels
    var panels = document.querySelectorAll('.question-panel');
    panels.forEach(function (p) { p.style.display = 'none'; });

    // Show the target panel
    var target = document.getElementById('questionPanel_' + index);
    if (target) target.style.display = 'block';

    // Update navigation button styles
    var navBtns = document.querySelectorAll('.question-nav-btn');
    navBtns.forEach(function (btn) { btn.classList.remove('current'); });
    var currentNav = document.getElementById('navBtn_' + index);
    if (currentNav) currentNav.classList.add('current');

    currentQuestion = index;

    // Update counter text
    var counter = document.getElementById('questionCounter');
    if (counter) counter.textContent = 'Question ' + (index + 1) + ' of ' + totalQuestions;

    // Update prev/next button states
    var prevBtn = document.getElementById('prevBtn');
    var nextBtn = document.getElementById('nextBtn');
    if (prevBtn) prevBtn.disabled = (index === 0);
    if (nextBtn) nextBtn.disabled = (index === totalQuestions - 1);
}

/** Navigates to the next question. */
function navigateNext() {
    if (currentQuestion < totalQuestions - 1) {
        jumpToQuestion(currentQuestion + 1);
    }
}

/** Navigates to the previous question. */
function navigatePrev() {
    if (currentQuestion > 0) {
        jumpToQuestion(currentQuestion - 1);
    }
}

/**
 * Called when a student selects an answer option.
 * Updates the option card styling and marks the nav button as answered.
 */
function markAnswered(questionId, option, questionIndex) {
    // Style the selected option card
    var allLabels = document.querySelectorAll('[id^="optLabel_' + questionId + '_"]');
    allLabels.forEach(function (label) { label.classList.remove('selected'); });
    var selectedLabel = document.getElementById('optLabel_' + questionId + '_' + option);
    if (selectedLabel) selectedLabel.classList.add('selected');

    // Mark the nav button as answered
    var navBtn = document.getElementById('navBtn_' + questionIndex);
    if (navBtn) navBtn.classList.add('answered');
}

/** Shows a confirmation dialog before manual exam submission. */
function confirmSubmit() {
    var answered = document.querySelectorAll('.question-nav-btn.answered').length;
    var unanswered = totalQuestions - answered;
    var msg = 'You have answered ' + answered + ' out of ' + totalQuestions + ' questions.';
    if (unanswered > 0) msg += '\n' + unanswered + ' question(s) are unanswered.';
    msg += '\n\nAre you sure you want to submit?';

    if (confirm(msg)) {
        clearInterval(timerInterval);
        var formId = 'examForm';
        sessionStorage.removeItem('examEndTime_' + formId);
        document.getElementById(formId).submit();
    }
}

// Prevent accidental back navigation during exam
if (typeof totalQuestions !== 'undefined') {
    history.pushState(null, null, location.href);
    window.onpopstate = function () {
        history.pushState(null, null, location.href);
        alert('You cannot go back during the exam. Please submit your exam first.');
    };
}
