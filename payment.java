// ===== Role check =====
var _role = localStorage.getItem('userRole');
if (_role === 'tutor') { window.location.href = 'tutor_dashboard.html'; }
else if (_role === 'admin') { window.location.href = 'admin_dashboard.html'; }

var params = new URLSearchParams(window.location.search);
var id = params.get('id') || 0; var amt = params.get('amt') || 300;
document.getElementById('amtDisplay').innerHTML = Number(amt).toLocaleString() + ' <span style="font-size:16px">บาท</span>';
function toSuccess() { window.location.href = 'success.html?id='+id+'&amt='+amt; }
