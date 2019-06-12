package jun.prospring5.ch10.validation;

import jun.prospring5.ch10.entity.Genre;
import jun.prospring5.ch10.entity.Singer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CountrySingerValidator implements
        ConstraintValidator<CheckCountrySinger, Singer> {

    @Override
    public boolean isValid(
            Singer singer, ConstraintValidatorContext context) {

        if (singer.getGenre() == null ||
                isNotCountrySinger(singer)) {
            return false;
        }

        if (StringUtils.isEmpty(singer.getLastName())) {
            return false;
        }

        if (singer.getGender() == null) {
            return false;
        }

        return true;
    }

    @Override
    public void initialize(CheckCountrySinger constraintAnnotation) {

    }

    private static boolean isNotCountrySinger(Singer singer) {
        return singer.getGenre() != Genre.COUNTRY;
    }
}
