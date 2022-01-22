function validate() {
    var name = document.getElementById("name").value;
    var surname = document.getElementById("surname").value;
    var login = document.getElementById("login").value;
    var password = document.getElementById("password").value;
    var secondPassword = document.getElementById("secondPassword").value;

    var loginPassNameSurnamePattern = /.{2}.*/

    if(!loginPassNameSurnamePattern.test(name)) {
        return false;
    }

    if(!loginPassNameSurnamePattern.test(surname)) {
        return false;
    }

    if(!loginPassNameSurnamePattern.test(login)) {
        return false;
    }

    if(!loginPassNameSurnamePattern.test(password)) {
        return false;
    }

    if(password != secondPassword) {
        return false;
    }

    return true;
}