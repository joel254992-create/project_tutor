// ===== Role check =====
var _role = localStorage.getItem('userRole');
if (_role !== 'admin' && _role !== null) {
    window.location.href = _role === 'tutor' ? 'tutor_dashboard.html' : 'index.html';
}

// ===== OOP: Class ActivityLog (Encapsulation + Polymorphism-like icon) =====
class ActivityLog {
    constructor(type,message,timeAgo) {
        this.message=message;this.timeAgo=timeAgo;
        var map={new_user:{icon:'fa-user-plus',color:'#FF6B6B'},verify:{icon:'fa-circle-check',color:'#2ECC71'},booking:{icon:'fa-calendar-check',color:'#3498DB'},alert:{icon:'fa-star',color:'#F39C12'},update:{icon:'fa-images',color:'#9B59B6'},payment:{icon:'fa-money-bill',color:'#2ECC71'},cancel:{icon:'fa-xmark',color:'#E74C3C'}};
        var m=map[type]||{icon:'fa-info-circle',color:'#999'};this.icon=m.icon;this.color=m.color;
    }
}
var acts=[new ActivityLog('new_user','นักเรียนใหม่ สมัครสมาชิก','5 นาทีก่อน'),new ActivityLog('verify','ติวเตอร์ ... ได้รับการอนุมัติ','30 นาทีก่อน'),new ActivityLog('booking','มีการจอง 3 ครั้ง ใหม่','50 นาทีก่อน'),new ActivityLog('alert','ติวเตอร์เปิด ...','1 ชม. ก่อน'),new ActivityLog('update','อัพเดทโปรไฟล์','2 ชม. ก่อน'),new ActivityLog('payment','การชำระเงิน 1,200 สำเร็จ','3 ชม. ก่อน'),new ActivityLog('cancel','... แคนเซิล','4 ชม. ก่อน')];
document.getElementById('content').innerHTML=acts.map(function(a){return '<div class="activity-card"><div class="act-icon" style="background:'+a.color+'15;color:'+a.color+'"><i class="fa-solid '+a.icon+'"></i></div><div style="flex:1"><div class="act-msg">'+a.message+'</div><div class="act-time">'+a.timeAgo+'</div></div></div>';}).join('');
