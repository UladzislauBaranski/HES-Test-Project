const passwordAdd = document.getElementById("password_add")
    , confirmPasswordAdd = document.getElementById("confirm_password_add");

const passwordRef = document.getElementById("password_ref")
    , confirmPasswordRef = document.getElementById("confirm_password_ref");

function validatePasswordAdd(password, confirmPassword){
    if(password.value !== confirmPassword.value) {
        confirmPassword.setCustomValidity("Пароли не совпадают");
    } else {
        confirmPassword.setCustomValidity('');
    }
}

passwordAdd.onchange = ()=>{validatePasswordAdd(passwordAdd, confirmPasswordAdd)};
confirmPasswordAdd.onkeyup = ()=>{validatePasswordAdd(passwordAdd, confirmPasswordAdd)};

passwordRef.onchange = ()=>{validatePasswordAdd(passwordRef, confirmPasswordRef)};
confirmPasswordRef.onkeyup = ()=>{validatePasswordAdd(passwordRef, confirmPasswordRef)};

