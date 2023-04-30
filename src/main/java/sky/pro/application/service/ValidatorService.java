package sky.pro.application.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import sky.pro.application.exception.IncorrectNameException;
import sky.pro.application.exception.IncorrectSurnameException;

@Service
public class ValidatorService {
    public String validateName(String name) {
        if (!StringUtils.isAlpha(name)) {
            throw new IncorrectNameException();
        }
        return StringUtils.capitalize(name.toLowerCase());
    }

    public String validateSurname(String surname) {
        String[] surnames = surname.split("-");
        for (int i = 0; i < surnames.length; i++) {
            String s = surnames[i];
            if (!StringUtils.isAlpha(surname)) {
                throw new IncorrectSurnameException();
            }

            surnames[i] = StringUtils.capitalize(surname.toLowerCase());
        }
        return String.join("-", surnames);
    }
}
