package oslomet.webprog.kinobilett;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class KinoController {

    @Autowired
    private KinoRepository rep;

    private Logger logger = LoggerFactory.getLogger(KinoController.class);

    private boolean validerBillett(Kino kino){
        String regexpAntall = "([1-9]|[1-3][0-9]|40)";
        String regexpFornavn = "[a-zæøåA-ZÆØÅ \\-]{2,20}";
        String regexpEtternavn = "[a-zæøåA-ZÆØÅ \\-]{2,20}";
        String regexpTlfNmr = "[0-9]{8}";
        String regexpEpost = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

        String antall = Integer.toString(kino.getAntall());
        String tlfNmr = Integer.toString(kino.getTlfNmr());

        boolean antallOk = antall.matches(regexpAntall);
        boolean fornavnOk = kino.getFornavn().matches(regexpFornavn);
        boolean etternavnOk = kino.getEtternavn().matches(regexpEtternavn);
        boolean tlfNmrOk = tlfNmr.matches(regexpTlfNmr);
        boolean epostOk = kino.getEpost().matches(regexpEpost);
        if (antallOk && fornavnOk && etternavnOk && tlfNmrOk && epostOk){
            return true;
        }
        else {
            logger.error("Valideringsfeil");
            return false;
        }
    }

    @PostMapping("/lagreBillett")
    public void lagreBillett(Kino kino, HttpServletResponse response) throws IOException {
        if (!validerBillett(kino)){
            response.sendError(HttpStatus.NOT_ACCEPTABLE.value());
            }
        else {
            if (!rep.lagreBillett(kino)) {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil med å lagre biletten - prøv igjen senere");
            }
        }
    }

    @GetMapping("/hentBillett")
    public List<Kino> hentBillett(HttpServletResponse response) throws IOException {
        if (session.getAttribute("Innlogget") != null){
            List<Kino> kino = rep.hentBillett();
            if (kino==null){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil med å vise bilettene - prøv igjen senere");
            }
            return sortByEtternavn(kino);
        }
        else {
            response.sendError(HttpStatus.NOT_FOUND.value(), "Kan ikke vise biletter: Du er ikke logget inn!");
            return null;
        }
    }

    public List<Kino> sortByEtternavn(List<Kino> billetter){
        Collections.sort(billetter, new Comparator<Kino>() {
            @Override
            public int compare(Kino o1, Kino o2) {
                return o1.getEtternavn().compareTo(o2.getEtternavn());
            }
        });
        return billetter;
    }

    @GetMapping("/slettBillett")
    public void slettBillet(HttpServletResponse response) throws IOException {
        if (!rep.slettBillett()){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil med å slette billettene - prøv igjen senere");
        }
    }

    @Autowired
    private HttpSession session;

    @PostMapping("/logInn")
    public boolean login(Bruker bruker){
        if(rep.sjekkBrukernavnOgPassord(bruker)){
            session.setAttribute("Innlogget", bruker);
            return true;
        }
        else {
            return false;
        }
    }

    @GetMapping("/loggUt")
    public void logout(){
        session.removeAttribute("Innlogget");
    }

    @PostMapping("/registrer")
    public void registrerBruker(Bruker bruker){
        rep.lagreBruker(bruker);
    }
}
