var ga = document.getElementById("real-time-gravatar");
var email = document.getElementById("email");
var Ka=navigator.userAgent.toLowerCase();
var chrome = Ka.indexOf('webkit') != -1;
 
if (chrome) email.onblur = changeGravatar;
else email.onchange = changeGravatar;
 
function changeGravatar(){
    email_value = email.value;
    email_md5 = hex_md5(email_value);
    new_ga = "http://cn.gravatar.com/avatar/" + email_md5 +"s=60&d=identicon&r=G";
    newGravatar(new_ga);
}
 
function newGravatar(new_ga){
    ga.setAttribute('src', new_ga);
}