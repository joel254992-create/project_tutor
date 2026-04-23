// ===== OOP: Class Enrollment + EnrollmentManager (Encapsulation + Composition) =====
class Enrollment {
    constructor(id,tutorName,subject,status,startDate,endDate,progress) {
        this.id=id;this.tutorName=tutorName;this.subject=subject;this.status=status;this.startDate=startDate;this.endDate=endDate;this.progress=progress;
    }
    getStatusLabel() { return {active:'กำลังเรียน',completed:'เรียนจบแล้ว',upcoming:'รอเริ่มเรียน'}[this.status]||'ไม่ทราบ'; }
    getStatusColor() { return {active:'#FF6B6B',completed:'#2ECC71',upcoming:'#FEB47B'}[this.status]||'#999'; }
}
class EnrollmentManager {
    #enrollments = [];
    add(e) { this.#enrollments.push(e); }
    filterByStatus(s) { return this.#enrollments.filter(function(e){return e.status===s;}); }
}
var mgr = new EnrollmentManager();
mgr.add(new Enrollment(1,"Tutor Somchai","ฟิสิกส์","active","1 เม.ย. 2569","15 เม.ย. 2569",65));
mgr.add(new Enrollment(2,"Tutor Somsak","เคมี","active","3 เม.ย. 2569","17 เม.ย. 2569",40));
mgr.add(new Enrollment(3,"Tutor Somying","อังกฤษ","completed","1 มี.ค. 2569","30 มี.ค. 2569",100));
mgr.add(new Enrollment(4,"Tutor Alice","แคลคูลัส","upcoming","20 เม.ย. 2569","30 เม.ย. 2569",0));
var tab = new URLSearchParams(window.location.search).get('tab')||'active';
var tabData=[{key:'active',label:'กำลังเรียน'},{key:'completed',label:'เรียนจบแล้ว'},{key:'upcoming',label:'ประวัติ'}];
document.getElementById('tabs').innerHTML = tabData.map(function(t){return '<a class="tab '+(t.key===tab?'active':'')+'" href="?tab='+t.key+'">'+t.label+'</a>';}).join('');
var filtered = mgr.filterByStatus(tab);
if(filtered.length===0){document.getElementById('content').innerHTML='<div class="empty"><i class="fa-solid fa-book-open"></i><p>ไม่มีรายการ</p></div>';}
else{document.getElementById('content').innerHTML=filtered.map(function(e){var prog=e.status==='active'?'<div class="progress-bar"><div class="progress-fill" style="width:'+e.progress+'%"></div></div><div class="card-dates">ความคืบหน้า '+e.progress+'%</div>':'';var rev=e.status==='completed'?'<button class="btn-review">เขียนรีวิว</button>':'';return '<div class="class-card"><div class="card-top"><div class="avatar"><i class="fa-solid fa-user"></i></div><div style="flex:1"><div class="card-name">'+e.tutorName+'</div><div class="card-subject">'+e.subject+'</div></div><span class="status-badge" style="background:'+e.getStatusColor()+'">'+e.getStatusLabel()+'</span></div>'+prog+'<div class="card-dates">'+e.startDate+' — '+e.endDate+'</div>'+rev+'</div>';}).join('');}

// ===== Role-aware Navigation =====
var role = localStorage.getItem('userRole') || 'student';
var navHTML;
if (role === 'tutor') {
    navHTML = '<a href="tutor_dashboard.html"><i class="fa-solid fa-home"></i></a><a href="schedule.html"><i class="fa-solid fa-calendar-days"></i></a><a href="hot_board.html"><i class="fa-solid fa-trophy"></i></a><a href="tutor_edit.html"><i class="fa-solid fa-user"></i></a>';
} else {
    navHTML = '<a href="index.html"><i class="fa-solid fa-home"></i></a><a href="preference.html"><i class="fa-solid fa-search"></i></a><a href="my_classes.html" class="active"><i class="fa-solid fa-book-open"></i></a><a href="user_profile.html"><i class="fa-solid fa-user"></i></a>';
}
document.getElementById('navBar').innerHTML = navHTML;
