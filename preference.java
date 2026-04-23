// ===== Role check =====
var role = localStorage.getItem('userRole');
if (role === 'tutor') { window.location.href = 'tutor_dashboard.html'; }
else if (role === 'admin') { window.location.href = 'admin_dashboard.html'; }

// ===== OOP: Class PreferenceManager =====
class PreferenceManager {
    #options;
    constructor(options) { this.#options = options; }
    getOptions() { return this.#options; }
}

var prefMgr = new PreferenceManager(['ใจดี', 'เน้นโจทย์', 'สอนสนุก', 'เนื้อหาแน่น', 'ใจเย็น']);

function createFilterCard(canRemove) {
    var card = document.createElement('div');
    card.className = 'filter-card';
    var sel = '<select class="custom-select feature-select"><option value="">ลักษณะที่ต้องการ</option>';
    prefMgr.getOptions().forEach(function(o) { sel += '<option value="' + o + '">' + o + '</option>'; });
    sel += '</select>';
    var removeBtn = canRemove ? '<button type="button" class="btn-remove" onclick="this.closest(\'.filter-card\').remove()"><i class="fa-solid fa-xmark"></i></button>' : '';
    card.innerHTML = sel + '<div class="match-circle">% Match</div>' + removeBtn;
    return card;
}

var container = document.getElementById('filterContainer');
for (var i = 0; i < 3; i++) container.appendChild(createFilterCard(i > 0));

function addFilter() {
    if (container.children.length >= 6) { alert('เพิ่มได้สูงสุด 6 รายการ'); return; }
    container.appendChild(createFilterCard(true));
}

function submitFilters() {
    var features = [];
    document.querySelectorAll('.feature-select').forEach(function(s) { if (s.value) features.push(s.value); });
    window.location.href = 'index.html?features=' + encodeURIComponent(JSON.stringify(features));
}

document.querySelectorAll('.sub-tag').forEach(function(t) {
    t.addEventListener('click', function() {
        document.querySelectorAll('.sub-tag').forEach(function(x) { x.classList.remove('active'); });
        t.classList.add('active');
    });
});
