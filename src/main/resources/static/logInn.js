function loggInn(){
    const bruker = {
        brukernavn : $("#brukernavn").val(),
        passord : $("#passord").val()
    };
    $.post("/logInn", bruker, function (innlogget){
        if (innlogget){
            window.location.href = "index.html";
        }
        else{
            $("#feilLoggInn").html("Feil brukernavn eller passord!")
        }
    });
}

function loggUt(){
    $.get("/loggUt", function (){
        window.location.href = "index.html";
    });
}