// ===== Role check =====
var role = localStorage.getItem('userRole');
if (role === 'tutor') { window.location.href = 'tutor_dashboard.html'; }
else if (role === 'admin') { window.location.href = 'admin_dashboard.html'; }

// ===== OOP: Class MatchEngine (Encapsulation + Algorithm) =====
class MatchEngine {
    #tutors;
    constructor(tutorData) {
        this.#tutors = tutorData.map(function(t) { return Object.assign({}, t); });
    }
    getMatchedTutors(userFeatures) {
        var tutors = this.#tutors;
        if (!userFeatures || userFeatures.length === 0) {
            tutors.forEach(function(t) { t.score = 0; });
            return tutors;
        }
        tutors.forEach(function(t) {
            var intersect = userFeatures.filter(function(f) { return t.tags.includes(f); });
            t.score = Math.round((intersect.length / userFeatures.length) * 100);
        });
        tutors.sort(function(a, b) { return b.score - a.score; });
        return tutors;
    }
}

var tutorsRaw = [
    { id: 1, name: "Tutor Somchai", rating: 4.9, subjects: "ฟิสิกส์, เคมี", type: "Visual", price: 200, tags: ["ใจดี", "สอนสนุก", "เน้นเนื้อหา"] },
    { id: 2, name: "Tutor Somsak", rating: 4.9, subjects: "เคมี, ชีวะ", type: "Lecture", price: 180, tags: ["เน้นโจทย์", "เนื้อหาแน่น", "ใจเย็น"] },
    { id: 3, name: "Tutor Somying", rating: 4.8, subjects: "อังกฤษ, TOEIC", type: "Visual", price: 150, tags: ["สอนสนุก", "สำเนียงดี", "ใจดี"] },
    { id: 4, name: "Tutor Alice", rating: 4.7, subjects: "แคลคูลัส, Stat", type: "Lecture", price: 150, tags: ["เนื้อหาแน่น", "ใจเย็น", "เน้นโจทย์"] }
];

var params = new URLSearchParams(window.location.search);
var userPref = [];
try { userPref = JSON.parse(params.get('features') || '[]'); } catch(e) {}

var engine = new MatchEngine(tutorsRaw);
var tutorList = engine.getMatchedTutors(userPref);

document.getElementById('infoText').textContent = 'พบ ' + tutorList.length + ' ติวเตอร์ - เรียงตาม Match score';
var html = '';
tutorList.forEach(function(t) {
    html += '<div class="tutor-card" onclick="location.href=\'profile.html?id=' + t.id + '\'">' +
        '<div class="profile-img"><i class="fa-solid fa-user"></i></div>' +
        '<div class="tutor-detail"><div class="tutor-name">' + t.name + '</div>' +
        '<div class="rating-box"><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i> <span>' + t.rating + '</span> <span style="color:#aaa; margin-left:5px;">' + t.subjects + '</span></div>' +
        '<div class="tag-row"><span class="tag-badge">' + t.type + '</span><span class="tag-badge" style="background:#FFE4E4; font-weight:600;">' + t.price + 'บาท/ชม.</span></div></div>' +
        '<div class="match-badge"><div class="score-circle">' + t.score + '%</div><div class="score-label">Match</div></div></div>';
});
document.getElementById('tutorList').innerHTML = html;

document.querySelectorAll('.subject-tag').forEach(function(t) {
    t.addEventListener('click', function() {
        document.querySelectorAll('.subject-tag').forEach(function(x) { x.classList.remove('active'); });
        t.classList.add('active');
    });
});
