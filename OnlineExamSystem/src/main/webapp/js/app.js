/**
 * ExamPortal - Common Application Scripts
 * Toast notifications, alert auto-dismiss, counter animation, etc.
 */

document.addEventListener('DOMContentLoaded', function () {
    // Auto-dismiss alerts after 5 seconds
    document.querySelectorAll('.alert-dismissible').forEach(function (alert) {
        setTimeout(function () {
            var bsAlert = bootstrap.Alert.getOrCreateInstance(alert);
            if (bsAlert) bsAlert.close();
        }, 5000);
    });

    // Initialize Bootstrap tooltips
    var tooltipTriggers = document.querySelectorAll('[data-bs-toggle="tooltip"]');
    tooltipTriggers.forEach(function (el) { new bootstrap.Tooltip(el); });

    // Animated number counter for dashboard stat cards
    document.querySelectorAll('.counter').forEach(function (el) {
        var target = parseInt(el.textContent, 10);
        if (isNaN(target)) return;
        var current = 0;
        var increment = Math.max(1, Math.ceil(target / 40));
        var timer = setInterval(function () {
            current += increment;
            if (current >= target) { current = target; clearInterval(timer); }
            el.textContent = current;
        }, 30);
    });

    // Prevent form double-submission
    document.querySelectorAll('form').forEach(function (form) {
        form.addEventListener('submit', function () {
            var submitBtns = form.querySelectorAll('[type="submit"]');
            submitBtns.forEach(function (btn) {
                btn.disabled = true;
                setTimeout(function () { btn.disabled = false; }, 3000);
            });
        });
    });
});
