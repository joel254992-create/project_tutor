// ===== Role check =====
var _role = localStorage.getItem('userRole');
if (_role === 'tutor') { window.location.href = 'tutor_dashboard.html'; }
else if (_role === 'admin') { window.location.href = 'admin_dashboard.html'; }

// ===== OOP: Class BookingProcessor (Constructor + Method) =====
class BookingProcessor {
    constructor(name, subject, price, date, time) {
        this.tutorName = name; this.subject = subject;
        this.pricePerHour = price; this.bookingDate = date; this.bookingTime = time;
    }
    calculateTotal(hours) { return this.pricePerHour * (hours || 2); }
    getServiceFee() { return 20; }
}

var data = {1:{name:"Tutor Somchai",subject:"ฟิสิกส์, เคมี",price:140},2:{name:"Tutor Somsak",subject:"เคมี, ชีวะ",price:180},3:{name:"Tutor Somying",subject:"อังกฤษ, TOEIC",price:150},4:{name:"Tutor Alice",subject:"แคลคูลัส, Stat",price:150}};
var params = new URLSearchParams(window.location.search);
var id = params.get('id') || 1; var time = params.get('time') || '15:00-17:00';
var info = data[id] || data[1];
var booking = new BookingProcessor(info.name, info.subject, info.price, '14 เมษายน 2569', time);
var totalAmount = booking.calculateTotal(2) + booking.getServiceFee();

document.getElementById('bookingDetail').innerHTML = '<h3>สรุปรายละเอียดการจอง</h3><div class="detail-item"><span class="detail-label">ชื่อติวเตอร์</span><span class="detail-value">'+booking.tutorName+'</span></div><div class="detail-item"><span class="detail-label">วิชา</span><span class="detail-value">'+booking.subject+'</span></div><div class="detail-item"><span class="detail-label">วันที่และเวลา</span><span class="detail-value">'+booking.bookingDate+' | '+booking.bookingTime+'</span></div><div class="dashed-line"></div><div class="total-row"><span style="color:#666">ยอดชำระสุทธิ</span><span class="total-price">฿'+totalAmount.toFixed(2)+'</span></div>';
function goToPayment() { window.location.href = 'payment.html?id='+id+'&amt='+totalAmount; }
