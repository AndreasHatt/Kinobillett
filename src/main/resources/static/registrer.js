function registrerBruker(){
    const bruker = {
        brukernavn : $("#regBrukernavn").val(),
        passord : $("#regPassord").val()
    };
    $.post("/registrer", bruker, function (){
        window.location.href = "index.html";
    });
}