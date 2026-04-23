// ===== OOP: เก็บ role ไว้ใน localStorage เพื่อให้ nav bar แสดงตาม role ที่ถูกต้อง =====
var params = new URLSearchParams(window.location.search);
if (params.get('registered')) document.getElementById('successMsg').style.display = 'block';
if (params.get('logout')) {
    document.getElementById('logoutMsg').style.display = 'block';
    localStorage.removeItem('userRole');
}

document.getElementById('loginForm').addEventListener('submit', function(e) {
    e.preventDefault();
    var email = document.getElementById('email').value;
    var pass = document.getElementById('password').value;
    if (email === 'user@gmail.com' && pass === '1234') {
        localStorage.setItem('userRole', 'student');
        window.location.href = 'preference.html';
    } else if (email === 'tutor@gmail.com' && pass === '5678') {
        localStorage.setItem('userRole', 'tutor');
        window.location.href = 'tutor_dashboard.html';
    } else if (email === 'admin@gmail.com' && pass === '4321') {
        localStorage.setItem('userRole', 'admin');
        window.location.href = 'admin_dashboard.html';
    } else {
        document.getElementById('error-msg').style.display = 'block';
    }
});
