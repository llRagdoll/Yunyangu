const loginButton = document.querySelector('.loginbutton');
const registerButton = document.querySelector('.registerbutton');
const introduction = document.querySelector('.introduction');
const logincard = document.querySelector('.logincard');
const registercard = document.querySelector('.registercard');

loginButton.addEventListener('click', () => {
    introduction.style.display = 'none';
    logincard.style.display = 'block';
});


registerButton.addEventListener('click', () => {
    introduction.style.display = 'none';
    registercard.style.display = 'block';
});

function tryLogin() {
    let name= $("#name1").val();
    let pass = $("#password1").val();

    console.log(name,pass);

    $.ajax({
        url: 'http://localhost:8080/user/login',
        type: 'POST',
        //contentType: 'application/json',
        data: {
            userId: name,
            password: pass
        },
        success: function(response) {
            // 处理后端返回的响应
            alert("登录成功");
            console.log(response);
            window.sessionStorage.setItem("userID",name);
            window.sessionStorage.setItem("userName",response.data.name);
            window.sessionStorage.setItem("userAvatar",response.data.avatar);
            window.location.href = "/home";
        },
        error: function(error) {
            $("#logincard").removeClass('shake_effect');
            setTimeout(function()
            {
                $("#logincard").addClass('shake_effect')
            },1);
            console.error(error);
        }
    });
}

function tryRegister() {
    let name = $("#name2").val();
    let password = $("#password2").val();
    let phone = $("#phone2").val();
    let email=$("#email2").val();
    let address=$("#address2").val();

    if(name===""||password===""||phone===""||email===""||address===""){
        alert("请填写完整信息");
        return;
    }
    $.ajax({
        url: 'http://localhost:8080/user/register',
        type: 'POST',
        data: {
            "name": name,
            "password": password,
            "phone": phone,
            "address": address,
            "email":email
        },
        dataType: 'json',
        success: function(response) {
            // 处理后端返回的响应
            alert("注册成功!您的账号ID为："+response.data);
            console.log(response);
            window.location.href = '/loginPage';
        },
        error: function(error) {
            console.error(error);
            alert("注册失败,用户名已存在!");
        }
    });
}

