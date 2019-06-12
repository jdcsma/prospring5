package jun.prospring5.ch10.entity;

public enum Gender {

    MALE("M"), FEMALE("F");

    private String code;

    Gender(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Gender{" +
                "code='" + code + '\'' +
                '}';
    }
}
