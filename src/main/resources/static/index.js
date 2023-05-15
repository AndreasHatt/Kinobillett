$(function (){
    hentBillett();
});
function lagreBillett(){
    const kinoBilett = {
        film : $("#film").val(),
        antall : $("#antall").val(),
        fornavn : $("#fornavn").val(),
        etternavn : $("#etternavn").val(),
        tlfNmr : $("#tlfNmr").val(),
        epost : $("#epost").val()
    };
    $.post("/lagreBillett", kinoBilett, function (){
        hentBillett();
    }).fail(function (jqXHRLagre){
        const json = $.parseJSON(jqXHRLagre.responseText);
        $("#feil").html(json.message);
    });
    $("#film").val("Velg film her");
    $("#antall").val("");
    $("#fornavn").val("");
    $("#etternavn").val("");
    $("#tlfNmr").val("");
    $("#epost").val("");
}

function hentBillett(){
    $.get("/hentBillett", function (billett){
        formaterBillett(billett);
    }).fail(function (status){
        if (status.status = "404"){
            $("#feil").html("Du må logge inn for å vise bilettene dine")
        }
    });
}

function formaterBillett(billett){
    let ut = "<table><th>Film</th><th>Antall</th><th>Fornavn</th><th>Etternavn</th><th>TlfNmr</th><th>Epost</th>";
    for (const kino of billett){
        ut += "<tr><td>" + kino.film + "</td><td>" + kino.antall + "</td><td>" + kino.fornavn + "</td><td>" + kino.etternavn + "</td><td>" + kino.tlfNmr + "</td><td>" + kino.epost + "</td></tr>";
    }
    ut += "</table>";
    $("#visBilletter").html(ut);
}

function slettBillett(){
    $.get("/slettBillett", function (){
        hentBillett();
    }).fail(function (jqXHRSlett){
        const json = $.parseJSON(jqXHRSlett.responseText);
        $("#feilSlett").html(json.message);
    });
}