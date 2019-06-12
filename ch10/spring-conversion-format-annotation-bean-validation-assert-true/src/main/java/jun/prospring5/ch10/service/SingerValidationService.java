package jun.prospring5.ch10.service;

import jun.prospring5.ch10.entity.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class SingerValidationService {

    @Autowired
    private Validator validator;

    public Set<ConstraintViolation<Singer>>
    validateSinger(Singer singer) {
        return validator.validate(singer);
    }
}
