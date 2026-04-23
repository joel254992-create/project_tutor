// ===== Role check =====
var _role = localStorage.getItem('userRole');
if (_role === 'tutor') { window.location.href = 'tutor_dashboard.html'; }
else if (_role === 'admin') { window.location.href = 'admin_dashboard.html'; }

// ===== OOP: Class StudentProfile (Encapsulation + setter validation) =====
class StudentProfile {
    #name; #email; #level; #style; #budget; #avatar;

    constructor(name, email, level, style, budget) {
        this.#name = name;
        this.#email = email;
        this.#level = level;
        this.#style = style;
        this.#budget = budget;
        this.#avatar = null;
    }

    getName() { return this.#name; }
    getEmail() { return this.#email; }
    getLevel() { return this.#level; }
    getStyle() { return this.#style; }
    getBudget() { return this.#budget; }
    getAvatar() { return this.#avatar; }

    setLevel(l) { this.#level = l; this.save(); }
    setStyle(s) { this.#style = s; this.save(); }
    setBudget(b) { this.#budget = b; this.save(); }
    setAvatar(a) { this.#avatar = a; this.save(); }

    save() {
        localStorage.setItem('studentProfile', JSON.stringify({
            name: this.#name, email: this.#email, level: this.#level,
            style: this.#style, budget: this.#budget, avatar: this.#avatar
        }));
    }

    load() {
        var saved = localStorage.getItem('studentProfile');
        if (saved) {
            var d = JSON.parse(saved);
            this.#level = d.level; this.#style = d.style;
            this.#budget = d.budget; this.#avatar = d.avatar;
        }
    }
}

var student = new StudentProfile("User", "user@gmail.com", "มัธยมปลาย", "Visual", "100-300 บาท/ชม.");
student.load();

// แสดงรูปโปรไฟล์
function renderAvatar() {
    var av = student.getAvatar();
    var el = document.getElementById('avatarDisplay');
    if (av) { el.innerHTML = '<img src="' + av + '" alt="profile">'; }
    else { el.innerHTML = '<i class="fa-solid fa-user"></i>'; }
}
renderAvatar();

// อัพโหลดรูป
document.getElementById('avatarInput').addEventListener('change', function(e) {
    var file = e.target.files[0];
    if (!file) return;
    var reader = new FileReader();
    reader.onload = function(ev) {
        student.setAvatar(ev.target.result);
        renderAvatar();
        showToast('อัพโหลดรูปโปรไฟล์สำเร็จ');
    };
    reader.readAsDataURL(file);
});

// Toast
function showToast(msg) {
    var t = document.getElementById('saveToast');
    t.textContent = msg;
    t.style.display = 'block';
    setTimeout(function() { t.style.display = 'none'; }, 2000);
}

// แก้ไขระดับชั้น
var editMode = { level: false, style: false, budget: false };
var levels = ['มัธยมต้น', 'มัธยมปลาย', 'มหาวิทยาลัย', 'ทุกระดับ'];
var styles = ['Visual', 'Lecture', 'เน้นโจทย์', 'Discussion', 'Hands-on'];

function renderInfo() {
    var html = '';

    // Level
    if (editMode.level) {
        var opts = levels.map(function(l) { return '<option value="' + l + '"' + (l === student.getLevel() ? ' selected' : '') + '>' + l + '</option>'; }).join('');
        html += '<div class="info-row"><span style="color:#999">ระดับชั้น</span><select onchange="saveField(\'level\', this.value)">' + opts + '</select></div>';
    } else {
        html += '<div class="info-row"><span style="color:#999">ระดับชั้น</span><span style="font-weight:500">' + student.getLevel() + '</span></div>';
    }

    // Style
    if (editMode.style) {
        var opts = styles.map(function(s) { return '<option value="' + s + '"' + (s === student.getStyle() ? ' selected' : '') + '>' + s + '</option>'; }).join('');
        html += '<div class="info-row"><span style="color:#999">สไตล์การเรียน</span><select onchange="saveField(\'style\', this.value)">' + opts + '</select></div>';
    } else {
        html += '<div class="info-row"><span style="color:#999">สไตล์การเรียน</span><span style="font-weight:500">' + student.getStyle() + '</span></div>';
    }

    // Budget
    if (editMode.budget) {
        html += '<div class="info-row"><span style="color:#999">งบประมาณ</span><input type="text" value="' + student.getBudget() + '" onblur="saveField(\'budget\', this.value)" placeholder="เช่น 100-300 บาท/ชม."></div>';
    } else {
        html += '<div class="info-row"><span style="color:#999">งบประมาณ</span><span style="font-weight:500">' + student.getBudget() + '</span></div>';
    }

    document.getElementById('infoList').innerHTML = html;
}

function toggleEdit() {
    var anyEdit = editMode.level || editMode.style || editMode.budget;
    editMode.level = !anyEdit;
    editMode.style = !anyEdit;
    editMode.budget = !anyEdit;
    document.getElementById('editBtn').textContent = anyEdit ? 'แก้ไข' : 'เสร็จสิ้น';
    renderInfo();
    if (anyEdit) showToast('บันทึกข้อมูลสำเร็จ');
}

function saveField(field, value) {
    if (field === 'level') student.setLevel(value);
    else if (field === 'style') student.setStyle(value);
    else if (field === 'budget') student.setBudget(value);
}

renderInfo();
