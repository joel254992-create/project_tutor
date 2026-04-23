// ===== OOP: Interface-like + Leaderboard (Polymorphism + Sorting) =====
class TutorBoardEntry {
    constructor(name,rating,students,hours){this.name=name;this.rating=rating;this.students=students;this.hours=hours;}
    getScore(){return this.rating;} getDisplayName(){return this.name;}
}
class Leaderboard {
    #entries=[];
    add(e){this.#entries.push(e);}
    getRanked(){return this.#entries.slice().sort(function(a,b){return b.getScore()-a.getScore();});}
}
var board=new Leaderboard();
board.add(new TutorBoardEntry("Tutor Somchai",4.95,45,410));
board.add(new TutorBoardEntry("Tutor Somsak",4.90,38,405));
board.add(new TutorBoardEntry("Tutor Somying",4.85,42,400));
board.add(new TutorBoardEntry("Tutor Alice",4.80,35,395));
board.add(new TutorBoardEntry("Tutor Bob",4.75,30,380));
var colors=['#FFD700','#C0C0C0','#CD7F32','#FF6B6B','#FF6B6B'];
document.getElementById('boardContent').innerHTML=board.getRanked().map(function(t,i){return '<div class="rank-card"><div class="rank-num" style="color:'+colors[i]+'">'+(i+1)+'</div><div class="rank-avatar"><i class="fa-solid fa-user"></i></div><div style="flex:1"><div class="rank-name">'+t.getDisplayName()+'</div><div class="rank-stats">นักเรียน '+t.students+' คน • '+t.hours+' ชม.</div></div><div style="text-align:right"><div class="rank-rating">'+t.getScore()+'</div><div class="rank-label">Rating</div></div></div>';}).join('');

// ===== Role-aware Navigation =====
var role = localStorage.getItem('userRole') || 'student';
var navHTML;
if (role === 'tutor') {
    navHTML = '<a href="tutor_dashboard.html"><i class="fa-solid fa-home"></i></a><a href="schedule.html"><i class="fa-solid fa-calendar-days"></i></a><a href="hot_board.html" class="active"><i class="fa-solid fa-trophy"></i></a><a href="tutor_edit.html"><i class="fa-solid fa-user"></i></a>';
} else {
    navHTML = '<a href="index.html"><i class="fa-solid fa-home"></i></a><a href="preference.html"><i class="fa-solid fa-search"></i></a><a href="my_classes.html"><i class="fa-solid fa-book-open"></i></a><a href="user_profile.html" class="active"><i class="fa-solid fa-user"></i></a>';
}
document.getElementById('navBar').innerHTML = navHTML;
