package israel.squadra.bootcamp.service.excepValidate;

import israel.squadra.bootcamp.controller.exception.DomainException;
import org.springframework.util.StringUtils;

public class CheckValidate {

    public static void checkRequiredLogin(String login) {
        if (!StringUtils.hasText(login)) {
            throw new DomainException("O campo 'login' não pode estar vazio.");
        }
    }

    public static void checkRequiredStatus(int status) {
        if (status != 1 && status != 2) {
            throw new DomainException("O status deve ser 1 (ATIVO) ou 2 (DESATIVADO).");
        }
    }

    public static void checkRequiredPassword(String password) {
        if (!StringUtils.hasText(password)) {
            throw new DomainException("O campo 'senha' é obrigatório.");
        }
    }

    public static void checkNameLength(String name, int maxLength) {
        if (name.length() > maxLength) {
            throw new DomainException("O nome deve ter no máximo " + maxLength + " caracteres.");
        }
    }

    public static void checkLastNameLength(String lastName, int maxLength) {
        if (lastName.length() > maxLength) {
            throw new DomainException("O sobrenome deve ter no máximo " + maxLength + " caracteres.");
        }
    }

    public static void checkAgeLimit(Integer age) {
        if (age > 999) {
            throw new DomainException("A idade deve ter no máximo 3 dígitos.");
        }
    }

    public static void checkInitialsLength(String initials) {
        if (initials.length() > 3) {
            throw new DomainException("A sigla deve ter até 3 caracteres.");
        }
    }

    public static void checkRequiredInitials(String initials) {
        if (!StringUtils.hasText(initials)) {
            throw new DomainException("A sigla é obrigatória.");
        }
    }

    public static void checkRequiredName(String name) {
        if (!StringUtils.hasText(name)) {
            throw new DomainException("O nome é um campo obrigatório.");
        }
    }

    public static void checkRequiredLastName(String lastName) {
        if (!StringUtils.hasText(lastName)) {
            throw new DomainException("O sobrenome não pode estar vazio.");
        }
    }

    public static void checkRequiredAge(Integer age) {
        if (age == 0) {
            throw new DomainException("A idade é um campo necessário.");
        }
    }

    public static void checkPasswordLength(String password, int maxLength) {
        if (password.length() > maxLength) {
            throw new DomainException("A senha deve ter no máximo " + maxLength + " caracteres.");
        }
    }

    public static void checkLoginLength(String login, int maxLength) {
        if (login.length() > maxLength) {
            throw new DomainException("O login deve ter no máximo " + maxLength + " caracteres.");
        }
    }


}
