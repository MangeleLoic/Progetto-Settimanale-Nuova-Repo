package Epicode.ProgettoSettimanale.servicies;

import Epicode.ProgettoSettimanale.entities.Dipendente;
import Epicode.ProgettoSettimanale.payloads.DipendenteDTO;
import Epicode.ProgettoSettimanale.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
i

@Service
public class AuthService {
    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private JWT jwt;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(DipendenteDTO body) {

        Dipendente found = this.dipendenteService.findByEmail(body.email());

        if (bcrypt.matches(body.password(), found.getPassword())) {

            String accessToken = jwt.createToken(found);

            return accessToken;
        } else {

            throw new UnauthorizedException("Credenziali errate!");
        }
    }

}
