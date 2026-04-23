// ===== OOP: Class PasswordResetRequest (Encapsulation) =====
class PasswordResetRequest {
    #email;
    constructor(email) { this.#email = email.trim(); }
    validate() {
        if (!this.#email.includes('@')) return false;
        return true;
    }
    getEmail() { return this.#email; }
}

document.getElementById('resetForm').addEventListener('submit', function(e) {
    e.preventDefault();
    var req = new PasswordResetRequest(document.getElementById('email').value);
    if (req.validate()) {
        document.getElementById('successMsg').style.display = 'block';
        document.getElementById('resetForm').style.display = 'none';
        setTimeout(function() { window.location.href = 'login.html'; }, 2500);
    } else {
        alert('กรุณากรอกอีเมลให้ถูกต้อง');
    }
});
