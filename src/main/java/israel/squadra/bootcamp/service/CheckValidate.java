package israel.squadra.bootcamp.service;

import israel.squadra.bootcamp.controller.exception.DomainException;
import org.springframework.util.StringUtils;

public class Validate {

    public static void validateStatus(int status){
        if (status != 1 && status != 2){
            throw new DomainException("O campo status deve ser apenas 1 para ATIVO ou 2 para DESATIVADO");
        }
    }

    public static void validateNameSize(String name, int characters){
        if(name.length() > characters){
            throw new DomainException("O campo nome deve ter até " + characters + " caracteres.");
        }
    }

    public static void validateLastNameSize(String lastName, int characters){
        if(lastName.length() > characters){
            throw new DomainException("O campo sobrenome deve ter até " + characters + " caracteres.");
        }
    }

    public static void validateAgeSize(Integer age){
        if(age > 999){
            throw new DomainException("O campo idade deve ter até 3 caracteres.");
        }
    }

    public static void validateLoginSize(String login, int characters){
        if(login.length() > characters){
            throw new DomainException("O campo login deve ter até " + characters + " caracteres.");
        }
    }

    public static void validatePasswordSize(String password, int characters){
        if(password.length() > characters){
            throw new DomainException("O campo senha deve ter até " + characters + " caracteres.");
        }
    }

    public static void validateInitialsSize(String initials){
        if(initials.length() > 3){
            throw new DomainException("O campo sigla deve ter até 3 caracteres.");
        }
    }

    public static void validateInitialsRequired(String initials){
        if (!StringUtils.hasText(initials)){
            throw new DomainException("O campo sigla é obrigatório.");
        }
    }

    public static void validateNameRequired(String name){
        if (!StringUtils.hasText(name)){
            throw new DomainException("O campo nome é obrigatório.");
        }
    }

    public static void validateLastNameRequired(String lastName){
        if (!StringUtils.hasText(lastName)){
            throw new DomainException("O campo sobrenome é obrigatório.");
        }
    }

    public static void validateAgeRequired(Integer age){
        if (age == 0){
            throw new DomainException("O campo idade é obrigatório.");
        }
    }

    public static void validateLoginRequired(String login) {
        if (!StringUtils.hasText(login)){
            throw new DomainException("O campo login é obrigatório.");
        }
    }

    public static void validatePasswordRequired(String password) {
        if (!StringUtils.hasText(password)){
            throw new DomainException("O campo senha é obrigatório.");
        }
    }



}
