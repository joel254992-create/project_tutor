// ===== Role check =====
var _role = localStorage.getItem('userRole');
if (_role === 'tutor') { window.location.href = 'tutor_dashboard.html'; }
else if (_role === 'admin') { window.location.href = 'admin_dashboard.html'; }

// ===== OOP: Class PaymentStatus =====
class PaymentStatus {
    constructor(id, amt, name) {
        var now = new Date();
        this.bookingId = 'BK-' + String(id).padStart(5,'0') + '-' + String(now.getMinutes()).padStart(2,'0') + String(now.getSeconds()).padStart(2,'0');
        this.amount = amt; this.tutorName = name;
    }
}
var params = new URLSearchParams(window.location.search);
var id = params.get('id') || 0; var amt = params.get('amt') || 0;
var names = {1:'Tutor Somchai',2:'Tutor Somsak',3:'Tutor Somying',4:'Tutor Alice'};
var payment = new PaymentStatus(id, amt, names[id] || 'Tutor Name');
var today = new Date();
document.getElementById('receipt').innerHTML = '<div style="font-size:12px;color:#bbb;">เลขที่รายการ: '+payment.bookingId+'</div><div class="amount">฿'+Number(payment.amount).toLocaleString(undefined,{minimumFractionDigits:2})+'</div><div style="font-size:13px;color:#2ECC71;">ชำระสำเร็จ</div><div class="info-grid"><div class="info-row"><span style="color:#999">ผู้สอน</span><span style="font-weight:500">'+payment.tutorName+'</span></div><div class="info-row"><span style="color:#999">วันที่</span><span style="font-weight:500">'+today.getDate()+'/'+(today.getMonth()+1)+'/'+(today.getFullYear()+543)+'</span></div></div>';
