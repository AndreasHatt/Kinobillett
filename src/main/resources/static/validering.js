function validerOgLagreKunde(){
    const antallOk = validerAntall($("#antall").val());
    const fornavnOk = validerFornavn($("#fornavn").val());
    const etternavnOk = validerEtternavn($("#etternavn").val());
    const tlfNmrOk = validerTlfNmr($("#tlfNmr").val());
    const epostOk = validerEpost($("#epost").val());

    if (antallOk && fornavnOk && etternavnOk && tlfNmrOk && epostOk){
        lagreBillett();
        window.location.href = "index.html";
    }
    else{
        $("#visBilletter").html("Rett opp feil i skjema før kjøp av billett!")
    }
}

function validerAntall(antall) {
    const regexp = /^([1-9]|[1-3][0-9]|40)$/;
    const ok = regexp.test(antall);
    if (!ok){
        $("#feilAntall").html("Antall kan bare være 1 til 40")
        return false;
    }
    else {
        $("#feilAntall").html("");
        return true;
    }
}

function validerFornavn(fornavn){
    const regexp = /^[a-zæøåA-ZÆØÅ \-]{2,20}$/;
    const ok = regexp.test(fornavn);
    if (!ok){
        $("#feilFornavn").html("Fornavn må bestå av 2 til 20 bokstaver")
        return false;
    }
    else{
        $("#feilFornavn").html("");
        return true;
    }
}

function validerEtternavn(etternavn){
    const regexp = /^[a-zæøåA-ZÆØÅ \-]{2,20}$/;
    const ok = regexp.test(etternavn);
    if (!ok){
        $("#feilEtternavn").html("Etternavn må bestå av 2 til 20 bokstaver")
        return false;
    }
    else {
        $("#feilEtternavn").html("");
        return true;
    }
}

function validerTlfNmr(tlfNmr){
    const regexp = /^[0-9]{8}$/;
    const ok = regexp.test(tlfNmr);
    if (!ok){
        $("#feilTlfNmr").html("Telfonnummeret er ikke gyldig")
        return false;
    }
    else {
        $("#feilTlfNmr").html("");
        return true;
    }
}

function validerEpost(epost){
    const regexp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const ok = regexp.test(epost);
    if (!ok){
        $("#feilEpost").html("Epost inneholder ugyldige tegn eller er mindre enn 2 bokstaver")
        return false;
    }
    else {
        $("#feilEpost").html("");
        return true;
    }
}