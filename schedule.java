// ===== OOP: Class ScheduleSlot + ScheduleManager (Composition) =====
class ScheduleSlot {
    constructor(id,day,date,time,tutor,subject,status) {
        this.id=id; this.day=day; this.date=date; this.time=time;
        this.tutorName=tutor; this.subject=subject; this.status=status||'confirmed';
    }
}
class ScheduleManager {
    #slots = [];
    addSlot(s) { this.#slots.push(s); }
    getSlots() { return this.#slots; }
    getByDate(date) { return this.#slots.filter(function(s){return s.date === date;}); }
}

var sched = new ScheduleManager();
// ข้อมูลตารางเรียนทั้งสัปดาห์
sched.addSlot(new ScheduleSlot(1,'จ','7','15:00-17:00','Tutor Somchai','ฟิสิกส์'));
sched.addSlot(new ScheduleSlot(2,'จ','7','18:00-19:30','Tutor Alice','แคลคูลัส'));
sched.addSlot(new ScheduleSlot(3,'พ','9','14:00-16:00','Tutor Somsak','เคมี'));
sched.addSlot(new ScheduleSlot(4,'ศ','11','16:00-18:00','Tutor Somying','อังกฤษ'));
sched.addSlot(new ScheduleSlot(5,'ส','12','10:00-12:00','Tutor Alice','แคลคูลัส','pending'));
sched.addSlot(new ScheduleSlot(6,'ส','12','14:00-15:30','Tutor Somchai','ฟิสิกส์'));

var days = [
    {l:'จ',d:'7'},{l:'อ',d:'8'},{l:'พ',d:'9'},
    {l:'พฤ',d:'10'},{l:'ศ',d:'11'},{l:'ส',d:'12'},{l:'อา',d:'13'}
];
var classDays = sched.getSlots().map(function(s){return s.date;});
var selectedDate = '12';  // default วันนี้

function renderCalendar() {
    document.getElementById('calStrip').innerHTML = days.map(function(d) {
        var a = d.d === selectedDate ? ' active' : '';
        var h = classDays.includes(d.d) ? ' has-class' : '';
        return '<div class="day-cell' + a + h + '" onclick="selectDay(\'' + d.d + '\')"><div class="d-label">' + d.l + '</div><div class="d-num">' + d.d + '</div></div>';
    }).join('');
}

function renderSchedule() {
    var slots = sched.getByDate(selectedDate);
    var label = 'คลาสเรียนวันที่ ' + selectedDate + ' เมษายน';
    document.getElementById('sectionLabel').textContent = label;
    if (slots.length === 0) {
        document.getElementById('schedList').innerHTML = '<div class="empty-day"><i class="fa-solid fa-calendar-xmark"></i><p>ไม่มีคลาสเรียนในวันนี้</p></div>';
    } else {
        document.getElementById('schedList').innerHTML = slots.map(function(s){
            var p = s.status === 'pending';
            return '<div class="schedule-card' + (p?' pending':'') + '"><div class="time-block" style="min-width:55px;text-align:center;"><div class="t">' + s.time + '</div></div><div style="flex:1"><div class="sched-tutor">' + s.tutorName + '</div><div class="sched-subject">' + s.subject + '</div></div><span class="sched-status' + (p?' pending':'') + '">' + (p?'รอยืนยัน':'ยืนยันแล้ว') + '</span></div>';
        }).join('');
    }
}

function selectDay(date) {
    selectedDate = date;
    renderCalendar();
    renderSchedule();
}

renderCalendar();
renderSchedule();

// ===== Role-aware Navigation Bar =====
var role = localStorage.getItem('userRole') || 'student';
var navHTML;
if (role === 'tutor') {
    navHTML = '<a href="tutor_dashboard.html"><i class="fa-solid fa-home"></i></a><a href="schedule.html" class="active"><i class="fa-solid fa-calendar-days"></i></a><a href="hot_board.html"><i class="fa-solid fa-trophy"></i></a><a href="tutor_edit.html"><i class="fa-solid fa-user"></i></a>';
} else {
    navHTML = '<a href="index.html"><i class="fa-solid fa-home"></i></a><a href="preference.html"><i class="fa-solid fa-search"></i></a><a href="my_classes.html"><i class="fa-solid fa-book-open"></i></a><a href="user_profile.html" class="active"><i class="fa-solid fa-user"></i></a>';
}
document.getElementById('navBar').innerHTML = navHTML;
