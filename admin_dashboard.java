// ===== Role check =====
var _role = localStorage.getItem('userRole');
if (_role !== 'admin' && _role !== null) {
    window.location.href = _role === 'tutor' ? 'tutor_dashboard.html' : 'index.html';
}

// ===== OOP: Class DashboardStats + RevenueReport (Encapsulation + Abstraction) =====
class DashboardStats {
    #u;#t;#b;#r;
    constructor(u,t,b,r){this.#u=u;this.#t=t;this.#b=b;this.#r=r;}
    getTotalUsers(){return this.#u.toLocaleString();} getTotalTutors(){return this.#t.toLocaleString();}
    getTotalBookings(){return this.#b.toLocaleString();} getRevenue(){return this.#r.toLocaleString();}
}
class RevenueReport {
    #m=[];
    addMonth(m,a,g){this.#m.push({month:m,amount:a,growth:g});}
    getMonthly(){return this.#m;}
}
var stats=new DashboardStats(1240,186,3420,340000);
var rev=new RevenueReport();
rev.addMonth('มี.ค. 69',342000,'+22%');rev.addMonth('ก.พ. 69',280000,'+15%');rev.addMonth('ม.ค. 69',243000,'+8%');
var heights=[45,55,65,78,90,100,85];var months=['ก.ย.','ต.ค.','พ.ย.','ธ.ค.','ม.ค.','ก.พ.','มี.ค.'];
document.getElementById('content').innerHTML='<div class="stat-grid"><div class="stat-box"><div class="stat-num">'+stats.getTotalUsers()+'</div><div class="stat-label">ผู้ใช้ทั้งหมด</div></div><div class="stat-box"><div class="stat-num">'+stats.getTotalTutors()+'</div><div class="stat-label">ติวเตอร์</div></div><div class="stat-box"><div class="stat-num">'+stats.getTotalBookings()+'</div><div class="stat-label">การจองทั้งหมด</div></div><div class="stat-box"><div class="stat-num">'+stats.getRevenue()+'฿</div><div class="stat-label">รายได้รวม</div></div></div><div class="section-card"><h4>สถิติรายเดือน</h4><div class="chart-bars">'+heights.map(function(h,i){return '<div class="bar" style="height:'+h+'%"><span class="bar-label">'+months[i]+'</span></div>';}).join('')+'</div></div><div class="section-card"><h4>รายได้รายเดือน</h4>'+rev.getMonthly().map(function(r){return '<div class="revenue-row"><span style="color:#666">'+r.month+'</span><span><strong>'+r.amount.toLocaleString()+' ฿</strong> <span class="growth">'+r.growth+'</span></span></div>';}).join('')+'</div><div class="section-card"><h4>อัตราการจับคู่สำเร็จ</h4><div class="rate-center"><div class="rate-num">78%</div><div class="rate-bar"><div class="rate-fill" style="width:78%"></div></div><div class="rate-desc">จากจำนวนทั้งหมด 450 ครั้ง</div></div></div>';
