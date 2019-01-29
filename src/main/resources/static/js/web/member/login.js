window.onload = () => {
    login.init();
};

const login = {
    loginButton: null,
    joinButton: null,
    emailInput: null,
    passwordInput: null,

    init: function() {
        this.loginButton = document.getElementById("loginButton");
        this.joinButton = document.getElementById("joinButton");
        this.emailInput = document.getElementById("emailInput");
        this.passwordInput = document.getElementById("passwordInput");

    },

    loginButtonOnClick() {
        const email = this.emailInput.value;
        const password = this.passwordInput.value;

        const request = {
            email,
            password
        };

        fetch("/member/login/validate", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(request)
        })
            .then(response => response.json())
            .then(json => location.href = json.redirectUrl  )
            .catch(error => alert("이메일, 패스워드를 다시 확인해주세요."));
    },

    joinButtonOnClick() {
        alert("기능 구현 예정입니다");
    }
}