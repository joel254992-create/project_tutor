// ===== Role check =====
var role = localStorage.getItem('userRole');
if (role === 'tutor') { window.location.href = 'tutor_dashboard.html'; }
else if (role === 'admin') { window.location.href = 'admin_dashboard.html'; }

// ===== OOP: Class Tutor (Encapsulation + Constructor) =====
class Tutor {
    constructor(id, name, rating, reviews, exp, subjects, style, price, edu, matchPercent) {
        this.id = id; this.name = name; this.rating = rating;
        this.reviewCount = reviews; this.expYear = exp; this.subjects = subjects;
        this.style = style; this.price = price; this.edu = edu; this.matchPercent = matchPercent;
    }
}

var tutors = {
    1: new Tutor(1, "Tutor Somchai", 4.9, 13, 2, "ฟิสิกส์, เคมี", "Visual", 200, "วิศวกรรมศาสตร์ จุฬาฯ", 93),
    2: new Tutor(2, "Tutor Somsak", 4.9, 10, 3, "เคมี, ชีวะ", "Lecture", 180, "วิทยาศาสตร์ มช.", 85),
    3: new Tutor(3, "Tutor Somying", 4.8, 8, 1, "อังกฤษ, TOEIC", "Visual", 150, "อักษรศาสตร์ จุฬาฯ", 70),
    4: new Tutor(4, "Tutor Alice", 4.7, 6, 2, "แคลคูลัส, Stat", "Lecture", 150, "สถิติ ธรรมศาสตร์", 60)
};

var params = new URLSearchParams(window.location.search);
var id = parseInt(params.get('id')) || 1;
var tutor = tutors[id] || tutors[1];

document.getElementById('tutorName').textContent = tutor.name;
document.getElementById('tutorSub').textContent = '★ ' + tutor.rating + ' (' + tutor.reviewCount + ' รีวิว) • ประสบการณ์ ' + tutor.expYear + ' ปี';
document.getElementById('matchPct').textContent = tutor.matchPercent + '%';
document.getElementById('teachInfo').innerHTML =
    '<div class="row"><span class="label">วิชา</span><span class="val">' + tutor.subjects + '</span></div>' +
    '<div class="row"><span class="label">สไตล์การสอน</span><span class="val">' + tutor.style + '</span></div>' +
    '<div class="row"><span class="label">ราคา</span><span class="val">' + tutor.price + '฿/ชม.</span></div>' +
    '<div class="row"><span class="label">การศึกษา</span><span class="val">' + tutor.edu + '</span></div>';

var selectedTime = '';
function pickT(el, t) {
    document.querySelectorAll('.time-pill').forEach(function(p) { p.classList.remove('active'); });
    el.classList.add('active'); selectedTime = t;
}
function book() {
    if (!selectedTime) { alert('กรุณาเลือกเวลาเรียน'); return; }
    window.location.href = 'booking.html?id=' + tutor.id + '&time=' + encodeURIComponent(selectedTime);
}
