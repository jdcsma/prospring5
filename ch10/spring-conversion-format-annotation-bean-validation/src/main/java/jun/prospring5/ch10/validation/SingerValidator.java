package jun.prospring5.ch10.validation;

import jun.prospring5.ch10.entity.Singer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

@Component
public class SingerValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Singer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,
                "firstName", "firstName.empty");
    }
}
