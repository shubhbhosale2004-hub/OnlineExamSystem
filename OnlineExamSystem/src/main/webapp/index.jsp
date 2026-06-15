<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ExamPortal - Online Examination System</title>
    <meta name="description" content="Take exams anytime, anywhere with our secure online examination platform.">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <style>
        .hero-section {
            min-height: 90vh; display: flex; align-items: center; justify-content: center;
            background: var(--gradient-primary); position: relative; overflow: hidden;
        }
        .hero-section::before {
            content: ''; position: absolute; top: -50%; left: -50%; width: 200%; height: 200%;
            background: radial-gradient(circle at 30% 70%, rgba(118,75,162,0.4) 0%, transparent 50%),
                        radial-gradient(circle at 70% 30%, rgba(102,126,234,0.3) 0%, transparent 50%);
            animation: float 8s ease-in-out infinite;
        }
        @keyframes float { 0%,100% { transform: translate(0,0); } 50% { transform: translate(30px,-30px); } }
        .hero-content { position: relative; z-index: 1; text-align: center; }
        .hero-title { font-size: 3.5rem; font-weight: 800; color: #fff; letter-spacing: -1px; }
        .hero-subtitle { font-size: 1.25rem; color: rgba(255,255,255,0.8); max-width: 600px; margin: 1rem auto; }
        .feature-card { transition: transform 0.3s ease; }
        .feature-card:hover { transform: translateY(-8px); }
        .feature-icon { font-size: 2.5rem; margin-bottom: 1rem; }
    </style>
</head>
<body>
    <!-- Hero Section -->
    <section class="hero-section">
        <div class="hero-content">
            <i class="bi bi-mortarboard-fill text-white" style="font-size:4rem;opacity:0.9;"></i>
            <h1 class="hero-title mt-3">ExamPortal</h1>
            <p class="hero-subtitle">Take exams anytime, anywhere with our secure online examination platform. Instant results, detailed analytics, and a seamless experience.</p>
            <div class="mt-4">
                <a href="${pageContext.request.contextPath}/login" class="btn btn-light btn-lg px-4 me-2 fw-semibold">
                    <i class="bi bi-person-badge me-1"></i>Student Login
                </a>
                <a href="${pageContext.request.contextPath}/login" class="btn btn-outline-light btn-lg px-4 fw-semibold">
                    <i class="bi bi-shield-lock me-1"></i>Admin Login
                </a>
            </div>
        </div>
    </section>

    <!-- Features Section -->
    <section class="py-5" style="background: var(--bg-dark);">
        <div class="container">
            <h2 class="text-center text-white mb-5 fw-bold">Why ExamPortal?</h2>
            <div class="row g-4">
                <div class="col-md-4">
                    <div class="card glass-card feature-card text-center p-4 h-100">
                        <div class="card-body">
                            <div class="feature-icon text-gradient"><i class="bi bi-shield-check"></i></div>
                            <h5 class="text-white fw-bold">Secure Exams</h5>
                            <p class="text-muted">BCrypt encrypted passwords, session management, and SQL injection prevention ensure complete security.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card glass-card feature-card text-center p-4 h-100">
                        <div class="card-body">
                            <div class="feature-icon" style="color:#38ef7d;"><i class="bi bi-lightning-charge"></i></div>
                            <h5 class="text-white fw-bold">Instant Results</h5>
                            <p class="text-muted">Get your exam results immediately after submission with detailed score breakdown and pass/fail status.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card glass-card feature-card text-center p-4 h-100">
                        <div class="card-body">
                            <div class="feature-icon" style="color:#F2C94C;"><i class="bi bi-gear"></i></div>
                            <h5 class="text-white fw-bold">Easy Management</h5>
                            <p class="text-muted">Admins can manage students, subjects, exams, and questions with an intuitive dashboard interface.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <footer class="text-center py-3" style="background:rgba(0,0,0,0.3); border-top:1px solid rgba(255,255,255,0.08);">
        <span class="text-muted">&copy; 2026 ExamPortal &mdash; Online Examination System</span>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
