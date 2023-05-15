package oslomet.webprog.kinobilett;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class KinoRepository {
    @Autowired
    private JdbcTemplate db;

    private Logger logger = LoggerFactory.getLogger(KinoRepository.class);

    private String krypterPassord(String passord){
        String krypterPassord = BCrypt.hashpw(passord, BCrypt.gensalt(15));
        return krypterPassord;
    }
    private boolean sjekkPassord(String passord, String hashpassord){
        boolean ok = BCrypt.checkpw(passord, hashpassord);
        return ok;
    }
    public boolean lagreBillett(Kino kino){
        String sql = "INSERT INTO Kino(film, antall, fornavn, etternavn, tlfNmr, epost) VALUES (?,?,?,?,?,?)";
        try {
            db.update(sql, kino.getFilm(), kino.getAntall(), kino.getFornavn(), kino.getEtternavn(), kino.getTlfNmr(), kino.getEpost());
            return true;
        }
        catch (Exception e){
            logger.error("Feil i lagrekunde()" + e);
            return false;
        }
    }

    public void lagreBruker(Bruker bruker){
        String sql = "INSERT INTO Bruker(brukernavn, passord) VALUES (?,?)";
        db.update(sql, bruker.getBrukernavn(), krypterPassord(bruker.getPassord()));
    }

    public List<Kino> hentBillett(){
        String sql = "SELECT * FROM Kino";
        try{
            List<Kino> kino = db.query(sql, new BeanPropertyRowMapper(Kino.class));
            return kino;
        }
        catch (Exception e){
            logger.error("Feil i hentbilett()" + e);
            return null;
        }

    }

    public boolean slettBillett(){
        String sql = "DELETE FROM Kino";
        try{
            db.update(sql);
            return true;
        }
        catch (Exception e){
            logger.error("Feil i slettBilett()" + e);
            return false;
        }
    }

    public boolean sjekkBrukernavnOgPassord(Bruker bruker){
        String sql = "SELECT * FROM Bruker WHERE brukernavn = ?";
        try{
            Bruker dbBruker = db.queryForObject(sql,
                    BeanPropertyRowMapper.newInstance(Bruker.class), bruker.getBrukernavn());
            return sjekkPassord(bruker.getPassord(), dbBruker.getPassord());
        }
        catch (Exception e){
            logger.error("Feil i sjekkBrukernavnOgPassord()" + e);
            return false;
        }
    }
}
