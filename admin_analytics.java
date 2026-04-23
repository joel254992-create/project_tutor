// ===== Role check =====
var _role = localStorage.getItem('userRole');
if (_role !== 'admin' && _role !== null) {
    window.location.href = _role === 'tutor' ? 'tutor_dashboard.html' : 'index.html';
}

// ===== OOP: Class Analytics (Encapsulation) =====
class Analytics {
    #subjectStats=[];#matchRate;#totalMatches;
    constructor(rate,total){this.#matchRate=rate;this.#totalMatches=total;}
    addSubjectStat(name,pct){this.#subjectStats.push({name:name,percent:pct});}
    getSubjectStats(){return this.#subjectStats;} getMatchRate(){return this.#matchRate;} getTotalMatches(){return this.#totalMatches;}
}
var analytics=new Analytics(78,450);
analytics.addSubjectStat('ฟิสิกส์',80);analytics.addSubjectStat('คณิตศาสตร์',70);analytics.addSubjectStat('เคมี',60);analytics.addSubjectStat('อังกฤษ',45);analytics.addSubjectStat('ชีวะ',40);
var data=[120,180,220,310,280,350,400];var months=['ก.ย.','ต.ค.','พ.ย.','ธ.ค.','ม.ค.','ก.พ.','มี.ค.'];var max=Math.max.apply(null,data);
document.getElementById('content').innerHTML='<div class="section-card"><h4>สถิติรายเดือน</h4><div class="chart-bars">'+data.map(function(v,i){return '<div class="bar" style="height:'+(v/max*100)+'%"><span class="bar-val">'+v+'</span><span class="bar-label">'+months[i]+'</span></div>';}).join('')+'</div></div><div class="section-card"><h4>วิชายอดนิยม</h4>'+analytics.getSubjectStats().map(function(s){return '<div class="subject-row"><span class="subj-name">'+s.name+'</span><div class="subj-bar"><div class="subj-fill" style="width:'+s.percent+'%"></div></div><span class="subj-pct">'+s.percent+'%</span></div>';}).join('')+'</div><div class="section-card"><h4>อัตราการจับคู่สำเร็จ</h4><div class="rate-center"><div class="rate-big">'+analytics.getMatchRate()+' %</div><div class="rate-bar"><div class="rate-fill" style="width:'+analytics.getMatchRate()+'%"></div></div><div class="rate-desc">จากจำนวนทั้งหมด '+analytics.getTotalMatches()+' ครั้ง</div></div></div>';
