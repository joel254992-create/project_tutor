// ===== Role check =====
var _role = localStorage.getItem('userRole');
if (_role === 'student') { window.location.href = 'index.html'; }
else if (_role === 'admin') { window.location.href = 'admin_dashboard.html'; }

// ===== OOP: Class TutorProfile (Encapsulation + validation + persistence) =====
class TutorProfile {
    #name; #subjects; #style; #price; #level; #avatar;

    constructor(name, subjects, style, price, level) {
        this.#name = name;
        this.#subjects = subjects;  // array of subjects
        this.#style = style;        // array of styles
        this.#price = price;
        this.#level = level;
        this.#avatar = null;
    }

    getName() { return this.#name; }
    getSubjects() { return this.#subjects; }
    getStyle() { return this.#style; }
    getPrice() { return this.#price; }
    getLevel() { return this.#level; }
    getAvatar() { return this.#avatar; }

    setSubjects(s) { this.#subjects = s; }
    setStyle(s) { this.#style = s; }
    setLevel(l) { this.#level = l; }
    setAvatar(a) { this.#avatar = a; this.save(); }

    setPrice(p) {
        if (p < 50 || p > 1000) return false;
        this.#price = p; return true;
    }

    save() {
        localStorage.setItem('tutorProfile', JSON.stringify({
            subjects: this.#subjects, style: this.#style,
            price: this.#price, level: this.#level, avatar: this.#avatar
        }));
    }

    load() {
        var saved = localStorage.getItem('tutorProfile');
        if (saved) {
            var d = JSON.parse(saved);
            this.#subjects = d.subjects || this.#subjects;
            this.#style = d.style || this.#style;
            this.#price = d.price || this.#price;
            this.#level = d.level || this.#level;
            this.#avatar = d.avatar || null;
        }
    }
}

var tutor = new TutorProfile("User", ["ฟิสิกส์","เคมี"], ["Visual"], 200, "มัธยมปลาย");
tutor.load();

var allSubjects = ['ฟิสิกส์','เคมี','คณิตศาสตร์','ชีววิทยา','อังกฤษ','TOEIC','แคลคูลัส','สถิติ'];
var allStyles = ['Visual','Lecture','เน้นโจทย์','Discussion','Hands-on'];

// render avatar
function renderAvatar() {
    var av = tutor.getAvatar();
    var el = document.getElementById('avatarDisplay');
    if (av) { el.innerHTML = '<img src="' + av + '" alt="profile">'; }
    else { el.innerHTML = '<i class="fa-solid fa-user"></i>'; }
}
renderAvatar();

document.getElementById('avatarInput').addEventListener('change', function(e) {
    var file = e.target.files[0];
    if (!file) return;
    var reader = new FileReader();
    reader.onload = function(ev) {
        tutor.setAvatar(ev.target.result);
        renderAvatar();
        showToast('อัพโหลดรูปโปรไฟล์สำเร็จ');
    };
    reader.readAsDataURL(file);
});

// render subject chips
function renderSubjects() {
    document.getElementById('subjectChips').innerHTML = allSubjects.map(function(s) {
        var active = tutor.getSubjects().includes(s) ? ' active' : '';
        return '<div class="chip' + active + '" data-subject="' + s + '">' + s + '</div>';
    }).join('');
    document.querySelectorAll('#subjectChips .chip').forEach(function(chip) {
        chip.addEventListener('click', function() {
            var s = chip.getAttribute('data-subject');
            var subs = tutor.getSubjects().slice();
            var idx = subs.indexOf(s);
            if (idx >= 0) subs.splice(idx, 1);
            else subs.push(s);
            tutor.setSubjects(subs);
            chip.classList.toggle('active');
        });
    });
}

// render style chips
function renderStyles() {
    document.getElementById('styleChips').innerHTML = allStyles.map(function(s) {
        var active = tutor.getStyle().includes(s) ? ' active' : '';
        return '<div class="chip' + active + '" data-style="' + s + '">' + s + '</div>';
    }).join('');
    document.querySelectorAll('#styleChips .chip').forEach(function(chip) {
        chip.addEventListener('click', function() {
            var s = chip.getAttribute('data-style');
            var sts = tutor.getStyle().slice();
            var idx = sts.indexOf(s);
            if (idx >= 0) sts.splice(idx, 1);
            else sts.push(s);
            tutor.setStyle(sts);
            chip.classList.toggle('active');
        });
    });
}

// level selector
document.getElementById('levelSelect').value = tutor.getLevel();
document.getElementById('levelSelect').addEventListener('change', function(e) {
    tutor.setLevel(e.target.value);
});

// price input
document.getElementById('priceInput').value = tutor.getPrice();

renderSubjects();
renderStyles();

function showToast(msg) {
    var t = document.getElementById('saveToast');
    t.textContent = msg;
    t.style.display = 'block';
    setTimeout(function() { t.style.display = 'none'; }, 2000);
}

// บันทึกข้อมูล
document.getElementById('btnSave').addEventListener('click', function() {
    var priceVal = parseInt(document.getElementById('priceInput').value);
    if (!tutor.setPrice(priceVal)) {
        alert('ราคาต้องอยู่ระหว่าง 50 - 1000 บาท');
        return;
    }
    tutor.save();
    showToast('บันทึกข้อมูลสำเร็จ!');
    setTimeout(function() { window.location.href = 'tutor_dashboard.html'; }, 1200);
});
