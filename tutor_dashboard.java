// ===== Role check =====
var _role = localStorage.getItem('userRole');
if (_role === 'student') { window.location.href = 'index.html'; }
else if (_role === 'admin') { window.location.href = 'admin_dashboard.html'; }

// ===== OOP: Class TutorDashboard + UpcomingClass + confirm functionality =====
class TutorDashboard {
    #name; #rating; #students; #hours; #earnings;
    constructor(n,r,s,h,e) { this.#name=n; this.#rating=r; this.#students=s; this.#hours=h; this.#earnings=e; }
    getName(){return this.#name;} getRating(){return this.#rating;} getStudents(){return this.#students;}
    getHours(){return this.#hours;} getEarnings(){return this.#earnings.toLocaleString();}
}
class UpcomingClass {
    constructor(id, student, subject, time, status) {
        this.id = id; this.studentName = student; this.subject = subject;
        this.time = time; this.status = status || 'confirmed';
    }
    confirm() { this.status = 'confirmed'; }
}
class ClassManager {
    #classes = [];
    add(c) { this.#classes.push(c); }
    getAll() { return this.#classes; }
    confirmById(id) {
        var c = this.#classes.find(function(x){return x.id === id;});
        if (c) { c.confirm(); this.save(); }
    }
    save() {
        localStorage.setItem('tutorClasses', JSON.stringify(this.#classes.map(function(c){
            return {id:c.id, student:c.studentName, subject:c.subject, time:c.time, status:c.status};
        })));
    }
    load(defaults) {
        var saved = localStorage.getItem('tutorClasses');
        if (saved) {
            var d = JSON.parse(saved);
            this.#classes = d.map(function(x){return new UpcomingClass(x.id, x.student, x.subject, x.time, x.status);});
        } else {
            this.#classes = defaults;
        }
    }
}

var dash = new TutorDashboard("Tutor Somchai",4.9,24,156,28500);
var mgr = new ClassManager();
mgr.load([
    new UpcomingClass(1,"น้องเมย์","ฟิสิกส์","วันนี้ 15:00-17:00"),
    new UpcomingClass(2,"น้องบีม","เคมี","พรุ่งนี้ 10:00-12:00"),
    new UpcomingClass(3,"น้องมิ้นท์","ฟิสิกส์","ศ. 16:00-18:00","pending")
]);

// โหลดรูปโปรไฟล์จาก tutor_edit
var savedProfile = localStorage.getItem('tutorProfile');
if (savedProfile) {
    var p = JSON.parse(savedProfile);
    if (p.avatar) {
        document.getElementById('profileAvatar').innerHTML = '<img src="' + p.avatar + '" alt="profile">';
    }
}

function renderContent() {
    var cls = mgr.getAll();
    document.getElementById('content').innerHTML =
        '<div class="stat-grid"><div class="stat-box"><div class="stat-num">'+dash.getStudents()+'</div><div class="stat-label">นักเรียน</div></div><div class="stat-box"><div class="stat-num">'+dash.getHours()+'</div><div class="stat-label">ชั่วโมงสอน</div></div><div class="stat-box"><div class="stat-num">'+dash.getEarnings()+'฿</div><div class="stat-label">รายได้เดือนนี้</div></div></div>' +
        '<div class="section-card"><h4><i class="fa-solid fa-calendar"></i> คลาสที่กำลังจะมาถึง</h4>' +
        cls.map(function(c) {
            var btn = c.status === 'confirmed' ?
                '<span class="class-status confirmed">ยืนยันแล้ว</span>' :
                '<button class="class-status pending" onclick="confirmClass(' + c.id + ')">กดยืนยัน</button>';
            return '<div class="class-item"><div class="class-avatar"><i class="fa-solid fa-user"></i></div><div style="flex:1"><div class="class-name">'+c.studentName+'</div><div class="class-detail">'+c.subject+' • '+c.time+'</div></div>' + btn + '</div>';
        }).join('') + '</div>' +
        '<div class="menu-grid"><a href="tutor_edit.html" class="menu-item"><div class="menu-icon"><i class="fa-solid fa-pen-to-square"></i></div><div class="menu-label">แก้ไขโปรไฟล์</div></a><a href="schedule.html" class="menu-item"><div class="menu-icon"><i class="fa-solid fa-calendar-days"></i></div><div class="menu-label">ตารางสอน</div></a><a href="hot_board.html" class="menu-item"><div class="menu-icon"><i class="fa-solid fa-trophy"></i></div><div class="menu-label">HoT Board</div></a><a href="my_classes.html" class="menu-item"><div class="menu-icon"><i class="fa-solid fa-users"></i></div><div class="menu-label">นักเรียนของฉัน</div></a></div>';
}

function confirmClass(id) {
    mgr.confirmById(id);
    showToast('ยืนยันคลาสเรียบร้อย!');
    renderContent();
}

function showToast(msg) {
    var t = document.getElementById('saveToast');
    t.textContent = msg;
    t.style.display = 'block';
    setTimeout(function() { t.style.display = 'none'; }, 2000);
}

renderContent();
