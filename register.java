// ===== OOP: Class UserRegistration (Encapsulation) =====
class UserRegistration {
    #name; #email; #phone; #password; #role; #errors;

    constructor(name, email, phone, password, role) {
        this.#name = name.trim();
        this.#email = email.trim();
        this.#phone = phone.trim();
        this.#password = password;
        this.#role = role;
        this.#errors = [];
    }

    validate() {
        this.#errors = [];
        if (this.#name === '') this.#errors.push('กรุณากรอกชื่อ');
        if (!this.#email.includes('@')) this.#errors.push('อีเมลไม่ถูกต้อง');
        if (this.#password.length < 4) this.#errors.push('รหัสผ่านต้องมีอย่างน้อย 4 ตัวอักษร');
        return this.#errors.length === 0;
    }

    getErrors() { return this.#errors; }
    getName() { return this.#name; }
    getEmail() { return this.#email; }
}

function pickRole(el, role) {
    document.querySelectorAll('.role-btn').forEach(function(b) { b.classList.remove('active'); });
    el.classList.add('active');
    document.getElementById('roleInput').value = role;
}

document.getElementById('registerForm').addEventListener('submit', function(e) {
    e.preventDefault();
    var reg = new UserRegistration(
        document.getElementById('name').value,
        document.getElementById('email').value,
        document.getElementById('phone').value,
        document.getElementById('password').value,
        document.getElementById('roleInput').value
    );
    if (reg.validate()) {
        window.location.href = 'login.html?registered=1';
    } else {
        var errDiv = document.getElementById('errorMsg');
        errDiv.textContent = reg.getErrors().join(', ');
        errDiv.style.display = 'block';
    }
});
