$('document').ready(function () {

    var password=document.getElementById("password");
    var confirmpassword=document.getElementById("confirmpassword");
    //var btnRegister=document.getElementById("btn-register");

    function validatePassword() {
        if(password.value!=confirmpassword.value){
            // confirmpassword.setText("somelong sldkfjalsdfkja sldkfjasldfkja sldfkjsldfk");
            // console.log("password don't match")
            // alert("password don't match");

            confirmpassword.style.border="1px solid red";
            confirmpassword.setCustomValidity("password doesn't match");
        }else {
            confirmpassword.setCustomValidity("");
        }
    }
    //$('#btn-register').on('click', validatePassword)

    //btnRegister.onclick = validatePassword

    // java strongly typed - compile time
    // javascript dynamic typed - runtime
    // typescript

    password.onchange=validatePassword;
    confirmpassword.onkeyup=validatePassword;
});