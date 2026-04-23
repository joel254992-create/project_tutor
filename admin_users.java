// ===== Role check =====
var _role = localStorage.getItem('userRole');
if (_role !== 'admin' && _role !== null) {
    window.location.href = _role === 'tutor' ? 'tutor_dashboard.html' : 'index.html';
}

// ===== OOP: Inheritance (User -> Student, TutorUser) + Polymorphism =====
class User {
    constructor(id,name,email,join,status){this.id=id;this.name=name;this.email=email;this.joinDate=join;this.status=status;this.type='user';}
    getRole(){return 'ผู้ใช้';} getRoleBg(){return '#eee';} getRoleColor(){return '#666';}
    getStatusLabel(){return this.status==='active'?'ใช้งาน':'ระงับ';}
    getStatusColor(){return this.status==='active'?'#2ECC71':'#E74C3C';}
}
class Student extends User {
    constructor(id,name,email,join,status){super(id,name,email,join,status);this.type='student';}
    getRole(){return 'นักศึกษา';} getRoleBg(){return '#E3F2FD';} getRoleColor(){return '#1565C0';}
}
class TutorUser extends User {
    constructor(id,name,email,join,status){super(id,name,email,join,status);this.type='tutor';}
    getRole(){return 'ติวเตอร์';} getRoleBg(){return '#FFF3E0';} getRoleColor(){return '#EF6C00';}
}
var allUsers=[new TutorUser(1,"Tutor Name","tutor1@gmail.com","7 มี.ค. 69","active"),new Student(2,"Student Name","student1@gmail.com","7 มี.ค. 69","active"),new TutorUser(3,"Tutor Name","tutor2@gmail.com","7 มี.ค. 69","active"),new Student(4,"Student Name","student2@gmail.com","7 มี.ค. 69","active"),new TutorUser(5,"Tutor Name","tutor3@gmail.com","5 มี.ค. 69","active"),new Student(6,"Student Name","student3@gmail.com","3 มี.ค. 69","banned")];
var filter=new URLSearchParams(window.location.search).get('filter')||'all';
var tabs=[{key:'all',label:'ทั้งหมด'},{key:'tutor',label:'ติวเตอร์'},{key:'student',label:'นักศึกษา'},{key:'banned',label:'ถูกระงับ'}];
document.getElementById('filterTabs').innerHTML=tabs.map(function(t){return '<a class="ftab '+(t.key===filter?'active':'')+'" href="?filter='+t.key+'">'+t.label+'</a>';}).join('');
var filtered=allUsers;
if(filter==='tutor')filtered=allUsers.filter(function(u){return u.type==='tutor';});
else if(filter==='student')filtered=allUsers.filter(function(u){return u.type==='student';});
else if(filter==='banned')filtered=allUsers.filter(function(u){return u.status==='banned';});
document.getElementById('userList').innerHTML=filtered.map(function(u){return '<div class="user-card"><div class="user-avatar"><i class="fa-solid fa-user"></i></div><div style="flex:1"><div class="user-name">'+u.name+'</div><div class="user-email">'+u.email+'</div><div class="user-meta"><span class="role-badge" style="background:'+u.getRoleBg()+';color:'+u.getRoleColor()+'">'+u.getRole()+'</span><span style="font-size:10px;color:'+u.getStatusColor()+'"><span class="status-dot" style="background:'+u.getStatusColor()+'"></span>'+u.getStatusLabel()+'</span></div></div><div class="user-date">สมัคร '+u.joinDate+'</div></div>';}).join('');
